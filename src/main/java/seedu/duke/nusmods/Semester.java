package seedu.duke.nusmods;

import java.util.Calendar;
import java.util.Date;

public enum Semester {
    S1, S2, ST1, ST2;

    public static Semester fromInt(int n) {
        // n is 1-based, as in NUSMods data
        return Semester.values()[n - 1];
    }

    public int getStartWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        switch (this) {
        case S1:
            calendar.set(Calendar.MONTH, Calendar.AUGUST);
            calendar.set(Calendar.WEEK_OF_MONTH, 2);
            break;
        case S2:
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.WEEK_OF_MONTH, 2);
            break;
        case ST1:
            calendar.set(Calendar.MONTH, Calendar.MAY);
            calendar.set(Calendar.WEEK_OF_MONTH, 2);
            break;
        case ST2:
            calendar.set(Calendar.MONTH, Calendar.MAY);
            calendar.set(Calendar.WEEK_OF_MONTH, 2);
            calendar.add(Calendar.WEEK_OF_YEAR, 6);
            break;
        default:
            break;
        }
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}
