package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.address.model.project.Project;

public class ProjectSummary extends UiPart<Region> {

    private static final String FXML = "ProjectSummary.fxml";

    @javafx.fxml.FXML
    private Pane projectDetails;

    @FXML
    private Label projectName;

    public ProjectSummary(Project project) {
        super(FXML);
        projectName.setText(project.getProjectName().projectName);
    }
}
