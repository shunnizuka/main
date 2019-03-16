package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import seedu.address.model.employee.Employee;

public class EmployeeDetails {

    private Employee employee;
    private List<Node> employeeDetailsList;
    private EmployeeSummary employeeSummary;
    private EmployeeGitHub gitHubProfile;

    public EmployeeDetails(Employee employee) {
        this.employee = employee;
        employeeDetailsList = new ArrayList<>();
        employeeSummary = new EmployeeSummary(employee);
        gitHubProfile = new EmployeeGitHub(employee);

        employeeDetailsList.add(employeeSummary.getRoot());
        employeeDetailsList.add(gitHubProfile.getRoot());
    }

    public List<Node> getEmployeeDetails() {
        return employeeDetailsList;
    }

}
