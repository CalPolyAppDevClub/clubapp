package club.polyappdev.clubapp;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by ethan on 6/3/17.
 */

public class EasierDate {
    Date date;
    boolean hasTime = false;
    public EasierDate(int year, int month, int day) {
        this.date = new Date(year, month-1, day);
        this.hasTime = false;
    }

    public EasierDate(int year, int month, int day, int h, int m) {
        this(year, month, day);
        Date d =  this.date;
        d.setHours(h);
        d.setMinutes(m);
        this.hasTime = true;
    }

    public String toString() {
        String dateStr = DateFormat.getDateInstance(DateFormat.LONG).format(date);
        if(!this.hasTime) {
            return dateStr;
        } else {
            String timeStr = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
            return dateStr + ", " + timeStr;
        }
    }

    public void setTime(int h, int m) {
        date.setHours(h);
        date.setMinutes(m);
        hasTime = true;
    }

    public long getTime() {
        return date.getTime();
    }
}
