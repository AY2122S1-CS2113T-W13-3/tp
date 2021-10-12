package seedu.duke.nusmods;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Lesson;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NusModsParserTest {
    NusModsParser parser = new NusModsParser();

    @Test
    void getModuleEvents_CS2113T_success() throws IOException {
        Event[] moduleLessons = parser.getLessonEvents(new Lesson("CS2113T", "C02"));
        assertEquals(4, moduleLessons.length);
    }

    @Test
    void getModuleEvents_noNetworkAndNoLocalCache_failure() {
        try {
            FileUtils.deleteDirectory(new File(NusModsParser.CACHEDIR)); // remove local cache if existing
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("https.proxyHost", "localhost"); // simulate network down
        assertThrows(IOException.class,
            () -> parser.getLessonEvents(new Lesson("CS2113T", "C02")));
        System.clearProperty("https.proxyHost"); // simulate network down
    }

    @Test
    void getLessonEvents_AY2021_ST1() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2021, Calendar.AUGUST, 9, 0, 0, 0);
        assertEquals(cal.get(Calendar.WEEK_OF_YEAR), Semester.S1.getStartWeek());
    }
}