/*/package sample.controllers;

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
import java.io.IOException;
import java.sql.SQLException;

public class ControllerAddRecord {
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
    AbonentsData abonentsData = new AbonentsData();

    boolean nameSet;
    boolean typeSet;
    boolean priceSet;
    boolean dateSet;
    boolean durationSet;
    boolean descriptionSet;

    @FXML void initialize() {
        applyButton.setOnAction(actionEvent -> {
            try {
                errorLabel.setText("");

                String name = nameTextField.getText();
                String type = typeTextField.getText();
                String price = priceTextField.getText();
                String date = dateTextField.getText();
                String duration = durationTextField.getText();
                String description = descriptionTextField.getText();

                if(!name.equals("")){
                    abonentsData.setName(name);
                    nameSet = true;
                }
                if (!type.equals("")) {
                    abonentsData.setType(type);
                    typeSet = true;
                }
                if (!price.equals("")) {
                    abonentsData.setPrice(price);
                    priceSet = true;
                }
                if (!date.equals("")) {
                    abonentsData.setDate(date);
                    dateSet = true;
                }
                if (!duration.equals("")) {
                    abonentsData.setDuration(duration);
                    durationSet = true;
                }
                if (!description.equals("")) {
                    abonentsData.setDescription(description);
                    descriptionSet = true;
                }

                if (nameSet && typeSet && priceSet && dateSet && durationSet && descriptionSet) {
                    databaseHandler.addRecord(abonentsData);
                    openDesktopWindow();
                }
                else {
                    errorLabel.setText("Не все поля заполнены");
                }
            }
            catch(SQLException | ClassNotFoundException throwables){
                throwables.printStackTrace();
            }
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
