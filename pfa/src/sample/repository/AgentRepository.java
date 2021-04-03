package sample.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.connection.Connecter;
import sample.entite.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AgentRepository {
    static Logger log = Logger.getLogger(Connecter.class.getName());
    static Connection cnx = Connecter.getConnection();
    public static void ajouter(Agent a){
        try {
            PreparedStatement pr= cnx.prepareStatement("INSERT INTO AGENT VALUES (?,?,?,?,?,?)");
            pr.setInt(1,a.getCIN());
            pr.setString(2,a.getNom());
            pr.setString(3,a.getPrenom());
            pr.setString(4,a.getEmail());
            pr.setString(5,a.getPassword());
            pr.setString(6,a.getROLE());

            pr.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
            System.out.print("err2");
            log.severe("Message"+e.getMessage());
            log.severe("Cause"+e.getCause());
        }
    }

    public  static ObservableList<Agent> getListAgent ()  {

        ObservableList<Agent> list= FXCollections.observableArrayList();
        try {
            PreparedStatement pr =  cnx.prepareStatement("select * from AGENT ");
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                list.add(new Agent(Integer.parseInt(rs.getString("CIN")),rs.getString("nom"),rs.getString("prenom"),rs.getString("mail"), rs.getString("password"), rs.getString("role")));
            }
        } catch (SQLException throwables) {
            log.severe("Cause"+throwables.getCause());
            throwables.printStackTrace();
        }
        return list;
    }

    public static Agent recherchCIN(int c){
        Agent a = null;
        try {
            PreparedStatement pr = cnx.prepareStatement("select * from AGENT where (CIN = ? )");
            pr.setInt(1,c);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                a = new Agent(Integer.parseInt(rs.getString("CIN")), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getNString("password"),rs.getString("role"));
            }

        } catch (SQLException throwables) {
            log.severe("Message"+throwables.getMessage());
            log.severe("Cause"+throwables.getCause());
            throwables.printStackTrace();
        }


        return a;


    }
    public static void modifier(Agent a){
        try {
            PreparedStatement pr = cnx.prepareStatement("update AGENT set NOM=?,PRENOM=?,MAIL=?,PASSWORD=?,ROLE=? where CIN=?");
            pr.setString(1,a.getNom());
            pr.setString(2,a.getPrenom());
            pr.setString(3,a.getEmail());
            pr.setString(4,a.getPassword());
            pr.setString(5,a.getROLE());
            pr.setInt(6,a.getCIN());
            pr.executeQuery();
        } catch (SQLException throwables) {
            log.severe("Message"+throwables.getMessage());
            log.severe("Cause"+throwables.getCause());
            throwables.printStackTrace();
        }


    }

    public static void suprimer (Agent a){
        try {
            PreparedStatement pr = cnx.prepareStatement("delete from AGENT where (CIN = ? )");
            pr.setInt(1,a.getCIN());
            pr.executeQuery();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
}

