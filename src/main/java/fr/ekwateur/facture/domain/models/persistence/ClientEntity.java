package fr.ekwateur.facture.domain.models.persistence;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@Entity
@Table(name = "client")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "it_client")
    private Long itClient;

    @Basic
    @Column(name = "reference")
    private String reference;

    @Basic
    @Column(name = "type")
    private String type;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private ParticuliersEntity particuliers;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private ProfessionnelEntity professionnel;
}
