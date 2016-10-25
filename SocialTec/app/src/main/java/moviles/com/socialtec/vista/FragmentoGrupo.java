package moviles.com.socialtec.vista;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.GrupoAdapter;
import moviles.com.socialtec.modelo.Grupo;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoGrupo extends Fragment {

    private ListView lista;

    public FragmentoGrupo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_grupo, container, false);
        lista = (ListView) v.findViewById(R.id.lista);
        lista.setAdapter(new GrupoAdapter(v.getContext(), Grupo.GRUPOS));

        return v;
    }

}
