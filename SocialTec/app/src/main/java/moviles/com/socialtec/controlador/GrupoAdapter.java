package moviles.com.socialtec.controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.modelo.Grupo;

/**
 * Created by Edgar on 10/10/2016.
 */

public class GrupoAdapter extends ArrayAdapter {

    private Context contexto;
    private ArrayList<Grupo> grupos;


    public GrupoAdapter(Context contexto, ArrayList<Grupo> grupos) {
        super(contexto, R.layout.item_grupo, grupos);
        this.contexto =  contexto;
        this.setGrupos(grupos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View item = inflater.inflate(R.layout.item_grupo, null);

        Grupo grupo = (Grupo) grupos.get(position);

        TextView textView = (TextView) item.findViewById(R.id.textViewItemGrupo);
        String texto = grupo.getNombre().toString();

        textView.setText(texto);
        textView.setEnabled(false);
        notifyDataSetChanged();
        return item;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
    }
}
