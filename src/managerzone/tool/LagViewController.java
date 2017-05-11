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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static managerzone.tool.MainPageController.teamIndex;
import static managerzone.tool.ManagerzoneTool.teams;
import static managerzone.tool.removeTeamController.removeConfirm;
import static managerzone.tool.removeTeamController.teamRemove;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
    
public class LagViewController implements Initializable {
    
    public static ObservableList<String> data1 = FXCollections.observableArrayList();
    public static int playerIndex = -1;
    Mouse mouse = new Mouse();
    
    @FXML
    private Label nameLabel;
    @FXML
    private Label managerLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label TeamConfirmMenuLabel;
    @FXML
    private Label playerMenuButton;
    @FXML
    private Label playerSum;
    @FXML
    private ListView<String> playerListView;
    @FXML
    private Button addButton;
    @FXML
    private Button removeTeamButton;
    @FXML
    private Button changeTeamButton;
    @FXML
    private ProgressIndicator progress;
    @FXML
    private ImageView minimize;
    @FXML
    private ImageView maximize;
    @FXML
    private ImageView close;
    @FXML
    private HBox menuBar;
    
    
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
    private void chooseTeam(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setTitle("Softy MZ Tool");
        stg.setScene(s);
        stg.show();
        
    }
    @FXML
    private void choosePlayer(MouseEvent event) throws IOException {
        if(!(playerListView.getSelectionModel().getSelectedIndex() == -1)){
        playerIndex=playerListView.getSelectionModel().getSelectedIndex();
        Parent root = FXMLLoader.load(getClass().getResource("SpelarView.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setTitle("Softy MZ Tool");
        stg.setScene(s);
        stg.show();
        }
        
    }
    @FXML
    private void playerView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SpelarView.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setTitle("Softy MZ Tool");
        stg.setScene(s);
        stg.show();
    }
    @FXML
    private void remove(ActionEvent event) throws IOException {
        teamRemove = true;
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("Remove.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Radera lag");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(removeTeamButton.getScene().getWindow());
        stage.showAndWait();
        if(removeConfirm){
        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setTitle("Softy MZ Tool");
        stg.setScene(s);
        stg.show();
        removeConfirm = false;
        }
        
    }
    @FXML
    private void addPlayer(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("addPlayer.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addButton.getScene().getWindow());
        stage.setTitle("Add new player");
        stage.showAndWait();    
        String sum = Integer.toString(teams.get(teamIndex).getPlayers().size());
        playerSum.setText(sum);
    }
    @FXML
    private void changeTeam(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("ChangeTeam.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addButton.getScene().getWindow());
        stage.setTitle("Change team");
        stage.showAndWait();    
        
        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setTitle("Softy MZ Tool");
        stg.setScene(s);
        stg.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            menuBar.getScene().getWindow().setX( t.getScreenX() - mouse.getX() - 14);
            menuBar.getScene().getWindow().setY( t.getScreenY() - mouse.getY() - 14);
        }
    }); 
        
        String sum = Integer.toString(teams.get(teamIndex).getPlayers().size());
        playerSum.setText(sum);
        if(!teams.isEmpty()){
        nameLabel.setText(teams.get(teamIndex).getName());
        managerLabel.setText(teams.get(teamIndex).getManager());
        double balance=teams.get(teamIndex).getBalance();
        String balanceParse=Double.toString(balance);
        balanceLabel.setText(balanceParse);
        
        data1.clear();;
        
        for(int i = 0; i < teams.get(teamIndex).getPlayers().size();i++){
            data1.add(teams.get(teamIndex).getPlayers().get(i).getName());
        }
        
        playerListView.setItems(data1);
        }else{
            data1.clear();
        }
    }    
    
}
