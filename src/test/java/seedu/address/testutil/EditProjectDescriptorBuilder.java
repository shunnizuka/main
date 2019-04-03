package seedu.address.testutil;

import seedu.address.logic.commands.EditProjectInfoCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * A utility class to help with building EditProjectDescriptor objects.
 */
public class EditProjectDescriptorBuilder {

    private EditProjectInfoCommand.EditProjectDescriptor descriptor;

    public EditProjectDescriptorBuilder() {
        descriptor = new EditProjectInfoCommand.EditProjectDescriptor();
    }

    public EditProjectDescriptorBuilder(EditProjectInfoCommand.EditProjectDescriptor descriptor) {
        this.descriptor = new EditProjectInfoCommand.EditProjectDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProjectDescriptor} with fields containing {@code Project}'s details
     */
    public EditProjectDescriptorBuilder(Project Project) {
        descriptor = new EditProjectInfoCommand.EditProjectDescriptor();
        descriptor.setProjectName(Project.getProjectName());
        descriptor.setClient(Project.getClient());
        descriptor.setDeadline(Project.getDeadline());
        descriptor.setDescription(Project.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code EditProjectDescriptor} that we are building.
     */
    public seedu.address.testutil.EditProjectDescriptorBuilder withName(String name) throws ParseException {
        descriptor.setProjectName(ParserUtil.parseProjectName(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditProjectDescriptor} that we are building.
     */
    public seedu.address.testutil.EditProjectDescriptorBuilder withClient(String client) throws ParseException {
        descriptor.setClient(ParserUtil.parseClient(client));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditProjectDescriptor} that we are building.
     */
    public seedu.address.testutil.EditProjectDescriptorBuilder withDescription(String description) throws ParseException {
        descriptor.setDescription(ParserUtil.parseDescription(description));
        return this;
    }

    /**
     * Sets the {@code GitHubAccount} of the {@code EditProjectDescriptor} that we are building.
     */
    public seedu.address.testutil.EditProjectDescriptorBuilder withDeadline(String deadline) throws ParseException {
        descriptor.setDeadline(ParserUtil.parseDeadline(deadline));
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code EditProjectDescriptor}
     * that we are building.
     */

    public EditProjectInfoCommand.EditProjectDescriptor build() {
        return descriptor;
    }
}


