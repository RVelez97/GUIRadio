package Contest;

import java.util.Collections;
import java.util.stream.Collectors;

import GUI.MainMenuScene;
import Program.AddProgramScene;
import Program.EditProgramScene;
import Program.SuspendProgramScene;
import classes.Contest;
import classes.ContestStatus;
import classes.GUIRadio;
import classes.Program;
import classes.UIMethods;
import javafx.beans.property.ReadOnlyStringWrapper;
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

public class ContestScene implements UIMethods{

	private VBox mainFormat;
	private HBox fLvlCont,sLvlCont,tableCont;
	private Button showContest,addContest,delContest,enrollNPContest,assignWinner,returnMainMenu;
    
    TableView<Contest> contestInfo;
    ObservableList<Contest> programs;
    TableColumn <Contest,Integer> column1;
    TableColumn <Contest,String> column2,column3;
    TableColumn <Contest,ContestStatus> column4;

    private Stage sg;




    public ContestScene(Stage stg){
        sg = stg;
        createPanes();
        sortPanes();
        paneSizes();
        paneInteraction();

    }
    
    public void createPanes() {
    	mainFormat = new VBox();
	    fLvlCont = new HBox();
	    sLvlCont = new HBox();
	    
	    tableCont = new HBox();
	    showContest = new Button("Show Contest");
	    addContest = new Button("Add Contest");
	    delContest = new Button("Delete Contest");
	    enrollNPContest = new Button("Enroll Participants");
	    assignWinner = new Button("Assign Winner");
	    returnMainMenu = new Button("Return");
	    
	    contestInfo = new TableView<Contest>();
	    programs = FXCollections.observableArrayList();
	    column1= new TableColumn<>("CODE");
	    column2= new TableColumn<>("NAME");
	    column3= new TableColumn<>("PROGRAMS");
	    column4= new TableColumn<>("STATUS");
    	
    }

    public void sortPanes(){
    fLvlCont.getChildren().addAll(showContest,addContest,delContest);
    sLvlCont.getChildren().addAll(enrollNPContest,assignWinner,returnMainMenu);
    tableCont.getChildren().addAll(contestInfo);
    mainFormat.getChildren().addAll(fLvlCont,sLvlCont,tableCont);
    contestInfo.getColumns().add(column1);
    contestInfo.getColumns().add(column2);
    contestInfo.getColumns().add(column3);
    contestInfo.getColumns().add(column4);
    
    
    
     
    Collections.sort(GUIRadio.contestList);
    
    programs.addAll(GUIRadio.contestList);
    
    column1.setCellValueFactory(new PropertyValueFactory<Contest, Integer>("code"));
    column2.setCellValueFactory(new PropertyValueFactory<Contest, String>("name"));
    column3.setCellValueFactory(cellData->cellData.getValue().programsProperty());
    column4.setCellValueFactory(cellData->cellData.getValue().statusProperty());
    
    contestInfo.setItems(programs);
    

    }
    
    public void paneSizes() {
    	showContest.setMaxHeight(Double.MAX_VALUE);
    	addContest.setMaxHeight(Double.MAX_VALUE);
    	delContest.setMaxHeight(Double.MAX_VALUE);
    	returnMainMenu.setMaxHeight(Double.MAX_VALUE);
    	enrollNPContest.setMaxHeight(Double.MAX_VALUE);
    	assignWinner.setMaxHeight(Double.MAX_VALUE);
    	
    	showContest.prefWidthProperty().bind(sg.widthProperty().divide(3));
    	addContest.prefWidthProperty().bind(sg.widthProperty().divide(3));
    	delContest.prefWidthProperty().bind(sg.widthProperty().divide(3));
    	enrollNPContest.prefWidthProperty().bind(sg.widthProperty().divide(3));
    	assignWinner.prefWidthProperty().bind(sg.widthProperty().divide(3));
    	returnMainMenu.prefWidthProperty().bind(sg.widthProperty().divide(3));
    	
    	
    	contestInfo.prefWidthProperty().bind(sg.widthProperty());
    	
    	column1.prefWidthProperty().bind(tableCont.widthProperty().divide(4));
    	column2.prefWidthProperty().bind(tableCont.widthProperty().divide(4));
    	column3.prefWidthProperty().bind(tableCont.widthProperty().divide(4));
    	column4.prefWidthProperty().bind(tableCont.widthProperty().divide(4));
    	
    	fLvlCont.prefHeightProperty().bind(sg.heightProperty().divide(1));
    	sLvlCont.prefHeightProperty().bind(sg.heightProperty().divide(1));
    	tableCont.prefHeightProperty().bind(sg.heightProperty().divide(1));
    	
    	mainFormat.setPadding(new Insets(15));
    	fLvlCont.setPadding(new Insets(15));
    	fLvlCont.setSpacing(15);
    	sLvlCont.setPadding(new Insets(15));
    	sLvlCont.setSpacing(15);
    	tableCont.setPadding(new Insets(15));
    }

    
    private void emptyContestList(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText("Warning, There'nt Contests still registered!");
	    alert.showAndWait();
	}
    public void paneInteraction(){
    	
    	addContest.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainFormat.getChildren().clear();
                AddContestScene acs = new AddContestScene(sg);
                Pane pn = acs.getRoot();
                Scene sc = new Scene(pn);
                sc.getStylesheets().add("/resources/styles.css");
                sg.setScene(sc);

            }
        });
    	
    	showContest.setOnAction(e->{
            	if(!GUIRadio.contestList.isEmpty()) {
                mainFormat.getChildren().clear();
                ShowContestScene scs = new ShowContestScene(sg);
                Pane pn = scs.getRoot();
                Scene sc = new Scene(pn);
                sc.getStylesheets().add("/resources/styles.css");
                sg.setScene(sc);
            	}else {
        			emptyContestList(e);
        		}
        });
    	
    	delContest.setOnAction(e->{
    		if(!GUIRadio.contestList.isEmpty()) {
    		mainFormat.getChildren().clear();
    		DeleteContestScene dcs = new DeleteContestScene(sg);
    		Pane pn = dcs.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
    		}else {
    			emptyContestList(e);
    		}
    		
    	});
    	
    	
    	enrollNPContest.setOnAction(e->{
    		
    		if(!GUIRadio.contestList.isEmpty()) {
    		mainFormat.getChildren().clear();
    		EnrollParticipantsScene eps = new EnrollParticipantsScene(sg);
    		Pane pn = eps.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
    		}else {
    			emptyContestList(e);
    		}
    		
    	});
    	
    	
    	assignWinner.setOnAction(e->{
    		if(!GUIRadio.contestList.isEmpty()) {
    		mainFormat.getChildren().clear();
    		AssignWinnerScene aws = new AssignWinnerScene(sg);
    		Pane pn = aws.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
    		}else {
    			emptyContestList(e);
    		}
    		
    	});
    	
    	
    returnMainMenu.setOnAction(new EventHandler<ActionEvent>(){
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
