package club.polyappdev.clubapp.Models;

import java.util.Date;

/**
 * Created by Kris Campos on 1/28/17.
 */

public class Notification {
    private Date date;
    private Event event;
    private Club club;
    private String content;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}