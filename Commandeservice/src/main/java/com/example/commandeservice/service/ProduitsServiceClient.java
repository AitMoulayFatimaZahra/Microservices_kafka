package com.example.commandeservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProduitsServiceClient {

    private final RestTemplate restTemplate;

    public ProduitsServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "produitsService", fallbackMethod = "produitIndisponible")
    public String verifierProduit(Long produitId) {
        String url = "http://localhost:8081/api/produits/" + produitId;
        return restTemplate.getForObject(url, String.class);
    }

    public String produitIndisponible(Long produitId, Throwable throwable) {
        log.error("Microservice Produits est indisponible. Détails : {}", throwable.getMessage());
        return "Indisponible : produit temporairement inaccessible.";
    }
}
