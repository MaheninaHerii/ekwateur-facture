package fr.ekwateur.facture.application.services;

import fr.ekwateur.facture.application.dtos.FactureDto;
import fr.ekwateur.facture.application.dtos.FactureRequestDto;
import fr.ekwateur.facture.application.dtos.FactureResponseDto;
import fr.ekwateur.facture.application.ports.incoming.FactureService;
import fr.ekwateur.facture.domain.models.persistence.*;
import fr.ekwateur.facture.domain.services.ClientServiceImpl;
import fr.ekwateur.facture.domain.services.EnergieServiceImpl;
import fr.ekwateur.facture.domain.services.ReleveServiceImpl;
import fr.ekwateur.facture.exceptions.EkwateurFonctionnelException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static fr.ekwateur.facture.application.mappers.ClientMapper.mapToClientDto;
import static fr.ekwateur.facture.domain.models.constant.Constants.TYPE_CLIENT_PRO;


@Service
public class FactureServiceImpl implements FactureService {

    private final ReleveServiceImpl releveService;
    private final ClientServiceImpl clientService;
    private final EnergieServiceImpl energieService;

    public FactureServiceImpl(ReleveServiceImpl releveService, ClientServiceImpl clientService, EnergieServiceImpl energieService) {
        this.releveService = releveService;
        this.clientService = clientService;
        this.energieService = energieService;
    }

    @Override
    public FactureResponseDto calculFacture(FactureRequestDto factureRequest) {
        ClientEntity clientEntity = clientService.findClient(factureRequest.getReference());
        List<ReleveEntity> releves = releveService.getReleves(factureRequest.getPeriode(), clientEntity.getItClient());
        return FactureResponseDto.builder()
                .client(mapToClientDto(clientEntity))
                .facture(calculerFacture(releves, clientEntity))
                .build();
    }

    private List<FactureDto> calculerFacture(List<ReleveEntity> releves, ClientEntity clientEntity) {
        return releves.stream().map(releve -> calculerFacture(releve, clientEntity)).collect(Collectors.toList());
    }

    /**
     * Méhode pour calculer la facture d'un client pour un mois calandaire.
     * @param releve
     * @param clientEntity
     * @return
     */
    private FactureDto calculerFacture(ReleveEntity releve, ClientEntity clientEntity) {
        EnergieEntity energieEntity = energieService.findEnergie(releve.getIdReleve().getIdEnergie());
        EnergieConfigurationEntity configuration = energieService.findConfiguration(releve.getIdReleve().getIdEnergie(), clientEntity.getType());
        if (configuration == null){
            throw new EkwateurFonctionnelException("Aucune configuration trouvé pour ce type de client");
        }
        Double prixKwh;

        if (TYPE_CLIENT_PRO.equals(clientEntity.getType())) {
            prixKwh = getPrixKwhPro(clientEntity, configuration);
        } else {
            prixKwh = configuration.getPrixKwhCaInferieur();
        }

        return FactureDto.builder()
                .energie(energieEntity.getLibellle())
                .consommation(releve.getConsommation())
                .prixKwh(prixKwh)
                .periode(releve.getPeriode())
                .prixTotal(releve.getConsommation() * prixKwh)
                .build();
    }

    private Double getPrixKwhPro(ClientEntity clientEntity, EnergieConfigurationEntity configuration) {
        Double prixKwh;
        ProfessionnelEntity clientProfessionnel = clientService.findClientProfessionnel(clientEntity.getItClient());
        prixKwh = clientProfessionnel.getChiffreAffaire() <= configuration.getSeuilChiffreAffaire() ? configuration.getPrixKwhCaInferieur() : configuration.getPrixKwhCaSuperieur();
        return prixKwh;
    }
}
