package seedu.duke.local;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import seedu.duke.storage.FileCreator;
import seedu.duke.storage.TaskTypeAdapter;
import seedu.duke.task.Task;
import seedu.duke.task.taskmanager.Observer;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Lesson;
import seedu.duke.task.type.Todo;

public class DataManager implements Observer {

    private static final String ERROR_MESSAGE = "Error occurred while loading task list."
        + "Data will be overwritten when you add a task";

    private static final boolean DO_APPEND_TO_FILE = false;

    private File taskFile;
    private File diaryFile;
    private Gson gson;
    private List<Task> taskList;

    public DataManager() {
        FileCreator fileCreator = new FileCreator();
        taskFile = fileCreator.getTaskFile();
        diaryFile = fileCreator.getDiaryFile();

        gson = new GsonBuilder()
            .registerTypeAdapter(Task.class, new TaskTypeAdapter())
            .registerTypeAdapter(Todo.class, new TaskTypeAdapter())
            .registerTypeAdapter(Deadline.class, new TaskTypeAdapter())
            .registerTypeAdapter(Event.class, new TaskTypeAdapter())
            .registerTypeAdapter(Lesson.class, new TaskTypeAdapter())
            .setPrettyPrinting().create();
    }

    public List<Task> loadTaskList(int startingSize) {
        taskList = new ArrayList<>(startingSize);
        try {
            Reader taskFileReader = new FileReader(taskFile);
            List<Task> loadedTaskList = gson.fromJson(taskFileReader, new TypeToken<ArrayList<Task>>() {
            }.getType());
            taskFileReader.close();
            if (loadedTaskList != null) {
                taskList = loadedTaskList;
            }
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (JsonSyntaxException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (NullPointerException e) {
            System.out.println(ERROR_MESSAGE);
        }
        return taskList;
    }

    @Override
    public void update() {
        String taskListJson = gson.toJson(taskList);
        writeToTaskFile(taskListJson);
    }

    private void writeToTaskFile(String taskListJson) {
        try {
            Writer taskFileWriter = new FileWriter(taskFile, DO_APPEND_TO_FILE);
            taskFileWriter.write(taskListJson);
            taskFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
