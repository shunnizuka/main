package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;


import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.GitHubAccount;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.skill.Skill;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Employee objects.
 */
public class EmployeeBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_GITHUB = "aliceballer";

    private Name name;
    private Phone phone;
    private Email email;
    private GitHubAccount github;
    private Set<Skill> skills;

    public EmployeeBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        github = new GitHubAccount(DEFAULT_GITHUB);
        skills = new HashSet<>();
    }

    /**
     * Initializes the EmployeeBuilder with the data of {@code employeeToCopy}.
     */
    public EmployeeBuilder(Employee employeeToCopy) {
        name = employeeToCopy.getName();
        phone = employeeToCopy.getPhone();
        email = employeeToCopy.getEmail();
        github = employeeToCopy.getGithub();
        skills = new HashSet<>(employeeToCopy.getSkills());
    }

    /**
     * Sets the {@code Name} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code Employee} that we are building.
     */
    public EmployeeBuilder withSkills(String ... skills) {
        this.skills = SampleDataUtil.getSkillSet(skills);
        return this;
    }

    /**
     * Sets the {@code GitHubAccount} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withGitHubAccount(String account) {
        this.github = new GitHubAccount(account);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Employee build() {
        return new Employee(name, phone, email, github, skills);
    }

}
