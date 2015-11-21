package phone;

public class Contact {
	private String name;
	private String number; //Numbers will be in the ###-###-#### format
	
	public Contact(String name, String number) {
		this.name = name;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNumber() {
		return number;
	}

}
