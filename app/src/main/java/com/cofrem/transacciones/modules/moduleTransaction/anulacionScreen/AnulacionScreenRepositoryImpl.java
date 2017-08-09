package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen;

import android.content.Context;

import com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen.events.AnulacionScreenEvent;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;

public class AnulacionScreenRepositoryImpl implements AnulacionScreenRepository {
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
    public AnulacionScreenRepositoryImpl() {
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    @Override
    public void validarPasswordAdministrador(Context context, String pass) {

    }

    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que registra los eventos
     *
     * @param type
     * @param errorMessage
     */
    private void postEvent(int type, String errorMessage) {
        AnulacionScreenEvent anulacionScreenEvent = new AnulacionScreenEvent();
        anulacionScreenEvent.setEventType(type);
        if (errorMessage != null) {
            anulacionScreenEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(anulacionScreenEvent);
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
