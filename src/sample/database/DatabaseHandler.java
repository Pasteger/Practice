package sample.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public boolean checkNumber(String number) throws SQLException, ClassNotFoundException {
        String select = "SELECT number FROM users WHERE number=\"" + number + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (Exception exception){
            return false;
        }
    }

    public boolean checkPassword(String number, String password) throws SQLException, ClassNotFoundException {
        String select = "SELECT password FROM users WHERE number=\"" + number + " and password=\"" + password + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (Exception exception){
            return false;
        }
    }

    public void setUser(String number, String password) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM users WHERE number = \"" + number + "\" and password=\"" + password + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User.setId(resultSet.getString(1));
        User.setUsername(resultSet.getString(2));
        User.setRole(resultSet.getString(3));
    }

    public boolean signUpUser(String username, String password){
        try {
            String insert = "INSERT INTO users (username, password, role) VALUES(?,?,?)";

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setBoolean(3, false);

            preparedStatement.executeUpdate();
            return true;
        }
        catch (Exception exception) {return false;}
    }

    public ObservableList<AbonentsData> returnAbonents(String select) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<AbonentsData> travelsObservableList = FXCollections.observableArrayList();
            Statement statement = getDbConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                String number = resultSet.getString(1);
                String name = resultSet.getString(2);
                String sex = resultSet.getString(3);
                String birthdayDate = resultSet.getString(4);
                String telephoneNumber = resultSet.getString(5);
                String email = resultSet.getString(6);
                String addressRegistration = resultSet.getString(7);
                String addressResidential = resultSet.getString(8);
                String passportSeriesAndNumber = resultSet.getString(9);
                String divisionCode = resultSet.getString(10);
                String issuedBy = resultSet.getString(11);
                String dateOfIssue = resultSet.getString(12);
                String contractNumber = resultSet.getString(13);
                String dateOfConclusionOfTheContract = resultSet.getString(14);
                String typeContract = resultSet.getString(15);
                String reasonForTerminationOfTheContract = resultSet.getString(16);
                String personalAccount = resultSet.getString(17);
                String services = resultSet.getString(18);
                String services_0 = resultSet.getString(19);
                String services_1 = resultSet.getString(20);
                String dateOfTerminationOfTheContract = resultSet.getString(21);
                String leaseAgreement = resultSet.getString(22);
                String serialNumberOfTheEquipment = resultSet.getString(23);
                travelsObservableList.add(
                        new AbonentsData(number, name, sex, birthdayDate, telephoneNumber, email, addressRegistration,
                                addressResidential, passportSeriesAndNumber, divisionCode, issuedBy, dateOfIssue,
                                contractNumber, dateOfConclusionOfTheContract, typeContract,
                                reasonForTerminationOfTheContract, personalAccount, services, services_0,
                                services_1, dateOfTerminationOfTheContract, leaseAgreement, serialNumberOfTheEquipment)
                );
            }
            return travelsObservableList;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public ObservableList<StaffData> returnStaff(String select) throws SQLException, ClassNotFoundException {
        ObservableList<StaffData> requestsObservableList = FXCollections.observableArrayList();
        Statement statement = getDbConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String role = resultSet.getString(3);

            requestsObservableList.add(new StaffData(id, name, role));
        }
        return requestsObservableList;
    }

    public boolean addRequest(Request request){
        try {
            String insert = "INSERT INTO requests" +
                    "(NumberRequest, CreateDate, PersonalAccount, Service, ServiceStatus, ServiceGenus, ServiceType, " +
                    "EquipmentsType, ProblemType, ProblemDescription, CloseDate, AbonentNumber) " +
                    "VALUES ('" +
                    request.getNumber() + "', '" + request.getDateCreate() + "', '" +
                    request.getPersonalAccount() + "', '" + request.getService() + "', '" +
                    request.getServiceStatus() + "', '" + request.getServiceGenus() + "', '" +
                    request.getServiceType() + "', '" + request.getEquipmentsType() + "', '" +
                    request.getProblemType() + "', '" + request.getProblemDescription() + "', '" +
                    request.getDateClose() + "', '" + request.getAbonentNumber() + "');";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }
}
