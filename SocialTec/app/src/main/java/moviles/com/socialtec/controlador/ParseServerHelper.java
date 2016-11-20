package moviles.com.socialtec.controlador;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import moviles.com.socialtec.vista.MainActivity;

/**
 * Created by Edgar on 01/11/2016.
 */

public class ParseServerHelper {


    private final AppCompatActivity activity;

    public ParseServerHelper(AppCompatActivity activity) {
        this.activity = activity;

    }

    public static void inicializarParse(final Application application) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Parse.initialize(new Parse.Configuration.Builder(application.getApplicationContext())
                        .applicationId("4mzxRwdDo3ww4z8SXqHsadNK6ctQAM1qWTHT4i8y")
                        .clientKey("elKm9p1B3XFswo7GgwDJ6OHCzzyD3njvxZLlKj0s")
                        .server("https://parseapi.back4app.com/").build()
                );

            }
        }).start();
    }

    public void registrarUsuario(final String numControl, final String nickname, final String nombre, final String apellidos,
                                 final String carrera, final int semestre,final  String correo, final String contraseña) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseUser user = new ParseUser();

                user.put("numControl", numControl);
                user.setUsername(nickname);
                user.put("nombre" , nombre);
                user.put("apellidos", apellidos);
                user.put("carrera", carrera);
                user.put("semestre", semestre);
                user.setEmail(correo);
                user.setPassword(contraseña);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {
                            loginUsuario(nickname, contraseña);

                        } else {
                            lanzarAlert("Signup Error", e.getMessage());
                        }
                    }
                });

            }
        }).start();


    }

    public void loginUsuario(final String nickname, final String contraseña) {
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Iniciando sesión...");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseUser.logInInBackground(nickname, contraseña, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Intent i = new Intent(activity.getApplicationContext(), MainActivity.class);
                                activity.finish();
                                activity.startActivity(i);
                                progressDialog.dismiss();
                                Toast.makeText(activity.getApplicationContext(),"Bienvenido " + nickname, Toast.LENGTH_SHORT).show();
                            } else {
                                lanzarAlert("Login Error", e.getMessage());
                            }
                    }
                });
            }
        }).start();

    }

    public static ParseUser getCurrentUser() {
        return ParseUser.getCurrentUser();
    }

    public void lanzarAlert(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public  void registrarPublicacion(final String contenidoPublicacion, final ParseUser usuario,
                                      final String tipo, final Date fecha/*, final ParseObject grupo*/) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseObject publicacion = new ParseObject("Publicacion");
                publicacion.put("contenido", contenidoPublicacion);
                publicacion.put("usuario", usuario);
                publicacion.put("tipoPublicacion", tipo);
                publicacion.put("createdAt", fecha);
                //publicacion.put("grupo", grupo);

                publicacion.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(activity.getApplicationContext(), "Publicación compartida", Toast.LENGTH_SHORT).show();
                        } else {
                            lanzarAlert("ERROR PUBLICACION", e.getMessage());
                        }
                    }
                });

            }
        }).start();
    }


    public void getPublicaciones(final AdaptadorInicio adaptadorInicio ,final String tipoPublicacion) {
        final ArrayList<ParseObject> lista = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Publicacion");
        query.whereEqualTo("tipoPublicacion",tipoPublicacion);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null ) {
                    lista.addAll(objects);
                    adaptadorInicio.setPublicaciones(lista);
                    adaptadorInicio.notifyDataSetChanged();
                    Log.e("OBJETO", objects.size()+"");
                } else {
                    String listaVacia = objects.isEmpty()? "Lista vacía. " : "";
                    lanzarAlert("Error load posts", listaVacia + "Error quiensabe");
                }
            }
        });



    }


    public AppCompatActivity getActivity() {
        return activity;
    }


}
