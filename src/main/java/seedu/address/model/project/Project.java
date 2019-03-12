package seedu.address.model.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueEmployeeList;

/**
 * Represents a project in the pocket project.
 */
public class Project {

    public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final ProjectName projectName;
    private final List<Milestone> milestone;
    private final Client client;
    private final Deadline deadline;
    private final UniqueEmployeeList employees;


    /**
     * Constructor for each Project Object.
     */
    public Project (ProjectName pn, Client c, Deadline d) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        milestone = new ArrayList<>();
        employees = new UniqueEmployeeList();
    }

    /**
     * Constructor specifying milestones too.
     */
    public Project (ProjectName pn, Client c, Deadline d, List<Milestone> m) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        this.milestone = m;
        this.employees = new UniqueEmployeeList();
    }

    public ProjectName getProjectName() {
        return projectName;
    }
    public List<Milestone> getMilestone() {
        return milestone;
    }
    public Client getClient() {
        return client;
    }
    public Deadline getDeadline() {
        return deadline;
    }


    /**
     * Returns true if both projects have the same name.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getProjectName().equals(getProjectName());
    }

    /**
     *  Removes the employee with the given index on the last displayed list from this project.
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    /**
     * Returns true if this project has the given projectName.
     */
    public boolean hasProjectName(ProjectName projectName) {
        return this.projectName.equals(projectName);
    }
    @Override
    public boolean equals (Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getProjectName().equals(getProjectName())
                && otherProject.getClient().equals(getClient())
                && otherProject.getDeadline().equals(getDeadline());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(projectName, client, deadline);
    }

    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectName())
                .append(" Client: ")
                .append(getClient())
                .append(" Deadline: ")
                .append(getDeadline());

        return builder.toString();
    }



}
