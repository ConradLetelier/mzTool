/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerzone.tool;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static managerzone.tool.MainPageController.client;
import static managerzone.tool.MainPageController.data;
import static managerzone.tool.MainPageController.teamIndex;
import static managerzone.tool.ManagerzoneTool.teams;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class changeTeamController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField managerInput;
    @FXML
    private TextField balanceInput;
    @FXML
    private Button confirmAddTeamButton;
    @FXML
    private Button cancelButton;
    
    @FXML
    private void addNewTeam(ActionEvent event) {
        String name = nameInput.getText();
        String manager = managerInput.getText();
        String balanceString = balanceInput.getText();
        double balance = Double.parseDouble(balanceString);
        int id = teams.get(teamIndex).getId();
        DBTeam t = new DBTeam();
        t.setName(name);
        t.setManager(manager);
        t.setBalance(balance);
        t.setId(id);
        client.target("http://localhost:8080/mavenMall/webapi/teams/"+id).request().put(Entity.entity(t, MediaType.APPLICATION_JSON));
        teams.get(teamIndex).setTeamName(name);
        teams.get(teamIndex).setTeamManager(manager);
        teams.get(teamIndex).setTeamBalance(balance);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String name = teams.get(teamIndex).getTeamName();
        String manager = teams.get(teamIndex).getTeamName();
        double balance1 = teams.get(teamIndex).getTeamBalance();
        String balance = String.valueOf(balance1);
        nameInput.setText(name);
        managerInput.setText(manager);
        balanceInput.setText(balance);
    }    
    
}
