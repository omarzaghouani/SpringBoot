package tn.esprit.spring.stationdeski.services;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;
import tn.esprit.spring.stationdeski.entities.Abonnement;
import tn.esprit.spring.stationdeski.entities.Skieur;
import tn.esprit.spring.stationdeski.entities.TypeAbonnement;
import tn.esprit.spring.stationdeski.repositories.AbonnementRepository;
import tn.esprit.spring.stationdeski.repositories.SkieurRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;

import static tn.esprit.spring.stationdeski.entities.TypeAbonnement.MENSUEL;

@Service
@AllArgsConstructor
public class AbonnementService implements IAbonnementService{
   AbonnementRepository abonnementRepository;
   SkieurRepository skieurRepository;
    @Override
    public List<Abonnement> retrieveAllAbonnements() {
        return (List<Abonnement>) abonnementRepository.findAll();
    }

    @Override
    public Abonnement addAbonnement(Abonnement a) {
        abonnementRepository.save(a);
        return a;
    }

    @Override
    public Abonnement updateAbonnement(Abonnement a) {
        abonnementRepository.save(a);
        return a;
    }

    @Override
    public Abonnement retrieveAbonnement(Integer idAbonnement) {
        return abonnementRepository.findById(idAbonnement).get();
    }

    @Override
    public void deleteAbonnement(Integer idAbonnement) {
        abonnementRepository.deleteById(idAbonnement);
    }

    @Override
    public Set<Abonnement> getAbonnementByType(TypeAbonnement typeAbonnement) {
        Set<Abonnement> ab= (Set<Abonnement>) abonnementRepository.findByTypeAbonnement(typeAbonnement);
        return ab;
    }

    @Override
    public List<Abonnement> retrieveAbonnementsByDates(LocalDate startDate, LocalDate endDate) {
        List<Abonnement> ab =abonnementRepository.findByDateDebutBetween(startDate,endDate);
         return ab;
    }

    @Scheduled(cron = "* * 8 * * *") // ts les js à 8h
    public void retrieveSubscriptions(){
        LocalDate dateFin = LocalDate.now().plusDays(7);
        List<Abonnement> list = abonnementRepository.findByDateFinBefore(dateFin);
        for (Abonnement ab : list) {
            Skieur s = skieurRepository.findById(ab.getIdAbonnement()).orElse(null);
            if (s != null) {
                System.out.println("Skieur " +s.getNomS() + " " + s.getPrenomS() + " Num Skieur " + s.getNumSkieur());
            }
        }
    }

    @Scheduled(cron = "* * 8 * * *") // ts les js à 8h
    public void showMonthlyRecurringRevenue() {
        LocalDate now = LocalDate.now();
        List<Abonnement> ab = abonnementRepository.findByDateDebutBeforeAndDateFinAfter(now, now);
        float mrr = 0;
        for (Abonnement a : ab) {
            mrr += a.getPrixAbon();
        }
        System.out.println("Le MRR est de " + mrr + " dinars.");
    }

    }



