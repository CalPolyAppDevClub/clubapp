package club.polyappdev.clubapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import club.polyappdev.clubapp.Models.Event;
import club.polyappdev.clubapp.Models.Club;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClubProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClubProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubProfile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected TextView clubName;
    protected ImageView clubPhoto;
    protected TextView clubDescription;
    protected TextView clubEmail;
    protected TextView clubWebsite;
    protected Button subscribed;
    protected ScrollView clubEvent;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Club test = new Club();

    public ClubProfile() {

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
        test.setName("Mobile App Development Club");
        test.setPhoto("photo.png");
        test.setDescription("Where People Make Apps!!!");
        test.setEmail("appdev.calpoly.edu");
        test.setWebsite("mobileappdev.com");
        test.setClubType("Engineering");
        test.setEventList(events);
        test.setTags(tags);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubProfile newInstance(String param1, String param2) {
        ClubProfile fragment = new ClubProfile();
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
        View V = inflater.inflate(R.layout.fragment_club_profile, container, false);

        clubName = (TextView) V.findViewById(R.id.clubNameView);
        clubPhoto = (ImageView) V.findViewById(R.id.clubProfileImage);
        clubDescription = (TextView) V.findViewById(R.id.clubDescriptionView);
        clubEmail = (TextView) V.findViewById(R.id.emailView);
        clubWebsite = (TextView) V.findViewById(R.id.websiteView);
        subscribed = (Button) V.findViewById(R.id.subscribedButton);
        clubEvent = (ScrollView) V.findViewById(R.id.clubEventView);

        clubName.setText(test.getName());
        clubDescription.setText(test.getDescription());
        clubEmail.setText(test.getEmail());
        clubWebsite.setText(test.getWebsite());

        return V;
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
