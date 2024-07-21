package tn.esprit.spring.stationdeski.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.stationdeski.entities.*;
import tn.esprit.spring.stationdeski.services.IInscriptionService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/inscription")
@Tag(name="Inscription Management")
public class InscriptionRestController {
    IInscriptionService inscriptionService;

    // http://localhost:8089/stationSki/inscription/retrieve-all-inscriptions
    @GetMapping("/retrieve-all-inscriptions")
    @Operation(description = "Liste des inscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the inscriptions",
                    content = { @Content(mediaType = "application/json") })})
    public List<Inscription> getInscription(){
        List<Inscription> listInscriptions = inscriptionService.retrieveAllInscriptions();
        return listInscriptions;
    }

    // http://localhost:8089/stationSki/inscription/retrieve-inscription/8
    @GetMapping("/retrieve-inscription/{inscription-id}")
    @Operation(description = "Retrouver une inscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the inscription",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscription.class))}),
            @ApiResponse(responseCode = "400",description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Inscription not found",
                    content = @Content) })
    public Inscription retrieveInscription(@Parameter(description = "id of inscription to be searched") @PathVariable("inscription-id") Integer inscriptionId) {
        return inscriptionService.retrieveInscription(inscriptionId);
    }

    // http://localhost:8089/stationSki/inscription/add-inscription
    @PostMapping("/add-abonnement")
    @Operation(description = "Ajouter une inscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscription.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public Inscription addInscription(@RequestBody Inscription i) {
        Inscription inscription = inscriptionService.addInscription(i);
        return inscription;
    }

    // http://localhost:8089/stationSki/inscription/remove-inscription/1
    @DeleteMapping("/remove-inscription/{inscription-id}")
    @Operation(description = "Supprimer une inscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscription.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public void removeInscription(@PathVariable("inscription-id") Integer inscriptionId) {
        inscriptionService.deleteInscription(inscriptionId);
    }

    // http://localhost:8089/stationSki/inscription/update-inscription
    @PutMapping("/update-inscription")
    @Operation(description = "Mettre à jour une inscription")
    public Inscription updateInscription(@RequestBody Inscription i) {
        Inscription inscription= inscriptionService.updateInscription(i);
        return inscription;
    }


    @PostMapping("/assignInscriptionToCours/{numInscription}/{numCours}/")
    @Operation(description = "Add Skieur And Assign To Cours")
    public Inscription assignInscriptionToCours(@PathVariable("numInscription") Long numInscription , @PathVariable("numCours") Long numCours){
        Inscription insc =inscriptionService.assignInscriptionToCours(numInscription,numCours);
        return insc;
    }
    @PostMapping("addInscriptionAndAssignToSkieurAndCours/{numSkieur}/{numCours}")
    public Inscription addInscriptionAndAssignToSkieurAndCours(@RequestBody Inscription inscr, @PathVariable("numSkieur")Long numSkieur, @PathVariable("numCours")Long numCours){
        return inscriptionService.addInscriptionAndAssignToSkieurAndCours(inscr,numSkieur,numCours);
    }

}
