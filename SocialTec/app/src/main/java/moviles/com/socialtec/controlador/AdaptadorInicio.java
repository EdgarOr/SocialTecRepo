package moviles.com.socialtec.controlador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import moviles.com.socialtec.R;
import moviles.com.socialtec.modelo.Publicacion;

/**
 * Created by Edgar on 09/10/2016.
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {

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

    public AdaptadorInicio() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inicio, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Publicacion item = Publicacion.PUBLICACIONES.get(position);

        if (position%2 == 0)
            item.getUsuario().setImagenUsuario(R.drawable.perfil1);
        else
            item.getUsuario().setImagenUsuario(R.drawable.perfil2);

        Glide.with(holder.itemView.getContext())
                .load(item.getUsuario().getImagenUsuario())
                .centerCrop()
                .into(holder.imagenUsuario);
        holder.nickname.setText(item.getUsuario().getNickname());
        holder.contenido.setText(item.getContenido());
        holder.tiempo.setText(item.getFechaPublicacion());


    }

    @Override
    public int getItemCount() {

        return Publicacion.PUBLICACIONES.size();
    }


}
