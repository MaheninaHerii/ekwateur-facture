package fr.ekwateur.facture.domain.models.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
@Entity
@Table(name = "energie")
public class EnergieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "it_energie")
    private Long itEnergie;
    @Basic
    @Column(name = "type")
    private Byte type;
    @Basic
    @Column(name = "libellle")
    private String libellle;
}
