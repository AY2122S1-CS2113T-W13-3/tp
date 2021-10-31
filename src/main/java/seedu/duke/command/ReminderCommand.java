package seedu.duke.command;

import seedu.duke.command.annotation.RegisterCommand;
import seedu.duke.exception.MissingReminderFieldException;
import seedu.duke.exception.MissingUserMessageException;
import seedu.duke.exception.MissingUserTimeException;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.task.reminder.ReminderManager;

import java.util.Map;

@RegisterCommand
public class ReminderCommand extends Command {
    public static final String COMMAND_NAME = "reminder";
    private static final String USAGE = "reminder";

    public ReminderCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message;
        try {
            message = ReminderManager.customizeReminder(taskManager, commandArguments);
        } catch (NumberFormatException nfe) {
            message = "Please use integer values for time and index";
        } catch (MissingUserTimeException mute) {
            message = mute.getMessage();
        } catch (MissingUserMessageException mume) {
            message = mume.getMessage();
        } catch (MissingReminderFieldException mrfe) {
            message = mrfe.getMessage();
        }
        return new CommandResult(message, true, false);
    }
}
