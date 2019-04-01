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
                return o2.getEmployees().size() - o1.getEmployees().size();
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
}
