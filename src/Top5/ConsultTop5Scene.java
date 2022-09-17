package Top5;

import classes.GUIRadio;
import classes.Top5;
import classes.UIMethods;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsultTop5Scene implements UIMethods{
	
	private Stage sg;
	
	private VBox mainFormat;
	private HBox buttonCont;
	private ListView top5Registered;
	private Button back;
	
	public ConsultTop5Scene(Stage stg) {
		sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
		
	}

	@Override
	public void createPanes() {
		mainFormat=new VBox();
		
		buttonCont= new HBox();
		
		back= new Button("Back");
		
		top5Registered= new ListView();
		
		for(Top5 t: GUIRadio.top5List) {
			top5Registered.getItems().add(t.toString());
			
		}
		
		
	}

	@Override
	public void sortPanes() {
		buttonCont.getChildren().addAll(back);
		mainFormat.getChildren().addAll(top5Registered,buttonCont);
		
	}
	
    

	@Override
	public void paneInteraction() {
		back.setOnAction(e->{
			mainFormat.getChildren().clear();
            Top5Scene t5s = new Top5Scene(sg);
            Pane pn = t5s.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		});
		
	}

	@Override
	public Pane getRoot() {
		return mainFormat;
	}

	
}
