package hackathon.com.museuimpressoes;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.LogInCallback;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button signUpButton;
    private Button SignInFacebookButton;
    private Button signInButton;
    private ProgressDialog progressDialog;
    static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "socorroLG");
        setContentView(R.layout.activity_login);


        if (ParseUser.getCurrentUser() != null)
            goToMainActivity();
        else {


            SignInFacebookButton = (Button) findViewById(R.id.login_button);
            SignInFacebookButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LoginActivity.this.progressDialog = ProgressDialog.show(
                            LoginActivity.this, "", "Logging in...", true);
                    List<String> permissions = Arrays.asList("public_profile",
                            "user_birthday", "user_location");
                    ParseFacebookUtils.logInWithReadPermissionsInBackground(LoginActivity.this, permissions, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, com.parse.ParseException e) {
                            progressDialog.dismiss();
                            if (parseUser == null) {
                                Log.d(TAG, "Usuário cancelou login");

                            } else {
                                Log.d(TAG, "Entrou");
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            }
                        }

                    });


        }
    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    public void goToMainActivity(){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }


}
