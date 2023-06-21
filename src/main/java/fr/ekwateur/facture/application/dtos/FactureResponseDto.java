package fr.ekwateur.facture.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactureResponseDto {
    private ClientDto client;
    private List<FactureDto> facture;
}
