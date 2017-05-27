package club.polyappdev.clubapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import club.polyappdev.clubapp.Models.Club;
import club.polyappdev.clubapp.Models.Event;
import club.polyappdev.clubapp.StudentViewable.MainActivity;
import club.polyappdev.clubapp.StudentViewable.Profile;

/**
 * Created by USER on 3/4/2017.
 */

public class MySetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        Button cp = (Button) findViewById(R.id.C_P_but);
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent (MySetting.this, Pop.class));
            }
        });
        //Intent intent = getIntent();
        //getSupportActionBar().setTitle("Setting");

    }
    public void sendMessage(View view)
    {
        Log.w("test","test");
        Intent intent = new Intent(MySetting.this, EditProfile.class);
        startActivity(intent);
    }


}
