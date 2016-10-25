package moviles.com.socialtec.controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.modelo.Grupo;

import static android.R.attr.resource;

/**
 * Created by Edgar on 10/10/2016.
 */

public class GrupoAdapter extends ArrayAdapter {

    private Context contexto;
    private ArrayList<Grupo> grupos;


    public GrupoAdapter(Context contexto, ArrayList<Grupo> grupos) {
        super(contexto, R.layout.item_grupo, grupos);
        this.contexto =  contexto;
        this.grupos = grupos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(contexto);
        View item = inflater.inflate(R.layout.item_grupo, null);

        return item;
    }
}
