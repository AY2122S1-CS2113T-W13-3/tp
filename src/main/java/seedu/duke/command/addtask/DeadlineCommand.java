package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.annotation.RegisterCommand;
import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.parser.DateParser;
import seedu.duke.task.factory.DeadlineFactory;
import seedu.duke.task.factory.TaskFactory;
import seedu.duke.task.taskmanager.TaskManager;

//@@author SeanRobertDH
@RegisterCommand
public class DeadlineCommand extends TaskCommand {

    public static final String COMMAND_NAME = "deadline";
    private static final String USAGE = "deadline <description> <%s %s>";

    public DeadlineCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    TaskFactory setTaskFactory() {
        return new DeadlineFactory(commandArguments);
    }

    @Override
    protected String getTaskUsage() {
        return String.format(USAGE, DeadlineFlag.DUE_DATE, DateParser.getDefaultDateFormat());
    }
}
