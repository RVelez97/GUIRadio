package classes;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Contest implements Comparable<Contest>{
	private Integer code;
	private String name;
	private LocalDate dateStart;
	private LocalDate dateEnd;
	private ArrayList<Program> progWhereExist;
	private String prize;
	private ArrayList<User> participants;
	private  BooleanProperty selected;
	private String programs="";
	private ContestStatus status;
	private final ObjectProperty<String> programs2 = new SimpleObjectProperty<>();
	private final ObjectProperty<ContestStatus> status2 = new SimpleObjectProperty<>();
	
	public Contest(String name, LocalDate dateStart, LocalDate dateEnd, ArrayList<Program> progWhereExist, String prize,
			ArrayList<User> participants) {
		this.code = GUIRadio.idContest;
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.progWhereExist = progWhereExist;
		this.prize = prize;
		this.participants = participants;
		this.selected= new SimpleBooleanProperty(false);
		if(!this.progWhereExist.isEmpty()) {
		for(Program p: progWhereExist) {
			this.programs+=p.getName()+"\n";
		}}
		
		if(this.dateEnd.compareTo(LocalDate.now()) == 0 | this.dateEnd.compareTo(LocalDate.now()) >0) {
			this.status=ContestStatus.ACTIVE;
			
		}else {
			this.status=ContestStatus.PENDING;
		}
	}
	
	public Integer getCode() {
		return this.code;
	}
	public String getName() {
		return name;
	}
	public LocalDate getDateStart() {
		return dateStart;
	}
	public LocalDate getDateEnd() {
		return dateEnd;
	}
	public ArrayList<Program> getProgWhereExist() {
		return progWhereExist;
	}
	public String getPrize() {
		return prize;
	}
	public ArrayList<User> getParticipants() {
		return participants;
	}
	
	 public void setOn(Boolean b) {
	    	this.selected.set(b);
	    }
	    
	    public Boolean getOn() {
	    	
	    	return this.selected.get();
	    }
	    
	    public ContestStatus getStatus() {
	    	return this.status;
	    }
	    
	    public void setFinished() {
	    	this.status=ContestStatus.FINISHED;
	    }
	    
   public String toString() {
	  String out="";
	  out+="Contest Name: "+this.name+"\n";
	  out+="Start Date:   "+this.dateStart.toString()+"\n";
	  out+="End Date:     "+this.dateStart.toString()+"\n";
	  out+="Programs:     \n";
	  if(!this.progWhereExist.isEmpty()) {
	  for(Program p: this.progWhereExist) {
		  out+=p.getName()+"\n";
	  }}else {
		  out+="There are not Programs registered...\n";
	  }
	  
	  out+="Participants:     \n";
	  if(!this.participants.isEmpty()) {
	  for(User u: this.participants) {
		  out+=u.getName()+"\n";
	  }}else {
		  out+="There are not Participants registered...\n";
	  }
	  
	  out+=this.prize;
	  
	  
	  return out;
			  
	  
   }
   
   
   
   public final ObjectProperty<String> programsProperty() {
	   this.programs2.set(this.programs);
       return this.programs2;
   }
   
   public final ObjectProperty<ContestStatus> statusProperty() {
	   this.status2.set(this.status);
       return this.status2;
   }
   
   
   

@Override
public int compareTo(Contest c) {
	return this.name.compareTo(c.code+"");
}
	
	

}
