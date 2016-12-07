package moviles.com.socialtec.controlador;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
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

import moviles.com.socialtec.R;
import moviles.com.socialtec.modelo.Comentario;
import moviles.com.socialtec.modelo.Grupo;
import moviles.com.socialtec.modelo.Usuario;
import moviles.com.socialtec.vista.FragmentoInicio;
import moviles.com.socialtec.vista.MainActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Edgar on 01/11/2016.
 */

public class ParseServerHelper {


    private final AppCompatActivity activity;
    private Fragment fragmento;
    private final ProgressDialog progressDialog;

    public ParseServerHelper(AppCompatActivity activity) {
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);

    }

    public ParseServerHelper(AppCompatActivity activity, Fragment fragmento) {
        this.activity = activity;
        this.fragmento = fragmento;
        progressDialog = new ProgressDialog(activity);

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
        lanzarProgressDialog("Iniciando sesión...");
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
                                progressDialog.dismiss();
                                lanzarAlert("Login Error", e.getMessage());
                            }
                    }
                });
            }
        }).start();

    }

    public void changePassword(final String nickname, final String contraseñaActual, final String contraseñaNueva) {
        lanzarProgressDialog("Cambiando contraseña");
        ParseUser.logInInBackground(nickname, contraseñaActual, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                progressDialog.dismiss();
                if (e == null) {
                    user.setPassword(contraseñaNueva);
                    user.saveInBackground();
                    lanzarAlert("Cambiar contraseña","Su contraseña fue actualizada con éxito.");
                }   else {
                    lanzarAlert("Cambiar contraseña", "Error: " + e.getMessage());
                }
            }
        });
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

    public void lanzarProgressDialog(String mensaje) {
        progressDialog.setMessage(mensaje);
        progressDialog.show();

    }

    public  void registrarPublicacion(final String contenidoPublicacion, final ParseUser usuario,
                                      final String tipo, final Date fecha) {
        lanzarProgressDialog("Publicando...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseObject publicacion = new ParseObject("Publicacion");
                publicacion.put("contenido", contenidoPublicacion);
                publicacion.put("usuario", usuario);
                publicacion.put("tipoPublicacion", tipo);
                publicacion.put("createdAt", fecha);

                publicacion.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            progressDialog.dismiss();
                            activity.finish();
                        } else {
                            progressDialog.dismiss();
                            lanzarAlert("ERROR PUBLICACION", e.getMessage());
                        }
                    }
                });
            }
        }).start();
    }


    public void editarPublicacion(final String idPublicacion, final String contenido) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Publicacion");
        query.getInBackground(idPublicacion, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject publicacion, ParseException e) {
                if (e == null ) {
                    if  (!publicacion.get("contenido").equals(contenido)) {

                        publicacion.put("contenido", contenido);
                        try {
                            publicacion.save();
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        activity.finish();
                    }

                    Toast.makeText(activity.getApplicationContext(), "Publicacion editada", Toast.LENGTH_SHORT).show();
                } else {
                    lanzarAlert("ERROR EDITAR",e.getMessage());

                }
            }
        });
    }

    public void getPublicaciones(final AdaptadorNoticia adaptadorNoticia, final String tipoPublicacion) {
        lanzarProgressDialog("Cargando publicaciones...");
        final ArrayList<ParseObject> lista = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Publicacion");
        query.whereEqualTo("tipoPublicacion",tipoPublicacion);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null ) {
                    lista.addAll(objects);
                    adaptadorNoticia.setPublicaciones(lista);
                    adaptadorNoticia.notifyDataSetChanged();
                    progressDialog.dismiss();

                } else {
                    String listaVacia = objects.isEmpty()? "Lista vacía. " : "";
                    lanzarAlert("Error load posts", listaVacia + "Error quiensabe");
                }
            }
        });



    }


    public void crearGrupo (final String nombreGrupo, final Date date) {
        lanzarProgressDialog("Creando grupo...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseObject publicacion = new ParseObject("Grupo");
                publicacion.put("nombre", nombreGrupo);
                publicacion.put("usuario", getCurrentUser());
                publicacion.put("createdAt", date);

                publicacion.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            progressDialog.dismiss();

                            Bundle conData = new Bundle();
                            conData.putString("param_result", "Thanks Thanks");
                            Intent intent = new Intent();
                            intent.putExtras(conData);
                            activity.setResult(RESULT_OK, intent);
                            activity.finish();

                        } else {
                            progressDialog.dismiss();
                            lanzarAlert("ERROR PUBLICACION", e.getMessage());
                        }
                    }
                });
            }
        }).start();
    }

    public ArrayList<Grupo> getGrupos(final GrupoAdapter grupoAdapter) {
        lanzarProgressDialog("Cargando grupos...");
        final ArrayList<Grupo> lista = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Grupo");
        query.orderByAscending("nombre");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null ) {
                    for (int i = 0; i < objects.size(); i++) {
                        ParseObject o = objects.get(i);
                        lista.add(new Grupo((String) o.get("nombre"), new Usuario()));
                    }
                    grupoAdapter.setGrupos(lista);
                    grupoAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    //lista.addAll(objects);
                    //grupoAdapter.setGrupos(lista);
                    //grupoAdapter.notifyDataSetChanged();
                } else {
                    String listaVacia = objects.isEmpty()? "Lista vacía. " : "";
                    lanzarAlert("Error load posts", listaVacia + "Error quiensabe");
                }
            }
        });
        return lista;
    }


    public void insertarComentario(final String contenidoComentario, final String idPublicacion, final Date fecha) {
        lanzarProgressDialog("Insertando comentario");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseObject comentario = new ParseObject("Comentario");
                comentario.put("contenido_comentario", contenidoComentario);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Publicacion");
                try {
                    comentario.put("publicacion", query.get(idPublicacion));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                comentario.put("usuario", ParseServerHelper.getCurrentUser());
                comentario.put("createdAt", fecha);
                comentario.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        progressDialog.dismiss();
                        if (e == null) {
                            //activity.finish();
                        } else {
                            lanzarAlert("Error comentario", e.getMessage());
                        }
                    }
                });
            }
        }).start();


    }

    public void getComentarios(final ComentariosAdapter adapter, final String idPublicacion) {
        lanzarProgressDialog("Cargando comentarios....");
        adapter.getComentarios().clear();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Publicacion");
        ParseQuery<ParseObject> queryComentarios = ParseQuery.getQuery("Comentario");
        try {
            queryComentarios.whereEqualTo("publicacion", query.get(idPublicacion));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        queryComentarios.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Comentario comentario;
                    for (ParseObject objetoComentario : objects) {
                        try {
                            comentario = new Comentario(objetoComentario.getParseUser("usuario").fetchIfNeeded(),
                                                        objetoComentario.getParseObject("publicacion"),
                                                        objetoComentario.getString("contenido_comentario"),
                                                        objetoComentario.getCreatedAt(),
                                                        objetoComentario.getObjectId());
                            if (!adapter.getComentarios().contains(comentario)) {
                                adapter.getComentarios().add(comentario);
                            }
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                } else {
                    lanzarAlert("Error get comentarios", e.getMessage());
                }
            }
        });

    }

    public void eliminarComentario(final String idComentario, final ComentariosAdapter adapter, final String idPublicacion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle("Eliminar comentario")
                .setMessage("¿Desea eliminar este comentario?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        Toast.makeText(activity, "ID comentario: " + idComentario, Toast.LENGTH_SHORT).show();
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Comentario");
                        try {
                            ParseObject comentario = query.get(idComentario);
                            comentario.delete();
                            getComentarios(adapter, idPublicacion);
                        } catch (ParseException e) {
                            lanzarAlert("Error eliminar comentario",  e.getMessage());
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
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
