package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class DetailsPanel extends UiPart<Region> {

    public static final String FXML = "DetailsPanel.fxml";

    @FXML
    private BorderPane borderPanel;

    @FXML
    private ScrollPane informationPanel;
    @FXML
    private Button prevBtn;

    @FXML
    private Button nextBtn;

    public DetailsPanel() {
        super(FXML);

    }
}
