package com.ecom.msemprunt.controllers;


import com.ecom.msemprunt.entities.Emprunt17;
import com.ecom.msemprunt.models.Etudiant17;
import com.ecom.msemprunt.models.Livre17;
import com.ecom.msemprunt.repositories.EmpruntRepository;
import com.ecom.msemprunt.controllers.EtudiantOpenFeign;
import com.ecom.msemprunt.controllers.LivreOpenFeign;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpruntController {

    private final EmpruntRepository empruntRepository;
    private final EtudiantOpenFeign etudiantOpenFeign;
    private final LivreOpenFeign livreOpenFeign;
    public EmpruntController(EmpruntRepository empruntRepository, EtudiantOpenFeign etudiantOpenFeign, LivreOpenFeign livreOpenFeign) {
        this.empruntRepository = empruntRepository;
        this.etudiantOpenFeign = etudiantOpenFeign;
        this.livreOpenFeign = livreOpenFeign;
    }

    @GetMapping("/emprunts")
    public List<Emprunt17> getAllEmprunts() {
        List<Emprunt17> emprunts = empruntRepository.findAll();
        List<Etudiant17> etudiants = etudiantOpenFeign.getAllEtudiants();
        emprunts.forEach(emprunt -> {
            Etudiant17 etudiant = etudiants.stream()
                    .filter(e -> e.getId().equals(emprunt.getIdEtudiant()))
                    .findFirst()
                    .orElse(null);
            emprunt.setEtudiant(etudiant);

            if (etudiant != null) {
                emprunt.setLivre(livreOpenFeign.getLivreById(emprunt.getIdLivre()));
            }
        });

        List<Livre17> livres = livreOpenFeign.getAllLivres();
        emprunts.forEach(emprunt -> {
            Livre17 livre = livres.stream()
                    .filter(l -> l.getId().equals(emprunt.getIdLivre()))
                    .findFirst()
                    .orElse(null);
            emprunt.setLivre(livre);
        });

        return emprunts;

    }

    @GetMapping("/emprunts/{id}")
    public Emprunt17 getEmpruntById(@PathVariable Long id) {
        Emprunt17 emprunt = empruntRepository.findById(id).orElse(null);
        if (emprunt != null) {
            Etudiant17 etudiant = etudiantOpenFeign.getEtudiantById(emprunt.getIdEtudiant());
            emprunt.setEtudiant(etudiant);
            Livre17 livre = livreOpenFeign.getLivreById(emprunt.getIdLivre());
            emprunt.setLivre(livre);
        }
        return emprunt;

    }

    @PostMapping("/emprunts")
    public Long createEmprunt(@RequestBody Emprunt17 emprunt) {
        return empruntRepository.save(emprunt).getId();
    }

    @PutMapping("/emprunts/{id}")
    public Emprunt17 updateEmprunt(@RequestBody Emprunt17 emprunt, @PathVariable Long id) {
        Emprunt17 existingEmprunt = empruntRepository.findById(id).orElseThrow(() -> new RuntimeException("Emprunt not found"));

        if (emprunt.getIdEtudiant() != null) {
            existingEmprunt.setIdEtudiant(emprunt.getIdEtudiant());
        }
        if (emprunt.getIdLivre() != null) {
            existingEmprunt.setIdLivre(emprunt.getIdLivre());
        }
        if (emprunt.getDateDebut() != null) {
            existingEmprunt.setDateDebut(emprunt.getDateDebut());
        }
        if (emprunt.getDateFin() != null) {
            existingEmprunt.setDateFin(emprunt.getDateFin());
        }

        return empruntRepository.save(existingEmprunt);
    }

    @DeleteMapping("/emprunts/{id}")
    public void deleteEmprunt(@PathVariable Long id) {
        empruntRepository.deleteById(id);
    }


}
