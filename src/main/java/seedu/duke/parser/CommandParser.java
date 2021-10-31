package seedu.duke.parser;

import org.reflections.Reflections;
import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.annotation.RegisterCommand;
import seedu.duke.log.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import seedu.duke.task.taskmanager.TaskManager;
import seedu.duke.utility.Utility;

//@@author APZH
public class CommandParser {

    private static final String FLAG_REGEX = "^--\\w+";
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String INVALID_TASK_INDEX = "%s is not an integer!";
    private static final Set<Class<?>> commandClasses
            = new Reflections("seedu.duke.command")
            .getTypesAnnotatedWith(RegisterCommand.class);

    //@@author APZH
    public static Map<String, String> getCommandOptions(String commandArguments) {

        Map<String, String> flagsToArguments = new HashMap<>();
        String[] tokens = commandArguments.split(WHITESPACE_REGEX);
        StringBuilder mainArgument = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].matches(FLAG_REGEX)) {
                String flag = tokens[i];
                StringBuilder flagArguments = new StringBuilder();
                try {
                    while (!tokens[i + 1].matches(FLAG_REGEX)) {
                        flagArguments.append(tokens[i + 1]).append(" ");
                        i++;
                    }
                } catch (IndexOutOfBoundsException e) {
                    Log.warning(e.getMessage());
                }
                flagsToArguments.put(flag.substring(2), flagArguments.toString().trim());
            } else {
                mainArgument.append(tokens[i]).append(" ");
            }
        }

        flagsToArguments.put(Command.MAIN_ARGUMENT, mainArgument.toString().trim());
        return flagsToArguments;
    }

    //@@author APZH
    public static Command parseCommand(TaskManager taskManager, String userInput) {

        String[] inputArguments = userInput.split("\\s+", 2);
        String command = inputArguments[0];
        Map<String, String> commandOptions = new HashMap<>();

        if (inputArguments.length == 2) {
            commandOptions = getCommandOptions(inputArguments[1]);
        }
        for (Class<?> commandClass : commandClasses) {
            assert Command.class.isAssignableFrom(commandClass);
            // can only be programmer mistake if false
            try {
                if (command.equals(commandClass.getField("COMMAND_NAME").get(null))) {
                    return (Command) commandClass.getConstructor(taskManager.getClass(), Map.class)
                            .newInstance(taskManager, commandOptions);
                }
            } catch (NoSuchFieldException | NoSuchMethodException
                    | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                System.out.println(commandClass);
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
        return new InvalidCommand(null, null);
    }

    //@@author APZH
    // Used to debug and check the whether the user command mapping of flag->value works
    public static String printCommandOptions(Map<String, String> commandOptions) {

        StringBuilder flagsToArguments = new StringBuilder();

        for (String flag : commandOptions.keySet()) {
            flagsToArguments.append(flag).append(" = ").append(commandOptions.get(flag)).append("\n");
        }

        System.out.println(flagsToArguments);

        return flagsToArguments.toString();
    }

    //@@author SeanRobertDH
    public static Integer parseTaskIndex(String index) throws NumberFormatException {
        if (!Utility.isInteger(index)) {
            throw new NumberFormatException(String.format(INVALID_TASK_INDEX, index));
        }
        return Integer.parseInt(index);
    }
}
