package tn.esprit.spring.stationdeski.services;

import tn.esprit.spring.stationdeski.entities.Skieur;
import tn.esprit.spring.stationdeski.entities.TypeAbonnement;

import java.util.List;

public interface ISkieurService {

    List<Skieur> retrieveAllSkieurs();

    Skieur addSkieur(Skieur e);

    Skieur updateSkieur (Skieur e);

    Skieur retrieveSkieur (Integer idSkieur);

    void deleteSkieur( Integer idSkieur);

    Skieur assignSkieurToPiste(Long numSkieur, Long numPiste);
    Skieur addSkieurAndAssignToCours(Skieur skieur, Long numCours);
    List<Skieur> retrieveSkieursByAbonnementType(TypeAbonnement typeAbonnement);
}
