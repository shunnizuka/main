package seedu.address.ui;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Region;
import jdk.nashorn.api.tree.Tree;
import seedu.address.model.project.Milestone;

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
        TreeItem<String> ms1, ms2, ms3, ms4;
        ms1 = makeBranch("v1.1", root);
        makeBranch("Refactor classes in AB4 to match Pocket Project", ms1);
        makeBranch("Implement project classes and dependencies", ms1);
        makeBranch("Implement skeleton classes for additional parsers", ms1);

        milestonesTreeView.setRoot(root);
        milestonesTreeView.setShowRoot(false);
    }

    public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }



}
