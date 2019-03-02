package seedu.address.model.employee.exceptions;

/**
 * Signals that the operation will result in duplicate employees(projects are considered duplicates if they have the
 * same name).
 */
public class DuplicateEmployeeException extends RuntimeException {
    public DuplicateEmployeeException() {
        super("Operation would result in duplicate employees");
    }
}
