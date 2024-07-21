package tn.esprit.spring.stationdeski.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.stationdeski.entities.*;
import tn.esprit.spring.stationdeski.repositories.AbonnementRepository;
import tn.esprit.spring.stationdeski.repositories.CoursRepository;
import tn.esprit.spring.stationdeski.repositories.PisteRepository;
import tn.esprit.spring.stationdeski.repositories.SkieurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SkieurService implements ISkieurService{
    SkieurRepository skieurRepository;
    PisteRepository pisteRepository;
    CoursRepository coursRepository;
    AbonnementRepository abonnementRepository;
    @Override
    public List<Skieur> retrieveAllSkieurs() {
        return (List<Skieur>) skieurRepository.findAll();
    }

    @Override
    public Skieur addSkieur(Skieur s) {
        skieurRepository.save(s);
        return s;
    }

    @Override
    public Skieur updateSkieur(Skieur s) {
        skieurRepository.save(s);
        return s;
    }

    @Override
    public Skieur retrieveSkieur(Integer idSkieur) {
        return skieurRepository.findById(idSkieur).get();
    }

    @Override
    public void deleteSkieur(Integer idSkieur) {
        skieurRepository.deleteById(idSkieur);

    }

    @Transactional
    public Skieur assignSkieurToPiste(Long numSkieur, Long numPiste) {
        Skieur s =skieurRepository.findByNumSkieur(numSkieur);
        Piste p = pisteRepository.findByNumPiste(numPiste);
        //log.info("skieur " + s.getNumSkieur());
        //log.info("piste " + p.getNumPiste());
        s.getPistes().add(p);
        return s;
    }

    @Transactional
    public Skieur addSkieurAndAssignToCours(Skieur skieur, Long numCours){
        Cours cours=coursRepository.findByNumCours(numCours);
        Skieur s=skieurRepository.save(skieur);
        Set<Inscription> inscriptions= s.getInscriptions();
        for (Inscription ins:inscriptions
        ) {
            ins.setCours(cours);
        }
        return s;
    }

    @Transactional
    public List<Skieur> retrieveSkieursByAbonnementType(TypeAbonnement typeAbonnement) {
        Set<Abonnement> ab= (Set<Abonnement>) abonnementRepository.findByTypeAbonnement(typeAbonnement);
        Skieur skieur= new Skieur();
        List<Skieur> skieurList= new ArrayList<>();
        System.out.println("abonnements= "+ab);

        for (Abonnement a:ab)
        {
            skieurList=skieurRepository.findByAbonnement(a);
            System.out.println(skieur);


        }
        return skieurList;
    }


}
