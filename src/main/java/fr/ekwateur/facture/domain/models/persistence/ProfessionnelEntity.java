package fr.ekwateur.facture.domain.models.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
@Entity
@Table(name = "professionnel")
public class ProfessionnelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "it_professionnel")
    private Long itProfessionnel;
    @Basic
    @Column(name = "numero_siret")
    private Integer numeroSiret;
    @Basic
    @Column(name = "raison_sociale")
    private String raisonSociale;
    @Basic
    @Column(name = "chiffre_affaire")
    private Long chiffreAffaire;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "it_client")
    private ClientEntity client;
}
