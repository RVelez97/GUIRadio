package Contest;

import java.time.LocalDate;
import java.util.ArrayList;

import classes.Announcer;
import classes.Contest;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddContestScene implements UIMethods{
	
	private Stage sg;
	
	private TextField contestName;
	
	private DatePicker startDate;
	private DatePicker endDate;
	
	private TextArea giftDescription;
	
	private VBox mainFormat;
	
	private HBox fieldNameCont;
	private HBox fieldDateCont;
	private HBox rbCont;
	private HBox fieldPrizeDescription;
	private HBox buttonContainer;
	private TableView programs;
	
	private Button registerContest;
	private Button back;
	
	private RadioButton rb1;
	private RadioButton rb2;
	
	public AddContestScene(Stage stg) {
		sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
	}

	@Override
	public void createPanes() {
		mainFormat = new VBox();
		
		contestName = new TextField();
		
		startDate = new DatePicker();
		endDate = new DatePicker();
		
		startDate.getEditor().setDisable(true);
		endDate.getEditor().setDisable(true);
		
		final Callback<DatePicker, DateCell> dayCellFactory = 
	            new Callback<DatePicker, DateCell>() {
	                @Override
	                public DateCell call(final DatePicker datePicker) {
	                	
	                    return new DateCell() {
	                        @Override
	                        public void updateItem(LocalDate item, boolean empty) {
	                            super.updateItem(item, empty);
	                           if(startDate.getValue()!=null) {
	                            if (item.isBefore(
	                                    startDate.getValue().plusDays(1))
	                                ) {
	                                    setDisable(true);
	                                    setStyle("-fx-background-color: #ffc0cb;");
	                                    
	                            } }
	                            
	                    }
	                };
	            }
	                
	                
	        };
	        
	    
		
		endDate.setDayCellFactory(dayCellFactory);
		
		
	        
            
            
        
    
		
		giftDescription = new TextArea();
		
		buttonContainer = new HBox();
		fieldNameCont = new HBox();
		rbCont=new HBox();
		fieldDateCont = new HBox();
		fieldPrizeDescription = new HBox();
		
		registerContest = new Button("Register");
		back = new Button("Back");
		
		rb1=new RadioButton("All Programs");
		rb2=new RadioButton("Personalized Selection");
		
		programs=new TableView();
		ObservableList<Program> programsList=FXCollections.observableArrayList();
		if(classes.GUIRadio.programList.size()>0) {
			for(Program p: classes.GUIRadio.programList) {
				programsList.add(p);
				
			}
		}
		
		programs.setItems(programsList);
		TableColumn  column0= new TableColumn("Select");
	    TableColumn <Program,String> column1= new TableColumn<>("Name");
	    
	    column0.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Program, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<Program, CheckBox> arg) {
            	Program program = arg.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(program.getOn());



                checkBox.selectedProperty().addListener( (ov,old_val, new_val)-> {

                        program.setOn(new_val);

                    });

                return new SimpleObjectProperty<>(checkBox);

            }
            
            
            

        });
	    column1.setCellValueFactory(new PropertyValueFactory<Program, String>("name"));
	    
	    programs.getColumns().add(column0);
	    programs.getColumns().add(column1);
		
		
		
		
	}

	@Override
	public void sortPanes() {
		fieldNameCont.getChildren().addAll(new Label("Contest Name: "),contestName);
		fieldDateCont.getChildren().addAll(new Label("Star Date: "),startDate,new Label("End Date: "),endDate);
		
		final ToggleGroup group = new ToggleGroup();
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		changeProgramStatus(true);
		programs.setDisable(true);
		rb2.setToggleGroup(group);
		rbCont.getChildren().addAll(new Label("In wich programs will be the contest active?: "),rb1,rb2);
		fieldPrizeDescription.getChildren().addAll(new Label("Prize description: "),giftDescription);
		buttonContainer.getChildren().addAll(registerContest,back);
		mainFormat.getChildren().addAll(fieldNameCont,fieldDateCont,rbCont,programs,fieldPrizeDescription,buttonContainer);
		
	}
	
	private void emptyFieldsWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setHeaderText(null);
	    alert.setTitle("Warning!");
	    alert.setContentText("Warning, All fields must be filled!");
	    alert.showAndWait();
	}
	
	private void registeredConfirmationWarning(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setHeaderText(null);
	    alert.setTitle("Operation Result");
	    alert.setContentText("A new Contest was created!");
	    alert.showAndWait();
	}

	@Override
	public void paneInteraction() {
		
		rb1.setOnMouseClicked(e->{
				programs.setDisable(true);
			
			
			changeProgramStatus(true);
			programs.refresh();
			
		});
		
		rb2.setOnMouseClicked(e->{
				programs.setDisable(false);
			
			
			changeProgramStatus(false);
			
		});
		
		registerContest.setOnAction(e->{
			
			ArrayList<Program> programsInContest=new ArrayList<>();
			
			
			
			for(Program p: classes.GUIRadio.programList) {
				if(p.getOn()) {
					programsInContest.add(p);
					p.setOn(false);	
					
				}
				
			}
			
			if(contestName.getText().equals("") | startDate.getValue()==null | endDate.getValue()==null|giftDescription.getText().equals("")) {
				if(contestName.getText().equals("") ) {
					contestName.setStyle("-fx-background-color:#FF1744");
				}
				
				if(startDate.getValue()==null) {
					startDate.setStyle("-fx-background-color:#FF1744");
				}
				
				if(endDate.getValue()==null) {
					endDate.setStyle("-fx-background-color:#FF1744");
				}
				
				if(giftDescription.getText().equals("")) {
					giftDescription.setStyle("-fx-background-color:#FF1744");
				}
				
				emptyFieldsWarning(e);
				
			}else {
				Contest contest = new Contest(contestName.getText(),startDate.getValue(),endDate.getValue(),programsInContest,giftDescription.getText(),new ArrayList<User>());
				GUIRadio.contestList.add(contest);
				GUIRadio.idContest+=1;
				registeredConfirmationWarning(e);
				contestName.setText("");
				startDate.setValue(null);
				endDate.setValue(null);
				giftDescription.setText("");
				
				
			}
			
			
			
			
			programs.refresh();
			
		});
		
		
		contestName.setOnMouseClicked(e->contestName.setStyle("-fx-background-color:#f5f5f5"));
		startDate.setOnMouseClicked(e->startDate.setStyle("-fx-background-color:#f5f5f5"));
		endDate.setOnMouseClicked(e->startDate.setStyle("-fx-background-color:#f5f5f5"));
		giftDescription.setOnMouseClicked(e->startDate.setStyle("-fx-background-color:#f5f5f5"));
		
		back.setOnAction(e->{
			mainFormat.getChildren().clear();
            ContestScene cs = new ContestScene(sg);
            Pane pn = cs.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		});
		
	}
	
	public void changeProgramStatus(Boolean value) {
		for(Program p: classes.GUIRadio.programList) {
				p.setOn(value);	
			
		}
	}

	@Override
	public Pane getRoot() {
		// TODO Auto-generated method stub
		return this.mainFormat;
	}
	
	

}
