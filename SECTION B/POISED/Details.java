import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Details {

	/** 
	 * getProjectNames gathers a list of project titles, to ensure user does not duplicate 
	 * project names
	 * @return returns a list of strings of project names
	 */
	static List<String> getProjectNames() {
		// Initialise and get data from text file
		File x = new File("ProjectDetails.txt");
		Scanner scanner = null; // initialise
		List<String> names = new ArrayList<String>(); // initialise list of names
		try {
			scanner = new Scanner(x);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line;

		// Get the list of project names
		if (!scanner.hasNextLine()) {
			return names;  // for the event of first using the system and no names are found
		}
		else {
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				line.strip();
				String[] pd = line.split("% ");
				// Get project titles, put into list for return
				String projectTitles = pd[0]; 
				names.add(projectTitles);
			}
			return names;
		}
	}
	
	/**
	 * Finds the number of projects in ProjectDetails.txt.
	 * @return returns the largest project number in the ProjectDetails.txt
	 */
	static int getProjectNumber(ArrayList<TotalProject> project) {
		Iterator<TotalProject> itr = project.iterator();
		int count = 0;
		// Get the number of projects
		while (itr.hasNext()) {
			@SuppressWarnings("unused")
			TotalProject referenced = itr.next();
			count++;
		}
		// return the total number of projects + 1, for new project number.
		return count;
	}

	/**
	 * Displays all the projects that are overdue, which are projects that are not marked
	 * complete and deadline is in the past
	 * @throws ParseException
	 */
	static void checkOverdueProjects(ArrayList<TotalProject> project) 
			throws ParseException {
		// Initialise project list iterator, counter, and today's date reference
		Iterator<TotalProject> itr = project.iterator();
		int count = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String todaysDate = dtf.format(localDate);

		// Determine projects that are overdue comparing today with deadline and print
		while (itr.hasNext()) {
			TotalProject reference = itr.next();
			if ((reference.project.deadline.compareTo(todaysDate) < 0) &&
					(!reference.project.getCompleted()))	{
				System.out.println(project.get(count));
			}
			count++;
		}
	}

	/**
	 * Displays all the projects that are not completed, which are projects that have
	 * completed marked as false
	 * @throws ParseException
	 */
	static void checkUncompletedrojects(ArrayList<TotalProject> project) 
			throws ParseException {
		// Initialise project interator and counter
		Iterator<TotalProject> itr = project.iterator();
		int count = 0;
		// Validate if project is completed - getCompleted returns a boolean
		while (itr.hasNext()) {
			if (!itr.next().project.getCompleted()) {
				System.out.println(project.get(count));
			}
			count++;
		}
	}

}
