package fr.ekwateur.facture.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FactureRequestDto {
    private String reference;
    private Date periode;
}
