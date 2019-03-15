package seedu.address.ui;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Panel of the main window to show all information or employees and projects
 */
public class DetailsPanel extends UiPart<Region> {

    public static final String FXML = "DetailsPanel.fxml";
    private List<Pane> contentPanels;

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
    }



    private void initPrevBtn() {
        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

    private void initNextBtn() {
        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
