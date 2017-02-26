package club.polyappdev.clubapp.StudentViewable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import club.polyappdev.clubapp.AllViewable.events;
import club.polyappdev.clubapp.Models.Club;
import club.polyappdev.clubapp.Models.Event;
import club.polyappdev.clubapp.Models.Subscription;
import club.polyappdev.clubapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Subscribed.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Subscribed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Subscribed extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<Subscription> mSubscriptionList;
    private List<Event> mEventList;
    ListView subscribedListView;

    public Subscribed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Subscribed.
     */
    // TODO: Rename and change types and number of parameters
    public static Subscribed newInstance(String param1, String param2) {
        Subscribed fragment = new Subscribed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
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
        subscriptionRowAdapter adapter = new subscriptionRowAdapter(this.getContext(), R.layout.subscribed_layout_row, sample);

        subscribedListView.setAdapter(adapter);

        subscribedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event clickedEvent = (Event) parent.getItemAtPosition(position);

                Intent eventIntent = new Intent(getContext(),events.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("eventName", clickedEvent.getTitle()); //serializable?
//                bundle.putString("eventDesc", clickedEvent.getDescription());
//                bundle.putString("eventStrLoc", clickedEvent.getStringLoc());
//                bundle.putLong("eventDate", clickedEvent.getDate().getTime());
//                bundle.putString("eventClub", clickedEvent.getClub().getName());
//                eventIntent.putExtras(bundle);
                //based on item add info to intent
                startActivity(eventIntent);
            }
        });



        // Inflate the layout for this fragment

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
}
