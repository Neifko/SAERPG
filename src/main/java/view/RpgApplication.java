package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class RpgApplication extends Application {
    /**
     * Méthode start de l'application.
     * Elle crée la scène principale et affiche la fenêtre.
     * @param stage le stage de l'application
     */
    @Override
    public void start(Stage stage) {
        HBoxRoot root = new HBoxRoot();
        Scene scene = new Scene(root, 1000, 550);
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
