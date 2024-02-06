package com.example.demo200000.window;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demo200000.SceneLoader;
import com.example.demo200000.enity.Groups;
import com.example.demo200000.enity.Student;
import com.example.demo200000.server.ServerConnectionGroups;
import com.example.demo200000.server.ServerConnectionStudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminGroup {

    @FXML
    private TableView<Groups> table;
    @FXML
    private TableColumn<Groups, String> Group_number_table;
    @FXML
    private TableColumn<Groups, Integer> TrainerID_table;
    @FXML
    private TableColumn<Groups, String> age_table;
    @FXML
    private TableColumn<Groups, String> sport_table;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Group_number;

    @FXML
    private TextField TrainerID;

    @FXML
    private TextField agg;

    @FXML
    private Button back_button;

    @FXML
    private TextField colMax;

    @FXML
    private TextField colMin;

    @FXML
    private Button create_button;

    @FXML
    private Button schedule_button;

    @FXML
    private TextField sport;
    @FXML
    private Button student_button;

    @FXML
    void initialize() {
        displayingDataInATable();

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Получение данных из ново выбранной строки
                String name = newSelection.getGroupname();
                int Coaches = newSelection.getCoachesid();
                String Agg = newSelection.getAage();
                int Maxs = newSelection.getMaxstudents();
                String Sport = newSelection.getSporttype();

                // Отображение данных в соответствующих текстовых полях или других элементах интерфейса
                Group_number.setText(name);
                TrainerID.setText(String.valueOf(Coaches));
                agg.setText(Agg);
                sport.setText(String.valueOf(Sport));
                colMax.setText(String.valueOf(Maxs));

            }
        });

        create_button.setOnAction(event -> {
            // Получение данных из текстовых полей
            Groups selectedGroups = table.getSelectionModel().getSelectedItem();

            int id = selectedGroups.getGroupid();
            String Name = Group_number.getText();
            int trainer = Integer.parseInt(TrainerID.getText());
            String Agg = agg.getText();
            String Sport = sport.getText();
            int ColMax = Integer.parseInt(colMax.getText());

            // Создание объекта Student с обновленными данными
            Groups updatedGroups = new Groups(id, Name, Sport, Agg, ColMax, trainer);

            // Отправка данных на сервер для обновления
            ServerConnectionGroups.updateGroupsOnServer(updatedGroups);

            displayingDataInATable();
        });

        back_button.setOnAction(event -> {
            SceneLoader.loadNewScene("choice.fxml", back_button.getScene());});
    }

    public void displayingDataInATable() {
        List<Groups> students = ServerConnectionGroups.getGroupsData();

        // Создание ObservableList из студентов
        ObservableList<Groups> data = FXCollections.observableArrayList(students);

        // Привязка полей TableView к свойствам объекта Student
        Group_number_table.setCellValueFactory(new PropertyValueFactory<>("groupname"));
        TrainerID_table.setCellValueFactory(new PropertyValueFactory<>("coachesid"));
        age_table.setCellValueFactory(new PropertyValueFactory<>("aage"));
        sport_table.setCellValueFactory(new PropertyValueFactory<>("sporttype"));

        // Применение фильтра к TableView
        table.setItems(data);
    }

}
