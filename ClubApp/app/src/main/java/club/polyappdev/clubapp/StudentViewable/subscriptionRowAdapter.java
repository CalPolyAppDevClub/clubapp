package club.polyappdev.clubapp.StudentViewable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import club.polyappdev.clubapp.Models.Club;
import club.polyappdev.clubapp.Models.Event;
import club.polyappdev.clubapp.Models.Subscription;
import club.polyappdev.clubapp.R;

/**
 * Created by dphey on 2/4/2017.
 */

public class subscriptionRowAdapter extends ArrayAdapter<Event> {

    private ArrayList<Event> subscribedEvents;


    public subscriptionRowAdapter(Context context, int resource, ArrayList<Event> subscribedEvents) {
        super(context, resource, subscribedEvents);
        this.subscribedEvents = subscribedEvents;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.subscribed_layout_row, null);
        }
        Event event =  this.subscribedEvents.get(position);

        if (event != null){
            ImageView clubIcon = (ImageView) view.findViewById(R.id.subscriptionClubIcon);
            TextView clubName = (TextView) view.findViewById(R.id.clubNameText);
            TextView eventName = (TextView) view.findViewById(R.id.eventNameText);
            TextView eventLoc = (TextView) view.findViewById(R.id.eventLocationText);
            TextView eventDateTime = (TextView) view.findViewById(R.id.eventDateTimeText);
            if (clubIcon != null){
//                clubIcon.setImageDrawable(event.getClub().getPhoto()); // TODO not sure how we're handling photos yet
            }
            if (clubName != null){
                clubName.setText(event.getClub().getName());
            }
            if (eventName != null){ //todo fix to work with multiple events
                eventName.setText(event.getTitle());
            }
            if (eventLoc != null){
                eventLoc.setText(event.getStringLoc());
            }
            if (eventDateTime != null){
                eventDateTime.setText(event.getDate().toString());
            }
        }
        return view;
    }

}
