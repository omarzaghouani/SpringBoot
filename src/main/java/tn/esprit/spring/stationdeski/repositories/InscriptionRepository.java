package tn.esprit.spring.stationdeski.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.stationdeski.entities.Cours;
import tn.esprit.spring.stationdeski.entities.Inscription;

public interface InscriptionRepository extends CrudRepository<Inscription, Integer> {

    Inscription findByNumInscription(Long numInscription);
    Long countInscriptionByCours(Cours cours);
}
