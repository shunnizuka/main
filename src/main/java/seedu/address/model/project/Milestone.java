package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.util.PocketProjectDate;

/**
 * Milestone achieved in the project timeline.
 */

public class Milestone {

    public static final String MESSAGE_CONSTRAINTS = "The milestone info must not be empty or consisting of only spaces"
        + " and the date given must be in DD/MM/YYYY format";
    public static final String MESSAGE_INVALID_STRING = "The milestone info must not be empty or consisting "
        + "of only spaces";

    public final MilestoneDescription milestone;
    public final PocketProjectDate date;
    public final UniqueProjectTaskList projectTasks;

    public Milestone(MilestoneDescription milestone, PocketProjectDate date) {
        this(milestone, date, new UniqueProjectTaskList());
    }

    public Milestone(MilestoneDescription milestone, PocketProjectDate date, UniqueProjectTaskList projectTasks) {
        requireAllNonNull(milestone, date, projectTasks);
        this.milestone = milestone;
        this.date = date;
        this.projectTasks = projectTasks;
    }

    /**
     * Check if the milestone has the valid format by checking the components.
     * @return true if valid milestone and false otherwise.
     */
    public static boolean isValidMilestone(Milestone milestone) {
        return MilestoneDescription.isValidDescription(milestone.getMilestoneDescription().description)
                && PocketProjectDate.isValidDate(milestone.getDate().date);
    }

    /**
     * Adds the given project task to this milestone.
     */
    public void addTask(ProjectTask task) {
        this.projectTasks.add(task);
    }


    /**
     * Updates the given user story in this project.
     */
    public void updateProjectTask(ProjectTask task, Status newStatus) {
        projectTasks.forEach(pt -> {
            if (task.equals(pt)) {
                pt.updateStatus(newStatus);
            }
            return;
        });
    }

    /**
     * Returns a clone of this Milestone object.
     */
    public Milestone clone() {

        return new Milestone(this.milestone, this.date, this.projectTasks.clone());
    }

    public MilestoneDescription getMilestoneDescription() {
        return milestone;
    }

    public PocketProjectDate getDate() {
        return date;
    }

    public ObservableList<ProjectTask> getProjectTaskList() {
        return this.projectTasks.asUnmodifiableObservableList();
    }
    /**
     * Returns true if all tasks for this milestone is completed.
     */
    public boolean reached() {
        for (ProjectTask pt: this.projectTasks) {
            if (!pt.isComplete()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a new milestone which has its {@code milestone} and {@code date} edited.
     * {@code projectTasks} remains unchanged
     */
    public Milestone editMilestone (MilestoneDescription milestone, PocketProjectDate date,
                                    UniqueProjectTaskList projectTasks) {
        return new Milestone(milestone, date, projectTasks);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Milestone // instanceof handles nulls
            && milestone.equals(((Milestone) other).milestone)
            && date.equals(((Milestone) other).date)
            && projectTasks.equals(((Milestone) other).projectTasks)); // state check
    }
    @Override
    public String toString() {
        return this.milestone + " " + this.date;
    }

}


