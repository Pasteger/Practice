package sample.database;

import javafx.collections.ObservableList;

public class User {
    private static String id;
    private static String role;
    private static String username;
    private static AbonentsData abonentForAbonentInfo;

    public static String getId() {return id;}
    public static void setId(String id) {User.id = id;}
    public static String getRole() {return role;}
    public static void setRole(String role) {User.role = role;}
    public static String getUsername() {return username;}
    public static void setUsername(String editedRecordName) {User.username = editedRecordName;}
    public static AbonentsData getAbonentForAbonentInfo() {return abonentForAbonentInfo;}
    public static void setAbonentForAbonentInfo(AbonentsData abonentForAbonentInfo) {User.abonentForAbonentInfo = abonentForAbonentInfo;}
}
