package seedu.duke.command;

import seedu.duke.task.taskmanager.TaskManager;

import java.util.Map;

public class InvalidCommand extends Command {

    static final String INVALID_MSG = "The command you returned is not understood";

    public InvalidCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        return new CommandResult(INVALID_MSG, false, false);
    }

    @Override
    protected String getUsage() {
        return null;
    }
}
