package fr.ekwateur.facture.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FactureDto {
    private Date periode;
    private String energie;
    private Double consommation;
    private Double prixKwh;
    private Double prixTotal;
}
