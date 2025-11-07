package com.example.banque;

import com.example.banque.entities.Client;
import com.example.banque.entities.Compte;
import com.example.banque.entities.TypeCompte;
import com.example.banque.repositories.ClientRepository;
import com.example.banque.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class BanqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanqueApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepo,
                            ClientRepository clientRepo,
                            RepositoryRestConfiguration restConfig) {
        return args -> {
            // Exposer les IDs pour Client et Compte
            restConfig.exposeIdsFor(Client.class, Compte.class);

            // Nettoyage pour tests reproductibles
            compteRepo.deleteAll();
            clientRepo.deleteAll();

            // Clients
            Client c1 = clientRepo.save(new Client(null, "Amal", "amal@mail.com", null));
            Client c2 = clientRepo.save(new Client(null, "Ali",  "ali@mail.com",  null));

            // Comptes liés
            compteRepo.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE, c1));
            compteRepo.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.COURANT, c1));
            compteRepo.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE, c2));

            compteRepo.findAll().forEach(System.out::println);
        };
    }
}
