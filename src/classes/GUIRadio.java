package classes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import GUI.MainMenuScene;

public class GUIRadio extends Application {
	private Stage stage;
	private Double wWidth=800.0;
	private Boolean change=false;
	public static ArrayList<Announcer> announcerList = new ArrayList<>();
	public static ArrayList<Program> programList = new ArrayList<>();
	public static ArrayList<Contest> contestList = new ArrayList<>();
	public static ArrayList<Top5> top5List = new ArrayList<>();
	public static Integer idProgram=1;
	public static Integer idContest=1;




    @Override
    public void start(Stage pStage) throws IOException {
    	
    	
    	
    	pStage.setTitle("Radio Canela");
        pStage.setWidth(800);
        pStage.setHeight(600);
        pStage.setResizable(true);
        MainMenuScene principalMenu = new MainMenuScene(pStage);
        Pane root = principalMenu.getRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/resources/styles.css");
        pStage.setScene(scene);
        
        pStage.show();
    }
    


    public static void main(String[] args) {
        launch(args);
    }
    
   
}
