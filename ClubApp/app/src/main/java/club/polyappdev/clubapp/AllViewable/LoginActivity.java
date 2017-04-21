package club.polyappdev.clubapp.AllViewable;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getUsername();
                String password = getPassword();
                if (username.toLowerCase().contains("club")){
                    Intent clubIntent = new Intent(LoginActivity.this, ClubMainActivity.class);
                    startActivity(clubIntent);
                } else{
                    signIn(username, password);
                    /*Intent userIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(userIntent);*/
                }
            }
        });
    }

    private String getUsername(){
        return this.usernameView.getText().toString();
    }
    private String getPassword() { return this.passwordView.getText().toString(); }

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
