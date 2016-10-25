package moviles.com.socialtec.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Edgar on 09/10/2016.
 */

public class Comentario implements Parcelable{

    public static final ArrayList<Comentario> COMENTARIOS = new ArrayList<>();

    private Usuario usuario;
    private Publicacion publicacion;
    private String contenidoComentario;
    private String fechaComentario;

    static {
        COMENTARIOS.add(new Comentario(Usuario.USUARIOS.get(3), Publicacion.PUBLICACIONES.get(0),"Que cools"));
        COMENTARIOS.add(new Comentario(Usuario.USUARIOS.get(0), Publicacion.PUBLICACIONES.get(0),"Que chido"));
        COMENTARIOS.add(new Comentario(Usuario.USUARIOS.get(2), Publicacion.PUBLICACIONES.get(2),"Oye pero que chido"));
        COMENTARIOS.add(new Comentario(Usuario.USUARIOS.get(1), Publicacion.PUBLICACIONES.get(1),"Wow!"));
        COMENTARIOS.add(new Comentario(Usuario.USUARIOS.get(1), Publicacion.PUBLICACIONES.get(1),"Wow!"));
    }

    public Comentario(Usuario usuario, Publicacion publicacion, String contenidoComentario) {
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        this.fechaComentario = df.format(Calendar.getInstance().getTime());
        this.usuario = usuario;
        this.publicacion = publicacion;
        this.contenidoComentario = contenidoComentario;
    }


    public String getFechaComentario() {
        return fechaComentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }


    public Comentario(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Comentario> CREATOR = new Parcelable.Creator<Comentario>() {
        public Comentario createFromParcel(Parcel in) {
            return new Comentario(in);
        }

        public Comentario[] newArray(int size) {

            return new Comentario[size];
        }

    };

    public void readFromParcel(Parcel in) {

        usuario.setNickname(in.readString());
        usuario.setNombre(in.readString());
        usuario.setContraseña(in.readString());
        usuario.setImagenUsuario(in.readInt());

        Usuario user = new Usuario();
        publicacion.setContenido(in.readString());
        publicacion.setFechaPublicacion(in.readString());
        user.setNickname(in.readString());
        user.setNombre(in.readString());
        user.setContraseña(in.readString());
        user.setImagenUsuario(in.readInt());
        publicacion.setUsuario(user);

        contenidoComentario = in.readString();
        fechaComentario = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(usuario.getNickname());
        dest.writeString(usuario.getNombre());
        dest.writeString(usuario.getContraseña());
        dest.writeInt(usuario.getImagenUsuario());

        Usuario user = publicacion.getUsuario();
        dest.writeString(publicacion.getContenido());
        dest.writeString(publicacion.getFechaPublicacion());
        dest.writeString(user.getNickname());
        dest.writeString(user.getNombre());
        dest.writeString(user.getContraseña());
        dest.writeInt(user.getImagenUsuario());

        dest.writeString(contenidoComentario);
        dest.writeString(fechaComentario);

    }
}
