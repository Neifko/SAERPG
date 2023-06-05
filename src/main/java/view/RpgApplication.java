package view;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.VBoxAffichageScenario;

import java.io.File;

public class RpgApplication extends Application {
    /**
     * Méthode start de l'application.
     * Elle crée la scène principale et affiche la fenêtre.
     * @param stage le stage de l'application
     */
    @Override
    public void start(Stage stage) {
        BorderPane rootPane = new BorderPane();
        rootPane.setPadding(new Insets(10));

        GridPaneRoot gridPaneRoot = new GridPaneRoot();
        VBoxAffichageScenario vboxAffichageScenario = new VBoxAffichageScenario();

        rootPane.setCenter(gridPaneRoot);
        rootPane.setRight(vboxAffichageScenario);

        Scene scene = new Scene(rootPane, 800, 500);

        // Chargement des fichiers CSS depuis le dossier "css"
        File[] fichiersCss = new File("css").listFiles();
        if (fichiersCss != null) {
            for (File fichier : fichiersCss) {
                scene.getStylesheets().add(fichier.toURI().toString());
            }
        }

        stage.setTitle("RPG SAE IHM");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode principale de l'application.
     * Elle lance l'application JavaFX.
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch();
    }
}
