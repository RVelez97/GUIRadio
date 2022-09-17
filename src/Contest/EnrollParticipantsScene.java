package Contest;

import java.util.ArrayList;

import classes.Announcer;
import classes.Contest;
import classes.ContestStatus;
import classes.GUIRadio;
import classes.Program;
import classes.UIMethods;
import classes.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EnrollParticipantsScene implements UIMethods{
	
	private Stage sg;
	
	private VBox mainFormat;
	
	private HBox buttonCont;
	
	private TextField id;
	private TextField name;
	private TextField lastName;
	private TextField phone;
	
	private TableView contestList;
    private TableColumn  column0;
    private TableColumn <Contest,Integer> column1;
    private TableColumn <Contest,String> column2;
    
    private Button submit;
    private Button back;
    
    private ArrayList<String> idsAnn;
	
	public EnrollParticipantsScene(Stage stg) {
		this.sg=stg;
		loadData();
		createPanes();
		sortPanes();
		paneInteraction();
	}
	
	public void loadData() {
		idsAnn=new ArrayList<>();
		for(Announcer a: GUIRadio.announcerList) {
			idsAnn.add(a.getId());
			
		}
	}

	@Override
	public void createPanes() {
		ObservableList<Contest> contests=FXCollections.observableArrayList();
		for(Contest c: GUIRadio.contestList) {
			if(c.getStatus().equals(ContestStatus.ACTIVE) | c.getStatus().equals(ContestStatus.PENDING)) {
				contests.add(c);
			}
		}
		
		mainFormat= new VBox();
		buttonCont = new HBox();
		
		submit = new Button("Submit");
		back = new Button("Back");
		
		id= new TextField(){
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
		name= new TextField();
		lastName= new TextField();
		
		
		phone= new TextField(){
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
		
		
		contestList= new TableView();
		contestList.setItems(contests);
		
		column0= new TableColumn("SELECT");
		column1= new TableColumn<>("ID");
	    column2= new TableColumn<>("NAME");
	    
	    column0.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contest, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<Contest, CheckBox> arg) {
            	Contest cont = arg.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(cont.getOn());



                checkBox.selectedProperty().addListener( (ov,old_val, new_val)-> {
                	cont.setOn(new_val);
                    });

                return new SimpleObjectProperty<>(checkBox);

            }
            
            
            

        });
	    
	    column1.setCellValueFactory(new PropertyValueFactory<Contest, Integer>("code"));
	    column2.setCellValueFactory(new PropertyValueFactory<Contest, String>("name"));
	    
	    contestList.getColumns().add(column0);
	    contestList.getColumns().add(column1);
	    contestList.getColumns().add(column2);
	    
	    
		
	}
	
	private void idOfAnnouncerWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setHeaderText(null);
	    alert.setTitle("ID Error");
	    alert.setContentText("The given ID is related to an Announcer!");
	    alert.showAndWait();
	}
	
	private void incorrectFilledFieldWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Field Error");
	    alert.setContentText("Some Fields are empty or not correctly filled!");
	    alert.showAndWait();
	}
	
	private void registerInformationWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Information");
	    alert.setContentText("The new Participant will be registered on the Contests selected, only if there are not another Participant with the same ID.");
	    alert.showAndWait();
	}

	@Override
	public void sortPanes() {
		buttonCont.getChildren().addAll(submit,back);
		mainFormat.getChildren().addAll(new Label("ID:"),id,new Label("NAME:"),name,new Label("LASTNAME:"),lastName,new Label("PHONE:"),phone,contestList,buttonCont);
		
	}

	@Override
	public void paneInteraction() {
		
		submit.setOnAction(e->{
			if(idsAnn.contains(id.getText())) {
				
				idOfAnnouncerWarning(e);
			}
			
			if(id.getText().equals("") | id.getText().length()<10 | phone.getText().equals("") | phone.getText().length()<10 | name.getText().equals("") | lastName.getText().equals("") ) {
				if(id.getText().equals("") | id.getText().length()<10) {
					id.setStyle("-fx-background-color:#FF1744");	
				}
				
				if(phone.getText().equals("") | phone.getText().length()<10) {
					phone.setStyle("-fx-background-color:#FF1744");	
				}
				
				if(name.getText().equals("")) {
					name.setStyle("-fx-background-color:#FF1744");	
				}
				
				if(lastName.getText().equals("")) {
					lastName.setStyle("-fx-background-color:#FF1744");	
				}
				
				incorrectFilledFieldWarning(e);
			}else {
				registerInformationWarning(e);
				for(Contest c: GUIRadio.contestList) {
					if(c.getOn()) {
						if(!c.getParticipants().isEmpty()) {
							Integer i=0;
						for(User u: c.getParticipants()) {
							if(u.getId().equals(id.getText())) {
								i+=1;
							}
							
						}
						
						if(i==0) {
							
							c.getParticipants().add(new User(id.getText(),name.getText(),lastName.getText(),phone.getText()));
						}
						}else {
						
						c.getParticipants().add(new User(id.getText(),name.getText(),lastName.getText(),phone.getText()));
					}
						}
					
				}
				
			}
			
			for(Contest c:GUIRadio.contestList) {
				c.setOn(false);
			}
			
			contestList.refresh();
			
		});
		
		
		back.setOnAction(e->{
			mainFormat.getChildren().clear();
            ContestScene cs = new ContestScene(sg);
            Pane pn = cs.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		});
		
		id.setOnMouseClicked(e->id.setStyle("-fx-background-color:#f5f5f5"));
		name.setOnMouseClicked(e->name.setStyle("-fx-background-color:#f5f5f5"));
		lastName.setOnMouseClicked(e->lastName.setStyle("-fx-background-color:#f5f5f5"));
		phone.setOnMouseClicked(e->phone.setStyle("-fx-background-color:#f5f5f5"));
		
	}

	@Override
	public Pane getRoot() {
		return this.mainFormat;
	}

}
