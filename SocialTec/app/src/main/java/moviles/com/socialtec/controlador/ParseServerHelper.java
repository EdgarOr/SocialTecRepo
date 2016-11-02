package moviles.com.socialtec.controlador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import moviles.com.socialtec.vista.MainActivity;

/**
 * Created by Edgar on 01/11/2016.
 */

public class ParseServerHelper {

    private Context context;
    private final AppCompatActivity activity;

    public ParseServerHelper(AppCompatActivity activity) {
        this.activity = activity;

    }

    public void inicializarParse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Parse.initialize(new Parse.Configuration.Builder(activity.getApplicationContext())
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

    /**
     *
     * @param nickname
     * @param contraseña
     */
    public void loginUsuario(final String nickname, final String contraseña) {
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


    public AppCompatActivity getActivity() {
        return activity;
    }


}
