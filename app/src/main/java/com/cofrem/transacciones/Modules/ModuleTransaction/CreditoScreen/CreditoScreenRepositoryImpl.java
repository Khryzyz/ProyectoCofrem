package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.events.CreditoScreenEvent;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.models.Transaccion;

public class CreditoScreenRepositoryImpl implements CreditoScreenRepository {
    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */


    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public CreditoScreenRepositoryImpl() {
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo para obtener el numero de tarjeta desde el dispositivo
     *
     * @param context
     * @param transaccion
     */
    @Override
    public void registrarTransaccion(Context context, Transaccion transaccion) {

        //Registra mediante el WS la transaccion
        if (registrarTransaccionConsumoWS(context, transaccion)) {

            postEvent(CreditoScreenEvent.onTransaccionWSRegisterSuccess);

            //Registro en la base de datos de la transaccion
            if (registrarTransaccionConsumoDB(context, transaccion)) {

                postEvent(CreditoScreenEvent.onTransaccionDBRegisterSuccess);

                //Imprime el recibo
                imprimitRecibo(context);


            } else {

                //Error en el registro en la Base de Datos la transaccion
                postEvent(CreditoScreenEvent.onTransaccionDBRegisterError);

            }

        } else {

            //Error en el registro mediante el WS la transaccion
            postEvent(CreditoScreenEvent.onTransaccionWSRegisterError);

        }

    }


    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que:
     * - registra mediante el WS la transaccion
     * - Extrae el estado de la transaccion
     *
     * @param context
     * @param transaccion
     * @return
     */

    private boolean registrarTransaccionConsumoWS(Context context, Transaccion transaccion) {

        //Se crea una variable de estado de la transaccion
        boolean statusTransaction = true;


        //TODO: IMPLEMENTAR WEB SERVICE DE REGISTRO DE TRANSACCION

        return statusTransaction;
    }

    /**
     * Metodo que registra en la base de datos interna la transaccion
     *
     * @param context
     * @param transaccion
     * @return
     */
    private boolean registrarTransaccionConsumoDB(Context context, Transaccion transaccion) {

        //Se crea una variable de estado de la transaccion
        boolean statusTransaction = true;


        //TODO: IMPLEMENTAR WEB SERVICE DE REGISTRO DE TRANSACCION

        return statusTransaction;
    }

    /**
     * Metodo que imprime el recibo de la transaccion
     *
     * @param context
     */
    private void imprimitRecibo(Context context) {


        //TODO: Implementar la imprecion del recibo
    }

    /**
     * Metodo que registra los eventos
     *
     * @param type
     * @param errorMessage
     */
    private void postEvent(int type, String errorMessage) {
        CreditoScreenEvent creditoScreenEvent = new CreditoScreenEvent();
        creditoScreenEvent.setEventType(type);
        if (errorMessage != null) {
            creditoScreenEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(creditoScreenEvent);
    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type) {

        postEvent(type, null);

    }
}


