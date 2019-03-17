package seedu.address.testutil;

import seedu.address.model.PocketProject;
import seedu.address.model.employee.Employee;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PocketProject ab = new AddressBookBuilder().withEmployee("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private PocketProject pocketProject;

    public AddressBookBuilder() {
        pocketProject = new PocketProject();
    }

    public AddressBookBuilder(PocketProject pocketProject) {
        this.pocketProject = pocketProject;
    }

    /**
     * Adds a new {@code Employee} to the {@code PocketProject} that we are building.
     */
    public AddressBookBuilder withEmployee(Employee employee) {
        pocketProject.addEmployee(employee);
        return this;
    }

    /**
     * Adds a new {@code Project} to the {@code PocketProject} that we are building.
     */
    public AddressBookBuilder withProject(Project project) {
        pocketProject.addProject(project);
        return this;
    }

    public PocketProject build() {
        return pocketProject;
    }
}
