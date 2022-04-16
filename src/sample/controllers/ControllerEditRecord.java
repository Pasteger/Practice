/*package sample.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.database.AbonentsData;
import sample.database.User;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerEditRecord {
    @FXML private Label errorLabel;

    @FXML private TextField nameTextField;
    @FXML private TextField typeTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField dateTextField;
    @FXML private TextField durationTextField;
    @FXML private TextField descriptionTextField;

    @FXML private Button applyButton;
    @FXML private Button cancelButton;

    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<AbonentsData> apartmentsObservableList = databaseHandler.returnAbonents(
                "SELECT * FROM travel WHERE id=" + User.getEditedRecordId()
        );
        AbonentsData abonentsData = apartmentsObservableList.get(0);

        nameTextField.setText(abonentsData.getName());
        typeTextField.setText(abonentsData.getType());
        priceTextField.setText(abonentsData.getPrice());
        dateTextField.setText(abonentsData.getDate());
        durationTextField.setText(abonentsData.getDuration());
        descriptionTextField.setText(abonentsData.getDescription());

        applyButton.setOnAction(actionEvent -> {

            String name = nameTextField.getText();
            String type = typeTextField.getText();
            String price = priceTextField.getText();
            String date = dateTextField.getText();
            String duration = durationTextField.getText();
            String description = descriptionTextField.getText();

            if (!name.equals("")){
                abonentsData.setType(name);
            }
            if (!type.equals("")){
                abonentsData.setType(type);
            }
            if (!price.equals("")){
                abonentsData.setPrice(price);
            }
            if (!date.equals("")){
                abonentsData.setDate(date);
            }
            if (!duration.equals("")){
                abonentsData.setDuration(duration);
            }
            if (!description.equals("")){
                abonentsData.setDescription(description);
            }

            try {databaseHandler.updateRecord(abonentsData);}
            catch (SQLException | ClassNotFoundException throwables) {
                errorLabel.setText("Не все поля заполнены");
                throwables.printStackTrace();
            }
            openDesktopWindow();
        });

        cancelButton.setOnAction(actionEvent -> openDesktopWindow());
    }

    private void openDesktopWindow(){
        cancelButton.getScene().getWindow().hide();
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