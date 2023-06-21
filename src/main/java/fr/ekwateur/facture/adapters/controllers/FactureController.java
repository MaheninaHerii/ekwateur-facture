package fr.ekwateur.facture.adapters.controllers;

import fr.ekwateur.facture.application.dtos.FactureRequestDto;
import fr.ekwateur.facture.application.dtos.FactureResponseDto;
import fr.ekwateur.facture.application.ports.incoming.FactureService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/facture")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @PostMapping(value = "/calculer")
    public ResponseEntity<FactureResponseDto> calculerFacture(@RequestBody FactureRequestDto client) {
        FactureResponseDto factureResponseDto = this.factureService.calculFacture(client);
        return new ResponseEntity<>(factureResponseDto, OK);
    }
}
