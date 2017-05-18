/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerzone.tool;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.text.PlainDocument;
import static managerzone.tool.LagViewController.data1;
import static managerzone.tool.MainPageController.data;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class ManagerzoneTool extends Application {
    
   public static ArrayList<Team> teams = new ArrayList<Team>();
   public static boolean listSet = true;
   
    
    @Override
    public void start(Stage stage) throws Exception {
        /*
         Team escala = new Team("Escala", "Conrad Letelier", 4987000);
         Team onDragons = new Team("Onside Dragons", "Conrad Letelier", 489000);
         Team MFF = new Team("Malmö FF", "Albert Nilsson", 837891);
         Team Val = new Team("Valencia", "Nikola Talsic", 123782);
         Team Bar = new Team("Barcelona", "Albino Leffe", 323122);
         Team Liv = new Team("Liverpool", "Fredde Al'paco", 23931);
         Team Man = new Team("Manchester United", "Moli Grenedo", 323212);
         teams.add(escala);
         teams.add(onDragons);
         teams.add(MFF);
         teams.add(Val);
         teams.add(Bar);
         teams.add(Liv);
         teams.add(Man);
         /*
         Player a = new Player("Mateo Dean", "http://mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Mateo%2520Dean:::22::&atts=6m:9m:3u:1u:1u:2u:1u:3u:7m:10m:2u:6:9&mzv=false");
         Player b = new Player("Alarico Alfaro", "http://mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Alarico%2520Alfaro:::21::&atts=5u:6m:2u:3u:0:1u:9m:2u:3u:1u:5u:4:9&mzv=false");
         Player c = new Player("Aznar Casas", "http://mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Aznar%2520Casas:::16::&atts=4:2u:0:1u:2u:1u:0:2u:1u:3u:1u:0:0&mzv=false");
         Player d = new Player("Guifre Reina", "http://mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Guifre%2520Reina:::18::&atts=4:5:2u:1u:5:0:2u:3u:1u:1u:0:1u:7&mzv=false");
         Player e = new Player("Luis-Javier Sacristan", "http://mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Luis-Javier%2520Sacrist%3fn:::22::&atts=8m:7m:4u:5m:2u:2u:1u:8m:10m:0:0:6:9&mzv=false");
         Player f = new Player("Valdo Mas", "http://mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Valdo%2520Mas:::24::&atts=9m:8m:4m:5m:7m:3u:2u:7m:8m:5m:1u:7:8&mzv=false");
         Player g = new Player("Zeng De Bo", "http://mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Zeng%2520De%2520Bo:::20::&atts=7m:8m:3u:2u:9m:3u:1u:6u:3u:1u:0:4:8&mzv=false");
         Player h = new Player("Hachemi Bendjedid", "http://www.mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Hachemi%20Bendjedid:::22::&atts=9m:7m:5m:6m:3u:1u:1u:7m:8m:7m:1u:5:8&mzv=false");
         Player i = new Player("Paula Lopez", "http://mzimage.com/image.php?lang=se&design=1:000000:000000:::&player=Paula%2520Lopez:::20::&atts=8m:6m:3u:3u:2u:2u:2u:5m:9m:1u:1u:4:9&mzv=false");
         
         
         escala.getPlayers().add(a);
         escala.getPlayers().add(b);
         escala.getPlayers().add(e);
         escala.getPlayers().add(f);
         escala.getPlayers().add(g);
         escala.getPlayers().add(h);
         escala.getPlayers().add(i);
         onDragons.getPlayers().add(c);
         onDragons.getPlayers().add(d);
         */
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
  
}
