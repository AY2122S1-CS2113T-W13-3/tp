package seedu.duke.command;

import seedu.duke.task.taskmanager.TaskManager;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private static final String MESSAGE_NOT_NULL_ASSERTION = "RegisterCommand Result message cannot be null!";

    private boolean isModified;
    private boolean isExited;
    private String message;

    public CommandResult(String message, boolean isModified, boolean isExited) {
        this.isExited = isExited;
        this.message = message;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public void setExited(boolean exited) {
        isExited = exited;
    }

    public void setMessage(String message) {
        assert message != null : MESSAGE_NOT_NULL_ASSERTION;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getIsModified() {
        return this.isModified;
    }

    public boolean getIsExited() {
        return this.isExited;
    }

}
