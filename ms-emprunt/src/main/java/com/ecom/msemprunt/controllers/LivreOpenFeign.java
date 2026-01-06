package com.ecom.msemprunt.controllers;


import com.ecom.msemprunt.models.Livre17;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-livre")
public interface LivreOpenFeign {

    @GetMapping("/livres")
    List<Livre17> getAllLivres();

    @GetMapping("/livres/{id}")
    Livre17 getLivreById(@PathVariable Long id);
}
