/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerzone.tool;

import com.sun.deploy.util.StringUtils;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static managerzone.tool.ManagerzoneTool.listSet;

import static managerzone.tool.ManagerzoneTool.teams;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class MainPageController implements Initializable {

    public static int teamIndex = -1;
    public static ObservableList<String> data = FXCollections.observableArrayList();
    Mouse mouse = new Mouse();
    public static Client client;

    @FXML
    private Label statusLabel;
    @FXML
    private ListView<String> teamListView;
    @FXML
    private Button addTeamButton;
    @FXML
    private TextField searchBox;
    @FXML
    private ImageView minimize;
    @FXML
    private ImageView maximize;
    @FXML
    private ImageView close;
    @FXML
    private HBox menuBar;

    @FXML
    private void teamConfirmer(MouseEvent event) throws IOException {
        if (!(teamListView.getSelectionModel().getSelectedIndex() == -1)) {
            for (int i = 0; i < teams.size(); i++) {
                if (teamListView.getSelectionModel().getSelectedItem() == teams.get(i).getTeamName()) {
                    teamIndex = i;
                }
            }

            Parent root = FXMLLoader.load(getClass().getResource("LagView.fxml"));
            Scene s = new Scene(root);
            Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stg.setScene(s);
            stg.show();
        }

    }

    @FXML
    private void minimizeWindow(MouseEvent event) throws IOException {
        Stage stage = null;
        stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void maximizeWindow(MouseEvent event) throws IOException {
        Stage stage = null;
        stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
    }

    @FXML
    private void closeWindow(MouseEvent event) throws IOException {
        Stage stage = null;
        stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addTeam(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("addTeam.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addTeamButton.getScene().getWindow());
        stage.setTitle("Lägg till nytt lag");
        stage.showAndWait();
    }

    @FXML
    private void search(KeyEvent event) {
        data.clear();
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamName().toLowerCase().matches("(.*)" + searchBox.getText().toLowerCase() + event.getText().toLowerCase() + "(.*)")) {
                data.add(teams.get(i).getTeamName());
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        client = ClientBuilder.newClient();
        getTeams();
        menuBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                mouse.setX(t.getX());
                mouse.setY(t.getY());
            }
        });
        menuBar.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                menuBar.getScene().getWindow().setX(t.getScreenX() - mouse.getX() - 14);
                menuBar.getScene().getWindow().setY(t.getScreenY() - mouse.getY() - 14);
            }
        });

        data.clear();;
        for (int i = 0; i < teams.size(); i++) {
            data.add(teams.get(i).getTeamName());
        }

        teamListView.setItems(data);

    }

    private void getTeams() {
        if (listSet) {
            List<DBTeam> DBteams = client.target("http://localhost:8080/mavenMall/webapi/teams").request(MediaType.APPLICATION_JSON).get(new GenericType<List<DBTeam>>() {
            });
            for (int i = 0; i < DBteams.size(); i++) {
                String teamName = DBteams.get(i).getName();
                String teamManager = DBteams.get(i).getManager();
                double teamBalance = DBteams.get(i).getBalance();
                Team teamToAdd = new Team(teamName, teamManager, teamBalance);
                teamToAdd.setPlayers((ArrayList<Player>) DBteams.get(i).getPlayers());
                teamToAdd.setId(DBteams.get(i).getId());
                teams.add(teamToAdd);
            }
            listSet = false;
        }
    }
}
