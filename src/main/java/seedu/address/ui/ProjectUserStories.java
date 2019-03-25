package seedu.address.ui;

import java.util.ArrayList;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.model.project.Project;
import seedu.address.model.project.UserStory;
import seedu.address.model.project.UserStoryUser;

public class ProjectUserStories extends UiPart<Region> {

    public static final String FXML = "ProjectUserStories.fxml";

    private ObservableList<UserStory> userStories;

    private ObservableList<UserStoryCell> userStoryCells;

    @FXML
    private TableView<UserStoryCell> userStoryTableView;

    @FXML
    private TableColumn<UserStoryCell, String> indexCol;

    @FXML
    private TableColumn<UserStoryCell, String> priorityCol;

    @FXML
    private TableColumn<UserStoryCell, String> userCol;

    @FXML
    private TableColumn<UserStoryCell, String> functionCol;

    @FXML
    private TableColumn<UserStoryCell, String> reasonCol;

    public ProjectUserStories(Project project) {
        super(FXML);
        this.userStories = project.getUserStories();
        userStoryCells = getUserStoryCells(userStories);
        userStoryTableView.setItems(userStoryCells);

        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("importance"));
        functionCol.setCellValueFactory(new PropertyValueFactory<>("function"));
        reasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));
    }

    public ObservableList<UserStoryCell> getUserStoryCells(ObservableList<UserStory> stories) {
        ObservableList<UserStoryCell> newList = FXCollections.observableArrayList();
        for (int i = 0; i < stories.size(); i++) {
            UserStoryCell newCell = new UserStoryCell(stories.get(i), i + 1);
            newList.add(newCell);
        }
        return newList;
    }


    public class UserStoryCell {

        public UserStory story;
        public Integer index;

        public UserStoryCell(UserStory s, int index) {
            this.story = s;
            this.index = index;
        }

        public String getIndex() {
            return this.index.toString();
        }

        public String getImportance() {
            return story.getUserStoryImportance().getImportance();
        }

        public String getFunction() {
            return story.getUserStoryFunction().getFunction();
        }

        public String getUser() {
            return story.getUserStoryUser().getUser();
        }

        public String getReason() {
            return story.getUserStoryReason().getReason();
        }
    }

}
