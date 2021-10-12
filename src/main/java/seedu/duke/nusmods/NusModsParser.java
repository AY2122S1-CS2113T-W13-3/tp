package seedu.duke.nusmods;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Lesson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.stream.StreamSupport;

import static org.apache.commons.io.FileUtils.copyURLToFile;

public class NusModsParser {
    public static final String CACHEDIR = "cache/";
    private static final String ENDPOINT = "https://api.nusmods.com/v2/";
    private static final int TIMEOUT = 5000;

    private static Semester getSemester() {
        // FIXME
        return Semester.S1;
    }


    /**
     * Get calendar events of the given lesson in the current semester.
     * @param lesson The lesson to be queried
     * @return An array of Events denoting all class occurrences
     * @throws IOException If there is neither network connection nor local cache
     */
    public Event[] getLessonEvents(Lesson lesson) throws IOException {
        DateFormat formatter = new SimpleDateFormat("HHmm");
        JsonObject obj = JsonParser.parseReader(getModuleReader(lesson.getModuleCode())).getAsJsonObject();
        JsonArray semesterData = obj.getAsJsonArray("semesterData");
        Event[] lessonEvents = null;
        for (JsonElement element : semesterData) {
            JsonObject semesterObject = element.getAsJsonObject();
            if (Semester.fromInt(semesterObject.get("semester").getAsInt()) == getSemester()) {
                JsonArray timetable = semesterObject.get("timetable").getAsJsonArray();
                lessonEvents = StreamSupport.stream(timetable.spliterator(), true)
                        .map(JsonElement::getAsJsonObject)
                        .filter(l -> lesson.getClassNo().equals(l.get("classNo").getAsString()))
                        .map(l -> {
                            try {
                                return new Event(lesson.getTaskEntryDescription(),
                                        formatter.parse(l.get("startTime").getAsString()),
                                        formatter.parse(l.get("endTime").getAsString()));
                            } catch (ParseException ex) {
                                return new Event(lesson.getTaskEntryDescription());
                            }
                        })
                        .toArray(Event[]::new);
            }
        }
        return lessonEvents;
    }

    private Reader getModuleReader(String moduleCode) throws IOException {
        if (!(new File(getModuleFilename(moduleCode)).isFile())) {
            // file not existing
            getModuleJson(moduleCode);
        }
        return new FileReader(getModuleFilename(moduleCode));
    }

    private String getModuleFilename(String moduleCode) {
        return CACHEDIR + moduleCode + ".json";
    }

    private static String getModuleUrl(String moduleCode) {
        return ENDPOINT + formatAcadYear(getAcadYear()) + "/modules/" + moduleCode + ".json";
    }

    private void getModuleJson(String moduleCode) throws IOException {
        copyURLToFile(new URL(getModuleUrl(moduleCode)), new File(getModuleFilename(moduleCode)), TIMEOUT, TIMEOUT);
    }

    private static Year getAcadYear() {
        YearMonth current = YearMonth.now();
        Year year = Year.of(current.getYear());
        if (current.getMonth().compareTo(Month.AUGUST) < 0) {
            year = year.minusYears(1);
        }
        return year;
    }

    private static String formatAcadYear(Year year) {
        return String.format("%tY-%tY", year, year.plusYears(1));
    }
}
