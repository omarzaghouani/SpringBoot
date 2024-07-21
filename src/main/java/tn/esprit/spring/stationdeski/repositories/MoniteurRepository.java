package tn.esprit.spring.stationdeski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.stationdeski.entities.Moniteur;

public interface MoniteurRepository extends CrudRepository<Moniteur, Integer> {

    Moniteur findMoniteurByNomM(Long numMoniteur);

}
