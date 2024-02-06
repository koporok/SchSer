package com.example.demo200000.window;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo200000.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class choice {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button groups_button;

    @FXML
    private Button students_button;

    @FXML
    private Button trainer_button;

    @FXML
    void initialize() {

        trainer_button.setOnAction(event -> {SceneLoader.loadNewScene("AdminTrainer.fxml", trainer_button.getScene());});

        students_button.setOnAction(event -> {SceneLoader.loadNewScene("AdminStudent.fxml", students_button.getScene());});

        groups_button.setOnAction(event -> {SceneLoader.loadNewScene("AdminGroup.fxml", groups_button.getScene());});


    }

}
