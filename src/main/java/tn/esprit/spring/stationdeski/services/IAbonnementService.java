package tn.esprit.spring.stationdeski.services;

import tn.esprit.spring.stationdeski.entities.Abonnement;
import tn.esprit.spring.stationdeski.entities.TypeAbonnement;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IAbonnementService {

    List<Abonnement> retrieveAllAbonnements();

    Abonnement addAbonnement(Abonnement a);

    Abonnement updateAbonnement (Abonnement a);

    Abonnement retrieveAbonnement (Integer idAbonnement);

    void deleteAbonnement( Integer idAbonnement);

    Set<Abonnement> getAbonnementByType(TypeAbonnement type);

    List<Abonnement> retrieveAbonnementsByDates(LocalDate startDate, LocalDate endDate);


}
