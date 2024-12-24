package com.example.commandeservice.service;

import com.example.commandeservice.model.Commande;
import com.example.commandeservice.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ProduitsServiceClient produitsServiceClient;

    public Commande creerCommande(Commande commande) {
        // Vérification de la disponibilité du produit
        String produitStatut = produitsServiceClient.verifierProduit(commande.getProduitId());
        if (produitStatut.contains("Indisponible")) {
            throw new RuntimeException("Produit non disponible, commande annulée.");
        }

        commande.setDateCommande(LocalDateTime.now());
        commande.setEtat("En cours");
        return commandeRepository.save(commande);
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
    }
}
