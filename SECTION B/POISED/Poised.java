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
import java.util.*;


public class Poised {
	/**
	 * This code is the Poised Project Management System (PMS)
	 *  <p>
	 * @author rusheil
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ParseException, IOException {

		// Initialise 
		Scanner scanner = new Scanner(System.in);
		String menu;
		// Display an introduction message and what user will be doing.
		System.out.println("This is the Poised Project Management System.");

		// Create a list of the projects for the program to use.
		ArrayList<TotalProject> projects = new ArrayList<>();
		// Gather all the projects for use of the program
		projects = ProjectInformation.makeObjects();

		// Main menu selection
		do {
			String textblockLogo = """
					 ____       _              _   ____  __  __ ____
					|  _ \\ ___ (_)___  ___  __| | |  _ \\|  \\/  / ___| 
					| |_) / _ \\| / __|/ _ \\/ _` | | |_) | |\\/| \\___ \\
					|  __/ (_) | \\__ \\  __/ (_| | |  __/| |  | |___) |
					|_|   \\___/|_|___/\\___|\\__,_| |_|   |_|  |_|____/ """;
			System.out.println(textblockLogo);
			System.out.println("\nEnter an option from the menu:");
			System.out.println("1 - Enter a new project"); // done
			System.out.println("2 - Search for and update a project's details"); 
			System.out.println("3 - View all overdue projects"); // done
			System.out.println("4 - View all projects in progress"); // done
			System.out.println("5 - View all projects"); // done
			System.out.println("0 - exit"); 
			menu = scanner.nextLine();

			switch(menu) {
			case "1":
				// ENTER A NEW PROJECT
				ProjectInformation.newProject(scanner, projects);
				break;

			case "2":
				// ALLOW USER TO SEARCH AND UPDATE 
				SearchAndUpdate.search(scanner, projects);
				break;

			case "3":
				// OVERDDUE PROJECTS
				System.out.println("These projects are overdue...");
				Details.checkOverdueProjects(projects);
				break;

			case "4":
				// PROJECTS IN PROGRESS
				System.out.println("These projects are still in progress...");
				Details.checkUncompletedrojects(projects);
				break;

			case "5":
				// DISPLAY ALL PROJECTS
				projects.forEach(System.out::println);
				break;

			case "0":
				// EXIT PROGRAM
				// Update the data in the textfile, display success message
				ProjectInformation.updateTextFile(projects);
				System.out.println("Your updates to the projects have been confirmed");
				System.out.println("Goodbye");
				scanner.close(); // close scanner
				System.exit(0);
				break;

			default:
				// Incorrect entry
				System.out.println("Invalid entry.");
				break;
			}
		} while (!"0".equals(menu));
	}}