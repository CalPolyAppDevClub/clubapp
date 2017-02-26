package club.polyappdev.clubapp.AllViewable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import club.polyappdev.clubapp.ClubViewable.ClubMainActivity;
import club.polyappdev.clubapp.R;
import club.polyappdev.clubapp.StudentViewable.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameView;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.usernameView = (EditText) findViewById(R.id.NameLoginView);
        this.loginButton = (Button) findViewById(R.id.LoginButton);
        usernameView.requestFocus();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getUsername();
                if (username.toLowerCase().contains("club")){
                    Intent clubIntent = new Intent(LoginActivity.this, ClubMainActivity.class);
                    startActivity(clubIntent);
                } else{
                    Intent userIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(userIntent);
                }
            }
        });
    }

    private String getUsername(){
        return this.usernameView.getText().toString();
    }
}
