package club.polyappdev.clubapp.Models;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kris Campos on 1/28/17.
 */
public class Event {
    private Location location;
    private Date date; // Tommy said it includes time in the millisecond "said google"
    private String description;
    private String stringLoc;
    private String stringDate;
    private String clubName;
    private Club club;
    //FIXME Let's add a photo for Events

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
        this.clubName = club.getName();// so I can parcel it
    }

    public String getStringLoc() {
        return stringLoc;
    }

    public void setStringLoc(String stringLoc) {
        this.stringLoc = stringLoc;
    }

    private String title;
    private String photo; /* reference to cache */
    private List<String> keywords;
    private boolean food; // yum ;)

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }
}