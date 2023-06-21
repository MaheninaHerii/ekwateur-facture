package fr.ekwateur.facture.adapters.persistences;

import fr.ekwateur.facture.domain.models.persistence.EnergieConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EnergieConfigurationDao extends JpaRepository<EnergieConfigurationEntity, Long> {

    EnergieConfigurationEntity findByTypeClientAndEnergieItEnergie(String typeClient, Long idEnergie);
}
