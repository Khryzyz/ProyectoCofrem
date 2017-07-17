package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.events.CreditoScreenEvent;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.global.InfoGlobalSettingsPrint;
import com.cofrem.transacciones.global.InfoGlobalTransaccionSOAP;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.lib.PrintHandler;
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

        if (registrarTransaccionConsumoWS(context, transaccion)) {
            if (registrarTransaccionConsumoDB(context, transaccion)) {
                postEvent(CreditoScreenEvent.onVerifySuccess);
            } else {
                postEvent(CreditoScreenEvent.onVerifyError);
            }
        } else {
            postEvent(CreditoScreenEvent.onVerifyError);
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
     * Metodo que:
     * - registra mediante el WS la transaccion
     * - Extrae el estado de la transaccion
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


