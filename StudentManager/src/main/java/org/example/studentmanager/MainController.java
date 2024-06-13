package org.example.studentmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> majorColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField majorField;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    private DBConnectivity databaseConnectivity = new DBConnectivity();

    @FXML
    private void initialize() {

        // Initialize the table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));

        loadStudentsFromDatabase();
    }


    // Handle Add, Update and Delete operations
    @FXML
    private void handleAdd() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String major = majorField.getText();

            String insertQuery = "INSERT INTO students_table (ID, Name, Email, Major) VALUES (?, ?, ?, ?)";

            try (Connection conn = databaseConnectivity.getConnection();
                 PreparedStatement ps = conn.prepareStatement(insertQuery)) {
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, major);
                ps.executeUpdate();
            }

            loadStudentsFromDatabase(); // Reload the data from the database
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not add student to the database.");
        }
    }

    //Handle Update
    @FXML
    private void handleUpdate() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String major = majorField.getText();

                String updateQuery = "UPDATE students_table SET Name = ?, Email = ?, Major = ? WHERE ID = ?";

                try (Connection conn = databaseConnectivity.getConnection();
                     PreparedStatement ps = conn.prepareStatement(updateQuery)) {
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, major);
                    ps.setInt(4, id);
                    ps.executeUpdate();
                }

                loadStudentsFromDatabase(); // Reload the data from the database
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter a valid ID.");
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Database Error", "Could not update student in the database.");
            }
        } else {
            showAlert("No Selection", "Please select a student to update.");
        }
    }

    //Handle Delete
    @FXML
    private void handleDelete() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            try {
                String deleteQuery = "DELETE FROM students_table WHERE ID = ?";

                try (Connection conn = databaseConnectivity.getConnection();
                     PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
                    ps.setInt(1, selectedStudent.getId());
                    ps.executeUpdate();
                }

                loadStudentsFromDatabase(); // Reload the data from the database
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Database Error", "Could not delete student from the database.");
            }
        } else {
            showAlert("No Selection", "Please select a student to delete.");
        }
    }

    // Load students from the database
    private void loadStudentsFromDatabase() {
        studentList.clear();
        String selectQuery = "SELECT * FROM students_table";

        try (Connection conn = databaseConnectivity.getConnection();
             PreparedStatement ps = conn.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String major = rs.getString("Major");
                studentList.add(new Student(id, name, email, major));
            }

            studentTable.setItems(studentList);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not load students from the database.");
        }
    }

    //Clear Input Fields
    private void clearFields() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        majorField.clear();
    }

    //Show Alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
