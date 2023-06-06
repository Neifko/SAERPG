package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import model.*;
import view.GridPaneRoot;
import view.HBoxRoot;

import java.util.ArrayList;
import java.util.List;

public class Controller implements EventHandler<ActionEvent> {

    private List<Quest> solutionsEfficaces = new ArrayList<>();
    private List<Quest> solutionsExhaustives = new ArrayList<>();
    private List<Quest> quetes = new ArrayList<>();

    @Override
    public void handle(ActionEvent event) {

        Scenario planning = HBoxRoot.getScenario();
        GridPaneRoot reservationPane = (GridPaneRoot) HBoxRoot.getGridPaneRoot();
        VBoxAffichageScenario affichagePane = HBoxRoot.getVBoxAffichageScenario ();

        if (event.getSource() instanceof Button) {
            Button sourceButton = (Button) event.getSource();

            if (sourceButton.getId().equals("Generer")) {
                generateSolutions();
                addQuetesToTables();
            }
        }
    }

//    if (event.getSource() instanceof ToggleButton) {
//        // Gestion de l'événement lorsque le bouton bascule est sélectionné/désélectionné
//        DateCalendrier date = (DateCalendrier) ((ToggleButton) event.getSource()).getUserData();
//        reservationPane.setDate(date);
//
//        dateSel = date; // Met à jour la date sélectionnée
//
//        reservationPane.setDate((DateCalendrier) dateSel); // Met à jour la date du formulaire de réservation
//
//        affichagePane.setDate(dateSel); // Met à jour la date de l'affichage du planning
//        affichagePane.updateTab(planning.getReservationSemaine(((DateCalendrier) dateSel).getWeekOfYear())); // Met à jour le tableau d'affichage des réservations
//    }
//
//        if (event.getSource() instanceof Button) {
//        // Gestion de l'événement lorsque le bouton est cliqué (ajout d'une réservation)
//        Reservation res = null;
//        try {
//            // Crée une nouvelle réservation à partir des informations du formulaire
//            res = new Reservation(reservationPane.getDate(), reservationPane.getPlageHoraire(), reservationPane.getTitre());
//            planning.ajout(res); // Ajoute la réservation au planning
//            affichagePane.updateTab(planning.getReservationSemaine(dateSel.getWeekOfYear())); // Met à jour le tableau d'affichage des réservations
//        } catch (ExceptionPlanning e) {
//            throw new RuntimeException(e); // En cas d'erreur de planification, lance une exception
//        }
//        LectureEcriture.ecriture(HBoxRoot.getPlanningFile(), planning); // Écriture du planning dans un fichier
//        System.out.println(planning); // Affiche le planning (à des fins de débogage)
//    }
    private void generateSolutions() {
        Scenario scenario = HBoxRoot.getScenario();
        Player player = new Player(scenario);
        SolutionEfficace soleff = new SolutionEfficace(player);
        SolutionExhaustive solexh = new SolutionExhaustive(player);

        // Génération de la solution efficace
        if(scenario == null){
            System.out.println("SCENARIO NULL");
            return;
        }

        solutionsEfficaces = soleff.parcours();

        // Génération de la solution exhaustive
        solutionsExhaustives = solexh.parcours();

        // Ajout des quêtes du scénario à la liste 'quetes'
        quetes = scenario.getQuests();

        // Mise à jour des tables dans VBoxAffichageScenario
        addQuetesToTables();
    }



    private void addQuetesToTables() {
        // Obtention des instances des différentes vues
        TableView<Quest> efficaceTable = HBoxRoot.getEfficaceTable();
        TableView<Quest> exhaustiveTable = HBoxRoot.getExhaustiveTable();
        TableView<Quest> queteTable = HBoxRoot.getQueteTable();

        // Ajout des quêtes à la table des solutions efficaces
        efficaceTable.getItems().addAll(solutionsEfficaces);

        // Ajout des quêtes à la table des solutions exhaustives
        exhaustiveTable.getItems().addAll(solutionsExhaustives);

        // Ajout des quêtes à la table des quêtes
        queteTable.getItems().addAll(quetes);

        // Mise à jour des tables dans VBoxAffichageScenario
        HBoxRoot.getVBoxAffichageScenario().updateTableEfficace(solutionsEfficaces);
        HBoxRoot.getVBoxAffichageScenario().updateTableExhaustive(solutionsExhaustives);
    }
}
