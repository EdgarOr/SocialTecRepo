package moviles.com.socialtec.vista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ParseServerHelper;

public class PublicacionActivity extends AppCompatActivity {

    private ParseUser parseUser;
    private ParseServerHelper helper;
    private EditText contenidoPublicacion;
    private Date fechaPublicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        fechaPublicacion = Calendar.getInstance().getTime();

        ((TextView) findViewById(R.id.tiempo_txt_view)).setText(df.format(fechaPublicacion));

        parseUser = ParseServerHelper.getCurrentUser();
        helper = new ParseServerHelper(this);
        contenidoPublicacion = (EditText) findViewById(R.id.publicacion_edit);
        ((TextView) findViewById(R.id.nickname_txt_view)).setText(parseUser.getUsername());



        Button boton = (Button) findViewById(R.id.fab_publicar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parseUser != null) {
                    helper.registrarPublicacion(contenidoPublicacion.getText().toString(), parseUser, "publica", fechaPublicacion);
                    finish();
                } else {
                    helper.lanzarAlert("Error pub", "Usuario nulo");
                }
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}
