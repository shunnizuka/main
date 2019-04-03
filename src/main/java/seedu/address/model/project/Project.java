package seedu.address.model.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private final UniqueMilestoneList milestones;
    private final Client client;
    private final Deadline deadline;
    private final UniqueEmployeeList employees;
    private final SortedUserStoryList userStories;
    private final Description description;


    /**
     * Constructor for each Project Object.
     */
    public Project (ProjectName pn, Client c, Deadline d) {
        this(pn, c, d, new UniqueMilestoneList(), new Description(), new UniqueEmployeeList(), new SortedUserStoryList());
    }

    /**
     * Constructor specifying milestones too.
     */
    public Project (ProjectName pn, Client c, Deadline d, UniqueMilestoneList m) {
        this(pn, c, d, m, new Description(), new UniqueEmployeeList(), new SortedUserStoryList());
    }

    /**
     * Constructor specifying description and milestone too.
     */
    public Project (ProjectName pn, Client c, Deadline d, UniqueMilestoneList m, Description desc) {
        this(pn, c, d, m, desc, new UniqueEmployeeList(), new SortedUserStoryList());
    }

    /**
     * Constructor specifying description.
     */
    public Project (ProjectName pn, Client c, Deadline d, Description desc) {
        this(pn, c, d, new UniqueMilestoneList(), desc, new UniqueEmployeeList(), new SortedUserStoryList());
    }

    /**
     * Constructor specifying employees in the project.
     */
    public Project(ProjectName pn, Client c, Deadline d, Description desc, UniqueEmployeeList emp) {
        this(pn, c, d, new UniqueMilestoneList(), desc, emp, new SortedUserStoryList());
    }

    /**
     * Constructor specifying all fields except userstories.
     */
    public Project(ProjectName pn, Client c, Deadline d, UniqueMilestoneList m, Description desc,
                   UniqueEmployeeList emp) {
        this(pn, c, d, m, desc, emp, new SortedUserStoryList());
    }

    /**
     * Constructor specifying all fields.
     */
    public Project(ProjectName pn, Client c, Deadline d, UniqueMilestoneList m, Description desc,
                   UniqueEmployeeList emp, SortedUserStoryList stories) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        this.description = desc;
        this.employees = emp;
        this.milestones = m;
        this.userStories = stories;

    }

    public ProjectName getProjectName() {
        return projectName;
    }
    public ObservableList<Milestone> getMilestones() {
        return milestones.asUnmodifiableObservableList();
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
    public ObservableList<UserStory> getUserStories() {
        return userStories.asUnmodifiableObservableList();
    }

    /**
     * Returns a clone of this Project object.
     */
    public Project clone() {
        return new Project(this.projectName.clone(), this.client.clone(), this.deadline.clone(),
                this.milestones.clone(), this.description.clone(), this.employees.clone(), userStories.clone());
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
     * Adds the given employee to this project.
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Adds the given milestone to this project.
     */
    public void addMilestone(Milestone milestone) {
        milestones.add(milestone);
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
     * Replace the existing employee with the new editedEmployee.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        employees.setEmployee(target, editedEmployee);
    }

    /**
     * Returns true if this project has the given projectName.
     */
    public boolean hasProjectName(ProjectName projectName) {
        return this.projectName.equals(projectName);
    }

    /**
     * Adds the given user story to this project.
     */
    public void addUserStory(UserStory story) {
        userStories.add(story);
    }

    /**
     * Removes the given user story from this project.
     */
    public void removeUserStory(UserStory story) {
        userStories.remove(story);
    }

    /**
     * Returns true if this project contains the given Employee.
     */
    public boolean containsEmployee(Employee employee) {
        return employees.containsExactSameEmployee(employee);
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
            && otherProject.milestones.equals(this.milestones)
            && otherProject.employees.equals(this.employees)
            && otherProject.userStories.equals(this.userStories);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(projectName, client, deadline, description, employees, userStories);
    }

    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectName())
            .append(" Client: ")
            .append(getClient())
            .append(" Deadline: ")
            .append(getDeadline())
            .append(" " + getDescription()).append("\nemployees:\n");
        for (Employee e: employees) {
            builder.append(e);
            builder.append("\n");
        }
        builder.append("milestones:\n");
        for (Milestone m: milestones) {
            builder.append(m);
            builder.append("\n");
        }
        builder.append("user stories:\n");
        for (UserStory s: userStories) {
            builder.append(s);
            builder.append("\n");
        }

        return builder.toString();
    }



}
