package sample.ctrl;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.entite.Agent;
import sample.repository.AgentRepository;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Personnels implements Initializable {

    @FXML
    private Button entet;

    @FXML
    private Button equipes;

    @FXML
    private Button logout;

    @FXML
    private Button quitter;

    @FXML
    private TableView<Agent> table_personnel;

    @FXML
    private TableColumn<Agent, Integer> CIN;

    @FXML
    private TableColumn<Agent,String> nom;

    @FXML
    private TableColumn<Agent, String> prenom;

    @FXML
    private TableColumn<Agent, String> email;

    @FXML
    private TableColumn<Agent, String> password;
    @FXML
    private TableColumn<Agent, String> ROLE;

    @FXML
    private Button add;

    @FXML
    private TextField PCIN;

    @FXML
    private TextField PNom;

    @FXML
    private TextField PPrenom;

    @FXML
    private TextField PROLE;

    @FXML
    private TextField PPassword;

    @FXML
    private TextField PEmail;
    @FXML
    private TextField filterField;

    @FXML
    private Label name;

    @FXML
    private Label lastName;

    @FXML
    private Label email1;

    ObservableList<Agent> dataList;

    int index = -1;


    private void actualiser() throws IOException {
        Main m = new Main();
        m.changeScene("front/Personnels.fxml",710,630);
    }

    @FXML
    void actualizer(MouseEvent event) throws IOException {
        actualiser();

    }



    @FXML
    void deletePersonnel(ActionEvent event) throws IOException {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("worning!!");
        alert.setHeaderText("Are you sure of deleting this ?");
        Optional<ButtonType> rs= alert.showAndWait();
        if (rs.get()==ButtonType.OK){
            try {
                Agent ag = new Agent(Integer.parseInt(PCIN.getText()), PNom.getText(), PPrenom.getText() ,PEmail.getText(), PPassword.getText(), PROLE.getText());
                AgentRepository.suprimer(ag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        actualiser();


    }

    public void search(){
        CIN.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("CIN"));
        nom.setCellValueFactory(new PropertyValueFactory<Agent , String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Agent, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Agent, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<Agent, String>("password"));
        ROLE.setCellValueFactory(new PropertyValueFactory<Agent, String>("ROLE"));
        dataList= AgentRepository.getListAgent();
        table_personnel.setItems(dataList);

        FilteredList<Agent> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Agent -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Agent.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches nom
                } else if (Agent.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (Agent.getROLE().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches role
                }
                else if (String.valueOf(Agent.getEmail()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<Agent> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_personnel.comparatorProperty());
        table_personnel.setItems(sortedData);
    }



    @FXML
    void getSelected (MouseEvent event){
        index = table_personnel.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        PCIN.setText(CIN.getCellData(index).toString());
        PNom.setText(nom.getCellData(index));
        PPrenom.setText(prenom.getCellData(index));
        PPassword.setText(password.getCellData(index));
        PEmail.setText(email.getCellData(index));
        PROLE.setText(ROLE.getCellData(index));

    }
    @FXML
    void modifyPersonnel(ActionEvent event) throws IOException {
        try {
            Agent ag = new Agent(Integer.parseInt(PCIN.getText()), PNom.getText(), PPrenom.getText() ,PEmail.getText(), PPassword.getText(), PROLE.getText());
            AgentRepository.modifier(ag);
        } catch (Exception e) {
            e.printStackTrace();
        }

        actualiser();
    }
    @FXML
    void addPersonnel(ActionEvent event) throws IOException {
        try {
            Agent ag = new Agent(Integer.parseInt(PCIN.getText()), PNom.getText(), PPrenom.getText() ,PEmail.getText(), PPassword.getText(), PROLE.getText());
            AgentRepository.ajouter(ag);
        } catch (NumberFormatException e) {

            System.out.print("err1");
        }
        actualiser();

    }


    @FXML
    void goEntet(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Entet.fxml",685,630);

    }

    @FXML
    void goEquipes(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Equipes.fxml",710,620);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Agent> list = AgentRepository.getListAgent();

        CIN.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("CIN"));
        nom.setCellValueFactory(new PropertyValueFactory<Agent , String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Agent, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Agent, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<Agent, String>("password"));
        ROLE.setCellValueFactory(new PropertyValueFactory<Agent, String>("ROLE"));
        table_personnel.getItems().setAll(list);

        search();

        name.setText(LogIn.getAcAdmin().getPrenom());
        lastName.setText(LogIn.getAcAdmin().getNom());
        email1.setText(LogIn.getAcAdmin().getEmail());

    }
}
