package club.polyappdev.clubapp.AllViewable;

import android.support.v7.app.AppCompatActivity;
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
/**
 * Created by bleac on 4/22/2017.
 */


public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button SignUp = (Button) findViewById(R.id.SignUpFinal);
    EditText FirstNameView = (EditText) findViewById(R.id.FirstNameView);
    EditText LastNameView = (EditText) findViewById(R.id.LastNameView);
    EditText EmailView = (EditText) findViewById(R.id.CalPolyEmailView);
    EditText PasswordView = (EditText) findViewById(R.id.PasswordView);
    EditText PasswordConfirmView = (EditText) findViewById(R.id.RetypePasswordView);



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        //Do I need this for registration?
        mAuthListener = new FirebaseAuth.AuthStateListener(){
          @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
              FirebaseUser user = firebaseAuth.getCurrentUser();
              if (user != null){
                  //user signed in
              }else{
                  //User signed out
              }
          }
        };

        this.FirstNameView = (EditText) findViewById(R.id.FirstNameView);
        this.LastNameView = (EditText) findViewById(R.id.LastNameView);
        this.EmailView = (EditText) findViewById(R.id.CalPolyEmailView);
        this.PasswordView = (EditText) findViewById(R.id.PasswordView);
        this.PasswordConfirmView = (EditText) findViewById(R.id.RetypePasswordView);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                signUpAttempt(FirstNameView, LastNameView, EmailView, PasswordView, PasswordConfirmView);
            }
        });


    }
    private void signUpAttempt(EditText first, EditText last, EditText mail, EditText pass, EditText passconfirm){
        String firstname = getString(first);
        String lastname = getString(last);
        String email = getString(mail);
        String password = getString(pass);
        String passwordconfirm = getString(passconfirm);
        if(firstname != "" && lastname != "" && email  != "" && password != "" && passwordconfirm != "" && password.equals(passwordconfirm)) {
            createAccount(email, password);
        }
    }

    private String getString(EditText view){
        if (!view.getText().toString().equals(null) && !view.getText().toString().equals(""))
            return view.getText().toString();
        else{
            Toast.makeText(RegistrationActivity.this, R.string.blank_field, Toast.LENGTH_SHORT).show();
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

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, R.string.reg_success, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(RegistrationActivity.this, R.string.reg_fail, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
