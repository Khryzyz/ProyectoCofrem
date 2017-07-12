package com.cofrem.transacciones.lib;

import com.telpo.tps550.api.magnetic.MagneticCard;

import java.util.concurrent.ExecutionException;

public class MagneticHandler {


    public static String[] readMagnetic() {

        //Inicializacion del objeto que sera devuelto por la lectura de la tarjeta
        String[] magneticRead = null;

        if (openMagnetic()) {

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

                closeMagnetic();

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (ExecutionException e) {

                e.printStackTrace();

            }

        }

        return magneticRead;

    }

    private static boolean openMagnetic() {

        boolean statusMagnetic = false;

        try {

            MagneticCard.open();

            statusMagnetic = true;

        } catch (Exception e) {

            e.printStackTrace();

        }
        return statusMagnetic;

    }

    private static void closeMagnetic() {
        MagneticCard.close();
    }

}
