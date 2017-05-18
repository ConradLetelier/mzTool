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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
import static managerzone.tool.LagViewController.data1;
import static managerzone.tool.LagViewController.playerIndex;
import static managerzone.tool.MainPageController.teamIndex;
import static managerzone.tool.ManagerzoneTool.teams;
import static managerzone.tool.removeTeamController.removeConfirm;
import static managerzone.tool.removeTeamController.playerRemove;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class SpelarViewController implements Initializable {
    Mouse mouse = new Mouse();
   
    @FXML
    private ImageView image;
    @FXML
    private Label TeamConfirmMenuLabel;    
    @FXML
    private Label teamMenuButton;
    @FXML
    private ChoiceBox playerList;
    @FXML
    private Button removePlayerButton;
    @FXML
    private Button changePlayerButton;
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
    private void TeamPage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LagView.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setTitle("Softy MZ Tool");
        stg.setScene(s);
        stg.show();
        
    }
    
    @FXML
    private void removePlayer(ActionEvent event) throws IOException {
        playerRemove = true;
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("Remove.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Radera lag");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(removePlayerButton.getScene().getWindow());
        stage.showAndWait();
        if(removeConfirm){
         root = FXMLLoader.load(getClass().getResource("LagView.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(s);
        stg.show();    
        removeConfirm = false;
        }
    }
    
    @FXML
    private void changePlayer(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("ChangePlayer.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Redigera Spelare");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(removePlayerButton.getScene().getWindow());
        stage.showAndWait();
        
        root = FXMLLoader.load(getClass().getResource("LagView.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(s);
        stg.show();
    };
    
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
        
    playerList.setItems(data1);
    playerList.getSelectionModel().select(playerIndex);
    setNewImg();
    playerList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
        public void changed(ObservableValue ov,Number value, Number new_value){
            setNewImg();
        }
    });
    }

    public void setNewImg() {
        Thread th = new Thread(() -> {
            int plIndx = playerList.getSelectionModel().getSelectedIndex();
            if (plIndx >= 0) {
                String url = teams.get(teamIndex).getPlayers().get(plIndx).getImgURL();
                Image img = new Image(url);
                image.setImage(img);
                playerIndex = plIndx;
            }
        });
        /*
        int plIndx=playerList.getSelectionModel().getSelectedIndex();
        if(plIndx>=0){
        String url = teams.get(teamIndex).getPlayers().get(plIndx).getImgurl();
        Image img = new Image(url);
        image.setImage(img);
        playerIndex=plIndx;
        }
         */
        th.start();
    }

    public ImageView getImage() {
        return image;
    }
    
    public void setImage(Image img) {
        image.setImage(img);
    }

    public ChoiceBox getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ChoiceBox playerList) {
        this.playerList = playerList;
    }
    
    
    
   
}
