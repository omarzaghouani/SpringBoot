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
import tn.esprit.spring.stationdeski.services.IPisteService;
import tn.esprit.spring.stationdeski.services.ISkieurService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/skieur")
@Tag(name="Skieur Management")

public class SkieurRestController {
    ISkieurService skieurService;
    IPisteService pisteService;

    // http://localhost:8089/stationSki/skieur/retrieve-all-skieurs
    @GetMapping("/retrieve-all-skieurs")
    @Operation(description = "Liste des skieurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the skieurs",
                    content = { @Content(mediaType = "application/json") })})
    public List<Skieur> getskieurs(){
        List<Skieur> listskieurs = skieurService.retrieveAllSkieurs();
        return listskieurs;
    }

    // http://localhost:8089/stationSki/skieur/retrieve-skieur/8
    @GetMapping("/retrieve-skieur/{skieur-id}")
    @Operation(description = "Retrouver un skieur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Skieur",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skieur.class))}),
            @ApiResponse(responseCode = "400",description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Skieur not found",
                    content = @Content) })

    public Skieur retrieveskieur(@Parameter(description = "id of Skieur to be searched") @PathVariable("skieur-id") Integer skieurId) {
        return skieurService.retrieveSkieur(skieurId);
    }

    // http://localhost:8089/stationSki/skieur/add-skieur
    @PostMapping("/add-skieur")
    @Operation(description = "Ajouter un skieur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skieur.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public Skieur addskieur(@RequestBody Skieur s) {
        Skieur skieur = skieurService.addSkieur(s);
        return skieur;
    }

    // http://localhost:8089/stationSki/skieur/remove-skieur/1
    @DeleteMapping("/remove-skieur/{skieur-id}")
    @Operation(description = "Supprimer un skieur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skieur.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public void removeskieur(@PathVariable("skieur-id") Integer skieurId) {
        skieurService.deleteSkieur(skieurId);
    }

    // http://localhost:8089/stationSki/skieur/update-skieur
    @PutMapping("/update-skieur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skieur.class))}),
            @ApiResponse(responseCode = "404",description = "Error",
                    content = @Content) })
    @Operation(description = "Mettre à jour un skieur")
    public Skieur updateskieur(@RequestBody Skieur s) {
        Skieur skieur= skieurService.updateSkieur(s);
        return skieur;
    }


    @PutMapping("/assignSkieurToPiste/{numSkieur}/{numPiste}")
    @Operation(description = "Assign Skieur To Piste")
    public Skieur assignSkieurToPiste(@PathVariable("numSkieur")Long numSkieur,
                                      @PathVariable("numPiste") Long numPiste) {
        Skieur s=skieurService.assignSkieurToPiste(numSkieur,numPiste);
        return s;
    }

    @PostMapping("/addSkieurAndAssignToCours/{numCours}")
    @Operation(description = "Add Skieur And Assign To Cours")
    public Skieur addSkieurAndAssignToCours(@RequestBody Skieur skieur, @PathVariable("numCours") Long numCours){
        Skieur s=skieurService.addSkieurAndAssignToCours(skieur,numCours);
        return s;
    }

    @GetMapping("/retrieve-skieurs/{abonnement-id}")
    @Operation(description = "Retrouver un skieur par son type d'abonnement")
    public List<Skieur> retrieveSkieurByTypeAbonnement(@PathVariable("abonnement-id") TypeAbonnement idAbonnement) {
        return skieurService.retrieveSkieursByAbonnementType(idAbonnement);
    }






}
