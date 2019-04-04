package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Region;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.ProjectTask;

/**
 * UI component to represent employees in a project
 */
public class ProjectMilestones extends UiPart<Region> {

    public static final String FXML = "ProjectMilestones.fxml";

    private ObservableList<Milestone> milestones;

    @FXML
    private TreeView<String> milestonesTreeView;

    public ProjectMilestones(ObservableList<Milestone> milestones) {
        super(FXML);
        this.milestones = milestones;

        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        addMilestoneBranches(root);

        milestonesTreeView.setRoot(root);
        milestonesTreeView.setShowRoot(false);
    }

    public void addMilestoneBranches(TreeItem<String> root) {
        int index = 1;
        for (Milestone m: milestones) {
            String mInfo = index + ". " + m.getMilestone() + " (" + m.getDate() + ")";
            TreeItem<String> item = setTreeItem(mInfo, root);
            addTaskLeaves(item, m);
            index++;
        }
    }

    public void addTaskLeaves(TreeItem<String> branch, Milestone m) {
        ObservableList<ProjectTask> projectTaskList = m.getProjectTaskList();
        for (ProjectTask pt: projectTaskList) {
            String ptInfo = "";
            if (pt.isComplete()) {
                ptInfo += "Complete: ";
            } else {
                ptInfo += "Ongoing:  ";
            }
            ptInfo += pt.getTaskName();
            setTreeItem(ptInfo, branch);
        }
    }

    public TreeItem<String> setTreeItem(String itemName, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(itemName);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
