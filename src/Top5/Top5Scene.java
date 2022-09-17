package Top5;

import GUI.MainMenuScene;
import classes.GUIRadio;
import classes.UIMethods;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Top5Scene implements UIMethods{
	
	private VBox mainFormat;
	private Button registerTop5;
	private Button consultTop5;
	private Button back;
	private Stage sg;
	
	
	
	public Top5Scene(Stage stg) {
		sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
		sceneStyle();
		
	}

	@Override
	public void createPanes() {
		mainFormat= new VBox();
		registerTop5=new Button("Register Top 5");
		consultTop5=new Button("Consult Top 5");
		back=new Button("Back");
		back.setId("back");
		
	}

	@Override
	public void sortPanes() {
		mainFormat.getChildren().addAll(registerTop5,consultTop5,back);
		
	}
	
	public void sceneStyle() {
		
		mainFormat.setPadding(new Insets(25));
		
		mainFormat.spacingProperty().bind(sg.heightProperty().divide(3));
	}
	
	private void emptyProgramListWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText("There are not Top5 registered to be shown.");
	    alert.showAndWait();
	}

	@Override
	public void paneInteraction() {
		back.setOnAction(e->{
			mainFormat.getChildren().clear();
            MainMenuScene mm = new MainMenuScene(sg);
            Pane pn = mm.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		});
		
		registerTop5.setOnAction(e->{
			mainFormat.getChildren().clear();
            RegisterTop5Scene rt5s = new RegisterTop5Scene(sg);
            Pane pn = rt5s.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
		
		});
		
		
		consultTop5.setOnAction(e->{
			if(!GUIRadio.top5List.isEmpty()) {
			mainFormat.getChildren().clear();
            ConsultTop5Scene rt5s = new ConsultTop5Scene(sg);
            Pane pn = rt5s.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			}else {
				emptyProgramListWarning(e);
			}
		});
		
	}
	
	
	public Pane getRoot() {
		
		return mainFormat;
	}
	
	

}
