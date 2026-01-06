package com.ecom.msetudiant;

import com.ecom.msetudiant.entities.Etudiant17;
import com.ecom.msetudiant.repositorie.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsEtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsEtudiantApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(EtudiantRepository repository) {
        Etudiant17 etudiant17 = Etudiant17.builder()
                .nom("walid")
                .prenom("Walid")
                .email("walid@gmail.com")
                .build();
        return args -> {
            repository.save(etudiant17);
        };


    }
}
