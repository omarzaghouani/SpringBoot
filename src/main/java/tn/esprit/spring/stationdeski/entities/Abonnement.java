package tn.esprit.spring.stationdeski.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "Abonnement")
public class Abonnement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAbonnement")
    private Integer idAbonnement; // Clé primaire
    private Long numAbonnement;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private float prixAbon;
    @Enumerated(EnumType.STRING)
    private TypeAbonnement typeAbonnement;
// Constructeur et accesseurs (getters) et mutateurs (setters)


}
