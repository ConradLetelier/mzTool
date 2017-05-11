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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static managerzone.tool.ManagerzoneTool.teams;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class MainPageController implements Initializable {
    public static int teamIndex=-1;
    public static ObservableList<String> data = FXCollections.observableArrayList();
    Mouse mouse = new Mouse();
    
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
        if(!(teamListView.getSelectionModel().getSelectedIndex() == -1)){
        teamIndex=teamListView.getSelectionModel().getSelectedIndex();
        Parent root = FXMLLoader.load(getClass().getResource("LagView.fxml"));
        Scene s = new Scene(root);
        Stage stg =(Stage)((Node)event.getSource()).getScene().getWindow();
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
    private void addTeam(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("addTeam.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(addTeamButton.getScene().getWindow());
        stage.setTitle("Add new player");
        stage.showAndWait();
    }

    @FXML
    private void search(KeyEvent event){
        data.clear();
        for(int i = 0; i < teams.size(); i++){
           if(teams.get(i).getName().toLowerCase().matches("(.*)"+searchBox.getText().toLowerCase()+event.getText().toLowerCase()+"(.*)")){
                data.add(teams.get(i).getName());
            }
        }
        
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

         data.clear();;

        for(int i = 0; i < teams.size();i++){
            data.add(teams.get(i).getName());
        }
       teamListView.setItems(data);

    }

}
