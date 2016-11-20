package moviles.com.socialtec;

import android.app.Application;

import moviles.com.socialtec.controlador.ParseServerHelper;

/**
 * Created by Edgar on 14/11/2016.
 */

public class SocialiTecApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseServerHelper.inicializarParse(this);
    }
}
