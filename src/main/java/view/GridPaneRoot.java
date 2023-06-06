package view;

import iofile.ReadTextFile;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Scenario;
import view.HBoxRoot;

import java.io.File;

public class GridPaneRoot extends GridPane {

    ComboBox<String> scenarioComboBox; // ComboBox pour sélectionner un scénario
    Button generateButton; // Bouton pour générer les solutions
    TextField yTextField; // Champ de texte pour les coordonnées Y
    Label yLabel; // Label pour les coordonnées Y
    TextField xTextField; // Champ de texte pour les coordonnées X
    Label xLabel; // Label pour les coordonnées X
    Label coordinatesLabel; // Label pour indiquer de sélectionner les coordonnées
    Label titleLabel; // Label pour indiquer de sélectionner un scénario

    /**
     * Constructeur de la classe GridPaneRoot.
     * Initialise les éléments de l'interface graphique.
     */
    public GridPaneRoot() {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);

        titleLabel = new Label("Sélectionnez un scénario :");
        add(titleLabel, 0, 0);

        scenarioComboBox = new ComboBox<>();
        add(scenarioComboBox, 0, 1);

        // Charger les scénarios disponibles
        loadScenarios(scenarioComboBox);
        Scenario scenario = (Scenario) scenarioComboBox.getUserData();
        HBoxRoot.setScenario(scenario);

        coordinatesLabel = new Label("Sélectionnez les coordonnées :");
        add(coordinatesLabel, 0, 2);

        xLabel = new Label("x :");
        add(xLabel, 0, 3);

        xTextField = new TextField("0");
        add(xTextField, 1, 3);

        yLabel = new Label("y :");
        add(yLabel, 0, 4);

        yTextField = new TextField("0");
        add(yTextField, 1, 4);

        generateButton = new Button("Generer");
        generateButton.setId("Generer");
        generateButton.addEventHandler(ActionEvent.ACTION, HBoxRoot.getController());
        add(generateButton, 0, 5);
    }

    /**
     * Charge les scénarios disponibles dans la ComboBox.
     * @param scenarioComboBox la ComboBox pour les scénarios
     */
    private void loadScenarios(ComboBox<String> scenarioComboBox) {
        File scenariosDirectory = new File("scenarios");
        File[] files = scenariosDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    scenarioComboBox.getItems().add(file.getName());
                    // Charger le scénario correspondant et l'associer à la valeur de la combobox
                    Scenario scenario = ReadTextFile.read(new File("scenarios" + File.separator + file.getName()));
                    scenarioComboBox.setUserData(scenario);
                }
            }
        }
        // Définir le scénario par défaut
        if (!scenarioComboBox.getItems().isEmpty()) {
            scenarioComboBox.setValue(scenarioComboBox.getItems().get(0));
        }
    }

}
