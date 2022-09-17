package Contest;

import GUI.MainMenuScene;
import classes.Contest;
import classes.GUIRadio;
import classes.UIMethods;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowContestScene implements UIMethods{
	Stage sg;
	private VBox mainFormat;
	private HBox fLvlCont;
	private HBox sLvlCont;
	private HBox tLvlCont;
	private ListView contestInfo;
	private Button search;
	private Button back;
	
	private TextField code;
	
	
	
	
	
	public ShowContestScene(Stage stg) {
		this.sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
		
	}
	
	
	
	public void createPanes() {
		mainFormat= new VBox();
		fLvlCont = new HBox();
		sLvlCont = new HBox();
		tLvlCont = new HBox();
		contestInfo=new ListView();
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
		back= new Button("Back");
		search= new Button();
		ImageView im=new ImageView(new Image("/resources/images/searchIcon.png"));
		im.setFitHeight(search.getHeight());
		im.setPreserveRatio(true);
		search.setGraphic(im);
		
		
		
		
	}



	@Override
	public void sortPanes() {
		fLvlCont.getChildren().addAll(new Label("Enter the Contest code to consult: "),code,search);
		sLvlCont.getChildren().add(contestInfo);
		tLvlCont.getChildren().add(back);
		mainFormat.getChildren().addAll(fLvlCont,sLvlCont,tLvlCont);
		
	}



	@Override
	public void paneInteraction() {
		search.setOnAction(e->{
			contestInfo.getItems().clear();
			if(!GUIRadio.contestList.isEmpty()) {
			for(Contest c: GUIRadio.contestList) {
				if(Integer.parseInt(code.getText()) == c.getCode()) {
				contestInfo.getItems().add(c.toString());
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
		return mainFormat;
	}
}
