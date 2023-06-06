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
        labelSolutionEfficace = new Label("Solution efficace");

        tableSolutionEfficace = new TableView<>();
        TableColumn<Quest, Integer> numeroColumnEfficace = new TableColumn<>("Numéro");
        TableColumn<Quest, String> titreColumnEfficace = new TableColumn<>("Titre");
        TableColumn<Quest, String> positionColumnEfficace = new TableColumn<>("Position");

        numeroColumnEfficace.setCellValueFactory(new PropertyValueFactory<>("numero"));
        titreColumnEfficace.setCellValueFactory(new PropertyValueFactory<>("titre"));
        positionColumnEfficace.setCellValueFactory(new PropertyValueFactory<>("position"));

        tableSolutionEfficace.getColumns().addAll(numeroColumnEfficace, titreColumnEfficace, positionColumnEfficace);

        labelSolutionExhaustive = new Label("Solution exhaustive");

        tableSolutionExhaustive = new TableView<>();
        TableColumn<Quest, Integer> numeroColumnExhaustive = new TableColumn<>("Numéro");
        TableColumn<Quest, String> titreColumnExhaustive = new TableColumn<>("Titre");
        TableColumn<Quest, String> positionColumnExhaustive = new TableColumn<>("Position");

        numeroColumnExhaustive.setCellValueFactory(new PropertyValueFactory<>("numero"));
        titreColumnExhaustive.setCellValueFactory(new PropertyValueFactory<>("titre"));
        positionColumnExhaustive.setCellValueFactory(new PropertyValueFactory<>("position"));

        tableSolutionExhaustive.getColumns().addAll(numeroColumnExhaustive, titreColumnExhaustive, positionColumnExhaustive);

        VBox vboxSolutionEfficace = new VBox(labelSolutionEfficace, tableSolutionEfficace);
        VBox vboxSolutionExhaustive = new VBox(labelSolutionExhaustive, tableSolutionExhaustive);

        HBox hboxTables = new HBox(vboxSolutionEfficace, vboxSolutionExhaustive);
        hboxTables.setSpacing(10);

        setSpacing(10);
        setPadding(new Insets(10));
        this.getChildren().add(hboxTables);
    }
    public void updateTableEfficace(List<Quest> quests) {
        tableSolutionEfficace.getItems().clear();
        if (quests == null) {return;}
        for (Quest quete : quests){
            tableSolutionEfficace.getItems().add(quete);
        }
    }
    public void updateTableExhaustive(List<Quest> quests) {
        tableSolutionExhaustive.getItems().clear();
        if (quests == null) {return;}
        for (Quest quete : quests){
            tableSolutionExhaustive.getItems().add(quete);
        }
    }
}
