package fr.ekwateur.facture.application.mappers;

import fr.ekwateur.facture.application.dtos.ClientDto;
import fr.ekwateur.facture.domain.models.persistence.ClientEntity;

public class ClientMapper {

    public static ClientDto mapToClientDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .reference(clientEntity.getReference())
                .build();
    }
}
