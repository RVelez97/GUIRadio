package Contest;

import java.time.LocalTime;
import java.util.Random;

import classes.Contest;
import classes.ContestStatus;
import classes.GUIRadio;
import classes.UIMethods;
import classes.User;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AssignWinnerScene implements UIMethods{
	Stage sg;
	private VBox mainFormat;
	private HBox fLvlCont,sLvlCont,tLvlCont,buttonCont;
	private ListView participantsList;
	private Button search,ok,back;
	private RadioButton rb1,rb2;
	private Label winnerInfo;
	
	private TextField code,winnerID;
	
	public AssignWinnerScene(Stage stg) {
		this.sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
	}
	

	@Override
	public void createPanes() {
		mainFormat= new VBox();
		fLvlCont = new HBox();
		sLvlCont = new HBox();
		tLvlCont = new HBox();
		buttonCont= new HBox();
		winnerInfo=new Label("Winner: Still not decided...");
		participantsList=new ListView();
		
		code= new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
            	if(winnerID.getText().length()<10) {
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
        
        winnerID= new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
            	if(winnerID.getText().length()<10) {
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
        
		back= new Button("Back");
		search= new Button();
		ok=new Button("Ok");
		rb1=new RadioButton("Set Winner Randomly");
		rb2 = new RadioButton("Set Winner Manually");
		
		ok.setDisable(true);
		rb1.setDisable(true);
		rb2.setDisable(true);
		winnerID.setDisable(true);
		ImageView im=new ImageView(new Image("/resources/images/searchIcon.png"));
		im.setFitHeight(search.getHeight());
		im.setPreserveRatio(true);
		search.setGraphic(im);
	}

	@Override
	public void sortPanes() {
		final ToggleGroup group = new ToggleGroup();
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		rb2.setToggleGroup(group);
		fLvlCont.getChildren().addAll(new Label("Enter the Contest code to consult: "),code,search);
		sLvlCont.getChildren().addAll(rb1,rb2,winnerID,ok);
		tLvlCont.getChildren().add(participantsList);
		buttonCont.getChildren().addAll(winnerInfo,back);
		mainFormat.getChildren().addAll(fLvlCont,sLvlCont,tLvlCont,buttonCont);
		
	}
	
	private void contestAlreadyFinished(ActionEvent event,User u) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText("This contest has already a Winner");
	    alert.setTitle("Warning!");
	    alert.setContentText("Winner: "+u.getId()+" "+u.getName()+" "+u.getLastname());
	    alert.showAndWait();
	}
	
	private void contestWithoutParticipants(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText("This contest has no Participants!");
	    alert.showAndWait();
	}
	
	private void winnerInfo(ActionEvent event,String s) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Information");
	    alert.setContentText(s);
	    alert.showAndWait();
	}
	
	

	@Override
	public void paneInteraction() {
		search.setOnAction(e->{
			Contest cont;
			
			for(Contest c: GUIRadio.contestList) {
				if(Integer.parseInt(code.getText()) == c.getCode()) {
					
					if(!c.getStatus().equals(ContestStatus.FINISHED)) {
						
					if(!c.getParticipants().isEmpty()  ) {
					for(User u: c.getParticipants()) {
						
						
							
						
							participantsList.getItems().add(u.getId()+"  "+u.getName()+" "+u.getLastname());
						
						
						
						
					}
					ok.setDisable(false);
					rb1.setDisable(false);
					rb2.setDisable(false);
					winnerID.setDisable(false);
					}else {
						contestWithoutParticipants(e);
					}
					
					}else {
						cont=c;
						for(User u: cont.getParticipants()) {
							if(u.getWinner()) {
								contestAlreadyFinished(e,u);
							}
						}
						
					}
				}
			}
			
			
		});
		
		
		rb1.setOnAction(e->{
			winnerID.setDisable(true);
			
		});
		
		rb2.setOnAction(e->{
			winnerID.setDisable(false);
		});
		
		ok.setOnAction(e->{
			if(rb1.isSelected()) {
				
				
				if(!GUIRadio.contestList.isEmpty()) {
					for(Contest c: GUIRadio.contestList) {
						if(Integer.parseInt(code.getText()) == c.getCode()) {
							if(!c.getParticipants().isEmpty()  ) {
								Random gen= new Random(LocalTime.now().toSecondOfDay());
								Integer rand = gen.nextInt(0, c.getParticipants().size());
								c.getParticipants().get(rand).setWinner();
								c.setFinished();
								winnerInfo(e,"Winner: "+c.getParticipants().get(rand).getId()+" "+c.getParticipants().get(rand).getName()+""+
										c.getParticipants().get(rand).getLastname()+" Prize: "+c.getPrize());
								
						}
					}
					}
				}
				
			}else {
				if(!GUIRadio.contestList.isEmpty()) {
					for(Contest c: GUIRadio.contestList) {
						if(Integer.parseInt(code.getText()) == c.getCode()) {
							if(!c.getParticipants().isEmpty()) {
							for(User u: c.getParticipants()) {
								if(u.getId().equals(winnerID.getText())) {
									u.setWinner();
									c.setFinished();
									winnerInfo(e,"Winner: "+u.getId()+" "+u.getName()+""+
											u.getLastname()+" Prize:"+c.getPrize());
									
								}
								
								
							}
							}
						}
					}
					}
				
			}
			participantsList.getItems().clear();
			ok.setDisable(true);
			rb1.setDisable(true);
			rb2.setDisable(true);
			winnerID.setDisable(true);
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
