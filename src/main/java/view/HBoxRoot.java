package view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.VBoxAffichageScenario;

public class HBoxRoot extends HBox {
    private GridPane gridPaneRoot;
    private VBoxAffichageScenario vBoxAffichageScenario;

    public HBoxRoot() {
        // Créer le GridPane existant
        gridPaneRoot = new GridPane();
        // Ajouter les éléments au GridPane
        // ...

        // Créer l'instance de VBoxAffichageScenario
        vBoxAffichageScenario = new VBoxAffichageScenario();

        // Configurer les marges et l'espacement
        setSpacing(10);
        setPadding(new Insets(10));

        // Ajouter les éléments à l'HBox
        getChildren().addAll(gridPaneRoot, vBoxAffichageScenario);
    }

    // Ajoutez des méthodes pour accéder aux éléments de votre HBoxRoot si nécessaire

}

