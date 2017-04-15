package club.polyappdev.clubapp.StudentViewable;

import android.widget.BaseAdapter;

/**
 * Created by Connor on 1/28/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import club.polyappdev.clubapp.Models.Notification;
import club.polyappdev.clubapp.R;

public class NotificationListAdapter extends BaseAdapter {
    Context context;
    Notification[] notification_list;
    LayoutInflater inflter;

    public NotificationListAdapter(Context applicationContext, Notification[] notification_list) {
        this.context = applicationContext;
        this.notification_list = notification_list;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return notification_list.length;
    }

    @Override
    public Object getItem(int i) {
        return notification_list[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notifications_listview, viewGroup, false);

        TextView eventClub = (TextView) view.findViewById(R.id.eventClub);
        eventClub.setText(notification_list[i].getClub().getName());

        TextView eventTitle = (TextView) view.findViewById(R.id.eventTitle);
        eventTitle.setText(notification_list[i].getEvent().getTitle());

        TextView eventDate = (TextView) view.findViewById(R.id.eventDate);
        eventDate.setText(notification_list[i].getDate().toString());

        TextView eventDescription = (TextView) view.findViewById(R.id.eventDescription);
        eventDescription.setText(notification_list[i].getContent());

        return view;
    }
}

