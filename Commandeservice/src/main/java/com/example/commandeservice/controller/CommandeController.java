package com.example.commandeservice.controller;


import com.example.commandeservice.kafka.KafkaProducer;
import com.example.commandeservice.model.Commande;
import com.example.commandeservice.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeService commandeService;
    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<Commande> creerCommande(@RequestBody Commande commande) {
        Commande nouvelleCommande = commandeService.creerCommande(commande);
        kafkaProducer.envoyerMessage(nouvelleCommande);
        return ResponseEntity.ok(nouvelleCommande);
    }

    @GetMapping
    public ResponseEntity<List<Commande>> getCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommande(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }
}
