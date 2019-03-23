package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.skill.Skill;

/**
 * Represents a Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private Name name;
    private Phone phone;
    private Email email;

    // Data fields
    private Address address;
    private Set<Skill> skills = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Phone phone, Email email, Address address, Set<Skill> skills) {
        requireAllNonNull(name, phone, email, address, skills);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.skills.addAll(skills);
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

    public Address getAddress() {
        return address;
    }

    public void editEmployee(Name name, Phone phone, Email email, Address address, Set<Skill> skills) {
        requireAllNonNull(name, phone, email, address, skills);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.skills.addAll(skills);
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
     * Returns a clone of this Employee object.
     */
    public Employee clone() {
        Set<Skill> newSkills = new HashSet<>();
        for (Skill s: skills) {
            newSkills.add(s.clone());
        }
        return new Employee(this.name.clone(), this.phone.clone(), this.email.clone(), this.address.clone(),
            newSkills);
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
            && otherEmployee.getAddress().equals(getAddress())
            && otherEmployee.getSkills().equals(getSkills());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, skills);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Phone: ")
            .append(getPhone())
            .append(" Email: ")
            .append(getEmail())
            .append(" Address: ")
            .append(getAddress())
            .append(" Tags: ");
        getSkills().forEach(builder::append);
        return builder.toString();
    }

}
