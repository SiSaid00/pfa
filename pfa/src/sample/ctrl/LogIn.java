package sample.ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.entite.Admin;
import sample.entite.Agent;
import sample.repository.AdminRepository;
import sample.repository.AgentRepository;

import java.io.IOException;

public class LogIn {
    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField CIN;
    @FXML
    private Label msg;
    static private Agent acAgent;
    static private Admin acAdmin;

    static public Agent getAcAgent() {
        return acAgent;
    }

    static public Admin getAcAdmin() {
        return acAdmin;
    }

    @FXML
    void userLogin(ActionEvent event) throws IOException {
        checkLogin();



    }
    private void checkLogin() throws IOException {
        Main m = new Main();

        if (CIN.getText().isEmpty() && password.getText().isEmpty()){
            msg.setText(" please enter your data");
        }

        else {
            int cc = Integer.parseInt(CIN.getText());


            if ((AdminRepository.rchercheCIN(cc) == null) && (AgentRepository.recherchCIN(cc) == null)) {
                msg.setText(" CIN invalid");
            } else if (AdminRepository.rchercheCIN(cc) == null) {
                if (AgentRepository.recherchCIN(cc).getPassword().equals(password.getText())) {
                    msg.setText(" correct");
                    acAgent=AgentRepository.recherchCIN(cc);
                    if (acAgent.getROLE().equals("financier") ){
                        m.changeScene("front/FPrincipal.fxml",680,630);
                    }
                    else if (acAgent.getROLE().equals("RH")){
                        m.changeScene("front/RHPrincipal.fxml",680,630);
                    }
                    else if (acAgent.getROLE().equals("RMateriels")){
                        m.changeScene("front/MPrincipal.fxml",680,630);
                    }

                } else {
                    msg.setText(" incorrect password");
                }
            } else if (AgentRepository.recherchCIN(cc) == null) {
                if (AdminRepository.rchercheCIN(cc).getPassword().equals(password.getText())) {
                    msg.setText("correct");
                    acAdmin=AdminRepository.rchercheCIN(cc);
                    m.changeScene("front/AdPrincipal.fxml", 680, 630);
                } else {
                    msg.setText(" incorrect password");
                }
            }

        }


    }
}
