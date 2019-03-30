package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.address.model.employee.Employee;

/**
 * Summary of an employee
 */
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
            Label label = new Label(skill.skillName);
            label.getStyleClass().add(SideTabPanel.getSkillLabelColor(skill.skillName));
            skillsList.getChildren().add(label);
        });
    }
}
