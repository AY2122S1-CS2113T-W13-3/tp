package seedu.duke.task.type;

import seedu.duke.nusmods.Semester;
import seedu.duke.task.RecurrenceEnum;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * A single lesson class e.g. CS2101 C02 Thursday
 */
public class Lesson extends Event {

    private int[] occurrences;

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    private String moduleCode;

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    private String classNo;

    public Lesson(String moduleCode, String classNo, DayOfWeek weekday, LocalTime start, LocalTime end, int[] weeks) {
        super(moduleCode, Semester.getSemester().getStartingMonday().with(weekday).atTime(start),
                Semester.getSemester().getStartingMonday().with(weekday).atTime(end),
                RecurrenceEnum.WEEKLY);
        setModuleCode(moduleCode);
        setClassNo(classNo);
        setOccurrences(weeks);
        System.out.println(Arrays.toString(occurrences));
    }

    public void setOccurrences(int[] occurrences) {
        this.occurrences = occurrences;
    }
}
