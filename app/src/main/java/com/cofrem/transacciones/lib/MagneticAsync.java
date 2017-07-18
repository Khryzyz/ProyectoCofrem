package com.cofrem.transacciones.lib;

import android.os.AsyncTask;

import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.TimeoutException;
import com.telpo.tps550.api.magnetic.MagneticCard;

public class MagneticAsync extends AsyncTask<Void, Integer, String[]> {
    /**
     * Interface para el callback de datos
     */
    public interface ResponseMagneticAsync {
        String[] processFinish(String[] magneticResponse);
    }

    //Call back interface
    public ResponseMagneticAsync delegate = null;

    /**
     * Metodo de la interface de devolucion
     *
     * @param response
     */
    public MagneticAsync(ResponseMagneticAsync response) {
        //Asignacion de retorno atraves del contructor
        delegate = response;
    }

    @Override
    protected String[] doInBackground(Void... params) {

        String[] TracData;

        MagneticCard.startReading();

        long strat = System.currentTimeMillis();

        while (System.currentTimeMillis() - strat < 60000) {

            try {

                TracData = MagneticCard.check(500);

                return TracData;

            } catch (TimeoutException e) {

                e.printStackTrace();

            } catch (TelpoException e) {

                e.printStackTrace();

                break;

            }

        }

        return null;

    }

    @Override
    protected void onPreExecute(){

        try {
            MagneticCard.open();
        } catch (TelpoException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(String[] result) {

        MagneticCard.close();

        //Delegado que retorna
        delegate.processFinish(result);
    }

}
