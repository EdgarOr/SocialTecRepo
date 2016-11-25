package moviles.com.socialtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.ParseUser;

import moviles.com.socialtec.vista.ActivityLogin;
import moviles.com.socialtec.vista.MainActivity;

public class DispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);

        } else {
            Intent intent = new Intent(this, ActivityLogin.class);
            finish();
            startActivity(intent);
        }
    }
}
