package moviles.com.socialtec.controlador;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import moviles.com.socialtec.R;
import moviles.com.socialtec.vista.CommentActivity;
import moviles.com.socialtec.vista.PublicacionActivity;

/**
 * Created by Edgar on 09/10/2016.
 */
public class AdaptadorNoticia
        extends RecyclerView.Adapter<AdaptadorNoticia.ViewHolder> {

    private ArrayList<ParseObject> publicaciones;
    private String usuario;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public String idPublicacion;

        public TextView nickname;
        public TextView contenido;
        public TextView tiempo;

        public ImageView imagenUsuario;

        public ImageView comentar_imgB;
        public ImageButton editar_imgB;


        public ViewHolder(View v) {
            super(v);

            nickname = (TextView) v.findViewById(R.id.nickname_txt_view);
            contenido = (TextView) v.findViewById(R.id.publicacion_txt_view);
            tiempo = (TextView) v.findViewById(R.id.tiempo_txt_view);
            imagenUsuario = (ImageView) v.findViewById(R.id.image_usuario);
            comentar_imgB = (ImageView) v.findViewById(R.id.comentar_imageb);
            editar_imgB = (ImageButton) v.findViewById(R.id.editar_imageb);

            comentar_imgB.setOnClickListener(this);
            editar_imgB.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.editar_imageb:
                    if (ParseServerHelper.getCurrentUser().getUsername().equals(nickname.getText().toString())){
                        Intent i = new Intent(v.getContext(), PublicacionActivity.class);
                        i.putExtra("idPublicacion", idPublicacion);
                        i.putExtra("nickname", nickname.getText().toString());
                        i.putExtra("contenido", contenido.getText().toString());
                        i.putExtra("tiempo", tiempo.getText().toString());
                        v.getContext().startActivity(i);

                    } else {
                        Toast.makeText(v.getContext(), "No puede editar esta noticia", Toast.LENGTH_SHORT).show();
                    }

//                    Toast.makeText(v.getContext(), "ID PUB: " + idPublicacion, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.comentar_imageb:
                    Intent intent = new Intent(v.getContext(), CommentActivity.class);
                    v.getContext().startActivity(intent);
                    break;

            }
        }
    }

    public AdaptadorNoticia(ArrayList<ParseObject> publicaciones, String usuario) {
        this.publicaciones = publicaciones;
        this.usuario = usuario;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noticia, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (publicaciones.size() > 0) {
            ParseObject item = publicaciones.get(position);

            try {

                holder.idPublicacion = item.getObjectId();
                holder.nickname.setText(item.getParseUser("usuario").fetchIfNeeded().getUsername());
//                if (!usuario.equals(holder.nickname.getText().toString())) {
//                    holder.editar_imgB.setVisibility(View.GONE);
//                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.contenido.setText(item.getString("contenido"));
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
            df.setTimeZone(TimeZone.getDefault());
            holder.tiempo.setText( df.format(item.getCreatedAt()));


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
