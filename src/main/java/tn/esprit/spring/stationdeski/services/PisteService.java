package tn.esprit.spring.stationdeski.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.stationdeski.entities.Couleur;
import tn.esprit.spring.stationdeski.entities.Inscription;
import tn.esprit.spring.stationdeski.entities.Piste;
import tn.esprit.spring.stationdeski.repositories.PisteRepository;
import tn.esprit.spring.stationdeski.repositories.SkieurRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class PisteService implements IPisteService{
    PisteRepository pisteRepository;
    SkieurRepository skieurRepository;
    @Override
    public List<Piste> retrieveAllPistes() {
            return (List<Piste>) pisteRepository.findAll();
        }


    @Override
    public Piste addPiste(Piste p) {
        pisteRepository.save(p);
        return p;
    }

    @Override
    public Piste updatePiste(Piste p) {
        pisteRepository.save(p);
        return p;
    }

    @Override
    public Piste retrievePiste(Integer idPiste) {
        return pisteRepository.findById(idPiste).get();
    }

    @Override
    public void deletePiste(Integer idPiste) {
        pisteRepository.deleteById(idPiste);

    }

    @Override
    public HashMap<Couleur,Integer> nombreSkieursParCouleurPiste() {
        HashMap<Couleur,Integer> nombreSkieursParCouleur = new HashMap<>();

        int nbskieurs =0;
        for (Couleur c: Couleur.values()) {
            nbskieurs = skieurRepository.getPistesBySkieurs(c).size();
            nombreSkieursParCouleur.put(c, nbskieurs);
        }
        return nombreSkieursParCouleur;
    }


}

