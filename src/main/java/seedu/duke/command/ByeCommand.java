package seedu.duke.command;

import seedu.duke.command.annotation.RegisterCommand;
import seedu.duke.task.taskmanager.TaskManager;

import java.util.Map;

@RegisterCommand
public class ByeCommand extends Command {

    public static final String COMMAND_NAME = "bye";

    private static final String EXIT_MSG = "Exiting program!";
    private static final String USAGE = "bye";

    public ByeCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(EXIT_MSG, false, true);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }
}
