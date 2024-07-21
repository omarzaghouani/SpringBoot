package tn.esprit.spring.stationdeski.services;

import tn.esprit.spring.stationdeski.entities.Inscription;

import java.util.List;

public interface IInscriptionService {

    List<Inscription> retrieveAllInscriptions();

    Inscription addInscription(Inscription i);

    Inscription updateInscription (Inscription i);

    Inscription retrieveInscription (Integer idInscription);

    void deleteInscription( Integer idInscription);

    Inscription assignInscriptionToCours(Long numInscription, Long numCours);
    Inscription addInscriptionAndAssignToSkieurAndCours(Inscription inscri, Long numSkieur, Long numCours);

}
