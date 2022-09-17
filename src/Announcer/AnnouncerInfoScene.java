package Announcer;

import classes.Announcer;
import classes.GUIRadio;
import classes.UIMethods;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AnnouncerInfoScene implements UIMethods{
	private VBox mainFormat,inputCont;
	private HBox firstCont,secondCont,thirdCont,fourthCont,fifthCont,sixthCont,seventhCont;
	private Label idToConsult,nameResult,lastnameResult,phoneResult,emailResult,socialNetworksResult;
	private TextField id;
	private Stage sg;
	private Button back,consult;
	
	
	public AnnouncerInfoScene(Stage stg) {
		sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
		sceneStyle();
		
	}
	
	@Override
	public void createPanes() {
		id = new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
            	if(id.getText().length()<10) {
                if ("0123456789".contains(text)) {
                    super.replaceText(start, end, text);   
                }
            	}else {
            		super.replaceText(start, end, ""); 
            	}
            }
 
            @Override
            public void replaceSelection(String text) {
                if ("0123456789".contains(text)) {
                    super.replaceSelection(text);
                }
            }
        };
        
        id.setFont(Font.font(25));
		mainFormat = new VBox();
		inputCont = new VBox();
		firstCont=new HBox();
		secondCont=new HBox();
		thirdCont=new HBox();
		fourthCont=new HBox();
		fifthCont=new HBox();
		sixthCont=new HBox();
		seventhCont=new HBox();
		
		idToConsult=new Label("ID to consult: ");
		nameResult=new Label();
		lastnameResult=new Label();
		phoneResult=new Label();
		emailResult=new Label();
		socialNetworksResult=new Label();
		
		back = new Button("Back");
		back.setId("back");
		consult= new Button("Consult");
	}
	
	@Override
	public void sortPanes() {
		inputCont.getChildren().addAll(idToConsult,id);
		firstCont.getChildren().addAll(inputCont,consult);
		secondCont.getChildren().addAll(new Label("NAME:          "),nameResult);
		thirdCont.getChildren().addAll(new Label("LASTNAME:   "),lastnameResult);
		fourthCont.getChildren().addAll(new Label("PHONE:         "),phoneResult);
		fifthCont.getChildren().addAll(new Label("EMAIL:           "),emailResult);
		sixthCont.getChildren().addAll(new Label("SOCIALNETWORKS:  "),socialNetworksResult);
		seventhCont.getChildren().addAll(back);
		mainFormat.getChildren().addAll(firstCont,secondCont,thirdCont,fourthCont,fifthCont,sixthCont,seventhCont);
		
	}
	
	public void sceneStyle(){
		firstCont.prefWidthProperty().bind(sg.widthProperty().divide(3));
		
		
		firstCont.prefHeightProperty().bind(sg.heightProperty().divide(7));
		secondCont.prefHeightProperty().bind(sg.heightProperty().divide(7));
		thirdCont.prefHeightProperty().bind(sg.heightProperty().divide(7));
		fourthCont.prefHeightProperty().bind(sg.heightProperty().divide(7));
		fifthCont.prefHeightProperty().bind(sg.heightProperty().divide(7));
		sixthCont.prefHeightProperty().bind(sg.heightProperty().divide(7));
		seventhCont.prefHeightProperty().bind(sg.heightProperty().divide(7));
		
		consult.prefHeightProperty().bind(firstCont.heightProperty());
		id.prefHeightProperty().bind(firstCont.heightProperty());
		inputCont.prefHeightProperty().bind(firstCont.heightProperty());
		
		secondCont.prefWidthProperty().bind(sg.heightProperty().divide(2));
		thirdCont.prefWidthProperty().bind(sg.heightProperty().divide(2));
		fourthCont.prefWidthProperty().bind(sg.heightProperty().divide(2));
		fifthCont.prefWidthProperty().bind(sg.heightProperty().divide(2));
		sixthCont.prefWidthProperty().bind(sg.heightProperty().divide(2));
		
		
		
		firstCont.setAlignment(Pos.BASELINE_RIGHT);
		seventhCont.setAlignment(Pos.BOTTOM_RIGHT);
		idToConsult.setAlignment(Pos.BOTTOM_LEFT);
		
		firstCont.setSpacing(30);
		secondCont.setSpacing(100);
		thirdCont.setSpacing(100);
		fourthCont.setSpacing(100);
		fifthCont.setSpacing(100);
		sixthCont.setSpacing(100);
		
		mainFormat.setPadding(new Insets(25));
		
	}
	
	public void generalWarning(ActionEvent event,String message) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Result");
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	
	
	@Override
	public void paneInteraction() {
		
		back.setOnAction(e->{
			AnnouncerScene as= new AnnouncerScene(sg);
			Pane pn = as.getRoot();
			Scene sc = new Scene(pn);
			sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		
	});
		
		consult.setOnAction(e->{
			Integer count = 0;
			for(Announcer a: GUIRadio.announcerList) {
				if(a.getId().equals(id.getText())) {
					nameResult.setText(a.getName());
					lastnameResult.setText(a.getLastname());
					phoneResult.setText(a.getCellphone());
					emailResult.setText(a.getEmail());
					socialNetworksResult.setText(a.getSocialNetworks());
					count+=1;
					break;
					
				}
				
			}
			
			
			if(count==0) {
				generalWarning(e,"No Announcer found with the given ID");
			}
			
		});
	}
	
	
	
	
	public Pane getRoot() {
		return mainFormat;
		
	}

	

	
	

	
}
