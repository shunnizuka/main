package seedu.address.ui;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.address.model.employee.Employee;

public class EmployeeSummary extends UiPart<Region> {

    private static final String FXML = "EmployeeSummary.fxml";

    @FXML
    private Pane employeeDetails;

    @FXML
    private Label name;

    @FXML
    private Label skills;

    @FXML
    private Label projects;

    @FXML
    private HBox skillsList;

    public EmployeeSummary(Employee employee) {
        super(FXML);
        name.setText(employee.getName().fullName);
        employee.getSkills().forEach(skill -> {
            skillsList.getChildren().add(new Label(skill.skillName));
        });
    }
}
