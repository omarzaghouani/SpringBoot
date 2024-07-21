package tn.esprit.spring.stationdeski.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.stationdeski.entities.Inscription;
import tn.esprit.spring.stationdeski.entities.Moniteur;
import tn.esprit.spring.stationdeski.entities.Support;
import tn.esprit.spring.stationdeski.services.IMoniteurService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/moniteur")
@Tag(name="Moniteur Management")

public class MoniteurRestController {
    IMoniteurService moniteurService;

    // http://localhost:8089/stationSki/moniteur/retrieve-all-moniteurs
    @GetMapping("/retrieve-all-moniteurs")
    @Operation(description = "Liste des moniteurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the moniteurs",
                    content = { @Content(mediaType = "application/json") })})
    public List<Moniteur> getMoniteurs(){
        List<Moniteur> listMoniteurs = moniteurService.retrieveAllMoniteurs();
        return listMoniteurs;
    }

    // http://localhost:8089/stationSki/moniteur/retrieve-moniteur/8
    @Operation(description = "Retrouver un moniteur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the monitor",
                         content = { @Content(mediaType = "application/json",
                         schema = @Schema(implementation = Moniteur.class))}),
            @ApiResponse(responseCode = "400",description = "Invalid id supplied",
                         content = @Content),
            @ApiResponse(responseCode = "404", description = "Moniteur not found",
                        content = @Content) })
    @GetMapping("/retrieve-moniteur/{moniteur-id}")
    public Moniteur retrieveMoniteur(@Parameter(description = "id of moniteur to be searched") @PathVariable("moniteur-id") Integer moniteurId) {
            return moniteurService.retrieveMoniteur(moniteurId);
}

    // http://localhost:8089/stationSki/moniteur/add-moniteur
    @PostMapping("/add-moniteur")
    @Operation(description = "Ajouter un moniteur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Moniteur.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public Moniteur addMoniteur(@RequestBody Moniteur m) {
        Moniteur moniteur = moniteurService.addMoniteur(m);
        return moniteur;
    }

    // http://localhost:8089/stationSki/moniteur/remove-moniteur/1
    @DeleteMapping("/remove-moniteur/{moniteur-id}")
    @Operation(description = "Supprimer un moniteur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Moniteur.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public void removeMoniteur(@PathVariable("moniteur-id") Integer moniteurId) {
        moniteurService.deleteMoniteur(moniteurId);
    }

    // http://localhost:8089/stationSki/moniteur/update-moniteur
    @PutMapping("/update-moniteur")
    @Operation(description = "Mettre à jour un moniteur")
    public Moniteur updateMoniteur(@RequestBody Moniteur m) {
        Moniteur moniteur= moniteurService.updateMoniteur(m);
        return moniteur;
    }

    @PostMapping("/addMoniteurToCours")
    @Operation(description = "Add Moniteur And Assign To Cours")
    public Moniteur addMoniteurAndAssignToCours(@RequestBody Moniteur m){
        Moniteur moniteur=moniteurService.addMoniteurAndAssignToCours(m);
        return moniteur;
    }


}



