package classes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Program extends RadioActivity {
	
	
	private Integer cod;
    private ArrayList<Day> days;
    private LocalTime hourStart;
    private LocalTime hourEnd;
    private ArrayList<Announcer> reporters;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
	private String annInfo;
	private SimpleStringProperty annReferenceColumn;
	private BooleanProperty selected;


    public Program( String name, String description, ArrayList<Day> days, LocalTime hourStart, LocalTime hourEnd, ArrayList<Announcer> reporters, LocalDateTime dateStart) {
        super(name,description,dateStart);
        this.cod=GUIRadio.idProgram;
        this.days = days;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.reporters = reporters;
        this.annInfo="";
        
        for(Announcer a: reporters) {
        	this.annInfo += a.getId()+" "+a.getName()+" "+a.getLastname()+"\n";
        }
        this.annReferenceColumn=new SimpleStringProperty(this.annInfo);
        this.selected= new SimpleBooleanProperty(false);
    }

    public Integer getCod() {
    	return this.cod;
    }

    public String getAnnInfo() {
    	
    	return this.annReferenceColumn.get();
    }
    
    public void updateAnnInfo() {
    	String newValue="";
    	for(Announcer a: reporters) {
    		newValue += a.getId()+" "+a.getName()+" "+a.getLastname()+"\n";
        }
    	this.annReferenceColumn.set(newValue);
    }
    
    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> dias) {
        this.days = dias;
    }

    public LocalTime getHourStart() {
        return hourStart;
    }

    public void setHourStart(LocalTime hourStart) {
        this.hourStart = hourStart;
    }

    public LocalTime getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(LocalTime hourEnd) {
        this.hourEnd = hourEnd;
    }

    public ArrayList<Announcer> getReporters() {
        return reporters;
    }

    public void setReporters(ArrayList<Announcer> reporters) {
        this.reporters = reporters;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    
    public void setOn(Boolean b) {
    	this.selected.set(b);
    }
    
    public Boolean getOn() {
    	
    	return this.selected.get();
    }
    
    
    
    
}
