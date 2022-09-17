package Announcer;


import java.util.Collections;

import GUI.MainMenuScene;
import classes.Announcer;
import classes.GUIRadio;
import classes.UIMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnnouncerScene implements UIMethods{
    private VBox mainFormat;
    private HBox fLvlCont,sLvlCont;
    private Button shAnnouncer,addAnnouncer,delAnnouncer,back;
    
    private TableView<Announcer> announcersInfo;
    private ObservableList<Announcer> announcers;
    private TableColumn <Announcer,String> column1,   column2, column3;

    private Stage sg;




    public AnnouncerScene(Stage stg){
        sg = stg;
        createPanes();
        sortPanes();
        sceneStyle();
        paneInteraction();

    }
    
    @Override
	public void createPanes() {
    	mainFormat = new VBox();
        fLvlCont = new HBox();
        sLvlCont = new HBox();
        
        shAnnouncer = new Button("Show \nAnnouncer");
        addAnnouncer = new Button("Add \nAnnouncer");
        delAnnouncer = new Button("Delete \nAnnouncer");
        back = new Button("Back");
        back.setId("back");
        
        announcersInfo = new TableView<Announcer>();
        announcers = FXCollections.observableArrayList();
        column1= new TableColumn<>("ID");
        column2= new TableColumn<>("NAME");
        column3= new TableColumn<>("LASTNAME");
		
	}
    
    @Override
	public void sortPanes() {
    	fLvlCont.getChildren().addAll(shAnnouncer,addAnnouncer,delAnnouncer,back,announcersInfo);
        sLvlCont.getChildren().addAll(announcersInfo);
        mainFormat.getChildren().addAll(fLvlCont,sLvlCont);
        announcersInfo.getColumns().add(column1);
        announcersInfo.getColumns().add(column2);
        announcersInfo.getColumns().add(column3);
        
        
        
         
        Collections.sort(GUIRadio.announcerList);
        
        announcers.addAll(GUIRadio.announcerList);
        
        column1.setCellValueFactory(new PropertyValueFactory<Announcer, String>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<Announcer, String>("name"));
        column3.setCellValueFactory(new PropertyValueFactory<Announcer, String>("lastname"));
        
        announcersInfo.setItems(announcers);
	}

    
    
    public void sceneStyle() {
    	shAnnouncer.setMaxHeight(Double.MAX_VALUE);
    	addAnnouncer.setMaxHeight(Double.MAX_VALUE);
    	delAnnouncer.setMaxHeight(Double.MAX_VALUE);
    	back.setMaxHeight(Double.MAX_VALUE);
        
    	shAnnouncer.prefWidthProperty().bind(sg.widthProperty().divide(4));
    	addAnnouncer.prefWidthProperty().bind(sg.widthProperty().divide(4));
    	delAnnouncer.prefWidthProperty().bind(sg.widthProperty().divide(4));
    	back.prefWidthProperty().bind(sg.widthProperty().divide(4));
    	
    	announcersInfo.prefWidthProperty().bind(sg.widthProperty());
    	column1.prefWidthProperty().bind(sLvlCont.widthProperty().divide(3));
    	column2.prefWidthProperty().bind(sLvlCont.widthProperty().divide(3));
    	column3.prefWidthProperty().bind(sLvlCont.widthProperty().divide(3));
    	
    	fLvlCont.prefHeightProperty().bind(sg.heightProperty().divide(2.5));
    	sLvlCont.prefHeightProperty().bind(sg.heightProperty().divide(1.5));
    	
    	fLvlCont.setPadding(new Insets(15));
    	sLvlCont.setPadding(new Insets(15));
    	
    	fLvlCont.setSpacing(15);
    	sLvlCont.setSpacing(15);
    	
    }
    
    private void generalWarning(ActionEvent event,String message) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText(message);
	    alert.showAndWait();
	}

    @Override
	public void paneInteraction() {
    	addAnnouncer.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainFormat.getChildren().clear();
                AddAnnouncerScene raw = new AddAnnouncerScene(sg);
                Pane pn = raw.getRoot();
                Scene sc = new Scene(pn);
                sc.getStylesheets().add("/resources/styles.css");
                sg.setScene(sc);

            }
        });
    	
    	shAnnouncer.setOnAction(e->{
    		if(!GUIRadio.announcerList.isEmpty()) {
                mainFormat.getChildren().clear();
                AnnouncerInfoScene ais = new AnnouncerInfoScene(sg);
                Pane pn = ais.getRoot();
                Scene sc = new Scene(pn);
                sc.getStylesheets().add("/resources/styles.css");
                sg.setScene(sc);
    		}else {
    			generalWarning(e,"There's no info to be shown, there must be registered at least one Announcer.");
    		}

            }
        );
    	
    	delAnnouncer.setOnAction(e->{
    		mainFormat.getChildren().clear();
    		DeleteAnnouncerScene das = new DeleteAnnouncerScene(sg);
    		Pane pn = das.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
    		
    	});
    	
    	
    	
    back.setOnAction(new EventHandler<ActionEvent>(){
        public void handle(ActionEvent t){
            mainFormat.getChildren().clear();
            MainMenuScene mm = new MainMenuScene(sg);
            Pane pn = mm.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);

        }
    });

    }

    public Pane getRoot(){
        return mainFormat;
    }

	

	

	
	


}