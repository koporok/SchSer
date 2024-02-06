package com.example.demo200000.window;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import com.example.demo200000.SceneLoader;
import com.example.demo200000.additionalMethods.Recurring;
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

public class AdminApplications {

    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> fullName_table;
    @FXML
    private TableColumn<Student, String> phone_number_table;
    @FXML
    private TableColumn<Student, String> data_table;
    @FXML
    private TableColumn<Student, String> type_sport;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Sport;

    @FXML
    private Button back_button;

    @FXML
    private Button create;

    @FXML
    private TextField data;

    @FXML
    private Button dell_button;

    @FXML
    private TextField fullName_students;

    @FXML
    private TextField login;

    @FXML
    private TextField phone_number;
    @FXML
    private Button register;

    @FXML
    void initialize() {

        displayingDataInATable();

        dell_button.setOnAction(event -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                int studentId = selectedStudent.getId(); // Получение ID выбранного студента
                ServerConnectionStudent.deleteStudentFromServer(studentId); // Вызов метода для удаления студента с выбранным ID
            }
            displayingDataInATable();
            clearTextField();
        });

        // Установка слушателя событий для выбора строки в таблице
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Получение данных из ново выбранной строки
                String fullName = newSelection.getFullName();
                String phoneNumber = newSelection.getContactInformation();
                String date = newSelection.getDateOfBirth();
                int numberGroup = newSelection.getGroupId();
                String sportType = newSelection.getSportType();

                // Отображение данных в соответствующих текстовых полях или других элементах интерфейса
                fullName_students.setText(fullName);
                phone_number.setText(phoneNumber);
                data.setText(date);
                Sport.setText(sportType);

            }
        });

        back_button.setOnAction(event -> {
            SceneLoader.loadNewScene( "AdminStudent.fxml", back_button.getScene());
        });

        create.setOnAction(event -> {
            String randomCombination = Recurring.generateRandomCombination(10);
            login.setText(randomCombination);
        });

        register.setOnAction(event -> {
            // Получение данных из текстовых полей
            Student selectedStudent = table.getSelectionModel().getSelectedItem();

            int id = selectedStudent.getId();
            int groupId = selectedStudent.getGroupId();
            String fullName = fullName_students.getText();
            String phone = phone_number.getText();
            String date = data.getText();
            String sportType = Sport.getText();
            String log = login.getText();

            // Создание объекта Student с обновленными данными
            Student updatedStudent = new Student(id, fullName, date, phone, groupId, sportType, log);

            // Отправка данных на сервер для обновления
            ServerConnectionStudent.updateStudentOnServer(updatedStudent);

            displayingDataInATable();
        });

    }

    public void displayingDataInATable() {
        List<Student> students = ServerConnectionStudent.getStudentsData();

        // Создание ObservableList из студентов
        ObservableList<Student> data = FXCollections.observableArrayList(students);

        // Привязка полей TableView к свойствам объекта Student
        fullName_table.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phone_number_table.setCellValueFactory(new PropertyValueFactory<>("contactInformation"));
        data_table.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        type_sport.setCellValueFactory(new PropertyValueFactory<>("sportType"));

        // Фильтрация данных для отображения только записей, где login не равен null
        FilteredList<Student> filteredData = new FilteredList<>(data, student -> student.getLogin() == "null");

        // Применение фильтра к TableView
        table.setItems(filteredData);
    }

    private void clearTextField() {
        fullName_students.clear();
        phone_number.clear();
        data.clear();
        Sport.clear();
        login.clear();

    }
}
