package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.Player;
import model.Quest;
import model.Scenario;
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
                generateSolutions();
                addQuetesToTables();
            }
        }
    }

    private void generateSolutions() {
        Scenario scenario = HBoxRoot.getScenario();
        Player player = new Player(scenario);

        // Génération de la solution efficace
        player.efficace();
        solutionsEfficaces = player.getCompletedQuests();

        // Génération de la solution exhaustive
        player.exhaustive();
        solutionsExhaustives = player.getCompletedQuests();

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
