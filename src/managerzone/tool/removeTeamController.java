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
import javafx.stage.Stage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static managerzone.tool.LagViewController.playerIndex;
import static managerzone.tool.MainPageController.teamIndex;
import static managerzone.tool.ManagerzoneTool.teams;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class removeTeamController implements Initializable {
    
    public static boolean removeConfirm = false;
    public static boolean playerRemove = false;
    public static boolean teamRemove = false;
    
    @FXML
    private Label label;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    
    @FXML
    private void removeTeam(ActionEvent event) throws IOException {
        if(teamRemove){
            teams.remove(teamIndex);
            teamRemove = false;
        }else if (playerRemove){
            teams.get(teamIndex).getPlayers().remove(playerIndex);
            playerRemove = false;
        }
        
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
        removeConfirm=true;
    }
    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
