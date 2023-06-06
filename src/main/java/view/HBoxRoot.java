package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Quest;
import model.Scenario;
import model.VBoxAffichageScenario;

public class HBoxRoot extends HBox {
    private static GridPaneRoot gridPaneRoot; // Instance de GridPaneRoot
    private static VBoxAffichageScenario vBoxAffichageScenario; // Instance de VBoxAffichageScenario
    private static Controller controller; // Instance de Controller
    private static Scenario scenario; // Instance de Scenario

    /**
     * Constructeur de la classe HBoxRoot.
     * Initialise les éléments de l'interface graphique.
     */
    public HBoxRoot() {
        // Instancier le contrôleur
        controller = new Controller();
        // Créer le GridPane existant
        gridPaneRoot = new GridPaneRoot();

        // Créer l'instance de VBoxAffichageScenario
        vBoxAffichageScenario = new VBoxAffichageScenario();

        // Configurer les marges et l'espacement
        setSpacing(10);
        setPadding(new Insets(10));

        // Ajouter les éléments à l'HBox
        this.getChildren().add(gridPaneRoot);
        this.getChildren().add(vBoxAffichageScenario);
    }

    /**
     * Renvoie l'instance de GridPaneRoot.
     * @return l'instance de GridPaneRoot
     */
    public static GridPaneRoot getGridPaneRoot() {
        return gridPaneRoot;
    }

    /**
     * Renvoie l'instance de Controller.
     * @return l'instance de Controller
     */
    public static Controller getController() {
        return controller;
    }

    /**
     * Renvoie l'instance de VBoxAffichageScenario.
     * @return l'instance de VBoxAffichageScenario
     */
    public static VBoxAffichageScenario getVBoxAffichageScenario() {
        return vBoxAffichageScenario;
    }

    /**
     * Renvoie l'instance de Scenario.
     * @return l'instance de Scenario
     */
    public static Scenario getScenario() {
        System.out.println(scenario);
        return scenario;
    }

    /**
     * Définit l'instance de Scenario.
     * @param scenario l'instance de Scenario à définir
     */
    public static void setScenario(Scenario scenario) {
        HBoxRoot.scenario = scenario;
    }

    /**
     * Crée et retourne une TableView pour afficher les quêtes efficaces.
     * @return la TableView des quêtes efficaces
     */
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

    /**
     * Crée et retourne une TableView pour afficher les quêtes exhaustives.
     * @return la TableView des quêtes exhaustives
     */
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

    /**
     * Crée et retourne une TableView pour afficher les quêtes.
     * @return la TableView des quêtes
     */
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

