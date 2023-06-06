package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Quest;
import model.Scenario;
import model.VBoxAffichageScenario;

public class HBoxRoot extends HBox {
    private static GridPaneRoot gridPaneRoot;
    private static VBoxAffichageScenario vBoxAffichageScenario;
    private static Controller controller;
    private static Scenario scenario;

    public HBoxRoot() {
        // Instancier le contrôleur
        controller = new Controller();
        // Créer le GridPane existant
        gridPaneRoot = new GridPaneRoot();

        // Créer l'instance de VBoxAffichageScenario
        vBoxAffichageScenario = new VBoxAffichageScenario();
        scenario = new Scenario();

        // Configurer les marges et l'espacement
        setSpacing(10);
        setPadding(new Insets(10));

        // Ajouter les éléments à l'HBox
        this.getChildren().add(gridPaneRoot);
        this.getChildren().add(vBoxAffichageScenario);
    }

    public static GridPane getGridPaneRoot() {
        return gridPaneRoot;
    }

    public static Controller getController() {
        return controller;
    }

    public static VBoxAffichageScenario getVBoxAffichageScenario() {
        return vBoxAffichageScenario;
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static void setScenario(Scenario scenario) {
        HBoxRoot.scenario = scenario;
    }

    public static TableView<Quest> getEfficaceTable() {
        TableView<Quest> table = new TableView<>();

        // Création des colonnes de la table
        TableColumn<Quest, Integer> colQuestId = new TableColumn<>("ID");
        TableColumn<Quest, String> colQuestDuration = new TableColumn<>("Durée");
        TableColumn<Quest, String> colQuestExperience = new TableColumn<>("Expérience");

        // Configuration des accesseurs pour les valeurs de chaque colonne
        colQuestId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQuestDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colQuestExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));

        // Ajout des colonnes à la table
        table.getColumns().addAll(colQuestId, colQuestDuration, colQuestExperience);

        return table;
    }

    public static TableView<Quest> getExhaustiveTable() {
        TableView<Quest> table = new TableView<>();

        // Création des colonnes de la table
        TableColumn<Quest, Integer> colQuestId = new TableColumn<>("ID");
        TableColumn<Quest, String> colQuestDuration = new TableColumn<>("Durée");
        TableColumn<Quest, String> colQuestExperience = new TableColumn<>("Expérience");

        // Configuration des accesseurs pour les valeurs de chaque colonne
        colQuestId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQuestDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colQuestExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));

        // Ajout des colonnes à la table
        table.getColumns().addAll(colQuestId, colQuestDuration, colQuestExperience);

        return table;
    }

    public static TableView<Quest> getQueteTable() {
        TableView<Quest> table = new TableView<>();

        // Création des colonnes de la table
        TableColumn<Quest, Integer> colQuestId = new TableColumn<>("ID");
        TableColumn<Quest, String> colQuestDuration = new TableColumn<>("Durée");
        TableColumn<Quest, String> colQuestExperience = new TableColumn<>("Expérience");

        // Configuration des accesseurs pour les valeurs de chaque colonne
        colQuestId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQuestDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colQuestExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));

        // Ajout des colonnes à la table
        table.getColumns().addAll(colQuestId, colQuestDuration, colQuestExperience);

        return table;
    }
}
