package moviles.com.socialtec.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Edgar on 09/10/2016.
 */

public class Publicacion {

    public static  final ArrayList<Publicacion> PUBLICACIONES = new ArrayList<>();

    private String fechaPublicacion;
    private String contenido;
    private Usuario usuario;


    public Publicacion(String contenido, Usuario usuario) {
        //"EEE, d MMM yyyy HH:mm"------- Wed, 4 Jul 2001 12:08
        //Se puede parsear con el metodo parse(String) de la case Date para manipularlo posteriormente
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        this.fechaPublicacion = df.format(Calendar.getInstance().getTime());
        this.contenido = contenido;
        this.usuario = usuario;
    }


    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getContenido() {
        return contenido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
