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

import java.util.Date;

import club.polyappdev.clubapp.AllViewable.events;
import club.polyappdev.clubapp.Models.Club;
import club.polyappdev.clubapp.Models.Event;
import club.polyappdev.clubapp.Models.Notification;
import club.polyappdev.clubapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Notifications.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Notifications#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Notifications extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //test
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Notification[] notification_list = new Notification[20];
    ListView listView;

    public Notifications() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Notifications.
     */
    // TODO: Rename and change types and number of parameters
    public static Notifications newInstance(String param1, String param2) {
        Notifications fragment = new Notifications();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        createList();

        listView = (ListView) view.findViewById(R.id.notificationListView);
        NotificationListAdapter customAdapter = new NotificationListAdapter(getActivity().getApplicationContext(), notification_list);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Event clickedEvent = ((Notification)parent.getAdapter().getItem(position)).getEvent();


                Intent eventIntent = new Intent(getContext(),events.class);
                Bundle bundle = new Bundle();
                bundle.putString("eventName", clickedEvent.getTitle()); //serializable?
                bundle.putString("eventDesc", clickedEvent.getDescription());
                bundle.putString("eventStrLoc", clickedEvent.getStringLoc());
                bundle.putLong("eventDate", clickedEvent.getDate().getTime());
                bundle.putString("eventClub", clickedEvent.getClub().getName());
                eventIntent.putExtras(bundle);

                startActivity(eventIntent);
            }
        });

        return view;
    }

    public void createList() {
        Club tempClub;
        Event tempEvent;
        for (int i = 0; i < notification_list.length; i++) {
            notification_list[i] = new Notification();
            tempClub = new Club();
            tempEvent = new Event();
            tempClub.setName("Club Name " + i);
            tempEvent.setTitle("Event Name " + i);
            tempEvent.setDescription("Description " + i);
            tempEvent.setStringLoc("Building " + i);
            tempEvent.setDate(new Date(i*1000000));
            tempEvent.setClub(tempClub);
            notification_list[i].setClub(tempClub);
            notification_list[i].setDate(new Date(i*1000000));
            notification_list[i].setContent("Description " + i);
            notification_list[i].setEvent(tempEvent);
        }

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
