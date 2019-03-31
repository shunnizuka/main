package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PocketProject;
import seedu.address.model.ReadOnlyPocketProject;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.GitHubAccount;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.skill.Skill;

/**
 * Contains utility methods for populating {@code PocketProject} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new GitHubAccount("yeohyeoh"),
                getSkillSet("friends")),
            new Employee(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new GitHubAccount("bernthemall"),
                getSkillSet("colleagues", "friends")),
            new Employee(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new GitHubAccount("thespiderweb"),
                getSkillSet("neighbours")),
            new Employee(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new GitHubAccount("liddavid"),
                getSkillSet("family")),
            new Employee(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new GitHubAccount("relaxandcode"),
                getSkillSet("classmates")),
            new Employee(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
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

}
