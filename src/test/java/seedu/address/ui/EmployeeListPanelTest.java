package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalEmployees.getTypicalEmployees;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardEquals;

import guitests.guihandles.EmployeeListPanelHandle;
import java.util.Collections;

import org.junit.Test;

import guitests.guihandles.EmployeeCardHandle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;

public class EmployeeListPanelTest extends GuiUnitTest {
    private static final ObservableList<Employee> TYPICAL_EMPLOYEES =
            FXCollections.observableList(getTypicalEmployees());

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private final SimpleObjectProperty<Employee> selectedPerson = new SimpleObjectProperty<>();
    private EmployeeListPanelHandle employeeListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_EMPLOYEES);

        for (int i = 0; i < TYPICAL_EMPLOYEES.size(); i++) {
            employeeListPanelHandle.navigateToCard(TYPICAL_EMPLOYEES.get(i));
            Employee expectedEmployee = TYPICAL_EMPLOYEES.get(i);
            EmployeeCardHandle actualCard = employeeListPanelHandle.getEmployeeCardHandle(i);

            assertCardDisplaysPerson(expectedEmployee, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    @Test
    public void selection_modelSelectedPersonChanged_selectionChanges() {
        initUi(TYPICAL_EMPLOYEES);
        Employee secondEmployee = TYPICAL_EMPLOYEES.get(INDEX_SECOND_PERSON.getZeroBased());
        guiRobot.interact(() -> selectedPerson.set(secondEmployee));
        guiRobot.pauseForHuman();

        EmployeeCardHandle expectedPerson = employeeListPanelHandle.getEmployeeCardHandle(INDEX_SECOND_PERSON.getZeroBased());
        EmployeeCardHandle selectedPerson = employeeListPanelHandle.getHandleToSelectedCard();
        assertCardEquals(expectedPerson, selectedPerson);
    }

    /**
     * Verifies that creating and deleting large number of persons in {@code EmployeeListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() {
        ObservableList<Employee> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of employee cards exceeded time limit");
    }

    /**
     * Returns a list of persons containing {@code personCount} persons that is used to populate the
     * {@code EmployeeListPanel}.
     */
    private ObservableList<Employee> createBackingList(int personCount) {
        ObservableList<Employee> backingList = FXCollections.observableArrayList();
        for (int i = 0; i < personCount; i++) {
            Name name = new Name(i + "a");
            Phone phone = new Phone("000");
            Email email = new Email("a@aa");
            Address address = new Address("a");
            Employee employee = new Employee(name, phone, email, address, Collections.emptySet());
            backingList.add(employee);
        }
        return backingList;
    }

    /**
     * Initializes {@code employeeListPanelHandle} with a {@code EmployeeListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code EmployeeListPanel}.
     */
    private void initUi(ObservableList<Employee> backingList) {
        EmployeeListPanel employeeListPanel =
                new EmployeeListPanel(backingList, selectedPerson, selectedPerson::set);
        uiPartRule.setUiPart(employeeListPanel);

        employeeListPanelHandle = new EmployeeListPanelHandle(getChildNode(employeeListPanel.getRoot(),
                EmployeeListPanelHandle.EMPLOYEE_LIST_VIEW_ID));
    }
}
