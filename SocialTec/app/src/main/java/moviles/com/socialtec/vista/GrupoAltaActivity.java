package moviles.com.socialtec.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ParseServerHelper;

public class GrupoAltaActivity extends AppCompatActivity implements View.OnClickListener {

    private ParseServerHelper helper;
    private Button boton;
    private EditText nombreGrupo;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_grupo);
        helper = new ParseServerHelper(this);
        boton = (Button) findViewById(R.id.crear_grupo);
        nombreGrupo = (EditText) findViewById(R.id.edit_text_grupo_nombre);
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        date = Calendar.getInstance().getTime();
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            helper.crearGrupo(nombreGrupo.getText().toString(), date);
        } catch(Exception e) {
            Log.e("ERRORRR", e.getMessage());
        }
    }
}
