package sample.controllers;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import java.sql.SQLException;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.*;
import javafx.fxml.FXML;
import java.io.File;
import java.util.*;

public class ControllerDesktop {
    @FXML private AnchorPane verticalMenuWideAnchorPane;
    @FXML private AnchorPane verticalMenuNarrowAnchorPane;
    @FXML private AnchorPane abonentsAnchorPane;
    @FXML private AnchorPane controlDevisesAnchorPane;
    @FXML private AnchorPane activesAnchorPane;
    @FXML private AnchorPane billingAnchorPane;
    @FXML private AnchorPane supportUsersAnchorPane;
    @FXML private AnchorPane crmAnchorPane;

    @FXML private ComboBox<String> userComboBox;
    @FXML private ImageView userAvatarImageView;

    DatabaseHandler databaseHandler = new DatabaseHandler();

    //Vertical menu
    @FXML private Button abonentsButton;
    @FXML private Button controlDevisesButton;
    @FXML private Button activesButton;
    @FXML private Button billingButton;
    @FXML private Button supportUsersButton;
    @FXML private Button crmButton;
    @FXML private Button questionButtonWide;
    @FXML private Button questionButtonNarrow;
    @FXML private Button expandButton;
    @FXML private Button wrapButton;

    @FXML private ImageView abonentsImageView;
    @FXML private ImageView controlDevisesImageView;
    @FXML private ImageView activesImageView;
    @FXML private ImageView billingImageView;
    @FXML private ImageView supportUsersImageView;
    @FXML private ImageView crmImageView;

    //Abonents workspace
    @FXML private TableView<AbonentsData> abonentsTable;
    @FXML private TableColumn<AbonentsData, String> numberTC;
    @FXML private TableColumn<AbonentsData, String> nameTC;
    @FXML private TableColumn<AbonentsData, String> contractNumberTC;
    @FXML private TableColumn<AbonentsData, String> personalAccountTC;
    @FXML private TableColumn<AbonentsData, String> servicesTC;

    @FXML private CheckBox activeCheckBox;
    @FXML private CheckBox notActiveCheckBox;

    private boolean insertActiveAbonents;
    private boolean insertNotActiveAbonents;

    //CRM workspace
    @FXML private Label telephoneNumberLabel;
    @FXML private Label nameLabel;
    @FXML private Label numberRequestLabel;
    @FXML private Label dateCreateRequestLabel;
    @FXML private Label personalAccountLabel;
    @FXML private Label numberAbonentLabel;
    @FXML private Label equipmentTypeLabel;
    @FXML private Label problemTypeLabel;
    @FXML private Label problemDescriptionLabel;
    @FXML private Label closeDateLabel;
    @FXML private Label serviceLabel;
    @FXML private Label serviceStatusLabel;
    @FXML private Label genusServiceLabel;
    @FXML private Label serviceTypeLabel;

    @FXML private TextField findByNameTextField;
    @FXML private TextField findByTelephoneNumberTextField;
    @FXML private TextField numberRequestTextField;
    @FXML private TextField dateCreateRequestTextField;
    @FXML private TextField numberAbonentTextField;
    @FXML private TextField personalAccountTextField;
    @FXML private TextField equipmentTypeTextField;
    @FXML private TextField problemTypeTextField;
    @FXML private TextField problemDescriptionTextField;
    @FXML private TextField closeDateTextField;

    @FXML private Button findAbonentButton;
    @FXML private Button cancelCreateRequestButton;
    @FXML private Button applyCreateRequestButton;

    @FXML private ComboBox<String> serviceComboBox;
    @FXML private ComboBox<String> serviceStatusComboBox;
    @FXML private ComboBox<String> serviceGenusComboBox;
    @FXML private ComboBox<String> serviceTypeComboBox;

    ObservableList<String> services = FXCollections.observableArrayList(
            "Интернет", "Мобильная связь", "Телевидение", "Видеонаблюдение");
    ObservableList<String> serviceStatus = FXCollections.observableArrayList(
            "Новая", "Требует выезда", "Закрыта");
    ObservableList<String> serviceGenus = FXCollections.observableArrayList(
            "Подключение", "Управление договором/контактными данными", "Управление тарифом/услугой",
            "Диагностика и настройка оборудования/подключения", "Оплата услуг");

    Map<String, ObservableList<String>> servicesTypeByServicesGenus = new HashMap<>();

    Calendar calendar = new GregorianCalendar();

    AbonentsData abonentForCRM;

    @FXML
    void initialize() {
        abonentsAnchorPane.setVisible(true);
        controlDevisesAnchorPane.setVisible(false);
        activesAnchorPane.setVisible(false);
        billingAnchorPane.setVisible(false);
        supportUsersAnchorPane.setVisible(false);
        crmAnchorPane.setVisible(false);

        //Vertical menu
        verticalMenuWideAnchorPane.setVisible(true);
        verticalMenuNarrowAnchorPane.setVisible(false);

        //Wide and narrow vertical menu
        wrapButton.setOnAction(actionEvent -> {
            verticalMenuWideAnchorPane.setVisible(true);
            verticalMenuNarrowAnchorPane.setVisible(false);
        });
        expandButton.setOnAction(actionEvent -> {
            verticalMenuWideAnchorPane.setVisible(false);
            verticalMenuNarrowAnchorPane.setVisible(true);
        });

        //Operations of the vertical menu buttons
        abonentsButton.setOnAction(actionEvent -> showAnchorPane("abonentsAnchorPane"));
        abonentsImageView.setOnMousePressed(mouseEvent -> showAnchorPane("abonentsAnchorPane"));
        controlDevisesButton.setOnAction(actionEvent -> showAnchorPane("controlDevisesAnchorPane"));
        controlDevisesImageView.setOnMousePressed(mouseEvent -> showAnchorPane("controlDevisesAnchorPane"));
        activesButton.setOnAction(actionEvent -> showAnchorPane("activesAnchorPane"));
        activesImageView.setOnMousePressed(mouseEvent -> showAnchorPane("activesAnchorPane"));
        billingButton.setOnAction(actionEvent -> showAnchorPane("billingAnchorPane"));
        billingImageView.setOnMousePressed(mouseEvent -> showAnchorPane("billingAnchorPane"));
        supportUsersButton.setOnAction(actionEvent -> showAnchorPane("supportAnchorPane"));
        supportUsersImageView.setOnMousePressed(mouseEvent -> showAnchorPane("supportAnchorPane"));
        crmButton.setOnAction(actionEvent -> showAnchorPane("crmAnchorPane"));
        crmImageView.setOnMousePressed(mouseEvent -> showAnchorPane("crmAnchorPane"));


        //Abonents workspace
        //TableColumns
        numberTC.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        contractNumberTC.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
        personalAccountTC.setCellValueFactory(new PropertyValueFactory<>("personalAccount"));
        servicesTC.setCellValueFactory(new PropertyValueFactory<>("services"));

        updateAbonentsTable("SELECT * FROM abonents");

        //Combo box
        try {
            ObservableList<StaffData> staffData = databaseHandler.returnStaff("SELECT * FROM staff");
            ObservableList<String> names = FXCollections.observableArrayList();
            Map<String, String> idByName = new HashMap<>();
            Map<String, String> roleByName = new HashMap<>();

            for (StaffData staff : staffData){
                names.add(staff.getName());
                idByName.put(staff.getName(), staff.getId());
                roleByName.put(staff.getName(), staff.getRole());
            }
            userComboBox.setItems(names);

            User.setUsername(names.get(0));
            User.setId(idByName.get(User.getUsername()));
            User.setRole(roleByName.get(User.getUsername()));
            userComboBox.setValue(User.getUsername());
            changeAccessLevel(User.getRole());
            setAvatarImage();

            userComboBox.setOnAction(event -> {
                User.setUsername(userComboBox.getValue());
                User.setId(idByName.get(userComboBox.getValue()));
                User.setRole(roleByName.get(userComboBox.getValue()));
                changeAccessLevel(User.getRole());
                setAvatarImage();
            });
        }
        catch (SQLException | ClassNotFoundException throwables) {throwables.printStackTrace();}

        //Choice abonent
        abonentsTable.setOnMousePressed(me -> {
            if(abonentsTable.getSelectionModel().getSelectedItem() != null){
                try {
                    String abonentNumber = abonentsTable.getSelectionModel().getSelectedItem().getNumber();
                    String select = "SELECT * FROM abonents where Number = \"" + abonentNumber + "\"";
                    ObservableList<AbonentsData> abonent = databaseHandler.returnAbonents(select);
                    User.setAbonentForAbonentInfo(abonent.get(0));
                    openAbonentInfoWindow();
                }
                catch (Exception exception){
                    System.out.println("abonent not found");
                }
                abonentsTable.getSelectionModel().clearSelection();
            }
        });

        //Show active and not active abonents
        activeCheckBox.setOnAction(actionEvent -> {
            if(insertNotActiveAbonents && !insertActiveAbonents){
                insertActiveAbonents = true;
                updateAbonentsTable("SELECT * FROM abonents");
            }
            else if (insertNotActiveAbonents){
                insertActiveAbonents = false;
                updateAbonentsTable("SELECT * FROM abonents WHERE DateOfTerminationOfTheContract != ''");
            }
            else if (!insertActiveAbonents){
                insertActiveAbonents = true;
                updateAbonentsTable("SELECT * FROM abonents WHERE DateOfTerminationOfTheContract = ''");
            }
            else {
                insertActiveAbonents = false;
                updateAbonentsTable("SELECT * FROM abonents");
            }
        });
        notActiveCheckBox.setOnAction(actionEvent -> {
            if(insertNotActiveAbonents && !insertActiveAbonents){
                insertNotActiveAbonents = false;
                updateAbonentsTable("SELECT * FROM abonents");
            }
            else if (insertNotActiveAbonents){
                insertNotActiveAbonents = false;
                updateAbonentsTable("SELECT * FROM abonents WHERE DateOfTerminationOfTheContract = ''");
            }
            else if (!insertActiveAbonents){
                insertNotActiveAbonents = true;
                updateAbonentsTable("SELECT * FROM abonents WHERE DateOfTerminationOfTheContract != ''");
            }
            else {
                insertNotActiveAbonents = true;
                updateAbonentsTable("SELECT * FROM abonents");
            }
        });


        //CRM workspace
        //Preparation for serviceTypeComboBox
        servicesTypeByServicesGenus.put("Подключение",
                FXCollections.observableArrayList(
                        "Подключение услуг с новой инфраструктурой",
                        "Подключение услуг на существующей инфраструктуре"));
        servicesTypeByServicesGenus.put("Управление договором/контактными данными",
                FXCollections.observableArrayList(
                        "Изменение условий договора",
                        "Включение в договор дополнительной услуги",
                        "Изменение контактных данных"));
        servicesTypeByServicesGenus.put("Управление тарифом/услугой",
                FXCollections.observableArrayList(
                        "Изменение тарифа",
                        "Изменение адреса предоставления услуг",
                        "Отключение услуги",
                        "Приостановка предоставления услуги"));
        servicesTypeByServicesGenus.put("Диагностика и настройка оборудования/подключения",
                FXCollections.observableArrayList(
                        "Нет доступа к услуге",
                        "Разрыв соединения",
                        "Низкая скорость соединения"));
        servicesTypeByServicesGenus.put("Оплата услуг",
                FXCollections.observableArrayList(
                        "Выписка по платежам",
                        "Информация о платежах",
                        "Получение квитанции на оплату услуги"));

        //Find abonent
        findAbonentButton.setOnAction(actionEvent -> {
            try {
            String select = "SELECT * FROM abonents where Name like '" +
                            findByNameTextField.getText().trim() + "%' AND TelephoneNumber = '" +
                            findByTelephoneNumberTextField.getText().trim() + "'";
                ObservableList<AbonentsData> abonent = databaseHandler.returnAbonents(select);
                abonentForCRM = abonent.get(0);

                findAbonentButton.setVisible(false);
                cancelCreateRequestButton.setVisible(true);
                applyCreateRequestButton.setVisible(true);

                findByNameTextField.setText(abonentForCRM.getName());
                numberRequestTextField.setVisible(true);
                dateCreateRequestTextField.setVisible(true);
                findByTelephoneNumberTextField.setDisable(true);
                findByNameTextField.setDisable(true);
                personalAccountTextField.setVisible(true);
                numberAbonentTextField.setVisible(true);
                equipmentTypeTextField.setVisible(true);
                problemTypeTextField.setVisible(true);
                problemDescriptionTextField.setVisible(true);
                closeDateTextField.setVisible(true);

                telephoneNumberLabel.setVisible(true);
                nameLabel.setVisible(true);
                numberRequestLabel.setVisible(true);
                dateCreateRequestLabel.setVisible(true);
                personalAccountLabel.setVisible(true);
                numberAbonentLabel.setVisible(true);
                equipmentTypeLabel.setVisible(true);
                problemTypeLabel.setVisible(true);
                problemDescriptionLabel.setVisible(true);
                closeDateLabel.setVisible(true);
                serviceLabel.setVisible(true);
                serviceStatusLabel.setVisible(true);
                genusServiceLabel.setVisible(true);
                serviceTypeLabel.setVisible(true);

                serviceComboBox.setVisible(true);
                serviceStatusComboBox.setVisible(true);
                serviceGenusComboBox.setVisible(true);
                serviceTypeComboBox.setVisible(true);

                setCRMComboBox();
                numberRequestTextField.setText(
                        abonentForCRM.getPersonalAccount() + "/" +
                        calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                                (calendar.get(Calendar.MONTH) > 9 ?
                                calendar.get(Calendar.MONTH) : "0" + calendar.get(Calendar.MONTH)) + "/" +
                                calendar.get(Calendar.YEAR));
                dateCreateRequestTextField.setText(
                        calendar.get(Calendar.DAY_OF_MONTH) + "." +
                                (calendar.get(Calendar.MONTH) > 9 ?
                                calendar.get(Calendar.MONTH) : "0" + calendar.get(Calendar.MONTH)) + "." +
                                calendar.get(Calendar.YEAR));
                personalAccountTextField.setText(abonentForCRM.getPersonalAccount());
                numberAbonentTextField.setText(abonentForCRM.getNumber());
            } catch (Exception exception) {
                clearRequest();

                System.out.println("Abonent not found");
            }
        });

        cancelCreateRequestButton.setOnAction(actionEvent -> clearRequest());

        applyCreateRequestButton.setOnAction(actionEvent -> {
            try {
                String number = numberRequestTextField.getText().trim();
                String dateCreate = dateCreateRequestTextField.getText().trim();
                String abonentNumber = numberAbonentTextField.getText().trim();
                String personalAccount = personalAccountTextField.getText().trim();
                String service = serviceComboBox.getValue();
                String serviceStatus = serviceStatusComboBox.getValue();
                String serviceGenus = serviceGenusComboBox.getValue();
                String serviceType = serviceTypeComboBox.getValue();
                String equipmentsType = equipmentTypeTextField.getText().trim();
                String problemType = problemTypeTextField.getText().trim();
                String problemDescription = problemDescriptionTextField.getText().trim();
                String dateClose = closeDateTextField.getText().trim();
                Request request = new Request(
                        number, dateCreate, abonentNumber, personalAccount, service, serviceStatus, serviceGenus,
                        serviceType, equipmentsType, problemType, problemDescription, dateClose);
                if(databaseHandler.addRequest(request)){clearRequest();}
                else {System.out.println("Add request error");}
            }
            catch (Exception ignored){}
        });

        serviceGenusComboBox.setOnAction(event -> {
            String genus = serviceGenusComboBox.getValue();
            ObservableList<String> types = servicesTypeByServicesGenus.get(genus);
            serviceTypeComboBox.setItems(types);
            serviceTypeComboBox.setValue(types.get(0));
        });
    }

    //Common methods
    private void showAnchorPane(String id){
        switch (id) {
            case "abonentsAnchorPane" -> {
                abonentsAnchorPane.setVisible(true);
                controlDevisesAnchorPane.setVisible(false);
                activesAnchorPane.setVisible(false);
                billingAnchorPane.setVisible(false);
                supportUsersAnchorPane.setVisible(false);
                crmAnchorPane.setVisible(false);
            }
            case "controlDevisesAnchorPane" -> {
                abonentsAnchorPane.setVisible(false);
                controlDevisesAnchorPane.setVisible(true);
                activesAnchorPane.setVisible(false);
                billingAnchorPane.setVisible(false);
                supportUsersAnchorPane.setVisible(false);
                crmAnchorPane.setVisible(false);
            }
            case "activesAnchorPane" -> {
                abonentsAnchorPane.setVisible(false);
                controlDevisesAnchorPane.setVisible(false);
                activesAnchorPane.setVisible(true);
                billingAnchorPane.setVisible(false);
                supportUsersAnchorPane.setVisible(false);
                crmAnchorPane.setVisible(false);
            }
            case "billingAnchorPane" -> {
                abonentsAnchorPane.setVisible(false);
                controlDevisesAnchorPane.setVisible(false);
                activesAnchorPane.setVisible(false);
                billingAnchorPane.setVisible(true);
                supportUsersAnchorPane.setVisible(false);
                crmAnchorPane.setVisible(false);
            }
            case "supportUsersAnchorPane" -> {
                abonentsAnchorPane.setVisible(false);
                controlDevisesAnchorPane.setVisible(false);
                activesAnchorPane.setVisible(false);
                billingAnchorPane.setVisible(false);
                supportUsersAnchorPane.setVisible(true);
                crmAnchorPane.setVisible(false);
            }
            case "crmAnchorPane" -> {
                abonentsAnchorPane.setVisible(false);
                controlDevisesAnchorPane.setVisible(false);
                activesAnchorPane.setVisible(false);
                billingAnchorPane.setVisible(false);
                supportUsersAnchorPane.setVisible(false);
                crmAnchorPane.setVisible(true);
            }
        }
    }

    private void changeAccessLevel(String role){
        showAnchorPane("abonentsAnchorPane");
        switch (role) {
            case "Руководитель отдела по работе с клиентами" -> {
                controlDevisesButton.setVisible(false);
                controlDevisesImageView.setVisible(false);
                activesButton.setVisible(false);
                activesImageView.setVisible(false);
                billingButton.setVisible(true);
                billingImageView.setVisible(true);
                supportUsersButton.setVisible(false);
                supportUsersImageView.setVisible(false);
                crmButton.setVisible(true);
                crmImageView.setVisible(true);
            }
            case "Менеджер по работе с клиентами" -> {
                controlDevisesButton.setVisible(false);
                controlDevisesImageView.setVisible(false);
                activesButton.setVisible(false);
                activesImageView.setVisible(false);
                billingButton.setVisible(false);
                billingImageView.setVisible(false);
                supportUsersButton.setVisible(false);
                supportUsersImageView.setVisible(false);
                crmButton.setVisible(true);
                crmImageView.setVisible(true);
            }
            case "Руководитель отдела технической поддержки" -> {
                controlDevisesButton.setVisible(false);
                controlDevisesImageView.setVisible(false);
                activesButton.setVisible(false);
                activesImageView.setVisible(false);
                billingButton.setVisible(false);
                billingImageView.setVisible(false);
                supportUsersButton.setVisible(true);
                supportUsersImageView.setVisible(true);
                crmButton.setVisible(false);
                crmImageView.setVisible(false);
            }
            case "Специалист технической поддержки" -> {
                controlDevisesButton.setVisible(true);
                controlDevisesImageView.setVisible(true);
                activesButton.setVisible(false);
                activesImageView.setVisible(false);
                billingButton.setVisible(false);
                billingImageView.setVisible(false);
                supportUsersButton.setVisible(true);
                supportUsersImageView.setVisible(true);
                crmButton.setVisible(true);
                crmImageView.setVisible(true);
            }
            case "Бухгалтер" -> {
                controlDevisesButton.setVisible(false);
                controlDevisesImageView.setVisible(false);
                activesButton.setVisible(true);
                activesImageView.setVisible(true);
                billingButton.setVisible(true);
                billingImageView.setVisible(true);
                supportUsersButton.setVisible(false);
                supportUsersImageView.setVisible(false);
                crmButton.setVisible(false);
                crmImageView.setVisible(false);
            }
            case "Директор по развитию" -> {
                controlDevisesButton.setVisible(true);
                controlDevisesImageView.setVisible(true);
                activesButton.setVisible(true);
                activesImageView.setVisible(true);
                billingButton.setVisible(true);
                billingImageView.setVisible(true);
                supportUsersButton.setVisible(true);
                supportUsersImageView.setVisible(true);
                crmButton.setVisible(true);
                crmImageView.setVisible(true);
            }
            default -> {
                controlDevisesButton.setVisible(true);
                controlDevisesImageView.setVisible(true);
                activesButton.setVisible(true);
                activesImageView.setVisible(true);
                billingButton.setVisible(false);
                billingImageView.setVisible(false);
                supportUsersButton.setVisible(false);
                supportUsersImageView.setVisible(false);
                crmButton.setVisible(true);
                crmImageView.setVisible(true);
            }
        }
    }

    //Abonents workspace methods
    private void updateAbonentsTable(String select){
        try {
            abonentsTable.setItems(databaseHandler.returnAbonents(select));
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    private void setAvatarImage() {
        File file;
        Image image;
        try {
            file = new File("src\\resources\\images\\avatars\\" + User.getId() + ".jpg");
            image = new Image(file.toURI().toString());
            if (image.getHeight() == 0.0) {
                file = new File("src\\resources\\images\\avatars\\" + User.getId() + ".png");
                image = new Image(file.toURI().toString());
                if (image.getHeight() == 0.0) {
                    file = new File("src\\resources\\images\\avatars\\" + User.getId() + ".jfif");
                    image = new Image(file.toURI().toString());
                    if (image.getHeight() == 0.0) {
                        file = new File("src\\resources\\images\\avatars\\unknown.jpg");
                        image = new Image(file.toURI().toString());
                    }
                }
            }
            userAvatarImageView.setImage(image);
        } catch (Exception exception) {System.out.println("Не удалось поставить аватарку");}
    }

    private void openAbonentInfoWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/layout/abonent_info.fxml"));
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

    //CRM workspace methods
    private void clearRequest(){
        findByTelephoneNumberTextField.setText("");
        findByNameTextField.setText("");
        numberRequestTextField.setText("");
        dateCreateRequestTextField.setText("");
        numberAbonentTextField.setText("");
        personalAccountTextField.setText("");

        findByTelephoneNumberTextField.setDisable(false);
        findByNameTextField.setDisable(false);

        findAbonentButton.setVisible(true);
        cancelCreateRequestButton.setVisible(false);
        applyCreateRequestButton.setVisible(false);

        numberRequestTextField.setVisible(false);
        dateCreateRequestTextField.setVisible(false);
        personalAccountTextField.setVisible(false);
        numberAbonentTextField.setVisible(false);
        equipmentTypeTextField.setVisible(false);
        problemTypeTextField.setVisible(false);
        problemDescriptionTextField.setVisible(false);
        closeDateTextField.setVisible(false);

        telephoneNumberLabel.setVisible(false);
        nameLabel.setVisible(false);
        numberRequestLabel.setVisible(false);
        dateCreateRequestLabel.setVisible(false);
        personalAccountLabel.setVisible(false);
        numberAbonentLabel.setVisible(false);
        equipmentTypeLabel.setVisible(false);
        problemTypeLabel.setVisible(false);
        problemDescriptionLabel.setVisible(false);
        closeDateLabel.setVisible(false);
        serviceLabel.setVisible(false);
        serviceStatusLabel.setVisible(false);
        genusServiceLabel.setVisible(false);
        serviceTypeLabel.setVisible(false);

        serviceComboBox.setVisible(false);
        serviceStatusComboBox.setVisible(false);
        serviceGenusComboBox.setVisible(false);
        serviceTypeComboBox.setVisible(false);
    }

    private void setCRMComboBox(){
        serviceComboBox.setItems(services);
        serviceComboBox.setValue(services.get(0));

        serviceStatusComboBox.setItems(serviceStatus);
        serviceStatusComboBox.setValue(serviceStatus.get(0));

        serviceGenusComboBox.setItems(serviceGenus);
        serviceGenusComboBox.setValue(serviceGenus.get(0));

        String genus = serviceGenusComboBox.getValue();
        ObservableList<String> types = servicesTypeByServicesGenus.get(genus);
        serviceTypeComboBox.setItems(types);
        serviceTypeComboBox.setValue(types.get(0));
    }
}
