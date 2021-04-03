package sample.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.connection.Connecter;
import sample.ctrl.Equipes;
import sample.entite.Agent;
import sample.entite.Equipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class EquipeRepository {
    static Logger log = Logger.getLogger(Connecter.class.getName());
    static Connection cnx = Connecter.getConnection();

    public static void ajouter(Equipe e){
        try {
            PreparedStatement pr= cnx.prepareStatement("INSERT INTO EQUIPE VALUES (?,?)");
            pr.setInt(1,e.getId());
            pr.setString(2,e.getNom());
            pr.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.severe("Message"+ex.getMessage());
            log.severe("Cause"+ex.getCause());
        }
    }

    public static ObservableList<Equipe> getListeEquipe(){

        ObservableList<Equipe> list= FXCollections.observableArrayList();
        try {
            PreparedStatement pr =  cnx.prepareStatement("select * from EQUIPE ");
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                list.add(new Equipe(Integer.parseInt(rs.getString("id")),rs.getString("nom")));
            }
        } catch (SQLException throwables) {
            log.severe("Cause"+throwables.getCause());
            throwables.printStackTrace();
        }
        return list;
    }

    public static void supprimerMembre (int a, int e){
        try {
            PreparedStatement pr = cnx.prepareStatement("delete from AGENT_EQUIPE where (AGENT_ID = ? and EQUIPE_ID= ?)");
            pr.setInt(1,a);
            pr.setInt(2,e);
            pr.executeQuery();


        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
    static public void rename(Equipe e){
        try {
            PreparedStatement pr = cnx.prepareStatement("update EQUIPE set NOM=?  where ID=?");
            pr.setString(1,e.getNom());
            pr.setInt(2,e.getId());
            pr.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void supprimer(Equipe eq){
        try {
            PreparedStatement pr = cnx.prepareStatement("delete from EQUIPE where (ID = ? )");
            pr.setInt(1,eq.getId());
            pr.executeQuery();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        }

        public static void ajouterMembre(Agent a){
        try {
            PreparedStatement pr = cnx.prepareStatement("insert into AGENT_EQUIPE values (?,?)");
            pr.setInt(1,a.getCIN());
            pr.setInt(2, Equipes.getActiveEquipe().getId());
            pr.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            log.severe("Message "+e.getMessage());
            log.severe("Cause"+e.getCause());
        }

        }

        public static ObservableList<Agent> getListeMembre(){
            ObservableList<Agent> list= FXCollections.observableArrayList();
            try {
                PreparedStatement pr =  cnx.prepareStatement("select CIN, NOM, PRENOM, PASSWORD, MAIL, ROLE FROM AGENT INNER JOIN AGENT_EQUIPE ON AGENT.CIN = AGENT_EQUIPE.AGENT_ID where (AGENT_EQUIPE.EQUIPE_ID =?)  ");
                pr.setInt(1,Equipes.getActiveEquipe().getId());
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


}
