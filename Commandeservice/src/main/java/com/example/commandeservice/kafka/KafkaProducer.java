package com.example.commandeservice.kafka;

import com.example.commandeservice.model.Commande;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, Commande> kafkaTemplate;

    public void envoyerMessage(Commande commande) {
        LOGGER.info("Envoi de la commande au topic Kafka : {}", commande);
        kafkaTemplate.send("commandes_topic", commande);
    }
}

