package moviles.com.socialtec.vista;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.ParseObject;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.GrupoAdapter;
import moviles.com.socialtec.controlador.ParseServerHelper;
import moviles.com.socialtec.modelo.Grupo;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoGrupo extends Fragment {

    private ListView lista;
    private Button boton;
    private ParseServerHelper helper;
    private ArrayList<Grupo> grupos;
    private GrupoAdapter grupoAdapter;

    public FragmentoGrupo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_grupo, container, false);
        helper = new ParseServerHelper(new MainActivity());
        lista = (ListView) v.findViewById(R.id.lista);

        grupos = new ArrayList<>();

        grupoAdapter = new GrupoAdapter(v.getContext(), grupos);

        grupos = helper.getGrupos(grupoAdapter);
        //grupoAdapter.setGrupos(grupos);

        lista.setAdapter(new GrupoAdapter(v.getContext(), grupos));
        //lista.setAdapter(new GrupoAdapter(v.getContext(), Grupo.GRUPOS);
        boton = (Button) v.findViewById(R.id.llamar_crear_grupo);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GrupoAltaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            actualizarLista();
            Log.d("Error", "Actualizar lista");
        } else {
            Log.d("Error", "No finaliza bien lista");
        }
    }

    private void actualizarLista () {
        grupos = helper.getGrupos(grupoAdapter);
        lista.setAdapter(new GrupoAdapter(getView().getContext(), grupos));
    }
}
