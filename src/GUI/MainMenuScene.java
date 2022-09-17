package GUI;

import Announcer.AnnouncerScene;
import Contest.ContestScene;
import Program.ProgramScene;
import Top5.Top5Scene;
import classes.GUIRadio;
import classes.UIMethods;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuScene implements UIMethods{
    private VBox mainFormat;
    private HBox fLvlCont,sLvlCont;
    private Button annButton,progButton,contButton,top5Button,exitButton;
    private Stage sg;



    public  MainMenuScene(Stage stg){
        sg=stg;
        createPanes();
        sortPanes();
        sceneStyle();
        paneInteraction();

    }

    @Override
    public void createPanes(){
    	mainFormat = new VBox();
    	
        annButton = new Button("Announcers");
        progButton = new Button("Programs");
        contButton = new Button("Contests");
        top5Button = new Button("Top 5");
        exitButton = new Button("Exit");
        exitButton.setId("back");
        
        fLvlCont= new HBox();
        sLvlCont= new HBox();
        
    }
    
    @Override
	public void sortPanes() {
    	fLvlCont.getChildren().addAll(annButton,progButton,contButton);
    	sLvlCont.getChildren().addAll(top5Button,exitButton);
		mainFormat.getChildren().addAll(fLvlCont,sLvlCont);
		
	}
    
    public void sceneStyle() {
    	annButton.setMaxHeight(Double.MAX_VALUE);
        progButton.setMaxHeight(Double.MAX_VALUE);
        contButton.setMaxHeight(Double.MAX_VALUE);
        top5Button.setMaxHeight(Double.MAX_VALUE);
        exitButton.setMaxHeight(Double.MAX_VALUE);
        
        fLvlCont.prefHeightProperty().bind(sg.heightProperty().divide(3));
        sLvlCont.prefHeightProperty().bind(sg.heightProperty().divide(3));
        
        annButton.prefWidthProperty().bind(sg.widthProperty().divide(3));
        progButton.prefWidthProperty().bind(sg.widthProperty().divide(3));
        contButton.prefWidthProperty().bind(sg.widthProperty().divide(3));
        top5Button.prefWidthProperty().bind(sg.widthProperty().divide(3));
        exitButton.prefWidthProperty().bind(sg.widthProperty().divide(3));
        
        fLvlCont.setPadding(new Insets(15));
        sLvlCont.setPadding(new Insets(15));
        
        fLvlCont.setSpacing(15);
        sLvlCont.setSpacing(15);
        sLvlCont.setAlignment(Pos.CENTER);
        mainFormat.setAlignment(Pos.CENTER);
    	
    }
    
    
    private void generalWarning(ActionEvent event,String message) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText(message);
	    alert.showAndWait();
	}

    @Override
	public void paneInteraction(){
        annButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainFormat.getChildren().clear();
                AnnouncerScene ac = new AnnouncerScene(sg);
                Pane p = ac.getRoot();
                Scene sc = new Scene(p);
                sc.getStylesheets().add("/resources/styles.css");
                sg.setScene(sc);
            }
        });

        progButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
            	mainFormat.getChildren().clear();
                ProgramScene ac = new ProgramScene(sg);
                Pane p = ac.getRoot();
                Scene sc = new Scene(p);
                sc.getStylesheets().add("/resources/styles.css");
                sg.setScene(sc);

            }
        });

        contButton.setOnAction(e->{
        	if(!GUIRadio.programList.isEmpty()) {
                mainFormat.getChildren().clear();
                ContestScene cs = new ContestScene(sg);
                Pane p = cs.getRoot();
                Scene sc = new Scene(p);
                sc.getStylesheets().add("/resources/styles.css");
                sg.setScene(sc);
        	}else {
        		generalWarning(e,"To proceed to this option, there must be at least one Program registered!");
        	}
            });

        top5Button.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainFormat.getChildren().clear();
                Top5Scene t5s = new Top5Scene(sg);
                Pane p = t5s.getRoot();
                Scene sc = new Scene(p);
                sc.getStylesheets().add("/resources/styles.css");
                sg.setScene(sc);
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                sg.close();
            }
        });
    }

    public Pane getRoot(){
        return mainFormat;
    }

	

	

	 


}
