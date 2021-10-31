package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.annotation.RegisterCommand;
import seedu.duke.command.flags.EventFlag;
import seedu.duke.parser.DateParser;
import seedu.duke.task.factory.EventFactory;
import seedu.duke.task.factory.TaskFactory;
import seedu.duke.task.taskmanager.TaskManager;

//@@author SeanRobertDH
@RegisterCommand
public class EventCommand extends TaskCommand {

    public static final String COMMAND_NAME = "event";
    private static final String USAGE = "event <description> <%s %s> <%s %s>";

    public EventCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    TaskFactory setTaskFactory() {
        return new EventFactory(commandArguments);
    }

    @Override
    protected String getTaskUsage() {
        return String.format(USAGE,
            EventFlag.START_DATE, DateParser.getDefaultDateFormat(),
            EventFlag.END_DATE, DateParser.getDefaultDateFormat());
    }
}