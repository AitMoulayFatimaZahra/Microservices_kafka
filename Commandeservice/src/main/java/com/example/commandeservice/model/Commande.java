package com.example.commandeservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "commandes")
@Data
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produitId; // ID du produit commandé
    private Integer quantite;
    private LocalDateTime dateCommande;

    private String etat; // En cours, Livrée, etc.
}
