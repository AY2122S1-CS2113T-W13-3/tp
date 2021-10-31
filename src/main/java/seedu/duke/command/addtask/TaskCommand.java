package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.local.DataManager;
import seedu.duke.local.TasktoLineConverter;
import seedu.duke.parser.TaskUsageParser;
import seedu.duke.task.Task;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.task.factory.TaskFactory;

//@@author SeanRobertDH
public abstract class TaskCommand extends Command {

    private static final String TASK_CREATED_MESSAGE = "Task created!\n%s";

    private static final String OPTIONAL_ARGUMENT_FORMAT = "[%s]";
    private static final String ARGUMENT_SPLIT = "|";

    public TaskCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message;
        try {
            if (getMainArgument() == null) {
                throw new NullPointerException();
            }

            TaskFactory taskFactory = setTaskFactory();
            Task task = taskFactory.getTask();
            taskManager.addTask(task);
            DataManager.addTaskLine(TasktoLineConverter.convertTaskToLine(task));
            message = String.format(TASK_CREATED_MESSAGE, task.getTaskEntryDescription());
        } catch (NullPointerException npe) {
            message = getUsageMessage();
        } catch (GetTaskFailedException gtfe) {
            message = gtfe.getMessage();
        }
        return new CommandResult(message, true, false);
    }

    @Override
    protected String getUsage() {
        return getTaskUsage() + ' '
            + TaskUsageParser.getOptionalTaskArguments(OPTIONAL_ARGUMENT_FORMAT, ARGUMENT_SPLIT);
    }

    abstract String getTaskUsage();

    abstract TaskFactory setTaskFactory() throws GetTaskFailedException;
}