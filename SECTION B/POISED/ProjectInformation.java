import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ProjectInformation {

	/**
	 * Displays all of the projects from ProjectDetails.txt. Reads all the details and
	 * contructs objects for the project and/or person objects.
	 * @return 
	 */
	public static ArrayList<TotalProject> makeObjects() {
		// Read from the ProjectDetails.txt file to construct objects
		File x = new File("ProjectDetails.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(x);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		// Initialise a list of projects
		ArrayList<TotalProject> allProjects = new ArrayList<>();

		while (scanner.hasNextLine()) {
			// Prepare data for reading into objects
			String line = scanner.nextLine();
			line.strip();
			String[] pd = line.split("% ");
			// Contruct Project object
			String projectTitle = pd[0];
			String buildingType = pd[1];
			String buildingAddress = pd[2];
			String erfNumber = pd[3];
			String feeTotal = pd[4];
			double feeTotalDouble = Double.parseDouble(feeTotal);
			String feePaid = pd[5];
			double feePaidDouble = Double.parseDouble(feePaid);
			String deadline = pd[6];
			String completedCheck = pd[7];
			String projectNo = pd[20];
			boolean completed = false;
			String comDate = pd[7];
			if (completedCheck.equals("false")) {
				completed = false;
			}
			else if (completedCheck.equals("true")) {
				completed = true;
				comDate = pd[21];
			}
			Project newProject = new Project(projectNo, projectTitle, buildingType, 
					buildingAddress, erfNumber, feeTotalDouble, feePaidDouble, deadline, 
					completed, comDate);

			// Construct Architect Person Object
			String architectName = pd[8];
			String architectTelephone = pd[9];
			String architectEmail = pd[10];
			String architectAddress = pd[11];
			String architect = "Architect";
			Person newArchitect = new Person(architect, architectName, architectTelephone, 
					architectEmail, architectAddress);

			// Contstruct Contractor Person Object
			String contractorName = pd[12];
			String contractorTelephone = pd[13];
			String contractorEmail = pd[14];
			String contractorAddress = pd[15];
			String contractor = "Contractor";
			Person newContractor = new Person(contractor, contractorName, contractorTelephone, 
					contractorEmail, contractorAddress);

			// Construct Customer Person Object
			String customerName = pd[16];
			String customerTelephone = pd[17];
			String customerEmail = pd[18];
			String customerAddress = pd[19];
			String cust = "Customer";
			Person newCustomer = new Person(cust, customerName, customerTelephone, customerEmail, 
					customerAddress);

			// Compile TotalProject Object
			TotalProject totalProject = new TotalProject(newProject, newArchitect, newContractor, 
					newCustomer);
			// Add TotalProject Object to a list
			allProjects.add(totalProject);
		}
		scanner.close();
		return allProjects;
	}

	
	/** 
	 * This method adds a new project to the ProjectDetails.txt file
	 * @param scanner scanner from the main method
	 */
	static void newProject(Scanner scanner, ArrayList<TotalProject> project) {
		System.out.println("Please enter the details of the project:");
		System.out.println("What is the project title?");
		String projectName = scanner.nextLine();
		System.out.println("What is the building type?");
		String buildingType = scanner.nextLine();
		System.out.println("What is the building address?");
		String projectAddress = scanner.nextLine();
		System.out.println("What is the project ERF number?");
		String erfNo = scanner.nextLine();
		// Ensure user enters a numbered cost:
		double totalFee;
		while (true) {
			try {
				System.out.print("The total cost of the project? \nR");
				totalFee = scanner.nextDouble();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number");
				scanner.nextLine();
				continue;
			}
			break;
		}
		// Ensure user enters a numbered fee:
		double feePaid;
		while (true) {
			try {
				System.out.print("How much has already been paid to date? \nR");  	
				feePaid = scanner.nextDouble();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number");
				scanner.nextLine();
				continue;
			}
			break;
		}
		// Ensure user enters valid date in the asked for date format:
		String deadline;
		while (true) {
			try {
				System.out.println("Enter the deadline date in (yyyy/MM/dd) format: ");
				deadline = scanner.nextLine();
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				df.setLenient(false);
				df.parse(deadline);
			} catch (ParseException e) {
				System.out.println("Invalid date entry.");
				continue;
			}
			// Validate the deadline is set in the future
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			String completedToday = dtf.format(localDate);
			if (deadline.compareTo(completedToday) < 0) {
				System.out.println("New deadline cannot be in the past.");
				continue;
			}
			break;
		}
		System.out.println();

		// Project details relating to the persons involved.
		// The architect contact details to create Person object
		System.out.println("The following information will pertain to the architect "
				+ "assigned to this project.");
		System.out.println("Enter the name of the architect:");
		String architectName = scanner.nextLine(); 
		System.out.println("Enter their telephone number:");
		String architectTelephone = scanner.nextLine(); 
		System.out.println("Enter their email address:");
		String architectEmail = scanner.nextLine(); 
		System.out.println("Enter the relevant address:");
		String architectAddress = scanner.nextLine(); 
		System.out.println();

		// Contractor contact details to create Person object
		System.out.println("The following information will pertain to the contractor "
				+ "assigned to this project.");
		System.out.println("Enter the name of the contractor:");
		String contractorName = scanner.nextLine(); 
		System.out.println("Enter their telephone number:");
		String contractorTelephone = scanner.nextLine(); 
		System.out.println("Enter their email address:");
		String contractorEmail = scanner.nextLine(); 
		System.out.println("Enter the relevant address:");
		String contractorAddress = scanner.nextLine(); 
		System.out.println();

		// Customer contact details to create Person object
		System.out.println("The following information will pertain to the customer "
				+ "assigned to this project.");
		System.out.println("Enter the name of the customer:");
		String customerName = scanner.nextLine(); 
		System.out.println("Enter their telephone number:");
		String customerTelephone = scanner.nextLine(); 
		System.out.println("Enter their email address:");
		String customerEmail = scanner.nextLine(); 
		System.out.println("Enter the relevant address:");
		String customerAddress = scanner.nextLine(); 

		// If the project name is not entered, the default project name must be
		// the building type + last name of the architect assigned.
		if (projectName.isEmpty()) {
			// Split the architect name up by space " "
			String[] lastNameList = architectName.split(" ");
			// Isolate the last name of the architect
			String lastName = lastNameList[lastNameList.length -1];
			// Finalise the project name
			projectName = buildingType + " " + lastName;
		}
		// Validate the uniqueness of the project name, must be unique.
		boolean namesCheck = false;
		List<String> names = new ArrayList<>();
		names = Details.getProjectNames();
		while (!namesCheck) {
			if (!names.contains(projectName)) {
				namesCheck = true;
			}
			else {
				System.out.println("This project name already exists, please enter a new "
						+ "project name");
				projectName = scanner.nextLine();
			}
		}

		// Get the new project number
		String projectNo = Integer.toString(Details.getProjectNumber(project)+1);

		// Create Project, Arcitect, Contractor, Customer objects for TotalProject object
		Project newProject = new Project(projectNo, projectName, buildingType, projectAddress,
				erfNo, totalFee, feePaid, deadline, false, "N"); // false + N indicate incomplete
		Person newArchitect = new Person("Architect", architectName, architectTelephone, 
				architectEmail, architectAddress);
		Person newContractor = new Person("contractor", contractorName, contractorTelephone, 
				contractorEmail, contractorAddress);
		Person newCustomer = new Person("Customer", customerName, customerTelephone, 
				customerEmail, customerAddress);
		TotalProject nProject = new TotalProject(newProject, newArchitect, newContractor,
				newCustomer);

		// Add new project to projects list
		project.add(nProject);

		// Display confirmation message to user
		System.out.println("\nYou have successfully added the project " + projectName);
	}
	
	static void updateTextFile(ArrayList<TotalProject> projects) throws FileNotFoundException {
		// Add new project details to text file
		File x = new File("ProjectDetails.txt");
		Scanner sc = new Scanner(x);

		Iterator<TotalProject> itr = projects.iterator();
		String toTextfile = "";
		while (itr.hasNext()) {
			TotalProject ref = itr.next();
			// Construct a string with details of the project to put into a useable format
			toTextfile += "" + ref.project.projectName;
			toTextfile += "% " + ref.project.buildingType;
			toTextfile += "% " + ref.project.projectAddress;
			toTextfile += "% " + ref.project.erfNo;
			toTextfile += "% " + ref.project.getTotalFee();
			toTextfile += "% " + ref.project.getFeePaid();
			toTextfile += "% " + ref.project.deadline;
			toTextfile += "% " + ref.project.getCompleted();

			toTextfile += "% " + ref.architect.name;
			toTextfile += "% " + ref.architect.telephoneNo;
			toTextfile += "% " + ref.architect.email;
			toTextfile += "% " + ref.architect.address;

			toTextfile += "% " + ref.contractor.name;
			toTextfile += "% " + ref.contractor.telephoneNo;
			toTextfile += "% " + ref.contractor.email;
			toTextfile += "% " + ref.contractor.address;

			toTextfile += "% " + ref.customer.name;
			toTextfile += "% " + ref.customer.telephoneNo;
			toTextfile += "% " + ref.customer.email;
			toTextfile += "% " + ref.customer.address;
			toTextfile += "% " + ref.project.projectNo;
			toTextfile += "% " + ref.project.comDate + "\n";
		}
		try {    
			Files.write(Paths.get("ProjectDetails.txt"), toTextfile.getBytes());
		}
		catch(IOException e) {
			System.out.println(e);
		}
		sc.close();
	}	
}
