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
import tn.esprit.spring.stationdeski.entities.Abonnement;
import tn.esprit.spring.stationdeski.entities.Cours;
import tn.esprit.spring.stationdeski.entities.Moniteur;
import tn.esprit.spring.stationdeski.entities.Support;
import tn.esprit.spring.stationdeski.services.ICoursService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cours")
@Tag(name="Cours Management")
public class CoursRestController {
    ICoursService coursService;

    // http://localhost:8089/stationSki/cours/retrieve-all-cours
    @GetMapping("/retrieve-all-cours")
    @Operation(description = "Liste des cours")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Courses",
                    content = { @Content(mediaType = "application/json") })})
    public List<Cours> getCours(){
        List<Cours> listCours = coursService.retrieveAllCours();
        return listCours;
    }

    // http://localhost:8089/stationSki/cours/retrieve-cours/8
    @GetMapping("/retrieve-cours/{cours-id}")
    @Operation(description = "Retrouver un cours")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Course",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cours.class))}),
            @ApiResponse(responseCode = "400",description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Course not found",
                    content = @Content) })

    public Cours retrieveCours(@Parameter(description = "id of course to be searched") @PathVariable("cours-id") Integer coursId) {
        return coursService.retrieveCours(coursId);
    }

    // http://localhost:8089/stationSki/cours/add-cours
    @PostMapping("/add-cours")
    @Operation(description = "Ajouter un cours")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cours.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public Cours addCours(@RequestBody Cours c) {
        Cours cours = coursService.addCours(c);
        return cours;
    }

    // http://localhost:8089/stationSki/cours/remove-cours/1
    @DeleteMapping("/remove-cours/{cours-id}")
    @Operation(description = "Supprimer un cours")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cours.class))}),
            @ApiResponse(responseCode = "400",description = "Error",
                    content = @Content) })
    public void removeCours(@PathVariable("cours-id") Integer coursId) {
        coursService.deleteCours(coursId);
    }

    // http://localhost:8089/stationSki/cours/update-cours
    @PutMapping("/update-cours")
    @Operation(description = "Mettre à jour un cours")
    public Cours updateCours(@RequestBody Cours c) {
        Cours cours= coursService.updateCours(c);
        return cours;
    }


    @GetMapping("numWeek")
    public List<Integer> numWeeks(Long numMoniteur, Support support){
        return coursService.numWeeksCourseOfMoniteurBySupport(numMoniteur, support);

    }
}
