package Top5;

import java.time.LocalDateTime;
import java.util.ArrayList;

import GUI.MainMenuScene;
import classes.GUIRadio;
import classes.Top5;
import classes.UIMethods;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterTop5Scene implements UIMethods{
	private VBox mainFormat;
	private HBox top1Cont;
	private HBox top2Cont;
	private HBox top3Cont;
	private HBox top4Cont;
	private HBox top5Cont;
	private HBox buttonCont;
	
	private TextField top1;
	private TextField top2;
	private TextField top3;
	private TextField top4;
	private TextField top5;
	
	private Button register;
	private Button back;
	private Stage sg;
	
	
	public RegisterTop5Scene(Stage stg) {
		sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
		sceneStyle();
	}

	@Override
	public void createPanes() {
		mainFormat= new VBox();
		
		top1Cont= new HBox();
		top2Cont= new HBox();
		top3Cont= new HBox();
		top4Cont= new HBox();
		top5Cont= new HBox();
		buttonCont=new HBox();
		
		top1=new TextField();
		top2=new TextField();
		top3=new TextField();
		top4=new TextField();
		top5=new TextField();
		
		register= new Button("Register");
		back= new Button("Back");
		
	}

	@Override
	public void sortPanes() {
		top1Cont.getChildren().addAll(new Label("Top1 Song: "),top1);
		top2Cont.getChildren().addAll(new Label("Top2 Song: "),top2);
		top3Cont.getChildren().addAll(new Label("Top3 Song: "),top3);
		top4Cont.getChildren().addAll(new Label("Top4 Song: "),top4);
		top5Cont.getChildren().addAll(new Label("Top5 Song: "),top5);
		buttonCont.getChildren().addAll(register,back);
		mainFormat.getChildren().addAll(top1Cont,top2Cont,top3Cont,top4Cont,top5Cont,buttonCont);
		
	}
	
	
	public void sceneStyle() {
		mainFormat.setPadding(new Insets(25));
		top1.prefHeightProperty().bind(sg.heightProperty().divide(6));
		top2.prefHeightProperty().bind(sg.heightProperty().divide(6));
		top3.prefHeightProperty().bind(sg.heightProperty().divide(6));
		top4.prefHeightProperty().bind(sg.heightProperty().divide(6));
		top5.prefHeightProperty().bind(sg.heightProperty().divide(6));
		
		register.prefHeightProperty().bind(sg.heightProperty().divide(6));
		back.prefHeightProperty().bind(sg.heightProperty().divide(6));
		
	}
	
	private void emptyFieldsWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText("Warning, all the fields must be filled!");
	    alert.showAndWait();
	}
	
	private void top5RegisteredSuccessfullyWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Result");
	    alert.setContentText("New Top5 was registered!");
	    alert.showAndWait();
	}

	@Override
	public void paneInteraction() {
		
		top1.textProperty().addListener((observable,oldValue,newValue)->{
			newValue=newValue.replaceAll("( )+", " ");
			top1.setText(newValue);
			if(newValue.length()>25) {
				top1.setText(newValue.substring(0,25));
				
			}
			
		});
		
		
		top2.textProperty().addListener((observable,oldValue,newValue)->{
			newValue=newValue.replaceAll("( )+", " ");
			top2.setText(newValue);
			if(newValue.length()>25) {
				top2.setText(newValue.substring(0,25));
				
			}
			
		});
		
		top3.textProperty().addListener((observable,oldValue,newValue)->{
			newValue=newValue.replaceAll("( )+", " ");
			top3.setText(newValue);
			if(newValue.length()>25) {
				top3.setText(newValue.substring(0,25));
				
			}
			
		});
		
		top4.textProperty().addListener((observable,oldValue,newValue)->{
			newValue=newValue.replaceAll("( )+", " ");
			top4.setText(newValue);
			if(newValue.length()>25) {
				top4.setText(newValue.substring(0,25));
				
			}
			
		});
		
		top5.textProperty().addListener((observable,oldValue,newValue)->{
			newValue=newValue.replaceAll("( )+", " ");
			top5.setText(newValue);
			if(newValue.length()>25) {
				top5.setText(newValue.substring(0,25));
				
			}
			
		});
		
		
		register.setOnAction(e->{
			String song1=top1.getText();
			String song2=top2.getText();
			String song3=top3.getText();
			String song4=top4.getText();
			String song5=top5.getText();
			
			for(int i=2;i<26;i++) {
				song1=song1.replaceAll(" ".repeat(i), "");
				song2=song2.replaceAll(" ".repeat(i), "");
				song3=song3.replaceAll(" ".repeat(i), "");
				song4=song4.replaceAll(" ".repeat(i), "");
				song5=song5.replaceAll(" ".repeat(i), "");
			}
			
			
			if(song1.equals("")  || song2.equals("") || song3.equals("") || song4.equals("")||  song5.equals("") ) {
				
				emptyFieldsWarning(e);
			}else {
				ArrayList<String> songs = new ArrayList<>();
				songs.add(top1.getText().strip());
				songs.add(top2.getText().strip());
				songs.add(top3.getText().strip());
				songs.add(top4.getText().strip());
				songs.add(top5.getText().strip());
				Top5 newTop5= new Top5(LocalDateTime.now(),songs);
				GUIRadio.top5List.add(newTop5);
				top5RegisteredSuccessfullyWarning(e);
				top1.setText("");
				top2.setText("");
				top3.setText("");
				top4.setText("");
				top5.setText("");
			}
			
		});
		
		
		back.setOnAction(e->{
			mainFormat.getChildren().clear();
            Top5Scene t5s = new Top5Scene(sg);
            Pane pn = t5s.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
		});
		
	}
	
	public Pane getRoot() {
		
		return this.mainFormat;
	}

}
