package moviles.com.socialtec.vista;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ComentariosAdapter;
import moviles.com.socialtec.controlador.ParseServerHelper;
import moviles.com.socialtec.modelo.Comentario;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private String idPublicacion;
    private ListView listView;
    private EditText comentario_edit_text;

    private ParseServerHelper helper;
    private ComentariosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Comentando");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){ // add back arrow to toolbar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        idPublicacion = intent.getStringExtra("idPublicacion");
        ((TextView) findViewById(R.id.nickname_txt_view)).setText(intent.getStringExtra("nickname"));
        ((TextView) findViewById(R.id.publicacion_txt_view)).setText(intent.getStringExtra("contenido"));
        ((TextView) findViewById(R.id.tiempo_txt_view)).setText(intent.getStringExtra("tiempo"));
        comentario_edit_text = (EditText) findViewById(R.id.comentario_edit_text);

        helper = new ParseServerHelper(this);

        adapter = new ComentariosAdapter(this, Comentario.COMENTARIOS, idPublicacion);
        adapter.setHelper(helper);
        helper.getComentarios(adapter, idPublicacion);
        listView = (ListView) findViewById(R.id.lista_comentarios);
        listView.setAdapter(adapter);

        Button boton = (Button) findViewById(R.id.enviar_button);
        boton.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) { // handle arrow click here
            closeKeyboard();
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String comentario = comentario_edit_text.getText().toString();
        if (!comentario.isEmpty()) {
            closeKeyboard();
            helper.insertarComentario(comentario,idPublicacion, Calendar.getInstance().getTime());
            helper.getComentarios(adapter, idPublicacion);
            comentario_edit_text.setText("");


        }
    }

    public void closeKeyboard() {
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

    }
}
