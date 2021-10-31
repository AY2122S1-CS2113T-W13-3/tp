package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    private static final String LIST_STORAGE_FOLDER = "./data/";
    private static final String LIST_STORAGE_FILE = "./data/task.txt";
    private static final String DIARY_STORAGE_FILE = "./data/diary.txt";

    private File taskFile;
    private File diaryFile;

    public FileCreator() {
        createFolder();
        try {
            taskFile = createFile(LIST_STORAGE_FILE);
            diaryFile = createFile(DIARY_STORAGE_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFolder() {
        File newFolder = new File(LIST_STORAGE_FOLDER);
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
    }

    private File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public File getTaskFile() {
        return taskFile;
    }

    public File getDiaryFile() {
        return diaryFile;
    }
}
