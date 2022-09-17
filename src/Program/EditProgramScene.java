package Program;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import Announcer.AnnouncerScene;
import classes.Announcer;
import classes.Day;
import classes.GUIRadio;
import classes.Program;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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




public class EditProgramScene {
	private Stage sg;
	private VBox mainFormat;
	private HBox dayCont;
	private HBox nameCont;
	private HBox descCont;
	private HBox hourCont;
	private HBox buttonCont;
	private HBox listCont;
	private HBox buttonContainer;
	private HBox fInputCont;
	private Label lProgramToEdit;
	private TextField programToEdit;
	private Button next;
	private Label name;
	private Label monday;
	private Label tuesday;
	private Label wednesday;
	private Label thursday;
	private Label friday;
	private Label saturday;
	private Label sunday;
	private Label description;
	private Label result;
	private TextField nameText;
	private TextArea descriptionArea;
	private CheckBox monBox;
	private CheckBox tueBox;
	private CheckBox wedBox;
	private CheckBox thuBox;
	private CheckBox friBox;
	private CheckBox satBox;
	private CheckBox sunBox;
	
	private ComboBox<String> hour1;
	private ComboBox<String> minutes1;
	private ComboBox<String> hour2;
	private ComboBox<String> minutes2;
	
	private TableView announcers;
	private TableColumn  column0;
	
	private Button submit;
	private Button back;
	
	private Integer index;
	
	
	
	
	
	
	
	
	public EditProgramScene(Stage stg) {
		sg=stg;
		createtPanes();
		panesOrder();
		clickPanes();
		
	}
	
	
	public void createtPanes() {
		mainFormat=new VBox();
		nameCont=new HBox();
		descCont=new HBox();
		dayCont=new HBox();
		hourCont=new HBox();
		buttonCont = new HBox();
		listCont=new HBox();
		
		name=new Label("Program Name:* ");
		monday=new Label("Monday");
		tuesday=new Label("Tuesday");
		wednesday=new Label("Wednesday");
		thursday=new Label("Thursday");
		friday=new Label("Friday");
		saturday=new Label("Saturday");
		sunday=new Label("Sunday");
		description=new Label("Description:* ");
		result= new Label("Result: Waiting for a new Action...");
		
		lProgramToEdit=new Label("Program's ID to be edited: *");
		programToEdit=new TextField(){
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
		fInputCont= new HBox();
		buttonContainer=new HBox();
			
		next= new Button("Next");
		
		descriptionArea = new TextArea();
		nameText=new TextField();
		monBox=new CheckBox();
		tueBox=new CheckBox();
		wedBox=new CheckBox();
		thuBox=new CheckBox();
		friBox=new CheckBox();
		satBox=new CheckBox();
		sunBox=new CheckBox();
		
		submit= new Button("Submit");
		back = new Button("Back");
		
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
		
		
		
		announcers = new TableView();
		announcers.setItems(announcersList);
		column0= new TableColumn("Select");
		TableColumn <Announcer,String> column1= new TableColumn<>("ID");
	    TableColumn <Announcer,String> column2= new TableColumn<>("Name");
	    TableColumn <Announcer,String> column3= new TableColumn<>("Lastname");
	    
	    
	    
	    
	    
	    column1.setCellValueFactory(new PropertyValueFactory<Announcer, String>("id"));
	    column2.setCellValueFactory(new PropertyValueFactory<Announcer, String>("name"));
	    column3.setCellValueFactory(new PropertyValueFactory<Announcer, String>("lastname"));
	    
	    announcers.getColumns().add(column0);
	    announcers.getColumns().add(column1);
	    announcers.getColumns().add(column2);
	    announcers.getColumns().add(column3);
	    
	    announcers.setEditable(true);
	    
		
	    disableEnablePanes(true);
	    
		
		
	}
	
	public void disableEnablePanes(Boolean b) {
		nameCont.setDisable(b);
	    descCont.setDisable(b);
	    dayCont.setDisable(b);
	    hourCont.setDisable(b);
	    listCont.setDisable(b);
		
	}
	
	public void panesOrder() {
		mainFormat.getChildren().addAll(fInputCont,nameCont,descCont,dayCont,hourCont,listCont,result,buttonCont);
		fInputCont.getChildren().addAll(lProgramToEdit,programToEdit,next);
		
		
		nameCont.getChildren().addAll(name,nameText);
		descCont.getChildren().addAll(description,descriptionArea);
		dayCont.getChildren().addAll(new Label("Days on air:* "),monday,monBox,tuesday,tueBox,wednesday,wedBox,thursday,thuBox,friday,friBox,saturday,satBox,sunday,sunBox);
		hourCont.getChildren().addAll(new Label("Schedule:* "),new Label("Start: "),hour1,new Label(" : "),minutes1,new Label("End: "),hour2,new Label(" : "),minutes2);
		listCont.getChildren().add(announcers);
		buttonCont.getChildren().addAll(submit,back);
		
	}
	
	
	public void clickPanes() {
		
		next.setOnAction(e->{
			if(GUIRadio.programList.size()>0) {
				for(Program p: GUIRadio.programList) {
					index=Integer.parseInt(programToEdit.getText());
					if(p.getCod()==index) {
						result.setText("Result: Program found, edit the program's info below...");
						disableEnablePanes(false);
						nameText.setText(p.getName());
						descriptionArea.setText(p.getDescription());
						for(Day v: p.getDays()) {
							if(v.equals(Day.MONDAY)) {
								monBox.setSelected(true);
							}else if(v.equals(Day.TUESDAY)) {
								tueBox.setSelected(true);
							}else if(v.equals(Day.WEDNESDAY)) {
								wedBox.setSelected(true);
							}else if(v.equals(Day.THURSDAY)) {
								thuBox.setSelected(true);
							}else if(v.equals(Day.FRIDAY)) {
								friBox.setSelected(true);
							}else if(v.equals(Day.SATURDAY)) {
								satBox.setSelected(true);
							}else if(v.equals(Day.SUNDAY)) {
								sunBox.setSelected(true);
							}
						}
						String h1 = p.getHourStart().toString().substring(0, 2);
						String m1 = p.getHourStart().toString().substring(3);
						String h2 = p.getHourEnd().toString().substring(0, 2);
						String m2 = p.getHourEnd().toString().substring(3);
						
						
						
						
						for(int i=0;i<24;i++) {
							if(i==Integer.parseInt(h1)) {
								hour1.getSelectionModel().clearAndSelect(i);
							}
							
							if(i==Integer.parseInt(h2)) {
								hour2.getSelectionModel().clearAndSelect(i);
							}
						}
						
						int pos=0;
						
						for(int i=0;i<56;i+=5) {
							
							
							if(i==Integer.parseInt(m1)) {
								minutes1.getSelectionModel().clearAndSelect(pos);
							}
							
							if(i==Integer.parseInt(m2)) {
								minutes2.getSelectionModel().clearAndSelect(pos);
							}
							pos+=1;
						}
						
						for(Announcer a: GUIRadio.programList.get(index-1).getReporters()) {
		                	a.setOn(true);
		                }
						
						
						
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

				            }});
						
						
						
						
						
						pos=0;
						announcers.refresh();
						
						
						
					}else {
						result.setText("Result: There are not Programs with this code...");
					}
				}
			}else {
				result.setText("Result: There are not Programs to be edited...");
				
			}
			
			
		});
		
		submit.setOnAction(e->{
			Boolean pName=false;
			Boolean pDescription=false;
			ArrayList<Day> days = new ArrayList<>(); 
			ArrayList<Announcer> announcersInProgram = new ArrayList<>(); 
			String result2="Result: ";
			
			
			if(nameText.getText()=="") {
				result2+="Error at NAME- ";
			}else {
				pName=true;
			}
			
			if(descriptionArea.getText()=="") {
				result2+="Error at DESCRIPTION- ";
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
				result2+="No Day Selected-";
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
				GUIRadio.programList.get(index-1).setName(nameText.getText());
				GUIRadio.programList.get(index-1).setDescription(descriptionArea.getText());
				GUIRadio.programList.get(index-1).setDays(days);
				GUIRadio.programList.get(index-1).setHourStart(ltStart);
				GUIRadio.programList.get(index-1).setHourEnd(ltEnd);
				GUIRadio.programList.get(index-1).setReporters(announcersInProgram);
				GUIRadio.programList.get(index-1).updateAnnInfo();
				
			}
			
			
			
			
			result.setText(result2);
			
			
			
			
			
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
