package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import seedu.address.model.project.Project;

public class ProjectDetails {

    private Project project;
    private List<Node> projectDetailsList;
    private ProjectSummary projectSummary;


    public ProjectDetails(Project project) {
        this.project = project;
        projectDetailsList = new ArrayList<>();
        projectSummary = new ProjectSummary(project);

        projectDetailsList.add(projectSummary.getRoot());
    }

    public List<Node> getProjectDetails() {
        return projectDetailsList;
    }

}
