package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.command.CommandResult;
import seedu.duke.parser.CommandParser;
import seedu.duke.task.TaskManager;
import seedu.duke.task.reminder.ReminderManager;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private final Scanner in;
    private final Ui ui;
    private ReminderManager reminderManager;

    public Duke() {
        in = new Scanner(System.in);
        ui = new Ui();
        reminderManager = new ReminderManager();
    }

    public String readInput() {
        ui.printCursor();
        if (!in.hasNextLine()) {
            return CommandEnum.BYE.name();
        }
        String input = in.nextLine();
        return input;
    }

    public CommandResult runCommand(Command userCommand) {
        CommandResult commandResult = null;
        try {
            commandResult = userCommand.executeCommand();
        } catch (Exception e) {
            commandResult = new CommandResult(e.toString(), false, false);
        }
        return commandResult;
    }

    public String checkReminder() {
        return reminderManager.sendReminder();
    }

    public void startProgram() {

        ui.printLogo();

        Command userCommand;
        CommandResult commandResult = null;

        do {

            String userInput = readInput();

            userCommand = CommandParser.parseCommand(userInput);

            commandResult = runCommand(userCommand);

            ui.printMessage(commandResult.getMessage());

        } while (commandResult.getIsExited() != true);

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startProgram();
    }

}
