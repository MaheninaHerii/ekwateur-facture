package fr.ekwateur.facture.domain.services;

import fr.ekwateur.facture.adapters.persistences.EnergieConfigurationDao;
import fr.ekwateur.facture.adapters.persistences.EnergieDao;
import fr.ekwateur.facture.domain.models.persistence.EnergieConfigurationEntity;
import fr.ekwateur.facture.domain.models.persistence.EnergieEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnergieServiceImpl {
    private final EnergieConfigurationDao energieConfigurationDao;
    private final EnergieDao energieDao;

    public EnergieServiceImpl(EnergieConfigurationDao energieConfigurationDao, EnergieDao energieDao) {
        this.energieConfigurationDao = energieConfigurationDao;
        this.energieDao = energieDao;
    }

    public EnergieConfigurationEntity findConfiguration(Long idEnergie, String typeClient) {
        return energieConfigurationDao.findByTypeClientAndEnergieItEnergie(typeClient, idEnergie);
    }

    public EnergieEntity findEnergie(Long idEnergie) {
        return energieDao.findByItEnergie(idEnergie);
    }
}
