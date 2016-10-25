package moviles.com.socialtec.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ComentariosAdapter;
import moviles.com.socialtec.modelo.Comentario;

public class CommentActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        //Intent intent = getIntent();
        ArrayList<Comentario> comentarios = Comentario.COMENTARIOS;
        listView = (ListView) findViewById(R.id.lista);
        listView.setAdapter(new ComentariosAdapter(this, comentarios));
    }
}
