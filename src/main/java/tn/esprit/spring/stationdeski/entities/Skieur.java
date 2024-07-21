package tn.esprit.spring.stationdeski.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "Skieur")
public class Skieur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSkieur")
    private Integer idSkieur; // Clé primaire
    private Long numSkieur;
    private String nomS;
    private String prenomS;
    private LocalDate dateNaissance;
    private String ville;

// Constructeur et accesseurs (getters) et mutateurs (setters)

    @ManyToMany
    private Set<Piste> pistes;

    @OneToMany(mappedBy = "skieur",cascade = CascadeType.PERSIST)
    private Set<Inscription> inscriptions;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Abonnement abonnement;
}

