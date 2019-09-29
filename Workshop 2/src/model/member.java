package model;

public class member {
	private String name;
	private int personalNumber;
	private int id;

	public member(String name, int personalNumber, int id) {
		super();
		this.name = name;
		this.personalNumber = personalNumber;
		this.id = id;
	}
	
	//    ****************** Getters And Setters *************
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(int personalNumber) {
		this.personalNumber = personalNumber;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
