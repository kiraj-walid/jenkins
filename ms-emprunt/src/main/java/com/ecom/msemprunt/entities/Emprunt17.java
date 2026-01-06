package com.ecom.msemprunt.entities;

import com.ecom.msemprunt.models.Etudiant17;
import com.ecom.msemprunt.models.Livre17;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Emprunt17 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idEtudiant;
    @Transient
    private Etudiant17 etudiant;
    private Long idLivre;
    @Transient
    private Livre17 livre;

    private LocalDate dateDebut;
    private LocalDate dateFin;

}
