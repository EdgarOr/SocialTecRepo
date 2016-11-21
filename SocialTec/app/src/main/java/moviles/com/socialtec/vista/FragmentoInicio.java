package moviles.com.socialtec.vista;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.AdaptadorNoticia;
import moviles.com.socialtec.controlador.ParseServerHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoInicio extends Fragment {

    private RecyclerView recycler;
    private static  AppCompatActivity activity;
    private LinearLayoutManager manager;
    private AdaptadorNoticia adaptadorNoticia;
    private ParseServerHelper helper;

    public FragmentoInicio() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        helper.getPublicaciones(adaptadorNoticia,"publica");
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

        helper = new ParseServerHelper(activity, this);

        adaptadorNoticia = new AdaptadorNoticia(new ArrayList<ParseObject>(), ParseServerHelper.getCurrentUser().getUsername());
        helper.getPublicaciones(adaptadorNoticia,"publica");
        recycler.setAdapter(adaptadorNoticia);
        /*
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {}

            @Override
            public void onItemLongClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), CommentActivity.class);
                startActivity(intent);

            }
        }));
        * */



        return v;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public void setRecycler(RecyclerView recycler) {
        this.recycler = recycler;
    }
}
