package seedu.address.model.util;

import java.util.List;

import seedu.address.model.employee.Employee;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;

/**
 * Provides static methods for looking through the list of projects/employees/single project for
 * information. Returns the result as a string contain the information.
 */
public class StatsUtil {
    /**
     * Returns a string describing the overall state of all the projects.
     */
    public static String overAllStatsString(List<Employee> employees, List<Project> currentProjects,
                                            List<Project> completedProjects) {
        StringBuilder builder = new StringBuilder();
        builder.append(ongoingProjects(currentProjects));
        builder.append(currentProjects(currentProjects));
        return builder.toString();
    }

    /**
     * Returns a string describing the total number of ongoing projects.
     */
    public static String ongoingProjects(List<Project> projects) {
        int number = projects.size();
        return "Number of ongoing projects: " + number + "\n";
    }

    /**
     * Look through all the projects and return a string describing all the projects' progress.
     */
    public static String currentProjects(List<Project> projects) {
        StringBuilder builder = new StringBuilder();
        builder.append("Projects with deadline in this month:\n------------------------\n");
        int count = 1;

        for (Project p : projects) {
            if (PocketProjectDate.isThisMonth(p.getDeadline())) {
                builder.append(count + ". " + p.getProjectName().projectName + ": " + roughProgress(p) + "\n");
                count++;
            }
        }
        builder.append("------------------------\n");
        for (Project project : projects) {
            if (!PocketProjectDate.isThisMonth(project.getDeadline())) {
                builder.append(count + ". " + project.getProjectName().projectName + ": " + roughProgress(project)
                        + "\n");
                count++;
            }
        }
        return builder.toString();
    }

    /**
     * Returns a string describing the rough progress of a project.
     */
    public static String roughProgress(Project project) {
        StringBuilder builder = new StringBuilder();
        int numMilestonesReached = 0;
        for (Milestone m: project.getMilestones()) {
            if (m.reached()) {
                numMilestonesReached++;
            }
        }
        int numTotalMilestones = project.getMilestones().size();
        builder.append(String.format("reached %d out of %d milestones", numMilestonesReached, numTotalMilestones));
        return builder.toString();
    }

    /**
     * Returns a string describing the progress of an individual project.
     */
    public static String individualStatsString(Project project) {
        return "";
    }

}
