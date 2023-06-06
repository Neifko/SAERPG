package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.File;

public class GridPaneRoot extends GridPane {
    public GridPaneRoot() {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);

        Label titleLabel = new Label("Sélectionnez un scénario :");
        add(titleLabel, 0, 0);

        ComboBox<String> scenarioComboBox = new ComboBox<>();
        add(scenarioComboBox, 0, 1);

        // Charger les scénarios disponibles
        loadScenarios(scenarioComboBox);

        Label coordinatesLabel = new Label("Sélectionnez les coordonnées :");
        add(coordinatesLabel, 0, 2);

        Label xLabel = new Label("x :");
        add(xLabel, 0, 3);

        TextField xTextField = new TextField("0");
        add(xTextField, 1, 3);

        Label yLabel = new Label("y :");
        add(yLabel, 0, 4);

        TextField yTextField = new TextField("0");
        add(yTextField, 1, 4);

        Button generateButton = new Button("Generer");
        generateButton.setOnAction(event -> {
            String selectedScenario = scenarioComboBox.getValue();
            if (selectedScenario != null) {
                int x = Integer.parseInt(xTextField.getText());
                int y = Integer.parseInt(yTextField.getText());
                System.out.println("fesse");
                // Appeler la méthode pour générer les tableaux avec le scénario sélectionné
                // TODO: Ajouter le code pour générer les tableaux
                generateSolutions(selectedScenario, x, y);
            }
        });
//        generateButton.addEventHandler(ActionEvent.ACTION, HBoxRoot.getController());
        add(generateButton, 0, 5);
    }

    private void loadScenarios(ComboBox<String> scenarioComboBox) {
        File scenariosDirectory = new File("scenarios");
        File[] files = scenariosDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    scenarioComboBox.getItems().add(file.getName());
                }
            }
        }

        // Définir le scénario par défaut
        if (!scenarioComboBox.getItems().isEmpty()) {
            scenarioComboBox.setValue(scenarioComboBox.getItems().get(0));
        }
    }


    private void generateSolutions(String selectedScenario, int x, int y) {
        // Code pour générer les tableaux de solutions efficace et exhaustive
        // en utilisant le scénario sélectionné et les coordonnées x et y

        // Exemple de code pour afficher les valeurs sélectionnées
        System.out.println("Scénario sélectionné : " + selectedScenario);
        System.out.println("Coordonnées x : " + x);
        System.out.println("Coordonnées y : " + y);
    }
}
