package sample.ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.entite.Admin;
import sample.repository.AdminRepository;

import java.io.IOException;

public class AdminInfo {
    @FXML
    private Label name;

    @FXML
    private Label lastName;

    @FXML
    private Label email;

    @FXML
    private Button personnels;

    @FXML
    private Button equipes;

    @FXML
    private Button entet;

    @FXML
    private TextField mail;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField cpassword;

    @FXML
    private Label msg;

    @FXML
    void enregistrer(ActionEvent event) {
        if(password.getText().equals(cpassword.getText()) ) {
            Admin admin = new Admin((LogIn.getAcAdmin().getCIN()), nom.getText(), prenom.getText(), mail.getText(), password.getText());
            AdminRepository.modifier(admin);
            msg.setText("ajout avec succee ");
        }
        else {msg.setText("check your password ");}
    }

    @FXML
    void goAdPrincipal(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/AdPrincipal.fxml",680,630);
    }

    @FXML
    void goEntet(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Entete.fxml",685,630);

    }

    @FXML
    void goEquipes(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Equipes.fxml",710,620);

    }

    @FXML
    void goPersonnels(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/LogIn.fxml",425,610);
    }

}
