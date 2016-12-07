package moviles.com.socialtec.vista;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;

import moviles.com.socialtec.R;
import moviles.com.socialtec.controlador.ParseServerHelper;
import moviles.com.socialtec.vista.FragmentoInicio;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarToolbar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }

        final Context context = this.getApplicationContext();
        final TextView username = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username);
        final TextView nickname= (TextView) navigationView.getHeaderView(0).findViewById(R.id.nickname);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ParseUser user = ParseServerHelper.getCurrentUser();
                if (user != null){
                    username.setText(user.get("nombre").toString() + " " + user.get("apellidos").toString());
                    nickname.setText(user.getUsername());
                } else {
                    Toast.makeText(context, "ERROR CURRENT USER", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab !=  null) {
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    /*
    //  Esto es para un menú sobre el ActionBar, mismo que es desplegado en el DrawerLayout
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_drawer, menu);
            return true;
        }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Intent intent = null;
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.nav_home:
                FragmentoInicio a = new FragmentoInicio();
                a.setActivity(this);
                fragmentoGenerico = a;
                break;
            case R.id.nav_grupos:
                fragmentoGenerico = new FragmentoGrupo();
                break;
            case R.id.nav_ajustes:
                fragmentoGenerico = new FragmentoPerfil();
            case R.id.nav_cerrar_sesion:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ParseUser.logOut();
                    }
                }).start();

                intent = new Intent(getApplicationContext(), ActivityLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(intent);
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }
}
