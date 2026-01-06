package com.ecom.msetudiant.controllers;


import com.ecom.msetudiant.entities.Etudiant17;
import com.ecom.msetudiant.repositorie.EtudiantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EtudiantController {

    private final EtudiantRepository repository;

    public EtudiantController(EtudiantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/etudiants")
    public List<Etudiant17> getAllEtudiants() {
        return repository.findAll();
    }

    @GetMapping("/etudiants/{id}")
    public Etudiant17 getEtudiantById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/etudiants")
    public Etudiant17 createEtudiant(Etudiant17 etudiant) {
        return repository.save(etudiant);
    }

    @PutMapping("/etudiants/{id}")
    public Etudiant17 updateEtudiant(@PathVariable Long id, @RequestBody Etudiant17 etudiantDetails) {
        Etudiant17 etudiant = repository.findById(id).orElseThrow(() -> new RuntimeException("Etudiant not found"));

        if (etudiantDetails.getNom() != null) {
            etudiant.setNom(etudiantDetails.getNom());
        }
        if (etudiantDetails.getEmail() != null) {
            etudiant.setEmail(etudiantDetails.getEmail());
        }

        return repository.save(etudiant);
    }

    @DeleteMapping("/etudiants/{id}")
    public void deleteEtudiant(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
