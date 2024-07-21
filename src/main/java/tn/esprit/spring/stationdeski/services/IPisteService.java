package tn.esprit.spring.stationdeski.services;

import tn.esprit.spring.stationdeski.entities.Couleur;
import tn.esprit.spring.stationdeski.entities.Piste;

import java.util.HashMap;
import java.util.List;

public interface IPisteService {

    List<Piste> retrieveAllPistes();

    Piste addPiste(Piste p);

    Piste updatePiste (Piste p);

    Piste retrievePiste (Integer idPiste);

    void deletePiste( Integer idPiste);

    HashMap<Couleur,Integer> nombreSkieursParCouleurPiste();
}
