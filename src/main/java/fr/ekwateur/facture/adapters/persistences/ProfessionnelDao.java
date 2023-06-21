package fr.ekwateur.facture.adapters.persistences;

import fr.ekwateur.facture.domain.models.persistence.ProfessionnelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionnelDao extends JpaRepository<ProfessionnelEntity, Long> {
    ProfessionnelEntity findByClientItClient(Long idClient);
}
