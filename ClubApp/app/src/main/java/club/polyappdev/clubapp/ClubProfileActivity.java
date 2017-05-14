package club.polyappdev.clubapp;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import club.polyappdev.clubapp.Models.Club;
import club.polyappdev.clubapp.Models.Event;

import static java.security.AccessController.getContext;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        test.setEmail("calpolyappdev@gmail.com");
        test.setWebsite("polyappdev.club");
        test.setClubType("Engineering");

        test.setTags(tags);

        List<Event> events = new ArrayList<>();

        for (int k = 0; k < 4; k++) {
            Event event = new Event();
            event.setClub(test);
            event.setDate(new Date());
            //event.setLocation(new Location(Location.convert(d1, d2)));
            event.setDescription("Weekly Overview");
            event.setStringLoc("180-114");
            event.setStringDate("4/22/2017");
            event.setTitle("General Meeting");
            event.setFood(false);
            event.setKeywords(keywords);
            events.add(event);
        }
        test.setEventList(events);

        /* initializing views */
        clubName = (TextView) findViewById(R.id.clubNameView);
        clubPhoto = (ImageView) findViewById(R.id.clubProfileImage);
        clubDescription = (TextView) findViewById(R.id.clubDescriptionView);
        clubEmail = (TextView) findViewById(R.id.emailView);
        clubWebsite = (TextView) findViewById(R.id.websiteView);
        subscribed = (Button) findViewById(R.id.subscribedButton);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
