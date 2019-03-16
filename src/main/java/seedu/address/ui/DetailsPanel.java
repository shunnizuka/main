package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel of the main window to show all information or employees and projects
 */
public class DetailsPanel extends UiPart<Region> {

    private final Logger logger = LogsCenter.getLogger(DetailsPanel.class);

    public static final String FXML = "DetailsPanel.fxml";
    private static final int INITIAL_PANEL_INDEX = 0;

    private List<Node> contentList;
    private int currentPanelIndex;

    @FXML
    private BorderPane borderPanel;

    @FXML
    private StackPane informationPanel;

    @FXML
    private Button prevBtn;

    @FXML
    private Button nextBtn;

    public DetailsPanel() {
        super(FXML);
        initPrevBtn();
        initNextBtn();
        initDefaultView();
        this.currentPanelIndex = INITIAL_PANEL_INDEX;
    }

    /**
     * Initialises a default list of panels to show when nothing is selected.
     *
     */
    private void initDefaultView() {
        Pane pane = new Pane();
        contentList = new ArrayList<>();
        contentList.add(pane);
        updateInformationPanel(pane);
    }

    private void updateInformationPanel(Node e) {
        informationPanel.getChildren().clear();
        informationPanel.getChildren().add(e);
    }

    /**
     * Updates the content in the information panel to the list of content provided.
     * @param content
     */
    private void setContent(List<Node> content) {
        contentList.clear();
        contentList.addAll(content);
    }

    private void initPrevBtn() {
        prevBtn.setOnAction((actionEvent) -> showPrevPanel());
    }

    private void initNextBtn() {
        nextBtn.setOnAction((actionEvent) -> showNextPanel());
    }

    private void showPrevPanel() {
        int nextIndex = currentPanelIndex - 1;
        currentPanelIndex = nextIndex % contentList.size();
        logger.info("Current pane: " + currentPanelIndex);
        updateInformationPanel(contentList.get(currentPanelIndex));
    }

    private void showNextPanel() {
        int nextIndex = currentPanelIndex + 1;
        currentPanelIndex = nextIndex % contentList.size();
        logger.info("Current pane: " + currentPanelIndex);
        updateInformationPanel(contentList.get(currentPanelIndex));
    }

}
