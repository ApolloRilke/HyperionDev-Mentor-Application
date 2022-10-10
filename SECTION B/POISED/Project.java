import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Allows for structuring all necessary project details, project title,
 * project number, building type, etc.
 * @author rusheil
 *
 */
public class Project {

	// Attributes
	String projectNo;
	String projectName;
	String buildingType;
	String projectAddress;
	String erfNo;
	private double totalFee;
	private double feePaid;
	String deadline;
	private boolean isFinishedy;
	String comDate;


	/**
	 * Constructor for the project object
	 * @param projectNo project number
	 * @param projectName project title
	 * @param buildingType building title
	 * @param projectAddress project address
	 * @param erfNo ERF number
	 * @param totalFee total cost of the project
	 * @param feePaid total balance customer has paid to date
	 * @param deadline duedate for project completion
	 * @param isFinished validates if the project is complete
	 */
	public Project(String projectNo, String projectName, String buildingType, 
			String projectAddress, String erfNo, double totalFee, double feePaid, String deadline, 
			boolean isFinished, String comDate) {
		this.projectName = projectName;
		this.projectNo = projectNo;
		this.buildingType = buildingType;
		this.projectAddress = projectAddress;
		this.erfNo = erfNo;
		this.totalFee = totalFee;
		this.feePaid = feePaid;
		this.deadline = deadline;
		this.isFinishedy = isFinished;
		this.comDate = comDate;
	}

	public boolean getCompleted() {
		return isFinishedy;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public double getFeePaid() {
		return feePaid;
	}

	
	public String getFeePaidDisplay() {
		return String.format("%.2f", feePaid);
	}

	public String getTotalFeeDisplay() {
		return String.format("%.2f", totalFee);
	}

	/**
	 * Changes the completed validator to true
	 * @return isFinished changes the completed parameter to true
	 */
	public boolean isFinished(String date) {
		comDate = date;
		return isFinishedy = true;
	}

	public void setFeePaid(double totalFee2) {
		feePaid = totalFee2;
	}

	public void setTotalFee(double totalFee2) {
		totalFee = totalFee2;
	}

	/**
	 * string details of the project details
	 * @return output string details of the project details
	 */
	public String toString() {

		// Initialise today's date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String today = dtf.format(localDate);

		// Print out string output of project
		String output = "\n-------------------------------------------";
		output += "\nProject no: " + projectNo;
		output += "\nProject name: " + projectName;
		
		// Display appropriate message if completed, overdue, or in progress
		if ((deadline.compareTo(today) < 0) && (!isFinishedy)) {
			output += "\nThis task is overdue!";
		}
		else if (!isFinishedy) {
			output += "\nThis project is still in progress.";
		}
		else {
			output += "\nThis project was completed on " + comDate;
		}
		
		output += "\n-------------------------------------------";
		output += "\nDeadline: " + deadline;
		if ((deadline.compareTo(today) < 0) && (!isFinishedy)) {
			output += "     !!!";
		}

		output += "\nBuilding type: " + buildingType;
		output += "\nProject Address: " + projectAddress;
		output += "\nERF number: " + erfNo;
		output += "\nCustomer paid: R" + String.format("%.2f", feePaid);
		output += "\nThe total cost of project: R" + String.format("%.2f", totalFee);

		return output;
	}
}