package seedu.address.model.util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import seedu.address.model.project.Project;

/**
 * Provides static methods for returning strings about projects/employees
 * with specific properties from a list of projects.
 */
public class SearchingUtil {

    /**
     * Look through a list of projects to find the project with the most number of employees.
     * Returns a string describing it.
     * @param projects The list of projects to look through.
     */
    public static String projectWithMostEmployee(List<Project> projects) {
        Comparator<? super Project> comparator = new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.getEmployees().size() - o2.getEmployees().size();
            }
        };
        Optional<Project> projectWithMostEmployee = projects.stream().max(comparator);
        String projectWithMostEmployeeString = null;
        if (projectWithMostEmployee.isPresent()) {
            int numEmployees = projectWithMostEmployee.get().getEmployees().size();
            projectWithMostEmployeeString = "The project with the most number of employees is "
                    + projectWithMostEmployee.get().getProjectName().projectName
                    + " with " + numEmployees + " employees";
        } else {
            projectWithMostEmployeeString = "There is no ongoing project.";
        }
        return projectWithMostEmployeeString;
    }

    /**
     * Look through a list of projects to find the project with the earliest deadline.
     * Returns a string describing it.
     */
    public static String projectWithEarliestDeadline(List<Project> projects) {
        Comparator<? super Project> comparator = new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return Project.DATE_STRING_COMPARATOR.compare(o1.getDeadline().deadline,
                        o2.getDeadline().deadline);
            }
        };
        Optional<Project> projectWithEarliestDeadline = projects.stream().min(comparator);
        String projectWithEarliestDeadlineString = null;
        if (projectWithEarliestDeadline.isPresent()) {
            int numEmployees = projectWithEarliestDeadline.get().getEmployees().size();
            projectWithEarliestDeadlineString = "The project with the earliest deadline is "
                    + projectWithEarliestDeadline.get().getProjectName().projectName
                    + " at " + projectWithEarliestDeadline.get().getDeadline().deadline;
        } else {
            projectWithEarliestDeadlineString = "There is no ongoing project.";
        }
        return projectWithEarliestDeadlineString;
    }
}
