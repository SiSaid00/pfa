package sample.ctrl;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Main;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import sample.entite.Equipe;
import sample.repository.EquipeRepository;

public class Equipes  implements Initializable {

    @FXML
    private Button personnels;

    @FXML
    private Button equipes;

    @FXML
    private Button entet;

    @FXML
    private TableView<Equipe> table_equipe;
    @FXML
    private TableColumn<Equipe, Integer> ID;

    @FXML
    private TableColumn<Equipe, String> nom;
    @FXML
    private TextField EID;

    @FXML
    private TextField ENom;

    static Equipe activeEquipe;
    @FXML
    private Label name;

    @FXML
    private Label lastName;

    @FXML
    private Label email;

    int index = -1;

    public Equipes() {
    }


    private void actualiz() throws IOException {
        Main m = new Main();
        m.changeScene("front/Equipes.fxml",710,620);
    }
    @FXML
    void actualiser(MouseEvent event) throws IOException {
        actualiz();

    }

    @FXML
    void addEquipe(ActionEvent event) throws IOException {
        try {
            Equipe eq = new Equipe(Integer.parseInt(EID.getText()),ENom.getText());
            EquipeRepository.ajouter(eq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        actualiz();


    }

    @FXML
    void deleteEquipe(ActionEvent event) throws IOException {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("worning!!");
        alert.setHeaderText("Are you sure of deleting this ?");
        Optional<ButtonType> rs= alert.showAndWait();
        if (rs.get()==ButtonType.OK){
            try {
                Equipe eq = new Equipe(Integer.parseInt(EID.getText()),ENom.getText());
                EquipeRepository.supprimer(eq);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        actualiz();

    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_equipe.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        EID.setText(ID.getCellData(index).toString());
        ENom.setText(nom.getCellData(index).toString());

    }

    public static Equipe getActiveEquipe() {
        return activeEquipe;
    }

    @FXML
    void goActiveEquipe(ActionEvent event) throws IOException {
        Main m = new Main();
        activeEquipe= new Equipe(Integer.parseInt(EID.getText()),ENom.getText());
        m.changeScene("front/ActiveEquipe.fxml",700,620);

    }

    @FXML
    void goEntet(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Entet.fxml",700,630);

    }

    @FXML
    void goPersonnels(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Personnels.fxml",700,630);

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/LogIn.fxml",425,610);

    }

    @FXML
    void quitter(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/AdPrincipal.fxml",680,630);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Equipe> list = EquipeRepository.getListeEquipe();

        ID.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Equipe , String>("nom"));

        table_equipe.getItems().setAll(list);

        name.setText(LogIn.getAcAdmin().getPrenom());
        lastName.setText(LogIn.getAcAdmin().getNom());
        email.setText(LogIn.getAcAdmin().getEmail());
    }
}

