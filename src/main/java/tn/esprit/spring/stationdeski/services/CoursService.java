package tn.esprit.spring.stationdeski.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.stationdeski.entities.Cours;
import tn.esprit.spring.stationdeski.entities.Moniteur;
import tn.esprit.spring.stationdeski.entities.Support;
import tn.esprit.spring.stationdeski.repositories.CoursRepository;
import tn.esprit.spring.stationdeski.repositories.MoniteurRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CoursService implements ICoursService{
    CoursRepository coursRepository;
    MoniteurRepository moniteurRepository;

    @Override
    public List<Cours> retrieveAllCours() {
        return (List<Cours>) coursRepository.findAll();
    }

    @Override
    public Cours addCours(Cours c) {
        coursRepository.save(c);
        return c;
    }

    @Override
    public Cours updateCours(Cours c) {
        coursRepository.save(c);
        return c;
    }

    @Override
    public Cours retrieveCours(Integer idCours) {
        return coursRepository.findById(idCours).get();
    }

    @Override
    public void deleteCours(Integer idCours) {
        coursRepository.deleteById(idCours);

    }


    @Override
    public List<Integer> numWeeksCourseOfMoniteurBySupport(Long numMoniteur, Support support) {
        List<Integer> l1=new ArrayList<>();
        Moniteur m= moniteurRepository.findMoniteurByNomM(numMoniteur);
        List<Cours> lCours=coursRepository.findCoursBySupport(support);
        for (Cours c:lCours
        ) {
            Integer integer=coursRepository.getCoursBySupport(support);
            l1.add(integer);

        }

        return l1;

    }

}
