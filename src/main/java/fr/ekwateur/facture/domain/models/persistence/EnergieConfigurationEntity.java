package fr.ekwateur.facture.domain.models.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@Entity
@Table(name = "energie_configuration")
public class EnergieConfigurationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "it_energie_configuraiton")
    private Long idEnergieConfiguration;

    @Basic
    @Column(name = "seuil_ca")
    private Double seuilChiffreAffaire;

    @Basic
    @Column(name = "prix_kwh_ca_inf")
    private Double prixKwhCaInferieur;

    @Basic
    @Column(name = "prix_kwh_ca_sup")
    private Double prixKwhCaSuperieur;

    @Basic
    @Column(name = "type_client")
    private String typeClient;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "it_energie")
    private EnergieEntity energie;
}
