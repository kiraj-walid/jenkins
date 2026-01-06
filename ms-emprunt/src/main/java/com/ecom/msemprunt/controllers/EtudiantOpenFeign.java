package com.ecom.msemprunt.controllers;


import com.ecom.msemprunt.models.Etudiant17;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-etudiant")
public interface EtudiantOpenFeign {
    @GetMapping("/etudiants")
    List<Etudiant17> getAllEtudiants();

    @GetMapping("/etudiants/{id}")
    Etudiant17 getEtudiantById(@PathVariable Long id);
}
