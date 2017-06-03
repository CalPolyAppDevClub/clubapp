package club.polyappdev.clubapp;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.CalendarContract;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import club.polyappdev.clubapp.AllViewable.LoginActivity;

/**
 * Created by USER on 4/15/2017.
 */

public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        InputMethodManager imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
        //for focus on EditText

        final EditText passwordView = (EditText)(findViewById(R.id.editTextCnf)) ;

        passwordView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                boolean keyEntered = false;
                if (i == KeyEvent.KEYCODE_ENTER){
                    keyEntered = true;
                    changePW(passwordView);

                }

                return keyEntered;
            }
        });

        final EditText ed = (EditText)(findViewById(R.id.editTextOld)) ;


        ed.requestFocus();
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

    }

    private String getString(EditText view){
        if (!view.getText().toString().equals(""))
            return view.getText().toString();
        else{
            return "";
        }
    }

    public void changePW(View v) {
        final EditText old = (EditText)(findViewById(R.id.editTextOld)) ;
        final EditText newP = (EditText)(findViewById(R.id.editTextNew)) ;


        final EditText confirm = (EditText)(findViewById(R.id.editTextCnf)) ;

         if(!getString(old).equals("")
                && !getString(newP).equals("")
                && !getString(confirm).equals("")) {

             if(!getString(confirm).equals(getString(newP))) {
                 Toast.makeText(this, "The two new passwords don't match!", Toast.LENGTH_SHORT).show();
             } else if(!check(getString(old))) {
                 Toast.makeText(this, "The current password is incorrect!", Toast.LENGTH_SHORT).show();
             } else {

                 Toast.makeText(this, "It would change the password here", Toast.LENGTH_SHORT).show();
                 finish();
             }
         } else {
             Toast.makeText(this, "You need to fill out all 3 fields!", Toast.LENGTH_SHORT).show();

         }
    }
    public void cancel(View v) {
        finish();
    }

    public boolean check(String pw) {
        return pw.equals(LoginActivity.password);
    }
}
