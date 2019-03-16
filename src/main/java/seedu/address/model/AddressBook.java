package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueEmployeeList;
import seedu.address.model.project.Milestone;
import seedu.address.model.project.Project;
import seedu.address.model.project.UniqueProjectList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameEmployee comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEmployeeList employees;
    private final UniqueProjectList projects;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        employees = new UniqueEmployeeList();
        projects = new UniqueProjectList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Employees in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the employee list with {@code employees}.
     * {@code employees} must not contain duplicate employees.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees.setEmployees(employees);
        indicateModified();
    }

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        this.projects.setProjects(projects);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        List<Employee> employeeList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        for (Employee e: newData.getEmployeeList()) {
            employeeList.add(e.clone());
        }
        for (Project p: newData.getProjectList()) {
            projectList.add(p.clone());
        }

        setEmployees(employeeList);
        setProjects(projectList);
    }



    //// employee-level and project-level operations

    /**
     * Returns true if a employee with the same identity as {@code employee} exists in the address book.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Returns true if a project with the same name as {@code project} exists in the address book.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Adds a employee to the address book.
     * The employee must not already exist in the address book.
     */
    public void addEmployee(Employee p) {
        employees.add(p);
        indicateModified();
    }

    /**
     * Adds a project to the address book.
     * The project must not already exist in the address book.
     */
    public void addProject(Project p) {
        projects.add(p);
        indicateModified();
    }

    /**
     * Replaces the given employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the employee list.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee in the address
     * book.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);

        employees.setEmployee(target, editedEmployee);
        indicateModified();
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the address book.
     * The project name of {@code editedProject} must not be the same as another existing project in the address
     * book.
     */
    public void setProject(Project target, Project editedProject) {
        requireNonNull(editedProject);

        projects.setProject(target, editedProject);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEmployee(Employee key) {
        employees.remove(key);
        indicateModified();
    }

    /**
     * Removes {@code project} from this {@code AddressBook}.
     * {@code project} must exist in the address book.
     */
    public void removeProject(Project project) {
        projects.remove(project);
        indicateModified();
    }

    /**
     * Removes {@code targetEmployee} from the {@code targetProject} from this {@code AddressBook}.
     *  {@code targetProject} and {@code targetEmployee} must exist.
     */
    public void removeEmployeeFrom(Project targetProject, Employee targetEmployee) {
        projects.removeEmployeeFrom(targetProject, targetEmployee);
        indicateModified();
    }

    /**
     * Removes {@code targetMilestone} from the {@code targetProject} from this {@code AddressBook}.
     *  {@code targetProject} and {@code targetMilestone} must exist.
     */
    public void removeMilestoneFrom(Project targetProject, Milestone targetMilestone) {
        projects.removeMilestoneFrom(targetProject, targetMilestone);
        indicateModified();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("employees:\n");
        for (Employee e: employees) {
            builder.append(e);
            builder.append("\n");
        }
        builder.append("projects:\n");
        for (Project p: projects) {
            builder.append(p);
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public ObservableList<Employee> getEmployeeList() {
        return employees.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Project> getProjectList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && employees.equals(((AddressBook) other).employees)
                && projects.equals(((AddressBook) other).projects));
    }

    @Override
    public int hashCode() {
        Object[] array = {this.projects, this.employees};
        return Arrays.hashCode(array);
    }


}
