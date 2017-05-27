package club.polyappdev.clubapp;

import android.app.Activity;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.DisplayMetrics;

/**
 * Created by USER on 4/15/2017.
 */

public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.5),(int)(height*0.5));
    }
}
