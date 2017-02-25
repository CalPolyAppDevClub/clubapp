package club.polyappdev.clubapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.Date;

import club.polyappdev.clubapp.Models.Club;
import club.polyappdev.clubapp.Models.Event;

public class events extends AppCompatActivity {
    /**
     *  Events page(user side)
     *  @author Jacky Huang (huangjacky.1996@gmail.com)
     *  @version 1 - Feb.25.2017 first init
     */
    protected TextView titleNameView;
    protected ImageView photoView;
    protected TextView locationView;
    protected TextView timeView;
    protected MapView mapView;
    protected TextView descriptionView;
    protected TextView keywordView;
    protected CheckBox notifymeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        /**
         *   init events(user) page view
         */
        this.titleNameView = (TextView)findViewById(R.id.titleView);
        this.photoView = (ImageView)findViewById(R.id.photos);
        this.locationView = (TextView)findViewById(R.id.location);
        this.timeView = (TextView)findViewById(R.id.time);
        this.mapView = (MapView)findViewById(R.id.map);
        this.descriptionView = (TextView)findViewById(R.id.description);
        this.keywordView = (TextView)findViewById(R.id.keyword);
        this.notifymeView = (CheckBox)findViewById(R.id.notifyBox);
    }

    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Club club = new Club();
        club.setName("Food Club");

        ArrayList<Event> sample = new ArrayList<>();
        Event event3 = new Event();
        event3.setDate(new Date(2017, 0, 9));
        event3.setStringLoc("San Luis Obispo");
        event3.setTitle("Picnic");
        event3.setDescription("We are going to eat.");
        event3.setFood(true);
        event3.setClub(club);

        sample.add(event3);

        View view = inflater.inflate(R.layout.fragment_subscribed, container, false);

        return view;
    }


}
