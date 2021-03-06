package moviles.com.socialtec.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ParseServerHelper;

public class PublicacionActivity extends AppCompatActivity {

    private String idPublicacion;
    private boolean indice;
    private ParseUser parseUser;
    private ParseServerHelper helper;

    private TextView tiempo_txt;
    private TextView nickname_txt;
    private EditText contenidoPublicacion;
    private Date fechaPublicacion;
    private String nombreGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent info = getIntent();
        tiempo_txt = (TextView) findViewById(R.id.tiempo_txt_view);
        contenidoPublicacion = (EditText) findViewById(R.id.publicacion_edit);
        nickname_txt  = (TextView) findViewById(R.id.nickname_txt_view);
        helper = new ParseServerHelper(this);

        nombreGrupo = getIntent().getStringExtra("nombre");

        indice = info.getBooleanExtra("indice", false);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (indice ) {
            idPublicacion = info.getStringExtra("idPublicacion");
            nickname_txt.setText(info.getStringExtra("nickname"));
            contenidoPublicacion.setText(info.getStringExtra("contenido"));
            tiempo_txt.setText(info.getStringExtra("tiempo"));


        } else {
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            fechaPublicacion = Calendar.getInstance().getTime();
            tiempo_txt.setText(df.format(fechaPublicacion));

            parseUser = ParseUser.getCurrentUser();
            nickname_txt.setText(parseUser.getUsername());
        }

        Button boton = (Button) findViewById(R.id.fab_publicar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (indice)
                        if (nombreGrupo == null) {
                            helper.registrarPublicacion(contenidoPublicacion.getText().toString(), parseUser, "publica", fechaPublicacion);
                        } else {
                            helper.registrarPublicacionGrupo(contenidoPublicacion.getText().toString(), parseUser, "grupo", fechaPublicacion, nombreGrupo);
                        }
                    else {
                        helper.registrarPublicacion(contenidoPublicacion.getText().toString(), parseUser, "publica", fechaPublicacion);
                    }
                } catch(Exception e) {
                    Log.e("ERRORRR", e.getMessage());
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
