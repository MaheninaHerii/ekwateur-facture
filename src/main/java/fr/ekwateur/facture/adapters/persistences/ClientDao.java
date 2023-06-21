package fr.ekwateur.facture.adapters.persistences;

import fr.ekwateur.facture.domain.models.persistence.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByReference(String reference);
}
