package seedu.address.model.util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import seedu.address.model.employee.Employee;
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
            projectWithMostEmployeeString = "There is no ongoing project";
        }
        return projectWithMostEmployeeString;
    }

    /**
     * Look through a list of projects to find the project with the least number of employees.
     * Returns a string describing it.
     * @param projects The list of projects to look through.
     */
    public static String projectWithLeastEmployee(List<Project> projects) {
        Comparator<? super Project> comparator = new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.getEmployees().size() - o2.getEmployees().size();
            }
        };
        Optional<Project> projectWithLeastEmployee = projects.stream().min(comparator);
        String projectWithLeastEmployeeString = null;
        if (projectWithLeastEmployee.isPresent()) {
            int numEmployees = projectWithLeastEmployee.get().getEmployees().size();
            projectWithLeastEmployeeString = "The project with the least number of employees is "
                    + projectWithLeastEmployee.get().getProjectName().projectName
                    + " with " + numEmployees + " employees";
        } else {
            projectWithLeastEmployeeString = "There is no ongoing project";
        }
        return projectWithLeastEmployeeString;
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
            projectWithEarliestDeadlineString = "The project with the earliest deadline is "
                    + projectWithEarliestDeadline.get().getProjectName().projectName
                    + " at " + projectWithEarliestDeadline.get().getDeadline().deadline;
        } else {
            projectWithEarliestDeadlineString = "There is no ongoing project";
        }
        return projectWithEarliestDeadlineString;
    }

    /**
     * Look through a list of employees to find the employee with the least number of projects.
     * Returns a string describing the employee.
     */
    public static String employeeWithLeastProject(List<Employee> employees) {
        Comparator<? super Employee> comparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getCurrentProjects().size() - o2.getCurrentProjects().size();
            }
        };
        Optional<Employee> employeeWithLeastProject = employees.stream().min(comparator);
        String employeeWithLeastProjectString = null;
        if (employeeWithLeastProject.isPresent()) {
            int numProjects = employeeWithLeastProject.get().getCurrentProjects().size();
            employeeWithLeastProjectString = "The employee with the least number of projects is "
                    + employeeWithLeastProject.get().getName().fullName
                    + " with " + employeeWithLeastProject.get().getCurrentProjects().size()
                    + " projects";
        } else {
            employeeWithLeastProjectString = "There is no employee";
        }
        return employeeWithLeastProjectString;
    }

    /**
     * Look through a list of employees to find the employee with the most number of projects.
     * Returns a string describing the employee.
     */
    public static String employeeWithMostProject(List<Employee> employees) {
        Comparator<? super Employee> comparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getCurrentProjects().size() - o2.getCurrentProjects().size();
            }
        };
        Optional<Employee> employeeWithMostProject = employees.stream().max(comparator);
        String employeeWithMostProjectString = null;
        if (employeeWithMostProject.isPresent()) {
            int numProjects = employeeWithMostProject.get().getCurrentProjects().size();
            employeeWithMostProjectString = "The employee with the most number of projects is "
                    + employeeWithMostProject.get().getName().fullName
                    + " with " + employeeWithMostProject.get().getCurrentProjects().size()
                    + " projects";
        } else {
            employeeWithMostProjectString = "There is no employee";
        }
        return employeeWithMostProjectString;
    }
}
