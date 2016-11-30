package moviles.com.socialtec.controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.modelo.Comentario;

/**
 * Created by Edgar on 09/10/2016.
 */

public class ComentariosAdapter extends ArrayAdapter<Comentario> {
    private Context contexto;
    private ArrayList<Comentario> comentarios;
    private ParseServerHelper helper;
    private String idPublicacion;
    private final ComentariosAdapter adapter;


    public ComentariosAdapter(Context context, ArrayList<Comentario> comentarios, String idPublicacion) {
        super(context, R.layout.item_comentario, comentarios);
        this.contexto = context;
        this.comentarios = comentarios;
        this.idPublicacion = idPublicacion;
        adapter = this;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(contexto);
        View item = inflater.inflate(R.layout.item_comentario, null);

        if (comentarios.isEmpty()) {
            Toast.makeText(item.getContext(), "No hay comentarios para esta publicación", Toast.LENGTH_SHORT).show();
        } else {
            Comentario comentario = comentarios.get(position);
            ((TextView) item.findViewById(R.id.nickname_txt_view)).setText(comentario.getUsuario().getUsername());
            ((TextView) item.findViewById(R.id.comentario_txt_view)).setText(comentario.getContenidoComentario());
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            ((TextView) item.findViewById(R.id.tiempo_txt_view)).setText(df.format(comentario.getFechaComentario()));
            (item.findViewById(R.id.delete_comment_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper.eliminarComentario(comentarios.get(position).getIdComentario(), adapter, idPublicacion);

                    //Toast.makeText(v.getContext(), "ID Comentario"+position+": " + comentarios.get(position).getIdComentario(), Toast.LENGTH_SHORT).show();
                }
            });

              /*helper.eliminarComentario(posicionComentario); // debe de ser id del comentario
        helper.getComentarios(this, idPublicacion);*/


        }

        return item;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setHelper(ParseServerHelper helper) {
        this.helper = helper;
    }

}
