package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class SideTabPanel extends UiPart<Region> {

    private static final String FXML = "SideTabPanel.fxml";

    @FXML
    private TabPane sideTabPanel;

    @FXML
    private Tab employeeTab;

    @FXML
    private Tab projectTab;

    public SideTabPanel(Node employeeListNode, Node projectListNode) {
        super(FXML);
        employeeTab.setContent(employeeListNode);
        projectTab.setContent(projectListNode);
    }
}
