/*package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.database.StaffData;
import java.io.IOException;

public class ControllerRequests {
    @FXML private TextField idTextField;

    @FXML private Button deleteButton;
    @FXML private Button exitButton;

    @FXML private TableColumn<StaffData, Integer> idTC;
    @FXML private TableColumn<StaffData, Integer> idTravelTC;
    @FXML private TableColumn<StaffData, String> usernameTC;

    @FXML private TableView<StaffData> requestsTable;

    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML
    void initialize() {
        idTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        idTravelTC.setCellValueFactory(new PropertyValueFactory<>("idTravel"));
        usernameTC.setCellValueFactory(new PropertyValueFactory<>("username"));

        updateTable();

        deleteButton.setOnAction(actionEvent -> {
            try {
                databaseHandler.deleteRequest(Integer.parseInt(idTextField.getText()));
                updateTable();
            }catch (Exception exception){exception.printStackTrace();}
        });

        exitButton.setOnAction(actionEvent -> openDesktopWindow());
    }

    private void updateTable() {
        try {requestsTable.setItems(databaseHandler.returnStaff());}
        catch (Exception exception){exception.printStackTrace();}
    }

    private void openDesktopWindow(){
        exitButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/layout/desktop.fxml"));
        try {
            loader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
*/