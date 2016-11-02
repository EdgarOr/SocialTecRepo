package moviles.com.socialtec.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ParseServerHelper;

/**
 * Created by Edgar on 10/10/2016.
 */

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private Button botonLogin;
    private Button botonRegister;
    private ParseServerHelper helper;
    private EditText username;
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new ParseServerHelper(this);
        helper.inicializarParse();

        username = (EditText) findViewById(R.id.et_user);
        password= (EditText) findViewById(R.id.et_pass);

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
                helper.loginUsuario(username.getText().toString(), password.getText().toString());
                break;

        }


    }
}
