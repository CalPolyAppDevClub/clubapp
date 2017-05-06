package club.polyappdev.clubapp.StudentViewable;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import club.polyappdev.clubapp.AllViewable.LoginActivity;
import club.polyappdev.clubapp.MySetting;
import club.polyappdev.clubapp.R;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity implements
        General.OnFragmentInteractionListener,
        Notifications.OnFragmentInteractionListener,
        Profile.OnFragmentInteractionListener,
        Subscribed.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
//    private SectionsPagerAdapter mSectionsPagerAdapter;
    private BottomNavigationView mBottomNavigationView;
    private List<Fragment> mFragmentList = new ArrayList<>();


    /**
     * The {@link ViewPager} that will host the section contents.
     */
//    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNav);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);

        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_subscribed:
                                switchFragment(0);
                                return true;
                            case R.id.nav_notification:
                                switchFragment(1);
                                return true;
                            case R.id.nav_general:
                                switchFragment(2);
                                return true;
                            case R.id.nav_profile:
                                switchFragment(3);
                                return true;
                        }
                        return false;
                    }
                });
        createFragments();
        switchFragment(0);
    }

    private void switchFragment(int pos) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FragmentViewer, mFragmentList.get(pos))
                .commit();
    }

    private void createFragments() {
        Fragment sub = new Subscribed();
        Fragment notif = new Notifications();
        Fragment prof = new Profile();
        Fragment general = new General();
        mFragmentList.add(sub);
        mFragmentList.add(notif);
        mFragmentList.add(general);
        mFragmentList.add(prof);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent2 = new Intent(MainActivity.this, MySetting.class);
            startActivity(intent2);
        }
        //4.22.2017 modify logout to clear stack and log out of account from firebase - Jacky Huang
        if (id == R.id.action_logout) {
            startActivity(LoginActivity.getLogOutIntent(this));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}