package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * VBoxAffichageScenario est une classe représentant un conteneur VBox affichant un titre "Solution efficace"
 * suivi d'un tableau de quêtes.
 */
public class VBoxAffichageScenario extends VBox {
    Label labelSolution;
    TableView<Quest> tableDesQuetes;

    /**
     * Constructeur de la classe VBoxAffichageScenario.
     * Initialise le VBox avec un label "Solution efficace" et un tableau de quêtes.
     */
    public VBoxAffichageScenario() {
        labelSolution = new Label(" Solution efficace ");

        tableDesQuetes = new TableView<>();
        TableColumn<Quest, Integer> numeroColumn = new TableColumn<>("Numéro");
        TableColumn<Quest, String> titreColumn = new TableColumn<>("Titre");
        TableColumn<Quest, String> positionColumn = new TableColumn<>("Position");

        numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        tableDesQuetes.getColumns().add(numeroColumn);
        tableDesQuetes.getColumns().add(titreColumn);
        tableDesQuetes.getColumns().add(positionColumn);

        this.getChildren().add(labelSolution);
        this.getChildren().add(tableDesQuetes);
    }

    /**
     * Met à jour le tableau de quêtes avec la liste spécifiée.
     *
     * @param quetes Liste des quêtes à afficher dans le tableau.
     */
    public void updateTable(List<Quest> quetes) {
        tableDesQuetes.getItems().clear();
        if (quetes == null) {
            return;
        }

        tableDesQuetes.getItems().addAll(quetes);
    }
}
