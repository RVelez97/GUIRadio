package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Top5 {
	private LocalDateTime  registeredDate;
	private ArrayList<String> songs;
	public Top5(LocalDateTime registeredDate, ArrayList<String> songs) {
		
		this.songs = songs;
		this.registeredDate = registeredDate;
		
		
	}
	public LocalDateTime getRegisteredDate() {
		return registeredDate;
	}
	public ArrayList<String> getSongs() {
		return songs;
	}
	
	
	public String toString() {
		String text="Date: ";
		int count=0;
		for(String s:songs) {
			if(count==0) {
				text+=this.registeredDate.toLocalDate().toString()+"	"+this.registeredDate.toLocalTime().toString().substring(0,8)+"\n";
				text+=" ".repeat(this.registeredDate.toString().length()-1)+"	"+s+"\n" ;
			}else {
				text+=" ".repeat(this.registeredDate.toString().length()-1) +"	"+s+"\n";
			}
			
			count+=1;
		}
		
		return text;
	}
	
	
	

}
