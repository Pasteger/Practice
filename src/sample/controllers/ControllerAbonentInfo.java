package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.database.AbonentsData;
import sample.database.User;

public class ControllerAbonentInfo {
    @FXML private Label addressLabel;
    @FXML private Label contractNumberLabel;
    @FXML private Label dateOfConclusionOfTheContractLabel;
    @FXML private Label dateOfIssueLabel;
    @FXML private Label dateOfTerminationOfTheContractLabel;
    @FXML private Label historyLabel;
    @FXML private Label issuedByLabel;
    @FXML private Label nameLabel;
    @FXML private Label numberLabel;
    @FXML private Label passportNumberLabel;
    @FXML private Label passportSeriesLabel;
    @FXML private Label personalAccountLabel;
    @FXML private Label reasonForTerminationOfTheContractLabel;
    @FXML private Label serialNumberOfTheEquipmentLabel;
    @FXML private Label servicesLabel;
    @FXML private Label typeContractLabel;

    AbonentsData abonent = User.getAbonentForAbonentInfo();

    @FXML
    void initialize() {
        numberLabel.setText(abonent.getNumber());
        nameLabel.setText(abonent.getName());

        String[] passport = abonent.getPassportSeriesAndNumber().split(" ");
        passportSeriesLabel.setText(passport[0]);
        passportNumberLabel.setText(passport[1]);

        dateOfIssueLabel.setText(abonent.getDateOfIssue());
        issuedByLabel.setText(abonent.getIssuedBy());
        contractNumberLabel.setText(abonent.getContractNumber());
        dateOfConclusionOfTheContractLabel.setText(abonent.getDateOfConclusionOfTheContract());
        typeContractLabel.setText(abonent.getTypeContract());
        dateOfTerminationOfTheContractLabel.setText(abonent.getDateOfTerminationOfTheContract());
        reasonForTerminationOfTheContractLabel.setText(abonent.getReasonForTerminationOfTheContract());
        personalAccountLabel.setText(abonent.getPersonalAccount());
        addressLabel.setText(abonent.getAddressRegistration() + " " + abonent.getAddressResidential());
        servicesLabel.setText(abonent.getServices() + ", " + abonent.getServices_0() + ", " + abonent.getServices_1());
        serialNumberOfTheEquipmentLabel.setText(abonent.getSerialNumberOfTheEquipment());
        historyLabel.setText("");
    }
}
