package classes;

public class User implements Comparable<User>{
	private String id;
    private String name;
    private String lastname;
    private String cellphone;
    private Boolean winner;

    public User(String id, String name, String lastname, String cellphone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.cellphone = cellphone;
        this.winner=false;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


    public String getLastname() {
        return this.lastname;
    }


    public String getCellphone() {
        return this.cellphone;
    }
    
    public Boolean getWinner() {
    	return this.winner;
    }
    
    public void setWinner() {
    	this.winner=true;
    }

    @Override
    public int compareTo(User u ) {
    	return this.name.compareTo(u.name);
    }
    

}
