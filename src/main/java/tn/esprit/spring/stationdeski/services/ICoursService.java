package tn.esprit.spring.stationdeski.services;

import tn.esprit.spring.stationdeski.entities.Cours;
import tn.esprit.spring.stationdeski.entities.Support;

import java.util.List;

public interface ICoursService {

    List<Cours> retrieveAllCours();

    Cours addCours(Cours c);

    Cours updateCours (Cours c);

    Cours retrieveCours (Integer idCours);

    void deleteCours( Integer idCours);
    List<Integer> numWeeksCourseOfMoniteurBySupport(Long numMoniteur, Support support);

    }
