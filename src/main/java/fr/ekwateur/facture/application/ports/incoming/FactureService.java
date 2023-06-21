package fr.ekwateur.facture.application.ports.incoming;

import fr.ekwateur.facture.application.dtos.FactureRequestDto;
import fr.ekwateur.facture.application.dtos.FactureResponseDto;

public interface FactureService {

    FactureResponseDto calculFacture(FactureRequestDto client);
}
