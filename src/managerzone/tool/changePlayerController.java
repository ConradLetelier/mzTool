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
import static managerzone.tool.LagViewController.playerIndex;
import static managerzone.tool.MainPageController.client;
import static managerzone.tool.MainPageController.teamIndex;
import static managerzone.tool.ManagerzoneTool.teams;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class changePlayerController implements Initializable {
    
    @FXML
    private TextField urlInput;
    @FXML
    private TextField nameInput;
    @FXML
    private Button changeButton;
    @FXML
    private Button cancelButton;
    
    @FXML
    private void change(ActionEvent event){
        String name = nameInput.getText();
        String url = urlInput.getText();
        int teamId = teams.get(teamIndex).getId();
        teams.get(teamIndex).changePlayer(playerIndex, name, url);
        Player changedPlayer = teams.get(teamIndex).getPlayers().get(playerIndex);
        client.target("http://localhost:8080/mavenMall/webapi/teams/"+teamId+"/players/"+changedPlayer.getId()).request().put(Entity.entity(changedPlayer, MediaType.APPLICATION_JSON));
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
        String name = teams.get(teamIndex).getPlayers().get(playerIndex).getName();
        String imgURl = teams.get(teamIndex).getPlayers().get(playerIndex).getImgURL();
        nameInput.setText(name);
        urlInput.setText(imgURl);
    }    
    
}
