package moviles.com.socialtec.modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Edgar on 23/11/2016.
 */

public class Colecciones {
    public static  final HashMap<String, Publicacion> PUBLICACIONES = new HashMap<>();
    public static  final HashMap<String, Grupo> GRUPOS= new HashMap<>();
    public static  final HashMap<String, Usuario> USUARIOS= new HashMap<>();

    public static final ArrayList<String> carrerasArray = new ArrayList<>();
    public static final ArrayList<String> semestresArray = new ArrayList<>();

    static {
        for (int i = 1; i <= 12; i++) {
            semestresArray.add("" + i);
        }

        carrerasArray.add("Ing. en Sistemas");
        carrerasArray.add("Ing. Industrial");
        carrerasArray.add("Ing. Química");
        carrerasArray.add("Ing. en Energías Renovables");
    }
}
