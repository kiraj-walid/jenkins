package com.ecom.msemprunt;

import com.ecom.msemprunt.controllers.LivreOpenFeign;
import com.ecom.msemprunt.entities.Emprunt17;
import com.ecom.msemprunt.repositories.EmpruntRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MsEmpruntApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsEmpruntApplication.class, args);
    }
    @Bean
    CommandLineRunner init(EmpruntRepository repository) {
        Emprunt17 emprunt17 = Emprunt17.builder()
                .idLivre(1L)
                .idEtudiant(1L)
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.now().plusWeeks(2))
                .build();
        return args -> {
            repository.save(emprunt17);
        };

    }

}
