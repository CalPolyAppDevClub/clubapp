package club.polyappdev.clubapp.AllViewable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import club.polyappdev.clubapp.R;

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


}
