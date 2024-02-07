package com.example.demo200000.window;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demo200000.SceneLoader;
import com.example.demo200000.enity.Student;
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

public class StudentsGroup {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Student, String> TrainerID_table;

    @FXML
    private TableColumn<Student, Student> TrainerID_table1;

    @FXML
    private Button add_button;

    @FXML
    private TableColumn<Student, String> age_table;

    @FXML
    private TableColumn<Student, String> age_table1;

    @FXML
    private Button back_button;

    @FXML
    private TextField colMax;

    @FXML
    private TextField colMin;

    @FXML
    private TextField student;

    @FXML
    private Button student_button;

    @FXML
    private TableView<Student> table;

    @FXML
    private TableView<Student> table1;


    AdminGroup group = new AdminGroup();
    int IdGrupp = group.getIdGroup();
    int max = group.getMaxsS();
    private int idGrupp;

    @FXML
    void initialize() {
        displayingDataInATable();
        displayingDataInATable1();

        colMax.setText(String.valueOf(max));

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Получение данных из ново выбранной строки
                String fullName = newSelection.getFullName();

                // Отображение данных в соответствующих текстовых полях или других элементах интерфейса
                student.setText(fullName);

            }
        });

        add_button.setOnAction(event -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();

            int id = selectedStudent.getId();
            int groupId = IdGrupp;
            String fullName = selectedStudent.getFullName();
            String phone = selectedStudent.getContactInformation();
            String date = selectedStudent.getDateOfBirth();
            String sportType = selectedStudent.getSportType();
            String log = selectedStudent.getLogin();

            // Создание объекта Student с обновленными данными
            Student updatedStudent = new Student(id, fullName, date, phone, groupId, sportType, log);

            // Отправка данных на сервер для обновления
            ServerConnectionStudent.updateStudentOnServer(updatedStudent);

            displayingDataInATable();
            displayingDataInATable1();
        });

        student_button.setOnAction(event -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();

            int id = selectedStudent.getId();
            int groupId = 1;
            String fullName = selectedStudent.getFullName();
            String phone = selectedStudent.getContactInformation();
            String date = selectedStudent.getDateOfBirth();
            String sportType = selectedStudent.getSportType();
            String log = selectedStudent.getLogin();

            // Создание объекта Student с обновленными данными
            Student updatedStudent = new Student(id, fullName, date, phone, groupId, sportType, log);

            // Отправка данных на сервер для обновления
            ServerConnectionStudent.updateStudentOnServer(updatedStudent);

            displayingDataInATable();
            displayingDataInATable1();
        });







        back_button.setOnAction(event -> {SceneLoader.loadNewScene("choice.fxml", back_button.getScene());});



    }

    public void displayingDataInATable() {
        List<Student> students = ServerConnectionStudent.getStudentsData();
        ObservableList<Student> data = FXCollections.observableArrayList(students);

        // Привязка полей TableView к свойствам объекта Student
        TrainerID_table.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        age_table.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        // Фильтрация данных для отображения только записей, где groupId совпадает с IdGrupp
        FilteredList<Student> filteredData = new FilteredList<>(data, student -> student.getGroupId() == IdGrupp);

        // Применение фильтра к TableView
        table.setItems(filteredData);
    }

    public void displayingDataInATable1() {
        List<Student> students = ServerConnectionStudent.getStudentsData();
        ObservableList<Student> data = FXCollections.observableArrayList(students);

        // Привязка полей TableView к свойствам объекта Student
        TrainerID_table1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        age_table1.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        // Фильтрация данных для отображения только записей, где groupId равен 1
        FilteredList<Student> filteredData1 = new FilteredList<>(data, student -> student.getGroupId() == 1);

        // Применение фильтра к TableView
        table1.setItems(filteredData1);
    }

}
