package com.example.demo200000.window;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.demo200000.SceneLoader;
import com.example.demo200000.additionalMethods.Recurring;
import com.example.demo200000.enity.Coaches;
import com.example.demo200000.server.ServerConnectionTrainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AdminTrainer {

    @FXML

    private TableView<Coaches> table;
    @FXML
    private TableColumn<Coaches, String> fioFullname_table;
    @FXML
    private TableColumn<Coaches, String> number_table;
    @FXML
    private TableColumn<Coaches, String> login_table;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea Information;

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @FXML
    private Button dell_button;

    @FXML
    private TextField fioFullname_trainer;

    @FXML
    private Button login_button;

    @FXML
    private TextField login_trainer;

    @FXML
    private TextField number_trainer;

    @FXML
    void setBack_button(MouseEvent event) {

    }

    @FXML
    void initialize() {

        displayingDataInATable();

        // Установка слушателя событий для выбора строки в таблице
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Получение данных из ново выбранной строки
                String fullName = newSelection.getFull_name();
                String phoneNumber = newSelection.getContact_information();
                String log = newSelection.getLogin();
                String information = newSelection.getInformation();

                // Отображение данных в соответствующих текстовых полях или других элементах интерфейса
                fioFullname_trainer.setText(fullName);
                number_trainer.setText(phoneNumber);
                login_trainer.setText(log);
                Information.setText(information);

            }
        });

        dell_button.setOnAction(event -> {
            Coaches selectedCoaches = table.getSelectionModel().getSelectedItem();
            if (selectedCoaches != null) {
                int studentId = selectedCoaches.getId(); // Получение ID выбранного студента
                ServerConnectionTrainer.deleteCoachesFromServer(studentId); // Вызов метода для удаления студента с выбранным ID
            }
            displayingDataInATable();
            clearTextField();
        });

        add_button.setOnAction(event -> {

            String fullName = fioFullname_trainer.getText();
            String phone = number_trainer.getText();
            String log = login_trainer.getText();
            String information = Information.getText();

            // Создание объекта Student с обновленными данными
            Coaches updatedCoaches= new Coaches(fullName, phone, log, information);

            // Отправка данных на сервер для обновления
            ServerConnectionTrainer.updateCoachesOnServer(updatedCoaches);

            displayingDataInATable();
            clearTextField();
        });

        login_button.setOnAction(event -> {
            String randomCombination = Recurring.generateRandomCombination(5);
            login_trainer.setText("CoaCheS" + randomCombination);
        });

        back_button.setOnAction(event -> {
            SceneLoader.loadNewScene("choice.fxml", back_button.getScene());});

    }

    public void displayingDataInATable() {
        List<Coaches> coaches = ServerConnectionTrainer.getCoachesData();

        // Создание ObservableList из студентов
        ObservableList<Coaches> data = FXCollections.observableArrayList(coaches);

        // Привязка полей TableView к свойствам объекта Student
        fioFullname_table.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        number_table.setCellValueFactory(new PropertyValueFactory<>("contact_information"));
        login_table.setCellValueFactory(new PropertyValueFactory<>("login"));

        // Применение фильтра к TableView
        table.setItems(data);
    }

    private void clearTextField() {
        fioFullname_trainer.clear();
        number_trainer.clear();
        login_trainer.clear();
        Information.clear();

    }
}
