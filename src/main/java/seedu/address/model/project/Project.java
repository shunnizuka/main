package seedu.address.model.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueEmployeeList;

/**
 * Represents a project in the pocket project.
 */
public class Project {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private final ProjectName projectName;
    private final List<Milestone> milestones;
    private final Client client;
    private final Deadline deadline;
    private final UniqueEmployeeList employees;
    private final Description description;


    /**
     * Constructor for each Project Object.
     */
    public Project (ProjectName pn, Client c, Deadline d) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        milestones = new ArrayList<>();
        employees = new UniqueEmployeeList();
        this.description = new Description();
    }

    /**
     * Constructor specifying milestones too.
     */
    public Project (ProjectName pn, Client c, Deadline d, List<Milestone> m) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        this.milestones = m;
        this.employees = new UniqueEmployeeList();
        this.description = new Description();
    }

    /**
     * Constructor specifying description and milestone too.
     */
    public Project (ProjectName pn, Client c, Deadline d, List<Milestone> m, Description desc) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        this.milestones = m;
        this.description = desc;
        this.employees = new UniqueEmployeeList();
    }

    /**
     * Constructor specifying description.
     */
    public Project (ProjectName pn, Client c, Deadline d, Description desc) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        this.milestones = new ArrayList<>();
        this.description = desc;
        this.employees = new UniqueEmployeeList();
    }

    /**
     * Constructor specifying employees in the project.
     */
    public Project(ProjectName pn, Client c, Deadline d, Description desc, UniqueEmployeeList emp) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        this.description = desc;
        this.employees = emp;
        this.milestones = new ArrayList<>();
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }
    public Client getClient() {
        return client;
    }
    public Deadline getDeadline() {
        return deadline;
    }
    public Description getDescription() {
        return description;
    }
    public ObservableList<Employee> getEmployees() {
        return employees.asUnmodifiableObservableList();
    }

    /**
     * Returns a clone of this Project object.
     */
    public Project clone() {
        return new Project(this.projectName.clone(), this.client.clone(), this.deadline.clone(),
                this.description.clone(), this.employees.clone());
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
     *  Removes the given employee from this project.
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    /**
     * Removes the given milestone from this project.
     */
    public void removeMilestone(Milestone milestone) {
        milestones.remove(milestone);
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
            && otherProject.getDeadline().equals(getDeadline())
            && otherProject.getDescription().equals(getDescription())
            && otherProject.getMilestones().equals(getMilestones())
            && otherProject.employees.equals(this.employees);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(projectName, client, deadline, description);
    }

    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectName())
            .append(" Client: ")
            .append(getClient())
            .append(" Deadline: ")
            .append(getDeadline())
            .append(" " + getDescription());

        return builder.toString();
    }



}
