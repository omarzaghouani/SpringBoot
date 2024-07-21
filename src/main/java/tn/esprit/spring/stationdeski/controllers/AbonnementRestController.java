package tn.esprit.spring.stationdeski.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.stationdeski.entities.Abonnement;
import tn.esprit.spring.stationdeski.entities.Cours;
import tn.esprit.spring.stationdeski.entities.Inscription;
import tn.esprit.spring.stationdeski.entities.TypeAbonnement;
import tn.esprit.spring.stationdeski.services.IAbonnementService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/abonnement")
@Tag(name="Abonnement Management")
public class AbonnementRestController {
    IAbonnementService abonnementService;

    // http://localhost:8089/stationSki/abonnement/retrieve-all-abonnements
    @GetMapping("/retrieve-all-abonnements")
    @Operation(description = "Liste des abonnements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Abonnement",
                    content = { @Content(mediaType = "application/json") })})
    public List<Abonnement> getAbonnements(){
        List<Abonnement> listAbonnements = abonnementService.retrieveAllAbonnements();
        return listAbonnements;
    }

    // http://localhost:8089/stationSki/abonnement/retrieve-abonnement/8
    @GetMapping("/retrieve-abonnement/{abonnement-id}")
    @Operation(description = "Retrouver un abonnement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Abonnement",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Abonnement.class))}),
            @ApiResponse(responseCode = "400",description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Abonnement not found",
                    content = @Content) })
    public Abonnement retrieveAbonnement(@Parameter(description = "id of Abonnement to be searched") @PathVariable("abonnement-id") Integer abonnementId) {
        return abonnementService.retrieveAbonnement(abonnementId);
    }

    // http://localhost:8089/stationSki/abonnement/add-abonnement
    @PostMapping("/add-abonnement")
    @Operation(description = "Ajouter un abonnement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Abonnement.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public Abonnement addAbonnement(@RequestBody Abonnement a) {
        Abonnement abonnement = abonnementService.addAbonnement(a);
        return abonnement;
    }

    // http://localhost:8089/stationSki/abonnement/remove-abonnement/1
    @DeleteMapping("/remove-abonnement/{abonnement-id}")
    @Operation(description = "Supprimer un abonnement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Abonnement.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public void removeAbonnement(@PathVariable("abonnement-id") Integer abonnementId) {
        abonnementService.deleteAbonnement(abonnementId);
    }

    // http://localhost:8089/stationSki/abonnement/update-abonnement
    @PutMapping("/update-abonnement")
    @Operation(description = "Mettre à jour un abonnement")
    public Abonnement updateAbonnement(@RequestBody Abonnement a) {
        Abonnement abonnement= abonnementService.updateAbonnement(a);
        return abonnement;
    }


    @GetMapping("/getAbonnementByType/{typeAbon}")
    @Operation(description = "Retrouver un abonnement par son type")
    public Set<Abonnement> getAbonnementByType(@PathVariable("typeAbon") TypeAbonnement typeAbonnement){
        return abonnementService.getAbonnementByType(typeAbonnement);
    }


    @PostMapping("/retrieveAbonnementsByDates/{startDate}/{endDate}")
    @Operation(description = "Retrieve Abonnements By Dates")
    public List<Abonnement> retrieveAbonnementsByDates(@RequestBody Abonnement abonnement, @PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate){
        List<Abonnement> ab=abonnementService.retrieveAbonnementsByDates(startDate,endDate);
        return ab;
    }
}
