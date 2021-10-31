package seedu.duke.command;

import java.util.Map;
import seedu.duke.task.taskmanager.TaskManager;

/**
 * Represents a command.
 */
public abstract class Command {
    public static final String MAIN_ARGUMENT = "mainArgument";

    private static final String USAGE_REGEX = "Usage: %s";

    protected final TaskManager taskManager;
    protected final Map<String, String> commandArguments;

    public Command(TaskManager taskManager, Map<String, String> commandArguments) {
        this.taskManager = taskManager;
        this.commandArguments = commandArguments;
    }

    protected String getMainArgument() {
        return commandArguments.get(MAIN_ARGUMENT);
    }

    protected abstract String getUsage();

    protected String getUsageMessage() {
        return String.format(USAGE_REGEX, getUsage());
    }

    public abstract CommandResult executeCommand() throws Exception;

}
