package moviles.com.socialtec.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ParseServerHelper;


public class ActivityRegistro extends AppCompatActivity implements View.OnClickListener {

    private Spinner carrera;
    private Spinner semestre;
    private String numControl;
    private String nickname;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contraseña;
    private String repetirContraseña;
    private ParseServerHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        helper = new ParseServerHelper(this);
        inicializarComponentes();
        inicializarSpinners();

        Button botonRegistrar = (Button) findViewById(R.id.botonRegistrar);
        Button botonCancelar= (Button) findViewById(R.id.botonCancelar);
        botonCancelar.setOnClickListener(this);
        botonRegistrar.setOnClickListener(this);

    }

    public void inicializarComponentes() {
        numControl = ((EditText) findViewById(R.id.numcontrol_edit)).getText().toString();
        nickname = ((EditText) findViewById(R.id.nickname_edit)).getText().toString();
        nombre = ((EditText) findViewById(R.id.nombres_edit)).getText().toString();
        apellidos = ((EditText) findViewById(R.id.apellidos_edit)).getText().toString();
        carrera = (Spinner) findViewById(R.id.carrera_spinner);
        semestre = (Spinner) findViewById(R.id.semestre_spinner);
        correo = ((EditText) findViewById(R.id.correo_edit)).getText().toString();
        contraseña = ((EditText) findViewById(R.id.contrasena_edit)).getText().toString();
        repetirContraseña = ((EditText) findViewById(R.id.repetir_contrasena_edit)).getText().toString();

    }

    public void inicializarSpinners() {
        ArrayList<String> carrerasArray = new ArrayList<>();
        ArrayList<String> semestresArray = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            semestresArray.add("" + i);
        }

        carrerasArray.add("Ing. en Sistemas");
        carrerasArray.add("Ing. Industrial");
        carrerasArray.add("Ing. Química");
        carrerasArray.add("Ing. en Energías Renovables");

        carrera.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carrerasArray));
        semestre.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semestresArray));

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
                inicializarComponentes();
                if (!validarCampos()) {
                    if  (contraseña.equals(repetirContraseña)) {
                        helper.registrarUsuario(numControl, nickname, nombre, apellidos, carrera.getSelectedItem().toString(),
                                semestre.getSelectedItemPosition() + 1, correo, contraseña);
                    } else {
                        helper.lanzarAlert("Error Contraseña", "No concuerda la contraseña ");
                    }
                } else {
                    helper.lanzarAlert("Error Campos", "Algún campo está vacío");
                }
                break;
            default:
                Toast.makeText(this, "Default Registro Click Listener", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validarCampos() {
        return numControl.isEmpty() && nickname.isEmpty() && nombre.isEmpty() &&
                apellidos.isEmpty() && correo.isEmpty() && contraseña.isEmpty() && repetirContraseña.isEmpty();
    }

}
