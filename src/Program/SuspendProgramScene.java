package Program;

import java.time.LocalDateTime;

import classes.GUIRadio;
import classes.Program;
import classes.UIMethods;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SuspendProgramScene implements UIMethods{
	
	private VBox mainFormat;
	private HBox buttonCont;
	private HBox inputCont;
	private Label labelProgram;
	private Label result;
	private TextField programCode;
	private Button suspend;
	private Button back;
	private Stage sg;
	
	public SuspendProgramScene(Stage stg) {
		sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
	}
	
	public void createPanes() {
		labelProgram=new Label("Write the program's code to be deleted:*");
		result=new Label("Result: Waiting for a new action...");
		programCode= new TextField();
		mainFormat=new VBox();
		buttonCont= new HBox();
		inputCont=new HBox();
		suspend=new Button("Suspend");
		back=new Button("Return");
		
	}
	
	public void sortPanes() {
		mainFormat.getChildren().addAll(inputCont,result,buttonCont);
		buttonCont.getChildren().addAll(suspend, back);
		inputCont.getChildren().addAll(labelProgram,programCode);
		
		
	}
	
	
	
	public void paneInteraction() {
		back.setOnAction(e->{
			mainFormat.getChildren().clear();
            ProgramScene ps = new ProgramScene(sg);
            Pane pn = ps.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		});
		
		
		suspend.setOnAction(e->{
				for(Program p: GUIRadio.programList) {
					if(p.getCod()==Integer.parseInt(programCode.getText())) {
						p.setDateEnd(LocalDateTime.now());
						result.setText("Result: Program was suspend succesfully...");
					}else {
						result.setText("Result: There are not Programs with this code...");
					}
				}
			
		});
		
	}
	
	public Pane getRoot() {
		return mainFormat;
		
	}
	
	

}
