package fr.ekwateur.facture.adapters.persistences;

import fr.ekwateur.facture.domain.models.persistence.ReleveEntity;
import fr.ekwateur.facture.domain.models.persistence.ReleveEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReleveDao extends JpaRepository<ReleveEntity, ReleveEntityId> {

    List<ReleveEntity> findByPeriodeBetweenAndIdReleveIdClient(Date debut, Date fin, Long idClient);
}
