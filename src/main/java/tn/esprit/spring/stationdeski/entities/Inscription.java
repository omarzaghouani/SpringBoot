package tn.esprit.spring.stationdeski.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "Inscription")
public class Inscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idInscription")
    private Integer idInscription; // Clé primaire
    private Long numInscription;
    private int numSemaine;

// Constructeur et accesseurs (getters) et mutateurs (setters)

    @ManyToOne
    @JsonIgnore
    private Skieur skieur ;

   @ManyToOne
    private Cours cours;
}