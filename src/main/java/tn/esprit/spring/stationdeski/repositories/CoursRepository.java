package tn.esprit.spring.stationdeski.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.stationdeski.entities.*;

import java.util.List;

public interface CoursRepository extends CrudRepository<Cours,Integer> {

    Cours findByNumCours(Long numCours);

    Cours findCoursByNumCours(Long numCours);
    List<Cours> findCoursBySupport(Support support);

    @Query("SELECT i.numSemaine ,m.cours,c.support FROM Inscription i,Moniteur m,Cours c WHERE i.cours MEMBER OF m.cours")
    Integer getCoursBySupport(@Param("support") Support support);
}
