package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;

public class ControllerAuthorization {
    @FXML private ImageView reloadImageView;

    @FXML private Button loginButton;
    @FXML private Button cancelButton;

    @FXML private TextField numberTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField codeTextField;

    private final StringBuilder code = new StringBuilder();

    private final Random random = new Random();
    private final DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML
    void initialize() {
        passwordTextField.setDisable(true);
        codeTextField.setDisable(true);

        numberTextField.setOnAction(actionEvent -> {
            String number = numberTextField.getText().trim();
            try {
                passwordTextField.setDisable(!databaseHandler.checkNumber(number));
            }
            catch (SQLException | ClassNotFoundException throwables){
                throwables.printStackTrace();
            }
        });

        passwordTextField.setOnAction(actionEvent -> {
            String number = numberTextField.getText().trim();
            String password = passwordTextField.getText().trim();
                try {
                    codeTextField.setDisable(!databaseHandler.checkPassword(number, password));
                }
                catch (SQLException | ClassNotFoundException throwables){
                    throwables.printStackTrace();
                }
                generateCode();
        });

        codeTextField.setOnAction(actionEvent -> {
            if(code.toString().equals(codeTextField.getText().trim())) {
                logIn();
            }
        });

        cancelButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/authorization.fxml"));

        loginButton.setOnAction(actionEvent -> {
            if(code.toString().equals(codeTextField.getText().trim())) {
                logIn();
            }
        });

        reloadImageView.setOnMousePressed(mouseEvent -> generateCode());
    }

    private void logIn(){
        try {databaseHandler.setUser(numberTextField.getText().trim(), passwordTextField.getText().trim());}
        catch (SQLException | ClassNotFoundException throwables) {throwables.printStackTrace();}
        openOtherWindow("/sample/layout/desktop.fxml");
    }

    private void generateCode(){
        for (int i = 0; i < 6; i++) {
            if (random.nextBoolean()){
                code.append((char) (64 + random.nextInt(26)));
            }
            else {
                code.append((char) (97 + random.nextInt(26)));
            }
        }
        code.append((char) (33 + random.nextInt(15)));
        code.append((char) (48 + random.nextInt(10)));
        System.out.println(code);
    }

    private void openOtherWindow(String path){
        loginButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {loader.load();}
        catch (IOException ioException) {ioException.printStackTrace();}
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
