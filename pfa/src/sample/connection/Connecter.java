package sample.connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Logger;


public class Connecter {

    static Logger log = Logger.getLogger(Connecter.class.getName());

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "test02", "test02");

        } catch (Exception e) {
            log.severe("Error occured during cnx initialization");
            log.severe("Message" + e.getMessage());
            log.severe("Cause" + e.getCause());
            e.printStackTrace();
            return null;
        }
    }
}

