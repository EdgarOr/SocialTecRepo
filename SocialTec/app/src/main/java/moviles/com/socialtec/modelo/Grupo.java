package moviles.com.socialtec.modelo;

import java.util.ArrayList;

/**
 * Created by Edgar on 10/10/2016.
 */

public class Grupo {
    public static final ArrayList<Grupo>  GRUPOS = new ArrayList<>();

    private String nombre;
    private Usuario administrador;
    private ArrayList<Usuario> usuarios;

    static {
        GRUPOS.add(new Grupo("Grupo 1", Usuario.USUARIOS.get(0)));
        GRUPOS.add(new Grupo("Grupo 2", Usuario.USUARIOS.get(1)));
        GRUPOS.add(new Grupo("Grupo 3", Usuario.USUARIOS.get(1)));
    }

    public Grupo(String nombre, Usuario administrador) {
        this.nombre = nombre;
        this.administrador = administrador;
        this.usuarios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
