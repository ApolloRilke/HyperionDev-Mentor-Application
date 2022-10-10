import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class SearchAndUpdate {

	/** 
	 * Searches through all the projects from the ProjectDetails.txt file and displays item
	 * and is an intermediary step to the update() method
	 * @param scanner takes main scanner object from main method
	 * @throws IOException
	 */
	static void search(Scanner scanner, ArrayList<TotalProject> project) throws IOException {
		// User enters either project number or title
		System.out.println("Search for a project using the project number or project name:");
		String search = scanner.nextLine();
		boolean numberSearch = false;
		int searchKey = 0;
		// Check if user has input a number or a string
		try {  
			searchKey = Integer.parseInt(search);  
			numberSearch = true;
		} catch(NumberFormatException e) {
		}
		// Search using project number and display project
		if (numberSearch) {
			if ((searchKey < (Details.getProjectNumber(project)+1)) && (searchKey > 0)) {
				update(scanner, searchKey-1, project.get(searchKey-1)); 
			} else {
				System.out.println("That is not a valid project number.");
			}
		}
		// Search using project title and display project
		else {
			Iterator<TotalProject> itr = project.iterator();
			int count = 0;
			boolean found = false;
			while (itr.hasNext()) {
				TotalProject reference = itr.next();
				if (reference.project.projectName.equalsIgnoreCase(search)) {
					update(scanner, count, project.get(count)); 
					found = true;
				} 
				count++;
			}
			if (!found) {
				System.out.println("Project not found");
			}
		}
	}

	/**
	 * Allows user to update any part of the selected project. The updated details are 
	 * displayed to the user. The updated details are written to the ProjectDetails.txt file.
	 * @param scanner Scanner object from main method
	 * @param projectNum Specifies which project details to update
	 * @throws IOException
	 */
	private static void update(Scanner scanner, int projectNum, TotalProject ref) throws IOException {
		// Display the project in question for confirmation and ability to see what details to change
		System.out.println(ref);
		String updateMenu; // initialise

		// Initialise today's date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String completedToday = dtf.format(localDate);

		String projectTitle = ref.project.projectName;

		do {
			// If the project has been completed, no more editing can be done
			if (ref.project.getCompleted()) {
				System.out.print("This project has been marked as complete and cannot be");
				System.out.println(" edited or updated further. ");
				updateMenu = "0";
				break;
			} else {
				System.out.println("\nWhat would you like to update?");
				System.out.println("1. The deadline");
				System.out.println("2. Update the paid fee");
				System.out.println("3. Settle account balance");
				System.out.println("4. Update the total fee");
				System.out.println("5. The architect's information");
				System.out.println("6. The contractor's information");
				System.out.println("7. The customer's information");
				System.out.println("8. finalise project");
				System.out.println("0. Main menu");
				updateMenu = scanner.nextLine();

				switch (updateMenu) {
				case "1":
					System.out.println("The current deadline for this project is: " + 
							ref.project.deadline);
					String deadlineNew;
					while (true) {
						try {
							System.out.println("Enter the new deadline date in (yyyy/MM/dd) "
									+ "format: ");
							deadlineNew = scanner.nextLine();
							DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
							df.parse(deadlineNew);
						} catch (ParseException e) {
							System.out.println("Invalid date entry.");
							continue;
						}
						if (deadlineNew.compareTo(completedToday) < 0) {
							System.out.println("New deadline cannot be in the past.");
							continue;
						}
						break;
					}
					ref.project.deadline = deadlineNew;
					System.out.println("You've successfully changed the deadline of project " 
							+ projectTitle + " to " + ref.project.deadline);
					break;

				case "2":
					System.out.println("The current balance on this account is: R" + 
							ref.project.getFeePaidDisplay());
					Double feePaid;
					// Get updated balance on account, ensure user enters a valid number
					while (true) {
						try {
							System.out.print("How much has been paid to date? \nR");  	
							feePaid = scanner.nextDouble();
						} catch (InputMismatchException e) {
							System.out.println("Please enter a number");
							scanner.nextLine();
							continue;
						}
						break;
					}
					ref.project.setFeePaid(feePaid);
					scanner.nextLine();
					System.out.print("You've successfully changed the fee balance of project " +
							projectTitle); // Display success message to user
					System.out.println(" to R" + ref.project.getFeePaidDisplay());
					break;

				case "3":
					// Account settling is total fee paid in full ie total fee = fee paid
					ref.project.setFeePaid(ref.project.getTotalFee());
					System.out.println("The account has been settled. Customer has paid in full.");
					break;

				case "4":
					// Update the total cost of the project
					System.out.println("The current total cost of this project is: R" + 
							ref.project.getTotalFeeDisplay());
					Double totalPaid;
					// Get updated balance on account, ensure user enters a valid number
					while (true) {
						try {
							System.out.print("What is the new total cost of the project? \nR");  	
							totalPaid = scanner.nextDouble();
							scanner.nextLine();
						} catch (InputMismatchException e) {
							System.out.println("Please enter a number");
							scanner.nextLine();
							continue;
						}
						break;
					}
					ref.project.setTotalFee(totalPaid);
					System.out.print("You've successfully changed the fee balance of project " + 
							projectTitle);
					System.out.println(" to R" + ref.project.getTotalFeeDisplay());
					break;

				case "5":
					// Update archetect details
					boolean architectUpdated = false;
					while (!architectUpdated) {
						System.out.println(ref.architect);
						System.out.println("\nWhat infomation would you like to update?: ");
						System.out.println("1 - Architect name");
						System.out.println("2 - telephone number \n3 - email address");
						System.out.println("4 - physical address \n5 - done updating "
								+ "architect details");
						String updateContactMenu = scanner.nextLine(); 
						switch (updateContactMenu) {
						case "1":
							System.out.println("Enter the new architect's name: ");
							String newArchitectName = scanner.nextLine();
							ref.architect.name = newArchitectName;
							System.out.println("The Architect is now: " + ref.architect.name);
							break;
						case "2":
							System.out.println("Enter the new telephone number: ");
							String newArchitectTelephone = scanner.nextLine();
							ref.architect.telephoneNo = newArchitectTelephone;
							System.out.println("The telephone number has been updated to " + 
									ref.architect.telephoneNo);
							break;
						case "3":
							System.out.println("Enter the new email address: ");
							String newArchitectEmail = scanner.nextLine();
							ref.architect.email = newArchitectEmail;
							System.out.println("The email address has been updated to " +
									ref.architect.email);
							break;
						case "4":
							System.out.println("Enter the new address number: ");
							String newArchitectAddress = scanner.nextLine();
							ref.architect.address = newArchitectAddress;
							System.out.println("The physical address has been updated to " +
									ref.architect.address);
							break;
						case "5":
							// Update complete, display confirmation message to user
							System.out.print("You've successfully updated details to: " + 
									ref.architect);
							architectUpdated = true; // exit while loop
							System.out.println();
							break;

						default:
							if (!updateContactMenu.equals("0")) {
								System.out.println("Incorrect menu selection.");
							}
							break;
						} 
					}
					break;


				case "6":
					// Update contractor's details
					boolean contractorUpdated = false;
					while (!contractorUpdated) {
						System.out.println(ref.contractor);
						System.out.println("\nWhat infomation would you like to update?: ");
						System.out.println("1 - Contractor name");
						System.out.println("2 - telephone number \n3 - email address");
						System.out.println("4 - physical address \n5 - done updating "
								+ "contractor details");
						String updateContactMenu = scanner.nextLine(); 
						switch (updateContactMenu) {
						case "1":
							System.out.println("Enter the new contractor's name: ");
							String newContractorName = scanner.nextLine();
							ref.contractor.name = newContractorName;
							System.out.println("The Contractor is now: " + ref.contractor.name);
							break;
						case "2":
							System.out.println("Enter the new telephone number: ");
							String newContractorTelephone = scanner.nextLine();
							ref.contractor.telephoneNo = newContractorTelephone;
							System.out.println("The telephone number has been updated to " + 
									ref.contractor.telephoneNo);
							break;
						case "3":
							System.out.println("Enter the new email address: ");
							String newContractorEmail = scanner.nextLine();
							ref.contractor.email = newContractorEmail;
							System.out.println("The email address has been updated to " +
									ref.contractor.email);
							break;
						case "4":
							System.out.println("Enter the new address number: ");
							String newContractorAddress = scanner.nextLine();
							ref.contractor.address = newContractorAddress;
							System.out.println("The physical address has been updated to " +
									ref.contractor.address);
							break;
						case "5":
							// Update complete, display confirmation message to user
							System.out.print("You've successfully updated details to: " + 
									ref.contractor);
							contractorUpdated = true; // exit while loop
							System.out.println();
							break;

						default:
							if (!updateContactMenu.equals("0")) {
								System.out.println("Incorrect menu selection.");
							}
							break;
						} 	
					}		
					break;


				case "7":
					// Update customer's details
					boolean customerUpdated = false;
					while (!customerUpdated) {
						System.out.println(ref.customer);
						System.out.println("\nWhat infomation would you like to update?: ");
						System.out.println("1 - Customer name");
						System.out.println("2 - telephone number \n3 - email address");
						System.out.println("4 - physical address \n5 - done updating "
								+ "customer details");
						String updateContactMenu = scanner.nextLine(); 
						
						switch (updateContactMenu) {
						case "1":
							System.out.println("Enter the new customer's name: ");
							String newCustomerName = scanner.nextLine();
							ref.customer.name = newCustomerName;
							System.out.println("The Customer is now: " + ref.customer.name);
							break;
						case "2":
							System.out.println("Enter the new telephone number: ");
							String newCustomerTelephone = scanner.nextLine();
							ref.customer.telephoneNo = newCustomerTelephone;
							System.out.println("The telephone number has been updated to " + 
									ref.customer.telephoneNo);
							break;
						case "3":
							System.out.println("Enter the new email address: ");
							String newCustomerEmail = scanner.nextLine();
							ref.customer.email = newCustomerEmail;
							System.out.println("The email address has been updated to " +
									ref.customer.email);
							break;
						case "4":
							System.out.println("Enter the new address number: ");
							String newCustomerAddress = scanner.nextLine();
							ref.customer.address = newCustomerAddress;
							System.out.println("The physical address has been updated to " +
									ref.customer.address);
							break;
						case "5":
							// Update complete
							System.out.print("You've successfully updated details to: " + 
									ref.customer);
							customerUpdated = true; // exit while loop
							System.out.println();
							break;

						default:
							if (!updateContactMenu.equals("0")) {
								System.out.println("Incorrect menu selection.");
							}
							break;
						} 	
					}		
					break;

				case "8":
					// If the amount owed is positive, the invoice is displayed, 
					// project is not completed
					if (ref.project.getTotalFee() > ref.project.getFeePaid()) {
						System.out.print("The account must be setlled to be marked complete.");
						System.out.println("\nPlease find below the invoice:");
						System.out.println(ref.customer);
						double amountOwed = ref.project.getTotalFee() - ref.project.getFeePaid();
						String amountOwedCurrency = String.format("%,.2f", amountOwed);
						System.out.println("The amount still outstanding is: R" + 
								amountOwedCurrency);
					}
					// Else the project will continue to be marked as completed.
					else {
						// Today's date will be used to mark completion of project
						// Mark the project as finished in projectDetails.txt with completion date
						ref.project.isFinished(completedToday);
						System.out.println("The project is marked completed on " + completedToday);
					}
					break;

				default:
					//Incorrect entry
					if (!updateMenu.equals("0")) {
						System.out.println("Incorrect menu selection.");
					}
					break;

				}}}while(!updateMenu.equals("0"));} 
}
