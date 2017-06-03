package club.polyappdev.clubapp.ClubViewable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import club.polyappdev.clubapp.AllViewable.LoginActivity;
import club.polyappdev.clubapp.MySetting;
import club.polyappdev.clubapp.R;
import club.polyappdev.clubapp.StudentViewable.MainActivity;

public class ClubMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_main);
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

        Log.w("test","test");
        //noinspection SimplifiableIfStatement

        //4.29.2017 modify logout to clear stack and log out of account from firebase - Jacky Huang
        if (id == R.id.action_logout){

            startActivity(LoginActivity.getLogOutIntent(this));
        }
        else if (id == R.id.action_settings) {
            Intent intent2 = new Intent(ClubMainActivity.this, MySetting.class);
            startActivity(intent2);
        }

        return super.onOptionsItemSelected(item);
    }
}
