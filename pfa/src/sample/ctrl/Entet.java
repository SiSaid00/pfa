package sample.ctrl;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.connection.Connecter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Entet implements Initializable {

    @FXML
    private Button entet;

    @FXML
    private Button equipes;

    @FXML
    private Button personnel;

    @FXML
    private Button quitter;

    @FXML
    private Button logout;
    @FXML
    private Label name;

    @FXML
    private Label lastName;

    @FXML
    private Label email;
    @FXML
    private TextField gr;

    @FXML
    private TextField dl;

    @FXML
    private TextField st;

    @FXML
    private TextField ad;

    @FXML
    void add(ActionEvent event) {
         Connection cnx = Connecter.getConnection();
         Logger log = Logger.getLogger(Connecter.class.getName());
        try {
            PreparedStatement pr= cnx.prepareStatement("INSERT INTO ENTETE VALUES (?,?,?,?)");
            pr.setString(1,gr.getText());
            pr.setString(2,dl.getText());
            pr.setString(3,st.getText());
            pr.setString(4, ad.getText());
            pr.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.severe("Message"+ex.getMessage());
            log.severe("Cause"+ex.getCause());
        }



    }

    @FXML
    void goPrincipal(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/AdPrincipal.fxml",680,630);

    }

    @FXML
    void userLogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/LogIn.fxml",425,610);

    }
    @FXML
    void goPersonnel(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Personnels.fxml",700,630);

    }
    @FXML
    void goEquipes(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Equipes.fxml",700,620);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(LogIn.getAcAdmin().getPrenom());
        lastName.setText(LogIn.getAcAdmin().getNom());
        email.setText(LogIn.getAcAdmin().getEmail());
    }
}
