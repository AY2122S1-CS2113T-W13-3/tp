package seedu.duke.command;

import seedu.duke.command.addtask.DeadlineCommand;
import seedu.duke.command.addtask.EventCommand;
import seedu.duke.command.addtask.TodoCommand;
import seedu.duke.task.taskmanager.TaskManager;

import java.util.Map;

//@@author SeanRobertDH
public class HelpCommand extends Command {

    public static final String COMMAND_NAME = "help";
    private static final String HEADER = "List of commands: \n";
    private static final char NEW_LINE = '\n';

    private static final Command[] COMMANDS_TO_LIST = {
        new ByeCommand(null, null),
        new DeleteCommand(null, null),
        new ListCommand(null, null),
        new SortCommand(null, null),
        new DeadlineCommand(null, null),
        new EventCommand(null, null),
        new TodoCommand(null, null)
    };

    public HelpCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        StringBuilder message = new StringBuilder(HEADER);
        for (Command command : COMMANDS_TO_LIST) {
            message.append(command.getUsage()).append(NEW_LINE);
        }
        return new CommandResult(message.toString(), false, false);
    }

    @Override
    protected String getUsage() {
        return null;
    }
}
