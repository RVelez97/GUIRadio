package Program;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import classes.Announcer;
import classes.Day;
import classes.GUIRadio;
import classes.Program;
import classes.UIMethods;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddProgramScene implements UIMethods{
	private Stage sg;
	private VBox mainFormat;
	private HBox dayCont, nameCont,descCont,hourCont,buttonCont,listCont,monCon,tueCon,wedCon,thuCon,friCon,satCon,sunCon,spacer;
	
	private Label name, monday,tuesday,wednesday,thursday,friday,saturday,sunday,description;
	private TextField nameLabel;
	private TextArea descriptionArea;
	private CheckBox monBox, tueBox, wedBox, thuBox,friBox,satBox,sunBox;
	
	private ComboBox<String> hour1,minutes1,hour2,minutes2;
	
	private TableView<Announcer> announcers;
	
	private TableColumn  <Announcer,CheckBox> column0;
	private TableColumn <Announcer,String> column1,column2,column3;
	
	private Button submit,back;
	
	
	
	
	
	
	
	public AddProgramScene(Stage stg) {
		this.sg=stg;
		createPanes();
		sortPanes();
		paneInteraction();
		sceneStyle();
		
	}
	
	@Override
	public void createPanes() {
		mainFormat=new VBox();
		nameCont=new HBox();
		descCont=new HBox();
		dayCont=new HBox();
		hourCont=new HBox();
		buttonCont = new HBox();
		listCont=new HBox();
		monCon=new HBox();
		tueCon=new HBox();
		wedCon=new HBox();
		thuCon=new HBox();
		friCon=new HBox();
		satCon=new HBox();
		sunCon=new HBox();
		spacer=new HBox();
		
		name=new Label("Program Name:* ");
		monday=new Label("Monday");
		tuesday=new Label("Tuesday");
		wednesday=new Label("Wednesday");
		thursday=new Label("Thursday");
		friday=new Label("Friday");
		saturday=new Label("Saturday");
		sunday=new Label("Sunday");
		description=new Label("Description:* ");
		
		descriptionArea = new TextArea();
		nameLabel=new TextField();
		monBox=new CheckBox();
		tueBox=new CheckBox();
		wedBox=new CheckBox();
		thuBox=new CheckBox();
		friBox=new CheckBox();
		satBox=new CheckBox();
		sunBox=new CheckBox();
		
		submit= new Button("Submit");
		back = new Button("Back");
		back.setId("back");
		
		ObservableList<String> hours=FXCollections.observableArrayList();
		ObservableList<String> minutes=FXCollections.observableArrayList();
		ObservableList<Announcer> announcersList=FXCollections.observableArrayList();
		for(int i=0;i<24;i++) {
			if(i<10) {
				hours.add("0"+i+"");
			}else {
				hours.add(i+"");	
			}
			
		}
		
		for(int i=0;i<56;i+=5) {
			if(i<10) {
				minutes.add("0"+i+"");
			}else {
				minutes.add(i+"");	
			}
			
		}
		
		if(classes.GUIRadio.announcerList.size()>0) {
			for(Announcer a: classes.GUIRadio.announcerList) {
				announcersList.add(a);
				
			}
		}
		
		hour1 = new ComboBox<>(hours);
		hour1.getSelectionModel().selectFirst();;
		hour2 = new ComboBox<>(hours);
		hour2.getSelectionModel().selectFirst();
		minutes1 = new ComboBox<>(minutes);
		minutes1.getSelectionModel().selectFirst();
		minutes2 = new ComboBox<>(minutes);
		minutes2.getSelectionModel().selectFirst();
		
		
		
		announcers = new TableView<Announcer>();
		announcers.setItems(announcersList);
		column0= new TableColumn<>("Select");
		column1= new TableColumn<>("ID");
		column2= new TableColumn<>("NAME");
		column3= new TableColumn<>("LASTNAME");
	    
	    
	    
	    column0.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Announcer, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<Announcer, CheckBox> arg) {
                Announcer user = arg.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(user.getOn());



                checkBox.selectedProperty().addListener( (ov,old_val, new_val)-> {

                        user.setOn(new_val);

                    });

                return new SimpleObjectProperty<>(checkBox);

            }
            
            
            

        });
	    
	    column1.setCellValueFactory(new PropertyValueFactory<Announcer, String>("id"));
	    column2.setCellValueFactory(new PropertyValueFactory<Announcer, String>("name"));
	    column3.setCellValueFactory(new PropertyValueFactory<Announcer, String>("lastname"));
	    
	    announcers.getColumns().add(column0);
	    announcers.getColumns().add(column1);
	    announcers.getColumns().add(column2);
	    announcers.getColumns().add(column3);
	    
	    announcers.setEditable(true);
	    
		
		
		
		
		
		
		
		
		
	}
	
	@Override
	public void sortPanes() {
		mainFormat.getChildren().addAll(nameCont,descCont,dayCont,hourCont,listCont,buttonCont);
		
		nameCont.getChildren().addAll(name,nameLabel);
		descCont.getChildren().addAll(description,descriptionArea);
		dayCont.getChildren().addAll(new Label("Days on air:* "),monCon,tueCon,wedCon,thuCon,friCon,satCon,sunCon);
		
		monCon.getChildren().addAll(monday,monBox);
		tueCon.getChildren().addAll(tuesday,tueBox);
		wedCon.getChildren().addAll(wednesday,wedBox);
		thuCon.getChildren().addAll(thursday,thuBox);
		friCon.getChildren().addAll(friday,friBox);
		satCon.getChildren().addAll(saturday,satBox);
		sunCon.getChildren().addAll(sunday,sunBox);
		hourCont.getChildren().addAll(new Label("Schedule:* "),new Label("Start: "),hour1,new Label(" : "),minutes1,new Label("End: "),hour2,new Label(" : "),minutes2);
		listCont.getChildren().add(announcers);
		buttonCont.getChildren().addAll(submit,spacer,back);
		
	}
	
	public void sceneStyle() {
		
		mainFormat.setPadding(new Insets(25));
		mainFormat.setSpacing(15);
		
		nameLabel.prefWidthProperty().bind(sg.widthProperty().divide(1));
		name.prefWidthProperty().bind(sg.widthProperty().divide(2));
		descriptionArea.prefWidthProperty().bind(sg.widthProperty().divide(1));
		description.prefWidthProperty().bind(sg.widthProperty().divide(2));
		dayCont.prefWidthProperty().bind(sg.widthProperty());
		spacer.prefWidthProperty().bind(buttonCont.widthProperty().divide(1.3));
		
		double spacing=monCon.getLayoutX();
		System.out.println(spacing);
		
		announcers.prefWidthProperty().bind(sg.widthProperty());
		column0.prefWidthProperty().bind(sg.widthProperty().divide(4));
		column1.prefWidthProperty().bind(sg.widthProperty().divide(4));
		column2.prefWidthProperty().bind(sg.widthProperty().divide(4));
		column3.prefWidthProperty().bind(sg.widthProperty().divide(4));
		
		
		
		
		//height
		nameCont.prefHeightProperty().bind(sg.heightProperty().divide(6));
		descCont.prefHeightProperty().bind(sg.heightProperty().divide(6));
		dayCont.prefHeightProperty().bind(sg.heightProperty().divide(6));
		hourCont.prefHeightProperty().bind(sg.heightProperty().divide(6));
		listCont.prefHeightProperty().bind(sg.heightProperty().divide(1.5));
		buttonCont.prefHeightProperty().bind(sg.heightProperty().divide(6));
		
		buttonCont.setAlignment(Pos.CENTER);
		back.setAlignment(Pos.BASELINE_RIGHT);
		
	}
	
	
	@Override
	public void paneInteraction() {
		
		submit.setOnAction(e->{
			Boolean pName=false;
			Boolean pDescription=false;
			ArrayList<Day> days = new ArrayList<>(); 
			ArrayList<Announcer> announcersInProgram = new ArrayList<>(); 
			
			
			
			if(nameLabel.getText()=="") {
			}else {
				pName=true;
			}
			
			if(descriptionArea.getText()=="") {
			}else {
				pDescription=true;
			}
			
			if(monBox.isSelected()) {
				days.add(Day.MONDAY);
			}
			
			if(tueBox.isSelected()) {
				days.add(Day.TUESDAY);
			}
			
			if(wedBox.isSelected()) {
				days.add(Day.WEDNESDAY);
			}
			
			if(thuBox.isSelected()) {
				days.add(Day.THURSDAY);
			}
			
			if(friBox.isSelected()) {
				days.add(Day.FRIDAY);
			}
			
			if(satBox.isSelected()) {
				days.add(Day.SATURDAY);
			}
			
			if(sunBox.isSelected()) {
				days.add(Day.SUNDAY);
			}
			
			if(days.size()==0) {
			}
			
			LocalTime ltStart=LocalTime.parse(hour1.getValue()+":"+minutes1.getValue());
			LocalTime ltEnd=LocalTime.parse(hour2.getValue()+":"+minutes2.getValue());
			
			
			
			
			for(Announcer a: classes.GUIRadio.announcerList) {
				if(a.getOn()) {
					announcersInProgram.add(a);
					a.setOn(false);	
					
				}
				
			}
			
			if(pName & pDescription & days.size()>0 ) {
				GUIRadio.programList.add(new Program(nameLabel.getText(),descriptionArea.getText(),days,ltStart,ltEnd,announcersInProgram,LocalDateTime.now()));
				GUIRadio.idProgram+=1;
			}
			
			
			
			
			
			
			
			
			
			
			announcers.refresh();
			
			
			
		});
		
		back.setOnAction(e->{
			mainFormat.getChildren().clear();
            ProgramScene ps = new ProgramScene(sg);
            Pane pn = ps.getRoot();
            Scene sc = new Scene(pn);
            sc.getStylesheets().add("/resources/styles.css");
            sg.setScene(sc);
			
		});
		
	}
	
	
	public Pane getRoot() {
		return mainFormat;
		
	}


	


	
	


	
	

}
