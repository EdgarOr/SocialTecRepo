package moviles.com.socialtec.vista;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.AdaptadorInicio;
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

        adaptador = new AdaptadorInicio();
        recycler.setAdapter(adaptador);
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {}

            @Override
            public void onItemLongClick(View view, int position) {
                //String nickname = Publicacion.PUBLICACIONES.get(position).getUsuario().getNickname();
                //Toast.makeText(view.getContext(), "Comentar " + nickname, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), CommentActivity.class);
                //TODO: hacer cambios sobre este asunto de los intends
                /*ArrayList<Comentario> comentarios = new ArrayList<>();
                for (Comentario comentario: Comentario.COMENTARIOS) {
                    if (comentario.getPublicacion() == Publicacion.PUBLICACIONES.get(position)) {
                        comentarios.add(comentario);
                    }
                }*/

                //intent.putExtra("nickname", nickname);
                //intent.putExtra("nickname", nickname);
                startActivity(intent);


            }
        }));

            return v;
    }

}
