package br.com.fedablio.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utilitario {

    public boolean internet(Context context) {
        boolean resultado = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if ((netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable())) {
                resultado = true;
            }
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        return resultado;
    }

}
