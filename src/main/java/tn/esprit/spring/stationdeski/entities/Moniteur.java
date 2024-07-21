package tn.esprit.spring.stationdeski.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table( name = "Moniteur")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Moniteur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMoniteur")
    Integer idMoniteur; // Clé primaire
    Long numMoniteur;
    String nomM;
    String prenomM;
    LocalDate dateRecru;

// Constructeur et accesseurs (getters) et mutateurs (setters)

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Cours> cours;


}
