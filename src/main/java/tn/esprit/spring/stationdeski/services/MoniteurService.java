package tn.esprit.spring.stationdeski.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.stationdeski.entities.Cours;
import tn.esprit.spring.stationdeski.entities.Inscription;
import tn.esprit.spring.stationdeski.entities.Moniteur;
import tn.esprit.spring.stationdeski.entities.Support;
import tn.esprit.spring.stationdeski.repositories.CoursRepository;
import tn.esprit.spring.stationdeski.repositories.MoniteurRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MoniteurService implements IMoniteurService {
    MoniteurRepository moniteurRepository;
    CoursRepository coursRepository;

    @Override
    public List<Moniteur> retrieveAllMoniteurs() {
        return (List<Moniteur>) moniteurRepository.findAll();

    }

    @Override
    public Moniteur addMoniteur(Moniteur e) {
        moniteurRepository.save(e);
        return e;
    }

    @Override
    public Moniteur updateMoniteur(Moniteur e) {
        moniteurRepository.save(e);
        return e;
    }

    @Override
    public Moniteur retrieveMoniteur(Integer idMoniteur) {
        return moniteurRepository.findById(idMoniteur).get();
    }

    @Override
    public void deleteMoniteur(Integer idMoniteur) {
        moniteurRepository.deleteById(idMoniteur);
    }

    @Override
    public Moniteur addMoniteurAndAssignToCours(Moniteur moniteur) {
        Moniteur m = moniteurRepository.save(moniteur);
        return m;
    }


}








