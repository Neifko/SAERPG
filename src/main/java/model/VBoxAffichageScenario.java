package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

public class VBoxAffichageScenario extends VBox {
    Label labelSolution;
    TableView<Quest> tableDesQuetes;

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

    public void updateTable(List<Quest> quetes) {
        tableDesQuetes.getItems().clear();
        if (quetes == null) {
            return;
        }

        tableDesQuetes.getItems().addAll(quetes);
    }
}

