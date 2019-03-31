package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.GitHubAccount;

/**
 * Contains a webview to load a github profile
 */
public class EmployeeGitHub extends UiPart<Region> {

    private static final String FXML = "EmployeeGitHub.fxml";
    private static final String PREFIX_GITHUB = "https://github.com/";
    private GitHubAccount employeeGithub;

    @FXML
    private WebView webView;

    public EmployeeGitHub(GitHubAccount employeeGitHub) {
        super(FXML);
        this.employeeGithub = employeeGithub;
        loadEmployeePage();
    }

    private void loadEmployeePage() {
        String url = PREFIX_GITHUB + employeeGithub.getGithubAccount();
        loadPage(url);
    }

    private void loadPage(String url) {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);
    }
}
