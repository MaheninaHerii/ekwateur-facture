package fr.ekwateur.facture.domain.models.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode
@Entity
@Table(name = "releve")
public class ReleveEntity {

    @EmbeddedId
    private ReleveEntityId idReleve;

    @Basic
    @Column(name = "periode")
    private Date periode;

    @Basic
    @Column(name = "fractionnement")
    private String fractionnement;

    @Basic
    @Column(name = "consommation")
    private Double consommation;
}
