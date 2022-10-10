
/**
 * Allows for the 3 main points of reference for the project, either
 * the architect, the contractor, or the customer. Displays the necessary
 * information when called due to the toString method
 * @author rusheil
 */
public class Person {

	// Attributes of Person 
	String type;		// Architect, contractor, or customer, etc
	String name;		
	String telephoneNo;
	String email;
	String address;		// Physical address of person

	/**
	 * Person constructor
	 */
	public Person(String type, String name, String telephoneNo, String email, String address) {
		this.type = type;
		this.name = name;
		this.telephoneNo = telephoneNo;
		this.email = email;
		this.address = address;
	}

	/**
	 * string summary of person object
	 * @return output string summary of person object
	 */
	public String toString() {
		// Get string details of person
		String output = "\n" + type + "'s information: \nName: " + name;
		output += "\nTelephone number: " + telephoneNo;
		output += "\nEmail address: " + email;
		output += "\nAddress: " + address;
		if (type.equalsIgnoreCase("customer")) {
			output += "\n-------------------------------------------";
		}
		return output;
	}
}