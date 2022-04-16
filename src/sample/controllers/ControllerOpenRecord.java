/*package sample.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.database.AbonentsData;
import sample.database.User;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerOpenRecord {
    @FXML private Label nameLabel;
    @FXML private Label typeLabel;
    @FXML private Label priceLabel;
    @FXML private Label dateLabel;
    @FXML private Label durationLabel;
    @FXML private Label descriptionLabel;
    @FXML private Button backButton;
    @FXML private Button bookButton;
    DatabaseHandler databaseHandler = new DatabaseHandler();
    @FXML void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<AbonentsData> apartmentsObservableList = databaseHandler.returnAbonents(
                "SELECT * FROM travel WHERE id=" + User.getEditedRecordId()
        );
        AbonentsData abonentsData = apartmentsObservableList.get(0);

        nameLabel.setText(abonentsData.getName());
        typeLabel.setText(abonentsData.getType());
        priceLabel.setText(abonentsData.getPrice());
        dateLabel.setText(abonentsData.getDate());
        durationLabel.setText(abonentsData.getDuration());
        descriptionLabel.setText(abonentsData.getDescription());

        bookButton.setOnAction(actionEvent -> {
            try {databaseHandler.addRequests(User.getEditedRecordId(), User.getRole());}
            catch (SQLException | ClassNotFoundException throwables){throwables.printStackTrace();}
        });

        backButton.setOnAction(actionEvent -> openDesktopWindow());
    }

    private void openDesktopWindow(){
        backButton.getScene().getWindow().hide();
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