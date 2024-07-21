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
import tn.esprit.spring.stationdeski.entities.Couleur;
import tn.esprit.spring.stationdeski.entities.Moniteur;
import tn.esprit.spring.stationdeski.entities.Piste;
import tn.esprit.spring.stationdeski.entities.Skieur;
import tn.esprit.spring.stationdeski.services.IPisteService;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/piste")
@Tag(name="Piste Management")

public class PisteRestController {
    IPisteService pisteService;

    // http://localhost:8089/stationSki/piste/retrieve-all-pistes
    @GetMapping("/retrieve-all-pistes")
    @Operation(description = "Liste des pistes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the pistes",
                    content = { @Content(mediaType = "application/json") })})
    public List<Piste> getPistes(){
        List<Piste> listPistes = pisteService.retrieveAllPistes();
        return listPistes;
    }

    // http://localhost:8089/stationSki/piste/retrieve-piste/8
    @GetMapping("/retrieve-piste/{piste-id}")
    @Operation(description = "Retrouver une piste")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Piste",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Piste.class))}),
            @ApiResponse(responseCode = "400",description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Piste not found",
                    content = @Content) })
    public Piste retrievePiste(@Parameter(description = "id of Piste to be searched") @PathVariable("piste-id") Integer pisteId) {
        return pisteService.retrievePiste(pisteId);
    }

    // http://localhost:8089/stationSki/piste/add-piste
    @PostMapping("/add-piste")
    @Operation(description = "Ajouter une piste")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Piste.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public Piste addPiste(@RequestBody Piste p) {
        Piste piste = pisteService.addPiste(p);
        return piste;
    }

    // http://localhost:8089/stationSki/piste/remove-piste/1
    @DeleteMapping("/remove-piste/{piste-id}")
    @Operation(description = "Supprimer une piste")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Piste.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public void removePiste(@PathVariable("piste-id") Integer pisteId) {
        pisteService.deletePiste(pisteId);
    }

    // http://localhost:8089/stationSki/piste/update-piste
    @PutMapping("/update-piste")
    @Operation(description = "Mettre à jour une piste")
    public Piste updatePiste(@RequestBody Piste p) {
        Piste piste= pisteService.updatePiste(p);
        return piste;
    }


    @GetMapping("/nombreSkieursParCouleurPiste")
    public HashMap<Couleur, Integer> nombreSkieursParCouleurPiste() {

        return pisteService.nombreSkieursParCouleurPiste();
    }





}
