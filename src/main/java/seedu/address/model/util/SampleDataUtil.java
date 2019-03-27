package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PocketProject;
import seedu.address.model.ReadOnlyPocketProject;
import seedu.address.model.employee.GitHubAccount;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
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
                new GitHubAccount("Blk 30 Geylang Street 29, #06-40"),
                getSkillSet("friends")),
            new Employee(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new GitHubAccount("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getSkillSet("colleagues", "friends")),
            new Employee(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new GitHubAccount("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getSkillSet("neighbours")),
            new Employee(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new GitHubAccount("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getSkillSet("family")),
            new Employee(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new GitHubAccount("Blk 47 Tampines Street 20, #17-35"),
                getSkillSet("classmates")),
            new Employee(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new GitHubAccount("Blk 45 Aljunied Street 85, #11-31"),
                getSkillSet("colleagues"))
        };
    }

    public static ReadOnlyPocketProject getSamplePocketProject() {
        PocketProject sampleAb = new PocketProject();
        for (Employee sampleEmployee : getSampleEmployees()) {
            sampleAb.addEmployee(sampleEmployee);
        }
        return sampleAb;
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
