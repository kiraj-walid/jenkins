package com.ecom.mslivre.controllers;


import com.ecom.mslivre.entities.Livre17;
import com.ecom.mslivre.repositories.LivreRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreController {

    private final LivreRepository livreRepository;
    public LivreController(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @GetMapping("/livres")
    public List<Livre17> getAllLivres() {
        return livreRepository.findAll();
    }

    @GetMapping("/livres/{id}")
    public Livre17 getLivreById(@PathVariable Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    @PostMapping("/livres")
    public Livre17 createLivre(@RequestBody Livre17 livre) {
        return livreRepository.save(livre);
    }

    @PutMapping("/livres/{id}")
    public Livre17 updateLivre(@RequestBody Livre17 livre, @PathVariable Long id) {
        Livre17 existingLivre = livreRepository.findById(id).orElseThrow(() -> new RuntimeException("Livre not found"));

        if (livre.getTitre() != null) {
            existingLivre.setTitre(livre.getTitre());
        }
        if (livre.getAuteur() != null) {
            existingLivre.setAuteur(livre.getAuteur());
        }

        return livreRepository.save(existingLivre);
    }

    @DeleteMapping("/livres/{id}")
    public void deleteLivre(@PathVariable Long id) {
        livreRepository.deleteById(id);
    }

}
