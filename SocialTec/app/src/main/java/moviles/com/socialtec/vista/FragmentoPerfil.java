package moviles.com.socialtec.vista;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ParseServerHelper;

import static moviles.com.socialtec.modelo.Colecciones.carrerasArray;
import static moviles.com.socialtec.modelo.Colecciones.semestresArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoPerfil extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText numcontrol_edit;
    private EditText nickname_edit;
    private EditText nombres_edit_tex;
    private EditText apellidos_edit_tex;
    private EditText correo_edit;
    private EditText pw_actual_edit;
    private EditText pw_new_edit;
    private Spinner semester_spinner;
    private Spinner carrera_spinner;
    private Switch cambiar_pw_switch;
    private Toolbar toolbar;
    private ImageView imageParallax;
    private FloatingActionButton fab;
    private Button cambiar_pw_button;
    private TextView etiqueta_cambiar_pw;

    private boolean editar;
    private ParseUser usuario;
    private ParseServerHelper helper;
    private AppCompatActivity activity;


    public FragmentoPerfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        editar = false;
        helper = new ParseServerHelper(activity);
        usuario = ParseServerHelper.getCurrentUser();

        View view = inflater.inflate(R.layout.fragmento_perfil, container, false);
        // Inflate the layout for this fragment

        numcontrol_edit = (EditText) view.findViewById(R.id.numcontrol_edit);
        nickname_edit = (EditText) view.findViewById(R.id.nickname_edit);
        nombres_edit_tex = (EditText) view.findViewById(R.id.nombres_edit_text);
        apellidos_edit_tex = (EditText) view.findViewById(R.id.apellidos_edit_text);
        correo_edit = (EditText) view.findViewById(R.id.correo_edit);
        pw_actual_edit = (EditText) view.findViewById(R.id.pw_actual_edit);
        pw_new_edit = (EditText) view.findViewById(R.id.pw_new_edit);
        semester_spinner = (Spinner) view.findViewById(R.id.semestre_spinner);
        carrera_spinner = (Spinner) view.findViewById(R.id.carrera_spinner);
        cambiar_pw_switch = (Switch) view.findViewById(R.id.cambiar_pw_switch);

        etiqueta_cambiar_pw = (TextView) view.findViewById(R.id.etiqueta_cambiar_pw);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        imageParallax = (ImageView) view.findViewById(R.id.image_paralax);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        cambiar_pw_button = (Button) view.findViewById(R.id.cambiar_pw_button);
        
        semester_spinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, semestresArray));
        carrera_spinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, carrerasArray));

        semester_spinner.setSelection(0);
        semester_spinner.setEnabled(false);
        carrera_spinner.setSelection(0);
        carrera_spinner.setEnabled(false);

        mostrarInfo();


        return view;
    }

    private void mostrarInfo() {
        toolbar.setTitle(usuario.getUsername());
        imageParallax.setImageResource(R.drawable.perfil2);
        fab.setOnClickListener(this);
        cambiar_pw_button.setOnClickListener(this);
        cambiar_pw_switch.setOnCheckedChangeListener(this);
        
        numcontrol_edit.setText(usuario.getString("numControl"));
        nickname_edit.setText(usuario.getUsername());
        nombres_edit_tex.setText(usuario.getString("nombre"));
        apellidos_edit_tex.setText(usuario.getString("apellidos"));
        correo_edit.setText(usuario.getEmail());
        semester_spinner.setSelection(usuario.getInt("semestre")-1);
        carrera_spinner.setSelection(carrerasArray.indexOf(usuario.getString("carrera")));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cambiar_pw_button:
                helper.changePassword(usuario.getUsername(),
                                    pw_actual_edit.getText().toString().trim(),
                                    pw_new_edit.getText().toString().trim());
                cambiar_pw_switch.setChecked(false);
                break;

            case R.id.fab :
                if (!editar) { // Se desbloquean los campos y está listo para ser editado
                    editar = true;
                    fab.setImageResource(R.drawable.ic_guardar);
                } else { // Se bloquean los campos  se guardan los cambios
                    editar = false;
                    actualizarUsuario();
                    fab.setImageResource(R.drawable.ic_editar);
                }
                habilitarCampos();
                Toast.makeText(v.getContext(), "Puchado editar", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    private void actualizarUsuario() {
        usuario.put("nombre", nombres_edit_tex.getText().toString().trim());
        usuario.put("apellidos", apellidos_edit_tex.getText().toString().trim());
        usuario.put("numControl", numcontrol_edit.getText().toString().trim());
        usuario.put("carrera", carrera_spinner.getSelectedItem().toString());
        usuario.put("semestre", semester_spinner.getSelectedItemPosition()+1);
        usuario.setUsername(nickname_edit.getText().toString().trim());
        usuario.saveInBackground();


    }

    private void habilitarCampos() {
        numcontrol_edit.setEnabled(editar);
        nickname_edit.setEnabled(editar);
        nombres_edit_tex.setEnabled(editar);
        apellidos_edit_tex.setEnabled(editar);
        correo_edit.setEnabled(editar);
        semester_spinner.setEnabled(editar);
        carrera_spinner.setEnabled(editar);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        pw_actual_edit.setEnabled(isChecked);
        pw_new_edit.setEnabled(isChecked);
        if (isChecked){
            etiqueta_cambiar_pw.setText(R.string.cancelar_contrase_a);
            cambiar_pw_button.setVisibility(View.VISIBLE);
            Toast.makeText(buttonView.getContext(), "Activado", Toast.LENGTH_SHORT).show();
        } else {
            pw_actual_edit.setText("");
            pw_new_edit.setText("");
            cambiar_pw_button.setVisibility(View.GONE);
            etiqueta_cambiar_pw.setText(R.string.cambiar_contrase_a);
            Toast.makeText(buttonView.getContext(), "Desactivado", Toast.LENGTH_SHORT).show();
        }

    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }
}
