package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RpgApplication extends Application {
    private List<String> scenarios;

    @Override
    public void start(Stage stage) throws IOException {
        // Chargement des scénarios disponibles dans le répertoire
        loadScenarios();

        // Création du GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label titleLabel = new Label("Sélectionnez un scénario :");
        gridPane.add(titleLabel, 0, 0);

        ObservableList<String> scenarioOptions = FXCollections.observableArrayList(scenarios);
        ComboBox<String> scenarioComboBox = new ComboBox<>(scenarioOptions);
        gridPane.add(scenarioComboBox, 0, 1);

        scenarioComboBox.getSelectionModel().selectFirst();

        Label coordinatesLabel = new Label("Sélectionnez les coordonnées :");
        gridPane.add(coordinatesLabel, 0, 2);

        Label xLabel = new Label("x :");
        gridPane.add(xLabel, 0, 3);

        TextField xTextField = new TextField("0");
        gridPane.add(xTextField, 1, 3);

        Label yLabel = new Label("y :");
        gridPane.add(yLabel, 0, 4);

        TextField yTextField = new TextField("0");
        gridPane.add(yTextField, 1, 4);

        Button generateButton = new Button("Générer");
        generateButton.setOnAction(event -> {
            String selectedScenario = scenarioComboBox.getValue();
            if (selectedScenario != null) {
                int x = Integer.parseInt(xTextField.getText());
                int y = Integer.parseInt(yTextField.getText());
                // Appeler la méthode pour générer les tableaux avec le scénario sélectionné
                //todo: jouter le code pr les tableaux
                generateSolutions(selectedScenario, x, y);
            }
        });
        gridPane.add(generateButton, 0, 5);

        Scene scene = new Scene(gridPane, 500, 300);

        // Chargement des fichiers CSS depuis le dossier "css"
        File[] fichiersCss = new File("css").listFiles();
        if (fichiersCss != null) {
            for (File fichier : fichiersCss) {
                try {
                    scene.getStylesheets().add(fichier.toURI().toString());
                } catch (Exception e) {
                    System.out.println("Erreur lors du chargement du fichier CSS : " + fichier.getName());
                    e.printStackTrace();
                }
            }
        }
        stage.setTitle("RPG SAE IHM");
        stage.setScene(scene);
        stage.show();
    }

    private void loadScenarios() {
        scenarios = new ArrayList<>();
        File scenariosDirectory = new File("scenarios");
        File[] files = scenariosDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    scenarios.add(file.getName());
                }
            }
        }
    }

    // à revoir
    private void generateSolutions(String selectedScenario, int x, int y) {
        // Code pour générer les tableaux de solutions efficace et exhaustive
        // en utilisant le scénario sélectionné et les coordonnées x et y
    }


    public static void main(String[] args) {
        launch();
    }
}