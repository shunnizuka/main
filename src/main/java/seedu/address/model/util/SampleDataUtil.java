package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PocketProject;
import seedu.address.model.ReadOnlyPocketProject;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeName;
import seedu.address.model.employee.GitHubAccount;
import seedu.address.model.employee.Phone;
import seedu.address.model.project.ProjectName;
import seedu.address.model.skill.Skill;

/**
 * Contains utility methods for populating {@code PocketProject} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new EmployeeName("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new GitHubAccount("yeohyeoh"),
                getSkillSet("friends")),
            new Employee(new EmployeeName("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new GitHubAccount("bernthemall"),
                getSkillSet("colleagues", "friends")),
            new Employee(new EmployeeName("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new GitHubAccount("thespiderweb"),
                getSkillSet("neighbours")),
            new Employee(new EmployeeName("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new GitHubAccount("liddavid"),
                getSkillSet("family")),
            new Employee(new EmployeeName("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new GitHubAccount("relaxandcode"),
                getSkillSet("classmates")),
            new Employee(new EmployeeName("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new GitHubAccount("chindianattacks"),
                getSkillSet("colleagues"))
        };
    }

    public static ReadOnlyPocketProject getSamplePocketProject() {
        PocketProject samplePp = new PocketProject();
        for (Employee sampleEmployee : getSampleEmployees()) {
            samplePp.addEmployee(sampleEmployee);
        }
        return samplePp;
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of project names containing the strings given.
     */
    public static List<ProjectName> getProjectNamesList(String... strings) {
        return Arrays.stream(strings).map(ProjectName::new).collect(Collectors.toList());
    }
}
