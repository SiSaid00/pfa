package sample.ctrl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdPrincipal implements Initializable {

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
    private Button logout;

    @FXML
    void adminLogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/LogIn.fxml",410,610);

    }
    @FXML
    void goEntet(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Entet.fxml",685,630);

    }

    @FXML
    void goEquipes(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("front/Equipes.fxml",710,620);

    }

    @FXML
    void goPersonnels(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("front/Personnels.fxml",710,630);

    }
    @FXML
    void goAdminInfo(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/AdminInfo.fxml",685,630);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(LogIn.getAcAdmin().getPrenom());
        lastName.setText(LogIn.getAcAdmin().getNom());
        email.setText(LogIn.getAcAdmin().getEmail());


    }
}
