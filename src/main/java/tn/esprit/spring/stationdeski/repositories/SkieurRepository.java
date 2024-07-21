package tn.esprit.spring.stationdeski.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.stationdeski.entities.Abonnement;
import tn.esprit.spring.stationdeski.entities.Couleur;
import tn.esprit.spring.stationdeski.entities.Skieur;

import java.util.List;

public interface SkieurRepository extends CrudRepository<Skieur, Integer> {


    Skieur findByNumSkieur(Long numSkieur);

    List<Skieur> findByAbonnement(Abonnement abonnement);

    @Query("select s FROM Skieur s JOIN s.pistes p WHERE p.couleur = :couleur")
    List<Skieur> getPistesBySkieurs(@Param("couleur") Couleur couleur);
}
