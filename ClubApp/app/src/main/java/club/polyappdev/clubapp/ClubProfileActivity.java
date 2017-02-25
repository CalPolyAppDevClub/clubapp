package club.polyappdev.clubapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import club.polyappdev.clubapp.Models.Club;
import club.polyappdev.clubapp.Models.Event;

public class ClubProfileActivity extends AppCompatActivity {

    protected TextView clubName;
    protected ImageView clubPhoto;
    protected TextView clubDescription;
    protected TextView clubEmail;
    protected TextView clubWebsite;
    protected Button subscribed;
    protected ScrollView clubEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Required empty public constructor
        List<String> tags = new ArrayList<>();
        tags.add("Apps");
        tags.add("Computer Science");
        tags.add("Java");
        tags.add("Android");
        tags.add("iOS");

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Event event = new Event();
            events.add(event);
        }
        Club test = new Club();
        test.setName("Mobile App Development Club");
        test.setPhoto("photo.png");
        test.setDescription("Where People Make Apps!!!");
        test.setEmail("appdev.calpoly.edu");
        test.setWebsite("mobileappdev.com");
        test.setClubType("Engineering");
        test.setEventList(events);
        test.setTags(tags);

        clubName = (TextView) findViewById(R.id.clubNameView);
        clubPhoto = (ImageView) findViewById(R.id.clubProfileImage);
        clubDescription = (TextView) findViewById(R.id.clubDescriptionView);
        clubEmail = (TextView) findViewById(R.id.emailView);
        clubWebsite = (TextView) findViewById(R.id.websiteView);
        subscribed = (Button) findViewById(R.id.subscribedButton);
        clubEvent = (ScrollView) findViewById(R.id.clubEventView);

        clubName.setText(test.getName());
        clubDescription.setText(test.getDescription());
        clubEmail.setText(test.getEmail());
        clubWebsite.setText(test.getWebsite());


    }

}
