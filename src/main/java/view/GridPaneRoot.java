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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        // Sélectionner le scénario par défaut
        scenarioComboBox.setValue(scenarioComboBox.getItems().get(0));

        // Écouter les changements de sélection de la ComboBox
        scenarioComboBox.setOnAction(this::handleScenarioSelection);

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
        List<String> scenarioNames = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String scenarioName = file.getName();
                    scenarioNames.add(scenarioName);
                }
            }
        }

        // Tri des noms de scénarios dans l'ordre numérique
        Collections.sort(scenarioNames, (s1, s2) -> {
            int scenarioNumber1 = extractScenarioNumber(s1);
            int scenarioNumber2 = extractScenarioNumber(s2);
            return Integer.compare(scenarioNumber1, scenarioNumber2);
        });

        // Ajouter les scénarios triés à la ComboBox
        scenarioComboBox.getItems().addAll(scenarioNames);

        // Définir le scénario par défaut
        if (!scenarioComboBox.getItems().isEmpty()) {
            scenarioComboBox.setValue(scenarioComboBox.getItems().get(0));
            String defaultScenarioName = scenarioComboBox.getValue();
            Scenario defaultScenario = ReadTextFile.read(new File("scenarios" + File.separator + defaultScenarioName));
            HBoxRoot.setScenario(defaultScenario);
        }
    }

    /**
     * Extrait le numéro de scénario à partir du nom du fichier.
     * Par exemple, pour le fichier "scenario_3.txt", le numéro de scénario est 3.
     * @param scenarioFileName le nom du fichier de scénario
     * @return le numéro de scénario extrait
     */
    private int extractScenarioNumber(String scenarioFileName) {
        try {
            String scenarioNumber = scenarioFileName.replace("scenario_", "").replace(".txt", "");
            return Integer.parseInt(scenarioNumber);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; // Valeur maximale si le numéro de scénario n'est pas un entier valide
        }
    }

    /**
     * Gère la sélection d'un scénario dans la ComboBox.
     * @param event l'événement de sélection
     */
    private void handleScenarioSelection(ActionEvent event) {
        String selectedScenarioName = scenarioComboBox.getValue();
        File selectedScenarioFile = new File("scenarios" + File.separator + selectedScenarioName);
        Scenario selectedScenario = ReadTextFile.read(selectedScenarioFile);
        HBoxRoot.setScenario(selectedScenario);
    }

}
