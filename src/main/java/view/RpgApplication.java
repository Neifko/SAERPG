package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class RpgApplication extends Application {
    @Override
    public void start(Stage stage) {
        GridPaneRoot gridPaneRoot = new GridPaneRoot();
        Scene scene = new Scene(gridPaneRoot, 500, 300);

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

    public static void main(String[] args) {
        launch();
    }
}


