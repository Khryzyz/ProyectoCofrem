package com.cofrem.transacciones.lib;

import com.telpo.tps550.api.magnetic.MagneticCard;

import java.util.concurrent.ExecutionException;

public class MagneticHandler {


    public static String[] readMagnetic() {

        //Inicializacion del objeto que sera devuelto por la lectura de la tarjeta
        String[] magneticRead = null;

            try {

                //Transaccion solicitada al web service
                magneticRead = new MagneticAsync(new MagneticAsync.ResponseMagneticAsync() {

                    /**
                     * Metodo sobrecargado que maneja el callback de los datos
                     *
                     * @param magneticResponse
                     * @return
                     */
                    @Override
                    public String[] processFinish(String[] magneticResponse) {
                        return magneticResponse;
                    }

                }).execute().get();

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (ExecutionException e) {

                e.printStackTrace();

            }

        return magneticRead;

    }

}
