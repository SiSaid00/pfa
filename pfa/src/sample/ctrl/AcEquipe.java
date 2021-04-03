package sample.ctrl;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.entite.Agent;
import sample.entite.Equipe;
import sample.repository.AgentRepository;
import sample.repository.EquipeRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AcEquipe implements Initializable {
    @FXML
    private TextField filterField;

    @FXML
    private TableView<Agent> table_personnel;

    @FXML
    private TableColumn<Agent, Integer> CIN;

    @FXML
    private TableColumn<Agent, String> nom;

    @FXML
    private TableColumn<Agent, String> prenom;

    @FXML
    private TableColumn<Agent, String> role;

    @FXML
    private TableColumn<Agent, String> password;

    @FXML
    private TableColumn<Agent, String> email;
    @FXML
    private TableView<Agent> table_membre;

    @FXML
    private TableColumn<Agent, Integer> mCIN;

    @FXML
    private TableColumn<Agent, String> mNom;

    @FXML
    private TableColumn<Agent, String> mPrenom;

    @FXML
    private TableColumn<Agent, String> mEmail;

    @FXML
    private TableColumn<Agent, String> mPassword;
    @FXML
    private TableColumn<Agent, String> mRole;

    @FXML
    private TextField ENom;
    @FXML
    private Label name;

    @FXML
    private Label lastName;

    @FXML
    private Label email1;


    Equipe ac = Equipes.getActiveEquipe();
    ObservableList<Agent> dataList;

    Agent ag;
    int index;
    int agentID;

    @FXML
    void selectDelete(MouseEvent event) {
        index = table_membre.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
         agentID =  mCIN.getCellData(index);


    }
    public void search(){
        CIN.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("CIN"));
        nom.setCellValueFactory(new PropertyValueFactory<Agent , String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Agent, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Agent, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<Agent, String>("password"));
        role.setCellValueFactory(new PropertyValueFactory<Agent, String>("ROLE"));
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
    void selectedToAdd(MouseEvent event) {
        index = table_personnel.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        ag=new Agent(CIN.getCellData(index),nom.getCellData(index),prenom.getCellData(index),password.getCellData(index),email.getCellData(index),role.getCellData(index));

    }
    @FXML
    void addMembre(ActionEvent event) throws IOException {
        try {
            EquipeRepository.ajouterMembre(ag);


        } catch (Exception e) {
            e.printStackTrace();
        }

        actualz();

    }
    @FXML
    void goEntet(ActionEvent event) {


    }

    @FXML
    void goEquipes(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Equipes.fxml",715,620);

    }

    @FXML
    void goPersonnel(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/Personnel.fxml",715,620);

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("front/LogIn.fxml",410,620);

    }

    @FXML
    void removeMembre(ActionEvent event) throws IOException {
        try {
            EquipeRepository.supprimerMembre(agentID,Equipes.getActiveEquipe().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        actualz();

    }

    @FXML
    void renameEquipe(ActionEvent event) {
        try {
            Equipe equipe = new Equipe(ac.getId(),ENom.getText());
            EquipeRepository.rename(equipe);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void actualz() throws IOException {
        Main m = new Main();
        m.changeScene("front/ActiveEquipe.fxml",710,620);
    }
    @FXML
    void actualiser(MouseEvent event) throws IOException {
       actualz();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Agent> list = AgentRepository.getListAgent();
        ObservableList<Agent> list2 = EquipeRepository.getListeMembre();
        CIN.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("CIN"));
        nom.setCellValueFactory(new PropertyValueFactory<Agent , String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Agent, String>("prenom"));
        role.setCellValueFactory(new PropertyValueFactory<Agent, String>("ROLE"));
        email.setCellValueFactory(new PropertyValueFactory<Agent, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<Agent, String>("password"));
        table_personnel.getItems().setAll(list);

        mCIN.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("CIN"));
        mNom.setCellValueFactory(new PropertyValueFactory<Agent , String>("nom"));
        mPrenom.setCellValueFactory(new PropertyValueFactory<Agent, String>("prenom"));
        mRole.setCellValueFactory(new PropertyValueFactory<Agent, String>("ROLE"));
        mEmail.setCellValueFactory(new PropertyValueFactory<Agent, String>("email"));
        mPassword.setCellValueFactory(new PropertyValueFactory<Agent, String>("password"));
        table_membre.getItems().setAll(list2);


        ENom.setText(Equipes.getActiveEquipe().getNom());


        search();

        name.setText(LogIn.getAcAdmin().getPrenom());
        lastName.setText(LogIn.getAcAdmin().getNom());
        email1.setText(LogIn.getAcAdmin().getEmail());

    }
}
