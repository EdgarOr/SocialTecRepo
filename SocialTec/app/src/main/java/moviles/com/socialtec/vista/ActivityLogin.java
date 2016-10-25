package moviles.com.socialtec.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import moviles.com.socialtec.ActivityRegistro;
import moviles.com.socialtec.R;

/**
 * Created by Edgar on 10/10/2016.
 */

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private Button botonLogin;
    private Button botonRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botonLogin = (Button) findViewById(R.id.botonLogin);
        botonRegister = (Button) findViewById(R.id.botonRegistrar);
        botonLogin.setOnClickListener(this);
        botonRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        switch(v.getId()) {
            case R.id.botonRegistrar:
                finish();
                i = new Intent(this, ActivityRegistro.class);
                startActivity(i);
                break;
            case R.id.botonLogin:
                i= new Intent(this, MainActivity.class);
                finish();
                startActivity(i);
                break;

        }


    }
}
