package seedu.address.model.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a project in the pocket project.
 */
public class Project {

    private final ProjectName projectName;
    private final List<Milestone> milestone;
    private final Client client;
    private final Deadline deadline;
    private final Description description;

    /**
     * Constructor for each Project Object.
     */
    public Project (ProjectName pn, Client c, Deadline d) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        milestone = new ArrayList<>();
        this.description = new Description();
    }

    /**
     * Constructor specifying milestones too.
     */
    public Project (ProjectName pn, Client c, Deadline d, List<Milestone> m) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        this.milestone = m;
        this.description = new Description();
    }

    /**
     * Constructor specifying description too.
     * TODO: edit accordingly for addto function, I'm not sure how you all want to implement
     */
    public Project (ProjectName pn, Client c, Deadline d, List<Milestone> m, Description desc) {
        this.projectName = pn;
        this.client = c;
        this.deadline = d;
        this.milestone = m;
        this.description = desc;
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
    public Description getDescription() {
        return description; }

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
            && otherProject.getDescription().equals(getDescription());

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
            .append(getDescription());

        return builder.toString();
    }



}
