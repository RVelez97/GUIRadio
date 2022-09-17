package classes;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public final class Announcer extends User {

    private  String idNumber;
    private  String name;
    private  String lastname;
    private  String phone;
    private  String email;
    private  String socialNetworks;
    private  BooleanProperty selected;

    public Announcer(String idNumber, String name, String lastname, String phone, String email, String socialNetworks) {
        super(idNumber, name, lastname, phone);
        this.email = email;
        this.socialNetworks = socialNetworks;
        this.selected= new SimpleBooleanProperty(false);
    }

    public String getEmail() {
        return email;
    }

    public String getSocialNetworks() {
        return socialNetworks;
    }
    
    public BooleanProperty getSelected() {
    	
    	return selected;
    }
    
    public void setOn(Boolean b) {
    	this.selected.set(b);
    }
    
    public Boolean getOn() {
    	
    	return this.selected.get();
    }
    
    


    
    

}
