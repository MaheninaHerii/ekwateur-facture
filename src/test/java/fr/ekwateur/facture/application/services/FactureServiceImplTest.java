package fr.ekwateur.facture.application.services;

import fr.ekwateur.facture.application.dtos.FactureRequestDto;
import fr.ekwateur.facture.application.dtos.FactureResponseDto;
import fr.ekwateur.facture.domain.models.persistence.*;
import fr.ekwateur.facture.domain.services.ClientServiceImpl;
import fr.ekwateur.facture.domain.services.EnergieServiceImpl;
import fr.ekwateur.facture.domain.services.ReleveServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FactureServiceImplTest {
    @InjectMocks
    private FactureServiceImpl factureService;

    @Mock
    ReleveServiceImpl releveService;
    @Mock
    ClientServiceImpl clientService;
    @Mock
    EnergieServiceImpl energieService;

    @Test
    void calculFacture() {
        //GIVEN
        FactureRequestDto factureRequestDto = FactureRequestDto.builder().periode(new Date()).reference("1234").build();

        //WHEN
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setItClient(1L);
        clientEntity.setReference("1234");
        ReleveEntityId releveEntityId = new ReleveEntityId();
        releveEntityId.setIdClient(2L);
        releveEntityId.setIdEnergie(1L);
        ReleveEntity releve = new ReleveEntity();
        releve.setIdReleve(releveEntityId);
        releve.setConsommation(150.0);
        releve.setPeriode(new Date());
        EnergieEntity energie = new EnergieEntity();
        energie.setLibellle("GAZ");
        EnergieConfigurationEntity configuration = new EnergieConfigurationEntity();
        configuration.setPrixKwhCaInferieur(0.118);
        when(clientService.findClient(any())).thenReturn(clientEntity);
        when(releveService.getReleves(any(), any())).thenReturn(Collections.singletonList(releve));
        when(energieService.findEnergie(any())).thenReturn(energie);
        when(energieService.findConfiguration(any(), any())).thenReturn(configuration);

        //THEN
        FactureResponseDto factureResponseDto = factureService.calculFacture(factureRequestDto);
        assertEquals("1234", factureResponseDto.getClient().getReference());
        assertEquals(150.0, factureResponseDto.getFacture().stream().findFirst().orElseThrow().getConsommation());
        assertEquals("GAZ", factureResponseDto.getFacture().stream().findFirst().orElseThrow().getEnergie());
        assertEquals(0.118, factureResponseDto.getFacture().stream().findFirst().orElseThrow().getPrixKwh());
        assertEquals(17.7, factureResponseDto.getFacture().stream().findFirst().orElseThrow().getPrixTotal());
    }
}