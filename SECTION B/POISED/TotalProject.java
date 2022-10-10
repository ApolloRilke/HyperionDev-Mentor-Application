/**
 * Allows for projects to be grouped together with the architect, contractor, and customer
 * @author rusheil
 */
public class TotalProject {

	Project project;
	Person architect;
	Person contractor;
	Person customer;
	
	/**
	 * Constructor to group and reference all project details
	 * @param project project details object
	 * @param architect architect person object
	 * @param contractor contractor person object
	 * @param customer customer person object
	 */
	public TotalProject(Project project, Person architect, Person contractor, Person customer) {
		this.project = project;
		this.architect = architect;
		this.contractor = contractor;
		this.customer = customer;
	}
	
	/**
	 * calls the toString methods from the objects
	 * @return the string details of the objects that make a project
	 */
	public String toString() {
		return project + "\n" + architect + "\n" + contractor + "\n" + customer;
		
	}
}
