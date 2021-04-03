package sample.repository;

import sample.connection.Connecter;
import sample.entite.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AdminRepository {

    static Logger log = Logger.getLogger(Connecter.class.getName());
    static  Connection cnx = Connecter.getConnection();

    public void ajouter(Admin a) {
        try {
            PreparedStatement pr = cnx.prepareStatement("insert into admin values (?,?,?,?,?)");
            pr.setInt(1, a.getCIN());
            pr.setString(2, a.getNom());
            pr.setString(3, a.getPrenom());
            pr.setString(4, a.getEmail());
            pr.setString(5, a.getPassword());
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.print("err2");
        }

    }

    public static Admin rchercheCIN(int C) {

        Admin a = null;
        try {
            PreparedStatement pr = cnx.prepareStatement("select * from admin where (CIN = ? )");
            pr.setInt(1,C);

            ResultSet rs = pr.executeQuery();

            if(rs.next()){
                a = new Admin(Integer.parseInt(rs.getString("CIN")), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getNString("password"));
            }

        } catch (SQLException throwables) {
            log.severe("Error occured during search");
            throwables.printStackTrace();


        }
        return a;


    }
    public static void modifier(Admin a){
        try {
            PreparedStatement pr = cnx.prepareStatement("update ADMIN set NOM=?,PRENOM=?,EMAIL=?,PASSWORD=? where CIN=?");
            pr.setString(1,a.getNom());
            pr.setString(2,a.getPrenom());
            pr.setString(3,a.getEmail());
            pr.setString(4,a.getPassword());
            pr.setInt(5,a.getCIN());
            pr.executeQuery();
        } catch (SQLException throwables) {
            log.severe("Message"+throwables.getMessage());
            log.severe("Cause"+throwables.getCause());
            throwables.printStackTrace();
        }


    }
}

