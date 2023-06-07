package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class VBoxAffichageScenario extends VBox {
    private Label labelSolutionEfficace;
    private TableView<Quest> tableSolutionEfficace;
    private Label labelSolutionExhaustive;
    private TableView<Quest> tableSolutionExhaustive;

    public VBoxAffichageScenario() {
        // Création de l'étiquette pour la solution efficace
        labelSolutionEfficace = new Label("Solution efficace");

        // Création de la table pour afficher la solution efficace
        tableSolutionEfficace = new TableView<>();
        // Création des colonnes de la table pour afficher les propriétés de l'objet Quest
        TableColumn<Quest, Integer> numeroColumnEfficace = new TableColumn<>("Numéro");
        TableColumn<Quest, String> titreColumnEfficace = new TableColumn<>("Titre");
        TableColumn<Quest, String> positionColumnEfficace = new TableColumn<>("Position");

        // Configuration des propriétés des colonnes pour qu'elles affichent les valeurs des propriétés correspondantes de l'objet Quest
        numeroColumnEfficace.setCellValueFactory(new PropertyValueFactory<>("id"));
        titreColumnEfficace.setCellValueFactory(new PropertyValueFactory<>("title"));
        positionColumnEfficace.setCellValueFactory(new PropertyValueFactory<>("formattedCoordinates")); // Utilisation de la méthode getFormattedCoordinates() pour la colonne position

        // Ajout des colonnes à la table de la solution efficace
        tableSolutionEfficace.getColumns().addAll(numeroColumnEfficace, titreColumnEfficace, positionColumnEfficace);

        // Création de l'étiquette pour la solution exhaustive
        labelSolutionExhaustive = new Label("Solution exhaustive");

        // Création de la table pour afficher la solution exhaustive
        tableSolutionExhaustive = new TableView<>();
        // Création des colonnes de la table pour afficher les propriétés de l'objet Quest
        TableColumn<Quest, Integer> numeroColumnExhaustive = new TableColumn<>("Numéro");
        TableColumn<Quest, String> titreColumnExhaustive = new TableColumn<>("Titre");
        TableColumn<Quest, String> positionColumnExhaustive = new TableColumn<>("Position");

        // Configuration des propriétés des colonnes pour qu'elles affichent les valeurs des propriétés correspondantes de l'objet Quest
        numeroColumnExhaustive.setCellValueFactory(new PropertyValueFactory<>("id"));
        titreColumnExhaustive.setCellValueFactory(new PropertyValueFactory<>("title"));
        positionColumnExhaustive.setCellValueFactory(new PropertyValueFactory<>("formattedCoordinates")); // Utilisation de la méthode getFormattedCoordinates() pour la colonne position

        // Ajout des colonnes à la table de la solution exhaustive
        tableSolutionExhaustive.getColumns().addAll(numeroColumnExhaustive, titreColumnExhaustive, positionColumnExhaustive);

        // Création des conteneurs pour organiser les éléments de l'interface utilisateur
        VBox vboxSolutionEfficace = new VBox(labelSolutionEfficace, tableSolutionEfficace);
        VBox vboxSolutionExhaustive = new VBox(labelSolutionExhaustive, tableSolutionExhaustive);

        // Création du conteneur horizontal pour afficher les deux tables côte à côte
        HBox hboxTables = new HBox(vboxSolutionEfficace, vboxSolutionExhaustive);
        hboxTables.setSpacing(10);

        // Configuration de l'espacement et du padding du VBox principal
        setSpacing(10);
        setPadding(new Insets(10));

        // Ajout du conteneur horizontal à ce VBox principal
        this.getChildren().add(hboxTables);
    }

    /**
     * Met à jour la table de la solution efficace avec une nouvelle liste de quêtes.
     * @param quests la liste de quêtes à afficher
     */
    public void updateTableEfficace(List<Quest> quests) {
        // Efface le contenu de la table de la solution efficace
        tableSolutionEfficace.getItems().clear();
        // Vérifie si la liste de quêtes est null, dans ce cas on arrête la méthode
        if (quests == null) {
            return;
        }
        // Ajoute chaque quête de la liste à la table de la solution efficace
        for (Quest quete : quests) {
            tableSolutionEfficace.getItems().add(quete);
        }
    }

    /**
     * Met à jour la table de la solution exhaustive avec une nouvelle liste de quêtes.
     * @param quests la liste de quêtes à afficher
     */
    public void updateTableExhaustive(List<Quest> quests) {
        // Efface le contenu de la table de la solution exhaustive
        tableSolutionExhaustive.getItems().clear();
        // Vérifie si la liste de quêtes est null, dans ce cas on arrête la méthode
        if (quests == null) {
            return;
        }
        // Ajoute chaque quête de la liste à la table de la solution exhaustive
        for (Quest quete : quests) {
            tableSolutionExhaustive.getItems().add(quete);
        }
    }
}
