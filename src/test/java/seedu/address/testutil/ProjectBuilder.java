package seedu.address.testutil;

import java.util.List;

import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueEmployeeList;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.SortedUserStoryList;
import seedu.address.model.project.UniqueMilestoneList;
import seedu.address.model.project.UserStory;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_PROJECT_NAME = "Apollo";
    public static final String DEFAULT_DEADLINE = "03/03/2019";
    public static final String DEFAULT_CLIENT = "NUS";
    public static final String DEFAULT_DESCRIPTION = "build an application that manages projects";


    private ProjectName projectName;
    private Deadline deadline;
    private Client client;
    private Description description;
    private UniqueEmployeeList employees;
    private UniqueMilestoneList milestones;
    private SortedUserStoryList userStories;

    public ProjectBuilder() {
        projectName = new ProjectName(DEFAULT_PROJECT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE);
        client = new Client(DEFAULT_CLIENT);
        description = new Description((DEFAULT_DESCRIPTION));
        employees = new UniqueEmployeeList();
        milestones = new UniqueMilestoneList();
        userStories = new SortedUserStoryList();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        projectName = projectToCopy.getProjectName();
        deadline = projectToCopy.getDeadline();
        client = projectToCopy.getClient();
        description = projectToCopy.getDescription();
        employees = new UniqueEmployeeList();
        this.milestones = new UniqueMilestoneList();
        this.userStories = new SortedUserStoryList();
        for (Employee e: projectToCopy.getEmployees()) {
            employees.add(e);
        }
        for (Milestone m: projectToCopy.getMilestones()) {
            milestones.add(m);
        }
        for (UserStory u: projectToCopy.getUserStories()) {
            userStories.add(u);
        }
    }

    /**
     * Sets the {@code ProjectName} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectName(String projectName) {
        this.projectName = new ProjectName(projectName);
        return this;
    }


    /**
     * Sets the {@code Deadline} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Client} of the {@code Project} that we are building.
     */
    public ProjectBuilder withClient(String client) {
        this.client = new Client(client);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDescrption(String desc) {
        this.description = new Description(desc);
        return this;
    }
    /**
     * Sets the milestones of the {@code Project} that we are building.
     */
    public ProjectBuilder withMilestones(List<Milestone> milestones) {
        this.milestones.setMilestones(milestones);
        return this;
    }
    /**
     * Sets the employees of the {@code Project} that we are building.
     */
    public ProjectBuilder withEmployees(List<Employee> employees) {
        this.employees.setEmployees(employees);
        return this;
    }

    /**
     * Sets the user stories of the {@code Project} that we are building.
     */
    public ProjectBuilder withUserStories(List<UserStory> stories) {
        this.userStories.setUserStories(stories);
        return this;
    }

    /**
     * Build the components into a {@code Project}
     */
    public Project build() {
        return new Project(projectName, client, deadline, milestones, description, employees,
                userStories);
    }
}
