package seedu.address.model.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a project in the pocket project.
 */
public class Project {

    private final ProjectName projectName;
    private final List<Milestone> milestone;
    private final Client client;
    private final Deadline deadline;

    /**
     * Constructor for each Project Object.
     */
    public Project (ProjectName pn, Client c, Deadline d) {
        this. projectName = pn;
        this.client = c;
        this.deadline = d;
        milestone = new ArrayList<>();
    }


}
