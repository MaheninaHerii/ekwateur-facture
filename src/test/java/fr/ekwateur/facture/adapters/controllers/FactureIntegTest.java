package fr.ekwateur.facture.adapters.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ekwateur.facture.adapters.persistences.ClientDao;
import fr.ekwateur.facture.application.dtos.FactureRequestDto;
import fr.ekwateur.facture.application.dtos.FactureResponseDto;
import fr.ekwateur.facture.domain.models.persistence.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculFactureIntegTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void calculerFactureTest() throws Exception {
        // GIVEN
        FactureRequestDto factureRequestDto = FactureRequestDto.builder()
                .periode(new Date())
                .reference("1234")
                .build();

        // Créer l'en-tête avec Content-Type et Accept
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(factureRequestDto);

        // Créer l'objet HttpEntity avec l'en-tête et le corps de la requête
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        // Utiliser l'objet RestTemplate pour effectuer la requête POST
        ResponseEntity<FactureResponseDto> response = testRestTemplate.postForEntity("/api/v1/facture/calculer", request, FactureResponseDto.class);
        FactureResponseDto responseBody = response.getBody();

        // ASSERT
        assertEquals("1234", responseBody.getClient().getReference());
    }

}