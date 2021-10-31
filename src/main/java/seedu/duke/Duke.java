package seedu.duke;

import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.local.DataManager;
import seedu.duke.parser.CommandParser;
import seedu.duke.storage.FileCreator;
import seedu.duke.task.reminder.ReminderManager;
import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private final Scanner in;
    private final Ui ui;
    private ReminderManager reminderManager;
    private TaskManager taskManager;

    public Duke() {
        in = new Scanner(System.in);
        ui = new Ui();

        DataManager dataManager = new DataManager();
        taskManager = new TaskManager(dataManager);

        reminderManager = new ReminderManager();
    }

    public String readInput() {
        ui.printCursor();
        if (!in.hasNextLine()) {
            return ByeCommand.COMMAND_NAME;
        }
        return in.nextLine();
    }

    public CommandResult runCommand(Command userCommand) {
        CommandResult commandResult;
        try {
            commandResult = userCommand.executeCommand();
        } catch (Exception e) {
            commandResult = new CommandResult(e.toString(), false, false);
        }
        return commandResult;
    }

    public String checkReminder() {
        return reminderManager.sendReminder(taskManager);
    }

    public void startProgram() {

        ui.printLogo();

        Command userCommand;
        CommandResult commandResult;

        do {

            String userInput = readInput();

            userCommand = CommandParser.parseCommand(taskManager, userInput);

            commandResult = runCommand(userCommand);

            ui.printMessage(commandResult.getMessage());

        } while (!commandResult.getIsExited());
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startProgram();
    }
}
