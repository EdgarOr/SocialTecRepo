package moviles.com.socialtec.vista;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.AdaptadorInicio;
import moviles.com.socialtec.controlador.ParseServerHelper;
import moviles.com.socialtec.controlador.RecyclerItemClickListener;
import moviles.com.socialtec.modelo.Comentario;
import moviles.com.socialtec.modelo.Publicacion;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoInicio extends Fragment {

    private RecyclerView recycler;
    private LinearLayoutManager manager;
    private AdaptadorInicio adaptador;

    public FragmentoInicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragmento_inicio, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recycler_inicio);
        manager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(manager);

        ParseUser user = ParseServerHelper.getCurrentUser();
        if (user!= null) {
            ((TextView)v.findViewById(R.id.nickname_txt_view)).setText(user.getUsername());
        }

        LinearLayout linear = (LinearLayout) v.findViewById(R.id.linear_publicacion);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PublicacionActivity.class);
                startActivity(i);
            }
        });

        adaptador = new AdaptadorInicio();
        recycler.setAdapter(adaptador);
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {}

            @Override
            public void onItemLongClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), CommentActivity.class);
                startActivity(intent);

            }
        }));



        return v;
    }

}
