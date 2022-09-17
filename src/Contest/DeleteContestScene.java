package Contest;

import classes.Contest;
import classes.GUIRadio;
import classes.UIMethods;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteContestScene implements UIMethods{
	
	private Stage sg;
	
	private VBox mainFormat;
	private VBox fLevelCont;
	
	private HBox codDelSection;
	private HBox buttonCont;
	
	private TextField code;
	
	private Button delete;
	private Button back;
	
	public DeleteContestScene(Stage stg) {
		this.sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
		
	}

	@Override
	public void createPanes() {
		mainFormat=new VBox();
		fLevelCont = new VBox();
		
		codDelSection = new HBox();
		buttonCont = new HBox();
		
		code= new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
                if ("0123456789".contains(text)) {
                    super.replaceText(start, end, text);   
                }
            }
 
            @Override
            public void replaceSelection(String text) {
                if ("0123456789".contains(text)) {
                    super.replaceSelection(text);
                }
            }
        };
		
		delete=new Button();
		ImageView im=new ImageView(new Image("/resources/images/trashIcon.png"));
		im.setFitHeight(delete.getHeight());
		im.setPreserveRatio(true);
		delete.setGraphic(im);
		back= new Button("Back");
		
		
	}

	@Override
	public void sortPanes() {
		codDelSection.getChildren().addAll(code,delete);
		fLevelCont.getChildren().addAll(new Label("Contest's Code to be deleted: "),codDelSection);
		buttonCont.getChildren().add(back);
		mainFormat.getChildren().addAll(fLevelCont,buttonCont);
		
	}
	
	private void programCodeNotFoundWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText("Warning, No programs found with the given code!");
	    alert.showAndWait();
	}
	
	
	
	

	@Override
	public void paneInteraction() {
		
		
		delete.setOnAction(e->{
			if(!GUIRadio.contestList.isEmpty()) {
				for(Contest c: GUIRadio.contestList) {
					if(Integer.parseInt(code.getText())== c.getCode()) {
						GUIRadio.contestList.remove(c);
						break;
					}
				}
				
			}
			
		});
		
		back.setOnAction(e->{
			mainFormat.getChildren().clear();
            ContestScene cs = new ContestScene(sg);
            Pane pn = cs.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		});
		
	}

	@Override
	public Pane getRoot() {
		return this.mainFormat;
	}

}
