package sample.ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FPrincipal implements Initializable {
    @FXML
    private Label name;

    @FXML
    private Label lastName;

    @FXML
    private Label email;

    @FXML
    private Label role;

    @FXML
    void goBilan(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/LogIn.fxml",425,610);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(LogIn.getAcAgent().getPrenom());
        lastName.setText(LogIn.getAcAgent().getNom());
        email.setText(LogIn.getAcAgent().getEmail());
        role.setText(LogIn.getAcAgent().getROLE());

    }
}
