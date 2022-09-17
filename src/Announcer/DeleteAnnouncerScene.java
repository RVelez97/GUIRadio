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

public class DeleteAnnouncerScene implements UIMethods{
	private VBox mainFormat,inputCont;
	private HBox firstCont,secondCont;
	private Stage sg;
	private Label lb;
	private TextField id;
	private Button btnDelete,back;
	
	public DeleteAnnouncerScene(Stage stg) {
		sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
		sceneStyle();
		
	}
	
	@Override
	public void createPanes() {
		mainFormat = new VBox();
		inputCont= new VBox();
		firstCont = new HBox();
		secondCont = new HBox();
		lb = new Label("Announcer's ID to be deleted: ");
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
        id.setFont(Font.font(25));
		btnDelete = new Button("Delete Announcer");
		back= new Button("Back");
		back.setId("back");
		
		
	}
	
	@Override
	public void sortPanes() {
		inputCont.getChildren().addAll(lb,id);
		firstCont.getChildren().addAll(inputCont,btnDelete);
		secondCont.getChildren().add(back);
		mainFormat.getChildren().addAll(firstCont,secondCont);
		
		
		
	}
	
	public void sceneStyle() {
		firstCont.prefHeightProperty().bind(sg.heightProperty().divide(2));
		secondCont.prefHeightProperty().bind(sg.heightProperty().divide(2));
		inputCont.prefHeightProperty().bind(firstCont.heightProperty());
		
		id.prefHeightProperty().bind(inputCont.heightProperty().divide(1));
		lb.prefHeightProperty().bind(inputCont.heightProperty().divide(5));
		btnDelete.prefHeightProperty().bind(sg.heightProperty());
		
		
		firstCont.setSpacing(30);
		
		
		firstCont.setAlignment(Pos.BASELINE_RIGHT);
		secondCont.setAlignment(Pos.BOTTOM_RIGHT);
		back.setAlignment(Pos.BASELINE_RIGHT);
		
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
		btnDelete.setOnAction(e->{
			if(id.getText()!="") {
				
				Integer counts = 0;
				for(Announcer a: GUIRadio.announcerList) {
					if(a.getId().equals(id.getText())) {
						GUIRadio.announcerList.remove(a);
						generalWarning(e,"Announcer was removed successfully");
						counts+=1;
						break;
						
						
					}
					
				}
				
				if(counts==0) {
					generalWarning(e,"No Announcer found with the given ID");
					
				}
				
				
			}
			
			
			
		});
		
		
		back.setOnAction(e->{
			AnnouncerScene as= new AnnouncerScene(sg);
			Pane pn = as.getRoot();
			Scene sc = new Scene(pn);
			sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		});
		
	}
	
	
	
	public Pane getRoot() {
		
		return mainFormat;
	}

	
		

	
	

	
	

}
