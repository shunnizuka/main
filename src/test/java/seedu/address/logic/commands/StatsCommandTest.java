package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TestUtil;

/**
 * Contains unit tests for {@code StatsCommand}.
 */
public class StatsCommandTest {

    private final Model model = new ModelManager(TestUtil.typicalPocketProject(), new UserPrefs());
    private final Model expectedModel = new ModelManager(TestUtil.typicalPocketProject(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();
}
