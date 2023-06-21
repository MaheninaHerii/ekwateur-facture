package fr.ekwateur.facture.domain.services;

import fr.ekwateur.facture.adapters.persistences.ClientDao;
import fr.ekwateur.facture.adapters.persistences.ProfessionnelDao;
import fr.ekwateur.facture.domain.models.persistence.ClientEntity;
import fr.ekwateur.facture.domain.models.persistence.ProfessionnelEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl {

    private final ClientDao clientDao;
    private final ProfessionnelDao professionnelDao;

    public ClientServiceImpl(ClientDao clientDao, ProfessionnelDao professionnelDao) {
        this.clientDao = clientDao;
        this.professionnelDao = professionnelDao;
    }

    public ClientEntity findClient(String reference) {
        return clientDao.findByReference(reference);
    }

    public ProfessionnelEntity findClientProfessionnel(Long idClient) {
        return professionnelDao.findByClientItClient(idClient);
    }
}
