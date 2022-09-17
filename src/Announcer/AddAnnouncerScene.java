package Announcer;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.stage.Stage;

public class AddAnnouncerScene implements UIMethods{
	private Stage sg;
	private HBox buttonContainer;
	private VBox mainFormat;
	private TextField id,name,lastname,phone,email,socialNetworks;
	private Button submit,back,clear;
	
	
	public AddAnnouncerScene(Stage stage) {
		sg=stage;
		createPanes();
		sceneStyle();
		sortPanes();
		paneInteraction();
		
	}
	
	@Override
	public void createPanes() {
		mainFormat=new VBox();
		buttonContainer=new HBox();
		
		
		id=new TextField(){
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
		name=new TextField();
		lastname=new TextField();
		phone=new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
            	if(phone.getText().length()<10) {
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
		email=new TextField();
		socialNetworks=new TextField();
		submit =  new Button("Submit");
		back =  new Button("Back");
		back.setId("back");
		clear= new Button("Clear");
		
	}
	
	
	
	@Override
	public void sortPanes() {
		buttonContainer.getChildren().addAll(submit,clear, back);
		buttonContainer.spacingProperty().bind(sg.widthProperty().divide(4.2));
		mainFormat.getChildren().addAll(new Label("ID:*"),id,new Label("NAME:*"),name,new Label("LASTNAME:*"),lastname,
				new Label("PHONE:*"),phone,new Label("EMAIL:*"),email,new Label("SOCIAL NETWORKS:"),socialNetworks,buttonContainer);
		
	}
	
	public void sceneStyle() {
		
		
		
		mainFormat.setPadding(new Insets(sg.heightProperty().divide(100).doubleValue()*10));
		mainFormat.setSpacing(10);
		
		
		id.setPrefHeight(sg.heightProperty().divide(100).floatValue()*10);
		name.setPrefHeight(sg.heightProperty().divide(100).floatValue()*10);
		lastname.setPrefHeight(sg.heightProperty().divide(100).floatValue()*10);
		phone.setPrefHeight(sg.heightProperty().divide(100).floatValue()*10);
		email.setPrefHeight(sg.heightProperty().divide(100).floatValue()*10);
		socialNetworks.setPrefHeight(sg.heightProperty().divide(100).floatValue()*10);
		
		
		buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
		
	}
	
	public void generalWarning(ActionEvent event,String message) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	
	
	@Override
	public void paneInteraction() {
		id.setOnMouseClicked(e ->{
			id.setStyle("-fx-background-color:#f5f5f5");
			id.setStyle("-fx-border-color: #000000");
			
		});
		name.setOnMouseClicked(e ->{
			name.setStyle("-fx-background-color:#f5f5f5");
			name.setStyle("-fx-border-color: #000000");
			
		});
		lastname.setOnMouseClicked(e ->{
			lastname.setStyle("-fx-background-color:#f5f5f5");
			lastname.setStyle("-fx-border-color: #000000");
			
		});
		phone.setOnMouseClicked(e ->{
			phone.setStyle("-fx-background-color:#f5f5f5");
			phone.setStyle("-fx-border-color: #000000");
			
		});
		email.setOnMouseClicked(e ->{
			email.setStyle("-fx-background-color:#f5f5f5");
			email.setStyle("-fx-border-color: #000000");
			
		});
		
		submit.setOnAction(e->{
			String regx = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-]+$";
            Pattern pattern = Pattern.compile(regx);
            Matcher matcher = pattern.matcher(email.getText());
            
			
			
            if(id.getText().length()<10 | name.getText().equals("") | lastname.getText().equals("") |phone.getText().length()<10 |email.getText().equals("") 
            		| !matcher.matches() ) {
            	if(id.getText().length()<10) {
    				id.setStyle("-fx-background-color:#FF1744");
    			}
            	
            	if(name.getText().equals("")){
    				name.setStyle("-fx-background-color:#FF1744");
    				
    			}
            	if(lastname.getText().equals("")){
    				lastname.setStyle("-fx-background-color:#FF1744");
    				
    			}
            	if(phone.getText().length()<10){
    				phone.setStyle("-fx-background-color:#FF1744");
    			}
            	if(email.getText().equals("") | !matcher.matches()  ){
    				email.setStyle("-fx-background-color:#FF1744");
    				
    			}
            	generalWarning(e,"Some fields were not filled correctly!");
            }else {
            	classes.GUIRadio.announcerList.add(new classes.Announcer(id.getText(),name.getText(),lastname.getText(),phone.getText(),email.getText(),""));
            }
				
			
		});
		
		back.setOnMouseClicked(e->{
			AnnouncerScene as= new AnnouncerScene(sg);
			Pane pn = as.getRoot();
			Scene sc = new Scene(pn);
			sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		
	});
		
		clear.setOnMouseClicked(e->{
			id.clear();
			name.clear();
			lastname.clear();
			phone.clear();
			email.clear();
			socialNetworks.clear();
			id.setStyle("-fx-background-color:#f5f5f5");
			id.setStyle("-fx-border-color: #000000");
			name.setStyle("-fx-background-color:#f5f5f5");
			name.setStyle("-fx-border-color: #000000");
			lastname.setStyle("-fx-background-color:#f5f5f5");
			lastname.setStyle("-fx-border-color: #000000");
			phone.setStyle("-fx-background-color:#f5f5f5");
			phone.setStyle("-fx-border-color: #000000");
			email.setStyle("-fx-background-color:#f5f5f5");
			email.setStyle("-fx-border-color: #000000");
			
		});
		
	} 
	
	
	public Pane getRoot(){
        return mainFormat;
    }

	

	
	

}
