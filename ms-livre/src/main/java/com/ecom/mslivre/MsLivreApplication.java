package com.ecom.mslivre;

import com.ecom.mslivre.entities.Livre17;
import com.ecom.mslivre.repositories.LivreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsLivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsLivreApplication.class, args);
	}
	@Bean
	CommandLineRunner init(LivreRepository livreRepository) {
		Livre17 livre17 = Livre17.builder()
				.titre("Livre 17")
				.auteur("Auteur 17")
				.isbn("ISBN 17")
				.build();
		return args -> {
			livreRepository.save(livre17);
		};
	}
}
