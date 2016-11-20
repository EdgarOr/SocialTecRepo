package moviles.com.socialtec.controlador;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.modelo.Publicacion;

/**
 * Created by Edgar on 09/10/2016.
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {

    private ArrayList<ParseObject> publicaciones;
    private boolean cambioEstado;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nickname;
        public TextView contenido;
        public TextView tiempo;

        public ImageView imagenUsuario;

        public ViewHolder(View v) {
            super(v);
            nickname = (TextView) v.findViewById(R.id.nickname_txt_view);
            contenido = (TextView) v.findViewById(R.id.publicacion_txt_view);
            tiempo = (TextView) v.findViewById(R.id.tiempo_txt_view);
            imagenUsuario = (ImageView) v.findViewById(R.id.image_usuario);
        }
    }

    public AdaptadorInicio(ArrayList<ParseObject> publicaciones) {
        this.publicaciones = publicaciones;
        this.cambioEstado = false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inicio, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        if (publicaciones.size() > 0) {
            ParseObject item = publicaciones.get(position);

            try {
                holder.nickname.setText(item.getParseUser("usuario").fetchIfNeeded().getUsername());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.contenido.setText(item.getString("contenido"));
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            //df.setTimeZone();
            holder.tiempo.setText( df.format(item.getCreatedAt()));


        } else {

        }
    }

    @Override
    public int getItemCount() {
        return this.publicaciones.size();
    }

    public ArrayList<ParseObject> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<ParseObject> publicaciones) {
        this.publicaciones = publicaciones;
    }


}
