package tn.esprit.spring.stationdeski.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.stationdeski.entities.*;
import tn.esprit.spring.stationdeski.repositories.CoursRepository;
import tn.esprit.spring.stationdeski.repositories.InscriptionRepository;
import tn.esprit.spring.stationdeski.repositories.SkieurRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class InscriptionService implements IInscriptionService {
    InscriptionRepository inscriptionRepository;
    CoursRepository coursRepository;
    SkieurRepository skieurRepository;
    @Override
    public List<Inscription> retrieveAllInscriptions() {
        return (List<Inscription>) inscriptionRepository.findAll();
    }

    @Override
    public Inscription addInscription(Inscription i) {
        inscriptionRepository.save(i);
        return i;
    }

    @Override
    public Inscription updateInscription(Inscription i) {
        inscriptionRepository.save(i);
        return i;
    }

    @Override
    public Inscription retrieveInscription(Integer idInscription) {
        return inscriptionRepository.findById(idInscription).get();
    }

    @Override
    public void deleteInscription(Integer idInscription) {
        inscriptionRepository.deleteById(idInscription);

    }

    @Transactional
    public Inscription assignInscriptionToCours(Long numInscription, Long numCours) {
       Inscription i=inscriptionRepository.findByNumInscription(numInscription);
       Cours c = coursRepository.findByNumCours(numCours);
        List<Inscription> inscriptions= (List<Inscription>) i.getCours();
        for (Inscription ins:inscriptions
        ) {
            ins.setCours(c);
        }
        return i;
    }


    @Override
    public Inscription addInscriptionAndAssignToSkieurAndCours(Inscription inscri, Long numSkieur, Long numCours) {
        Cours cours = coursRepository.findByNumCours(numCours);
        Skieur skieur = skieurRepository.findByNumSkieur(numSkieur);
        Period p= Period.between(skieur.getDateNaissance(), LocalDate.now());
        int age=p.getYears();
        Long nb=inscriptionRepository.countInscriptionByCours(cours);

        if (( age<18  &&(cours.getTypeCours().equals(TypeCours.COLLECTIF_ENFANT)))
                || ( age>=18 && (cours.getTypeCours().equals(TypeCours.COLLECTIF_ADULTE)))){
                if(nb<6) {
                    inscriptionRepository.save(inscri);
                    inscri.setSkieur(skieur);
                    inscri.setCours(cours);
                }
        }
        return inscri;
    }






}
