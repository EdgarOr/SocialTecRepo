package moviles.com.socialtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import moviles.com.socialtec.vista.ActivityLogin;
import moviles.com.socialtec.vista.MainActivity;

public class ActivityRegistro extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button botonRegistrar = (Button) findViewById(R.id.botonRegistrar);
        Button botonCancelar= (Button) findViewById(R.id.botonCancelar);

        Spinner carreras = (Spinner) findViewById(R.id.carrera_spinner);
        ArrayList<String> carrerasArray = new ArrayList<>();

        Spinner semestres = (Spinner) findViewById(R.id.semestre_spinner);
        ArrayList<String> semestresArray = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            semestresArray.add("" + i);

        }

        carrerasArray.add("Ing. en Sistemas");
        carrerasArray.add("Ing. Industrial");
        carrerasArray.add("Ing. Química");
        carrerasArray.add("Ing. en Energías Renovables");

        carreras.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carrerasArray));
        semestres.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semestresArray));

        botonCancelar.setOnClickListener(this);
        botonRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        switch (v.getId()) {
            case R.id.botonCancelar:
                i = new Intent(this, ActivityLogin.class);
                finish();
                startActivity(i);
                break;
            case R.id.botonRegistrar:
                i = new Intent(this, MainActivity.class);
                finish();
                startActivity(i);
                break;
            default:
                Toast.makeText(this, "Default Registro Click Listener", Toast.LENGTH_SHORT).show();
        }

    }
}
