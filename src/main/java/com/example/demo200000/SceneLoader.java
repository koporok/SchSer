package com.example.demo200000;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {
    public static void loadNewScene(String fxml, Scene currentScene) {
        try {
            String xy = fxml;
            FXMLLoader fxmlLoader = new FXMLLoader(SceneLoader.class.getResource(fxml));
            Scene root = new Scene(fxmlLoader.load());

            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(root);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
