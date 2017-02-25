package club.polyappdev.clubapp.Models;

import java.util.List;

/**
 * Created by joel on 1/28/17.
 */
public class Subscription {

    private Club club;
    private List<Event> events;


    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
