package club.polyappdev.clubapp.AllViewable;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

import club.polyappdev.clubapp.ClubViewable.ClubMainActivity;
import club.polyappdev.clubapp.R;
import club.polyappdev.clubapp.StudentViewable.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameView;
    EditText passwordView;
    Button loginButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        this.usernameView = (EditText) findViewById(R.id.NameLoginView);
        this.passwordView = (EditText) findViewById(R.id.PassLoginView);
        this.loginButton = (Button) findViewById(R.id.LoginButton);
        usernameView.requestFocus();

        passwordView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                boolean keyEntered = false;
                if (i == keyEvent.KEYCODE_ENTER && (!getString(passwordView).equals("") && !getString(usernameView).equals(""))){
                    keyEntered = true;
                    loginAttempt();

                }

                return keyEntered;
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAttempt();
            }
        });
    }
    private void loginAttempt(){
        String username = getString(usernameView);
        String password = getString(passwordView);

        if (username.toLowerCase().contains("club")){
            Intent clubIntent = new Intent(LoginActivity.this, ClubMainActivity.class);
            startActivity(clubIntent);
        }else if (username.equals("") || password.equals("")){
            //do Nothing
        }else{
            signIn(username, password);
        }
    }
    private String getString(EditText view){
        if (!view.getText().toString().equals(null) && !view.getText().toString().equals(""))
            return view.getText().toString();
        else{
            Toast.makeText(LoginActivity.this, "Please enter username/password", Toast.LENGTH_SHORT).show();
            return "";
        }
    }

    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signIn(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_SHORT).show();
                } else{
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

}
