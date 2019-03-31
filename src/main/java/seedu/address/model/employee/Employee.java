package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.skill.Skill;

/**
 * Represents a Employee in the pocket project.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final GitHubAccount github;
    private final Set<Skill> skills = new HashSet<>();
    private final List<ProjectName> currentProjects = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Phone phone, Email email, GitHubAccount github, Set<Skill> skills) {
        requireAllNonNull(name, phone, email, github, skills);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.github = github;
        this.skills.addAll(skills);
    }

    /**
     * Constructor including the list of projects the employee is in.
     */
    public Employee(Name name, Phone phone, Email email, GitHubAccount github, Set<Skill> skills,
                    List<ProjectName> projectNames) {
        requireAllNonNull(name, phone, email, github, skills, projectNames);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.github = github;
        this.skills.addAll(skills);
        this.currentProjects.addAll(projectNames);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public GitHubAccount getGithub() {
        return github;
    }

    /**
     * Returns the list of project names of the projects this employee is in.
     */
    public List<ProjectName> getCurrentProjects() {
        return this.currentProjects;
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
            && otherEmployee.getName().equals(getName())
            && (otherEmployee.getPhone().equals(getPhone()) || otherEmployee.getEmail().equals(getEmail()));
    }

    /**
     * Indicates that this employee is in a certain project.
     */
    public void join(Project project) {
        this.currentProjects.add(project.getProjectName());
    }

    /**
     * Indicates that this employee has left a certain project.
     */
    public void leave(Project project) {
        this.currentProjects.remove(project.getProjectName());
    }


    /**
     * Returns a clone of this Employee object.
     */
    public Employee clone() {
        Set<Skill> newSkills = new HashSet<>();
        for (Skill s: skills) {
            newSkills.add(s.clone());
        }
        List<ProjectName> newProjectNames = new ArrayList<>();
        for (ProjectName pn: currentProjects) {
            newProjectNames.add(pn.clone());
        }
        return new Employee(this.name.clone(), this.phone.clone(), this.email.clone(), this.github.clone(),
                newSkills, newProjectNames);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return otherEmployee.getName().equals(getName())
            && otherEmployee.getPhone().equals(getPhone())
            && otherEmployee.getEmail().equals(getEmail())
            && otherEmployee.getGithub().equals(getGithub())
            && otherEmployee.getSkills().equals(getSkills())
            && otherEmployee.getCurrentProjects().equals(getCurrentProjects());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, github, skills);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Phone: ")
            .append(getPhone())
            .append(" Email: ")
            .append(getEmail())
            .append(" GitHubAccount: ")
            .append(getGithub())
            .append(" Skills: ");
        getSkills().forEach(builder::append);
        builder.append("\nprojects: ");
        getCurrentProjects().forEach(builder::append);
        return builder.toString();
    }

}
