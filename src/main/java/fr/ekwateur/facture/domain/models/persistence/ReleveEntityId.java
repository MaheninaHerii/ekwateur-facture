package fr.ekwateur.facture.domain.models.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class ReleveEntityId implements Serializable {

    @Column(name = "it_client")
    private Long idClient;

    @Column(name = "it_energie")
    private Long idEnergie;
}
