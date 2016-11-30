package moviles.com.socialtec.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Edgar on 09/10/2016.
 */

public class Comentario {

    public static final ArrayList<Comentario> COMENTARIOS = new ArrayList<>();

    private ParseUser usuario;
    private ParseObject publicacion;
    private String contenidoComentario;
    private Date fechaComentario;
    private String idComentario;

    public Comentario(ParseUser usuario, ParseObject publicacion, String contenidoComentario, Date fechaComentario) {
        this.usuario = usuario;
        this.publicacion = publicacion;
        this.contenidoComentario = contenidoComentario;
        this.fechaComentario = fechaComentario;
    }

    public Comentario(ParseUser usuario, ParseObject publicacion, String contenidoComentario, Date fechaComentario, String idComentario) {
        this.usuario = usuario;
        this.publicacion = publicacion;
        this.contenidoComentario = contenidoComentario;
        this.fechaComentario = fechaComentario;
        this.idComentario = idComentario;
    }

    public Comentario() {
        this(ParseUser.getCurrentUser(), null, "contenido nulo", Calendar.getInstance().getTime());
    }

    public ParseUser getUsuario() {
        return usuario;
    }

    public void setUsuario(ParseUser usuario) {
        this.usuario = usuario;
    }

    public ParseObject getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(ParseObject publicacion) {
        this.publicacion = publicacion;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }
}
