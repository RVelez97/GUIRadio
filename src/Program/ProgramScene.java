package Program;

import java.util.Collections;
import java.util.stream.Collectors;

import GUI.MainMenuScene;
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

public class ProgramScene implements UIMethods{
	
		private VBox mainFormat;
		private HBox fLvlCont,sLvlCont;
		private Button addProgram,susProgram,editProgram,back;
	    
		private TableView<Program> programsInfo;
		private ObservableList<Program> programs;
		private TableColumn <Program,String> column1,column2,column3;

	    private Stage sg;




	    public ProgramScene(Stage stg){
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
		    addProgram = new Button("Add \nProgram");
		    susProgram = new Button("Suspend \nProgram");
		    editProgram = new Button("Edit \nProgram");
		    back = new Button("Back");
		    back.setId("back");
		    
		    programsInfo = new TableView<Program>();
		    programs = FXCollections.observableArrayList();
		    column1= new TableColumn<>("CODE");
		    column2= new TableColumn<>("NAME");
		    column3= new TableColumn<>("ANNOUNCERS");
	    	
	    }

	    public void sortPanes(){
	    fLvlCont.getChildren().addAll(addProgram,susProgram,editProgram,back,programsInfo);
	    sLvlCont.getChildren().addAll(programsInfo);
	    mainFormat.getChildren().addAll(fLvlCont,sLvlCont);
	    programsInfo.getColumns().add(column1);
	    programsInfo.getColumns().add(column2);
	    programsInfo.getColumns().add(column3);
	    
	    
	    
	     
	    Collections.sort(GUIRadio.announcerList);
	    
	    programs.addAll(GUIRadio.programList.stream().filter(e->e.getDateEnd()==null).collect(Collectors.toList()));
	    
	    column1.setCellValueFactory(new PropertyValueFactory<Program, String>("cod"));
	    column2.setCellValueFactory(new PropertyValueFactory<Program, String>("name"));
	    column3.setCellValueFactory(c->new ReadOnlyStringWrapper(c.getValue().getAnnInfo()));
	    
	    programsInfo.setItems(programs);
	    

	    }
	    
	    public void paneSizes() {
	    	addProgram.setMaxHeight(Double.MAX_VALUE);
	    	susProgram.setMaxHeight(Double.MAX_VALUE);
	    	editProgram.setMaxHeight(Double.MAX_VALUE);
	    	back.setMaxHeight(Double.MAX_VALUE);
	        
	    	addProgram.prefWidthProperty().bind(sg.widthProperty().divide(4));
	    	susProgram.prefWidthProperty().bind(sg.widthProperty().divide(4));
	    	editProgram.prefWidthProperty().bind(sg.widthProperty().divide(4));
	    	back.prefWidthProperty().bind(sg.widthProperty().divide(4));
	    	
	    	programsInfo.prefWidthProperty().bind(sg.widthProperty());
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
	    
	    private void emptyProgramListWarning(ActionEvent event,String string) {
		    Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setHeaderText(null);
		    alert.setTitle("Warning!");
		    alert.setContentText("There are not programs registered to be "+string+".");
		    alert.showAndWait();
		}

	    public void paneInteraction(){
	    	susProgram.setOnAction(e->{
	    		
	    		if(programsInfo.getItems().size()>0) {
	                mainFormat.getChildren().clear();
	                SuspendProgramScene sps = new SuspendProgramScene(sg);
	                Pane pn = sps.getRoot();
	                Scene sc = new Scene(pn);
	                sc.getStylesheets().add("/resources/styles.css");
	                sg.setScene(sc);
	    		}else {
	    			emptyProgramListWarning(e,"deleted");
	    		}
	    		

	            
	        });
	    	
	    	addProgram.setOnAction(new EventHandler<ActionEvent>(){
	            public void handle(ActionEvent t){
	                mainFormat.getChildren().clear();
	                AddProgramScene ais = new AddProgramScene(sg);
	                Pane pn = ais.getRoot();
	                Scene sc = new Scene(pn);
	                sc.getStylesheets().add("/resources/styles.css");
	                sg.setScene(sc);

	            }
	        });
	    	
	    	editProgram.setOnAction(e->{
	    		if(programsInfo.getItems().size()>0) {
	    		mainFormat.getChildren().clear();
	    		EditProgramScene eps = new EditProgramScene(sg);
	    		Pane pn = eps.getRoot();
	            Scene sc = new Scene(pn);
	            sc.getStylesheets().add("/resources/styles.css");
	            sg.setScene(sc);
	    	}else {
    			emptyProgramListWarning(e,"modified");
    		}
	    		
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
