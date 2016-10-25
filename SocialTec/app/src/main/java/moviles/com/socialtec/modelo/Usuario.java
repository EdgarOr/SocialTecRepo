package moviles.com.socialtec.modelo;

import java.util.ArrayList;

/**
 * Created by Edgar on 09/10/2016.
 */

public class Usuario {

    public static final ArrayList<Usuario> USUARIOS = new ArrayList<>();

    private int imagenUsuario;
    private String nombre;
    private String nickname;
    private String contraseña;

    static {
        USUARIOS.add(new Usuario("Edgar Ordoñez", "eledgarts", "eyla"));
        USUARIOS.add(new Usuario("Adrian Ochoa", "ochoa", "123"));
        USUARIOS.add(new Usuario("Jesus Perez", "pichi", "123"));
        USUARIOS.add(new Usuario("Ivan Romero", "guajardo", "123"));
    }

    public Usuario() {
    }

    public Usuario(String nombre, String nickname, String contraseña) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.contraseña = contraseña;

    }


    public int getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(int imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
