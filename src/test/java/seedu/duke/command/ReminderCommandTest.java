package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.command.flags.ReminderFlag;
import seedu.duke.local.DataManager;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReminderCommandTest {
    private ArrayList<String> expectedOut = new ArrayList<>();
    TaskManager taskManager = new TaskManager();
    Task todoReminder;
    Task todoNoReminder;
    Task deadline;
    Task event;
    private static final String DATE1 = "2021-10-30T02:00";
    private static final String DATE2 = "2021-10-30T05:00";

    LocalDateTime startDate = LocalDateTime.parse(DATE1);
    LocalDateTime endDate = LocalDateTime.parse(DATE2);

    @Test
    void executeCommandTest() throws Exception {
        /*
        todoReminder = new Todo("go jogging", PriorityEnum.LOW, startDate, RecurrenceEnum.DAILY);
        todoNoReminder = new Todo("go jogging");
        deadline = new Deadline("return book", startDate, RecurrenceEnum.WEEKLY);
        event = new Event("project meeting", startDate, endDate, RecurrenceEnum.MONTHLY);
        taskManager.addTask(todoReminder);
        taskManager.addTask(todoNoReminder);
        taskManager.addTask(deadline);
        taskManager.addTask(event);

        Map<String, String> arguments1 = new HashMap<>();
        arguments1.put(Command.MAIN_ARGUMENT, "1");
        arguments1.put(ReminderFlag.REMINDER_MESSAGE, "HEY!");
        Command customize1 = new ReminderCommand(taskManager, arguments1);
        customize1.executeCommand();

        Map<String, String> arguments2 = new HashMap<>();
        arguments2.put(Command.MAIN_ARGUMENT, "2");
        arguments2.put(ReminderFlag.REMINDER_MESSAGE, "HEY!");
        Command customize2 = new ReminderCommand(taskManager, arguments2);
        customize2.executeCommand();

        Map<String, String> arguments3 = new HashMap<>();
        arguments3.put(Command.MAIN_ARGUMENT, "3");
        arguments3.put(ReminderFlag.REMINDER_MESSAGE, "HEY!");
        arguments3.put(ReminderFlag.TIME_AHEAD, "15");
        Command customize3 = new ReminderCommand(taskManager, arguments3);
        customize3.executeCommand();

        Map<String, String> arguments4 = new HashMap<>();
        arguments4.put(Command.MAIN_ARGUMENT, "5");
        arguments4.put(ReminderFlag.REMINDER_MESSAGE, "HEY!");
        arguments4.put(ReminderFlag.TIME_AHEAD, "15");
        Command customize4 = new ReminderCommand(taskManager, arguments4);
        customize4.executeCommand();

        expectedOut.add("todo|go jogging|2021-10-30T02:00|low|daily|"
                + "10|HEY!");
        expectedOut.add("todo|go jogging|null|medium|none|null|null");
        expectedOut.add("deadline|return book|2021-10-30T02:00|medium|weekly|"
                + "15|HEY!");
        expectedOut.add("event|project meeting|2021-10-30T02:00|2021-10-30T05:00|medium|monthly|"
                + "10|Reminder! 10 min before the following task:");

        assertEquals(expectedOut, results);
        */
    }
}
