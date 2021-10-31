package seedu.duke.command;

import seedu.duke.command.annotation.RegisterCommand;
import seedu.duke.exception.EmptySortCriteriaException;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.task.taskmanager.TaskManager;

import java.util.Map;

//@@author APZH
@RegisterCommand
public class SortCommand extends Command {
    public static final String COMMAND_NAME = "sort";

    private static final String USAGE = "sort";

    public SortCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    //@@author APZH
    @Override
    public CommandResult executeCommand() throws Exception {
        String message;

        try {
            message = taskManager.sortTasklist(commandArguments);
        } catch (EmptyTasklistException ete) {
            message = ete.toString();
        } catch (EmptySortCriteriaException esce) {
            message = esce.toString();
        }

        return new CommandResult(message, true, false);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }

}
