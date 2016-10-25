package moviles.com.socialtec.controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.modelo.Comentario;

/**
 * Created by Edgar on 09/10/2016.
 */

public class ComentariosAdapter extends ArrayAdapter<Comentario> {
    private Context contexto;
    private ArrayList<Comentario> comentarios;

    public ComentariosAdapter(Context context, ArrayList<Comentario> comentarios) {
        super(context, R.layout.item_comentario, comentarios);
        this.contexto = context;
        this.comentarios = comentarios;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(contexto);
        View item = inflater.inflate(R.layout.item_comentario, null);
/*
        TextView nickname = (TextView) item.findViewById(R.id.nickname_txt_view);
        TextView comentarioTV = (TextView) item.findViewById(R.id.comentario_txt_view);
        TextView tiempo = (TextView) item.findViewById(R.id.tiempo_txt_view);
        ImageView imagen = (ImageView) item.findViewById(R.id.image_usuario);

        Comentario comentario = comentarios.get(position);
        nickname.setText(comentario.getUsuario().getNickname());
        comentarioTV.setText(comentario.getContenidoComentario());
        tiempo.setText(comentario.getFechaComentario());
        imagen.setImageResource(comentario.getUsuario().getImagenUsuario());*/

        return item;
    }
}
