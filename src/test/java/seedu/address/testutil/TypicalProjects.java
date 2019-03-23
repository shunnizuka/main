package seedu.address.testutil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PocketProject;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final Project PROJECT_ALICE = new Project(new ProjectName("Project Alice"), new Client("Dehui"),
            new Deadline("11/02/2019"));

    public static final Project PROJECT_BENSON = new ProjectBuilder().withProjectName("Project Benson")
            .withClient("Jeff").withDeadline("23/01/2011").withDescrption("An application for Benson")
            .withEmployees(Arrays.asList(TypicalEmployees.CARL, TypicalEmployees.DANIEL))
            .withMilestones(Arrays.asList(TypicalMilestones.TYPICAL_MILESTONE_START,
                    TypicalMilestones.TYPICAL_MILESTONE_END)).build();

    public static final Project PROJECT_CARL = new ProjectBuilder().withProjectName("Project Carl").withClient("Darryl")
            .withDeadline("12/12/2012").withDescrption("An application for Carl")
            .withEmployees(Arrays.asList(TypicalEmployees.DANIEL, TypicalEmployees.ELLE)).build();

    public static final Project PROJECT_DANIEL = new ProjectBuilder().withProjectName("Project Daniel")
            .withClient("Shune").withDeadline("21/12/2012").withDescrption("An application for Daniel")
            .withEmployees(Arrays.asList(TypicalEmployees.ELLE, TypicalEmployees.FIONA)).build();

    public static final Project PROJECT_ELLE = new ProjectBuilder().withProjectName("Project Elle").withClient("Jothi")
            .withDeadline("22/03/2019").withDescrption("An application for Elle")
            .withEmployees(Arrays.asList(TypicalEmployees.FIONA, TypicalEmployees.GEORGE)).build();

    public static final Project PROJECT_FIONA = new ProjectBuilder().withProjectName("Project Fiona").withClient("SOC")
            .withDeadline("01/01/2020").withDescrption("An application for Fiona")
            .withEmployees(Arrays.asList(TypicalEmployees.GEORGE, TypicalEmployees.ALICE)).build();

    public static final Project PROJECT_GEORGE = new ProjectBuilder()
            .withProjectName("Project George").withClient("FASS")
            .withDeadline("11/02/2021").withDescrption("An application for George")
            .withEmployees(Arrays.asList(TypicalEmployees.ALICE, TypicalEmployees.BENSON)).build();


    // Manually added
    public static final Project PROJECT_HOON = new ProjectBuilder()
            .withProjectName("Project Hoon Meier").withClient("FOE")
            .withDeadline("21/11/2018").withDescrption("An application for Hoon Meir").build();

    public static final Project PROJECT_IDA = new ProjectBuilder()
            .withProjectName("Project Ida Mueller").withClient("FOS")
            .withDeadline("3/3/2013").withDescrption("An application for Ida Mueller").build();

    public static final Project PROJECT_XAVIER = new Project(new ProjectName("Xavier"), new Client("JJ Pte Ltd"),
            new Deadline("02/03/2033"));

    public static final Project PROJECT_ZULU = new Project(new ProjectName("Zulu"), new Client("Shunnizuka"),
            new Deadline("04/09/2023"));

    /*
    // Manually added - Project's details found in {@code CommandTestUtil}
    public static final Project AMY = new ProjectBuilder().withProjectName(VALID_NAME_AMY).withClient(VALID_PHONE_AMY)
            .withDeadline(VALID_EMAIL_AMY).withClient(VALID_ADDRESS_AMY).withSkills(VALID_SKILL_C).build();
    public static final Project BOB = new ProjectBuilder().withProjectName(VALID_NAME_BOB).withClient(VALID_PHONE_BOB)
            .withDeadline(VALID_EMAIL_BOB).withClient(VALID_ADDRESS_BOB)
            .withSkills(VALID_SKILL_JAVA, VALID_SKILL_C).build();
    */
    public static final String KEYWORD_MATCHING_ALICE = "Alice"; // A keyword that matches MEIER

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns an {@code PocketProject} with all the typical projects.
     */
    public static PocketProject getTypicalPocketProjectWithProjects() {
        PocketProject ab = new PocketProject();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    /**
     * Returns an {@code PocketProject} with all the typical projects added to the given {@code PocketProject}.
     */
    public static PocketProject addTypicalProjects(PocketProject ab) {
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(PROJECT_ALICE, PROJECT_BENSON, PROJECT_CARL,
                PROJECT_DANIEL, PROJECT_ELLE, PROJECT_FIONA, PROJECT_GEORGE));
    }
}
