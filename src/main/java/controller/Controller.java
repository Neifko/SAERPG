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
        if (event.getSource() instanceof Button) {
            Button sourceButton = (Button) event.getSource();

            if (sourceButton.getId().equals("Generer")) {
                clearTables();
                generateSolutions();
                addQuetesToTables();
            }
        }
    }

    /**
     * Génère les solutions efficaces et exhaustives en fonction du scénario actuel.
     * Met à jour les listes solutionsEfficaces et solutionsExhaustives.
     * Met également à jour la liste des quêtes 'quetes'.
     */
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

    /**
     * Ajoute les quêtes aux tables de solutions efficaces, exhaustives et de quêtes.
     * Met également à jour les tables dans VBoxAffichageScenario.
     */
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

    /**
     * Efface le contenu des tables de solutions efficaces, exhaustives et de quêtes.
     * Met également à jour les tables dans VBoxAffichageScenario.
     */
    private void clearTables() {
        HBoxRoot.getEfficaceTable().getItems().clear();
        HBoxRoot.getExhaustiveTable().getItems().clear();
        HBoxRoot.getQueteTable().getItems().clear();
        HBoxRoot.getVBoxAffichageScenario().updateTableEfficace(null);
        HBoxRoot.getVBoxAffichageScenario().updateTableExhaustive(null);
    }
}
