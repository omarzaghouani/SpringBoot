package tn.esprit.spring.stationdeski.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.stationdeski.entities.Abonnement;
import tn.esprit.spring.stationdeski.entities.Cours;
import tn.esprit.spring.stationdeski.entities.TypeAbonnement;

import java.time.LocalDate;
import java.util.List;

public interface AbonnementRepository extends CrudRepository<Abonnement, Integer> {

    List<Abonnement> findByTypeAbonnement(TypeAbonnement typeAbon);
    List<Abonnement> findByDateDebutBetween(LocalDate startDate, LocalDate endDate);

    List<Abonnement> findByDateFinBefore(LocalDate endDate);

  List<Abonnement> findByDateDebutBeforeAndDateFinAfter(LocalDate a, LocalDate b);
}
