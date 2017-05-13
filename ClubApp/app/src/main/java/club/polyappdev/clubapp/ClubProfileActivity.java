package club.polyappdev.clubapp;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import club.polyappdev.clubapp.AllViewable.events;
import club.polyappdev.clubapp.Models.Club;
import club.polyappdev.clubapp.Models.Event;
import club.polyappdev.clubapp.StudentViewable.Subscribed;
import club.polyappdev.clubapp.StudentViewable.subscriptionRowAdapter;

import static club.polyappdev.clubapp.R.id.subscribedListView;
import static java.security.AccessController.getContext;

public class ClubProfileActivity extends AppCompatActivity {

    protected TextView clubName;
    protected ImageView clubPhoto;
    protected TextView clubDescription;
    protected TextView clubEmail;
    protected TextView clubWebsite;
    protected Button subscribed;
    protected ListView subscribedListView;
    private double d1 = 0.0;
    private int d2 = 0;

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

        List<String> keywords = new ArrayList<>();
        keywords.add("Android");
        keywords.add("iOS");
        keywords.add("Mobile Apps");

        Club test = new Club();
        test.setName("Mobile App Development Club");
        test.setPhoto("photo.png");
        test.setDescription("Where People Make Apps!!! Where People Make Apps!!! Where People Make Apps!!! Where People Make Apps!!! Where People Make Apps!!! Where People Make Apps!!! Where People Make Apps!!! Where People Make Apps!!! Where People Make Apps!!! Where People Make Apps!!!");
        test.setEmail("appdev.calpoly.edu");
        test.setWebsite("mobileappdev.com");
        test.setClubType("Engineering");

        test.setTags(tags);

        Club club = new Club();
        club.setName("Space Club");

        Club otherClub = new Club();
        otherClub.setName("Better Space Club");

        ArrayList<Event> sample = new ArrayList<>();
        Event event1 = new Event();
        event1.setDate(new Date(2017, 0, 9));
        event1.setStringLoc("Canada");
        event1.setTitle("Moon Landing");
        event1.setDescription("We are going to attempt to land a man on the moon, with the generous help of the Canadian Space Program.");
        event1.setClub(club);

        Event event2 = new Event();
        event2.setDate(new Date(2017, 2, 11));
        event2.setStringLoc("Mexico");
        event2.setTitle("Another Moon Landing");
        event2.setDescription("We are doing a moon landing that is way better than Space Club's");
        event2.setClub(otherClub);

        Event event3 = new Event();
        event3.setDate(new Date(2017, 2, 12));
        event3.setStringLoc("Japan");
        event3.setTitle("Olympics");
        event3.setDescription("The best space athletes gather to compete for the gold.");
        event3.setClub(club);

        sample.add(event1);
        sample.add(event2);
        sample.add(event3);

        /*
        List<Event> events = new ArrayList<>();

        for (int k = 0; k < 4; k++) {
            Event event = new Event();
            event.setClub(test);
            event.setDate(new Date());
            event.setLocation(new Location(Location.convert(d1, d2)));
            event.setDescription("Weekly Overview");
            event.setStringLoc("180-114");
            event.setStringDate("4/22/2017");
            event.setTitle("General Meeting");
            event.setFood(false);
            event.setKeywords(keywords);
            events.add(event);
        }
        test.setEventList(events);
        */

        /* initializing views */
        clubName = (TextView) findViewById(R.id.clubNameView);
        clubPhoto = (ImageView) findViewById(R.id.clubProfileImage);
        clubDescription = (TextView) findViewById(R.id.clubDescriptionView);
        clubEmail = (TextView) findViewById(R.id.emailView);
        clubWebsite = (TextView) findViewById(R.id.websiteView);
        subscribed = (Button) findViewById(R.id.subscribedButton);
        subscribedListView = (ListView) findViewById(R.id.subscribedListView);

        //FIXME change sample to mEventList once we are getting subscriptions from online
        subscriptionRowAdapter adapter = new subscriptionRowAdapter(this, R.layout.subscribed_layout_row, sample);

        subscribedListView.setAdapter(adapter);

        subscribedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event clickedEvent = (Event) parent.getItemAtPosition(position);

                Intent eventIntent = new Intent(ClubProfileActivity.this, events.class);
                Bundle bundle = new Bundle();
                bundle.putString("eventName", clickedEvent.getTitle()); //serializable?
                bundle.putString("eventDesc", clickedEvent.getDescription());
                bundle.putString("eventStrLoc", clickedEvent.getStringLoc());
                bundle.putLong("eventDate", clickedEvent.getDate().getTime());
                bundle.putString("eventClub", clickedEvent.getClub().getName());
                eventIntent.putExtras(bundle);
                //based on item add info to intent
                startActivity(eventIntent);
            }
        });
        // Inflate the layout for this fragment

        //clubEvent = (ScrollView) findViewById(R.id.clubEventView);

        /* setting views */
        clubName.setText(test.getName());
        clubDescription.setText(test.getDescription());
        clubEmail.setText(test.getEmail());
        clubWebsite.setText(test.getWebsite());
        makeTextViewResizable(clubDescription, 1, "...More", true);

        clubPhoto.setImageResource(R.drawable.gettheapp);
    }

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null)
            tv.setTag(tv.getText());
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length()) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), BufferType.SPANNABLE);
                }
            }
        });
    }

    private static SpannableStringBuilder addClickablePartTextViewResizable (final Spanned strSpanned, final TextView tv,
        final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 1, "...More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);
        }
        return ssb;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Club club = new Club();
        club.setName("Space Club");

        Club otherClub = new Club();
        otherClub.setName("Better Space Club");

        ArrayList<Event> sample = new ArrayList<>();
        Event event1 = new Event();
        event1.setDate(new Date(2017, 0, 9));
        event1.setStringLoc("Canada");
        event1.setTitle("Moon Landing");
        event1.setDescription("We are going to attempt to land a man on the moon, with the generous help of the Canadian Space Program.");
        event1.setClub(club);

        Event event2 = new Event();
        event2.setDate(new Date(2017, 2, 11));
        event2.setStringLoc("Mexico");
        event2.setTitle("Another Moon Landing");
        event2.setDescription("We are doing a moon landing that is way better than Space Club's");
        event2.setClub(otherClub);

        Event event3 = new Event();
        event3.setDate(new Date(2017, 2, 12));
        event3.setStringLoc("Japan");
        event3.setTitle("Olympics");
        event3.setDescription("The best space athletes gather to compete for the gold.");
        event3.setClub(club);

        sample.add(event1);
        sample.add(event2);
        sample.add(event3);

        // FIXME delete this sample data
        // the above is all testing code
        // the adapter is written assuming we are making a list of events from subscribed clubs
        // rather than a list of subscribed clubs

//        for (Subscription sub : mSubscriptionList){ //FIXME this code should get subscriptions from database
//            for (Online? evnt : sub.getEvents()){
//                mEventList.add(evnt);
//            }
//        }

        View view = inflater.inflate(R.layout.fragment_subscribed, container, false);
        subscribedListView = (ListView) view.findViewById(R.id.subscribedListView);

        //FIXME change sample to mEventList once we are getting subscriptions from online
        subscriptionRowAdapter adapter = new subscriptionRowAdapter(this, R.layout.subscribed_layout_row, sample);

        subscribedListView.setAdapter(adapter);

        subscribedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event clickedEvent = (Event) parent.getItemAtPosition(position);

                Intent eventIntent = new Intent(ClubProfileActivity.this, events.class);
                Bundle bundle = new Bundle();
                bundle.putString("eventName", clickedEvent.getTitle()); //serializable?
                bundle.putString("eventDesc", clickedEvent.getDescription());
                bundle.putString("eventStrLoc", clickedEvent.getStringLoc());
                bundle.putLong("eventDate", clickedEvent.getDate().getTime());
                bundle.putString("eventClub", clickedEvent.getClub().getName());
                eventIntent.putExtras(bundle);
                //based on item add info to intent
                startActivity(eventIntent);
            }
        });
        // Inflate the layout for this fragment

        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /*
    @NonNull
    @Override
    public View getView(Club club) {
        ImageView clubIcon = (ImageView) findViewById(R.id.club.getEventList());
        TextView clubName = (TextView) view.findViewById(R.id.clubNameText);
        TextView eventName = (TextView) view.findViewById(R.id.eventNameText);
        TextView eventLoc = (TextView) view.findViewById(R.id.eventLocationText);
        TextView eventDateTime = (TextView) view.findViewById(R.id.eventDateTimeText);
    }
    */

    private void setEvent() {

    }
}
