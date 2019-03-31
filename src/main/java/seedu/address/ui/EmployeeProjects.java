package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.model.project.Project;

/**
 * UI component to represent projects an employee is working on.
 */
public class EmployeeProjects extends UiPart<Region>{

    public static final String FXML = "EmployeeProjects.fxml";

    private ObservableList<Project> projects;

    private ObservableList<EmployeeProjects.ProjectCell> projectCells;

    @FXML
    private TableView<ProjectCell> projectTableView;

    @FXML
    private TableColumn<ProjectCell, String> indexCol;

    @FXML
    private TableColumn<ProjectCell, String> projectNameCol;

    public EmployeeProjects(ObservableList<Project> projects) {
        super(FXML);
        this.projects = projects;
        this.projectCells = getProjectCells(projects);
        projectTableView.setItems(projectCells);

        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        projectNameCol.setCellValueFactory(new PropertyValueFactory<>("projectName"));
    }

    private ObservableList<ProjectCell> getProjectCells(ObservableList<Project> projects) {
        ObservableList<ProjectCell> newList = FXCollections.observableArrayList();
        for (int i = 0; i < projects.size(); i++) {
            ProjectCell newCell = new ProjectCell(projects.get(i), i + 1);
            newList.add(newCell);
        }
        return newList;
    }


    /**
     * A cell which contains an project.
     */
    public class ProjectCell {

        private Project project;
        private Integer index;

        public ProjectCell(Project project, int index) {
            this.project = project;
            this.index = index;
        }

        public String getIndex() {
            return this.index.toString();
        }

        public String getProjectName() {
            return project.getProjectName().projectName;
        }
    }
}
