package fr.ekwateur.facture.domain.models.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "particuliers")
public class ParticuliersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reference_client")
    private Long referenceClient;
    @Basic
    @Column(name = "civilite")
    private String civilite;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prenom")
    private String prenom;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "it_client")
    private ClientEntity client;
}
