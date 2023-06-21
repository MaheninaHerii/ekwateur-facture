package fr.ekwateur.facture.adapters.persistences;

import fr.ekwateur.facture.domain.models.persistence.EnergieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergieDao extends JpaRepository<EnergieEntity, Long> {

    EnergieEntity findByItEnergie(Long idEnergie);
}
