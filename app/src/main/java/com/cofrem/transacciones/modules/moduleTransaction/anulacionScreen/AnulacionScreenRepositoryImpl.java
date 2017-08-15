package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen;

import android.content.Context;
import android.util.Log;

import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.lib.MD5;
import com.cofrem.transacciones.models.Transaccion;
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

    /**
     * Metodo quevalida la existencia de la clave de administracion
     *
     * @param context
     * @param passAdmin
     */
    @Override
    public void validarPasswordAdministrador(Context context, String passAdmin) {

        int validateExistValorAcceso = AppDatabase.getInstance(context).validarAccesoByClaveAdministracion(MD5.crypt(passAdmin));

        switch (validateExistValorAcceso) {
            case 0:
                postEvent(AnulacionScreenEvent.onClaveAdministracionNoValida);
                break;
            case 1:
                postEvent(AnulacionScreenEvent.onClaveAdministracionValida);
                break;
            default:
                postEvent(AnulacionScreenEvent.onClaveAdministracionError);
                break;
        }

    }

    /**
     * @param context
     * @param numeroCargo
     */
    @Override
    public void obtenerValorTransaccion(Context context, String numeroCargo) {

        Log.i("Repositorio cargo",numeroCargo);

        int valorTransaccion = AppDatabase.getInstance(context).obtenerValorTransaccion(numeroCargo);

        switch (valorTransaccion) {
            case AnulacionScreenEvent.VALOR_TRANSACCION_NO_VALIDO:
                postEvent(AnulacionScreenEvent.onValorTransaccionNoValido);
                break;
            default:
                postEvent(AnulacionScreenEvent.onValorTransaccionValido, valorTransaccion);
                break;
        }
    }

    /**
     * @param context
     * @param transaccion
     */
    @Override
    public void registrarTransaccion(Context context, Transaccion transaccion) {

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
    private void postEvent(int type, String errorMessage, int valorInt) {

        AnulacionScreenEvent anulacionScreenEvent = new AnulacionScreenEvent();

        anulacionScreenEvent.setEventType(type);

        if (errorMessage != null) {
            anulacionScreenEvent.setErrorMessage(errorMessage);
        }

        if (valorInt != AnulacionScreenEvent.VALOR_TRANSACCION_NO_VALIDO) {
            anulacionScreenEvent.setValorInt(valorInt);
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

        postEvent(type, null, AnulacionScreenEvent.VALOR_TRANSACCION_NO_VALIDO);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, int valorInt) {

        postEvent(type, null, valorInt);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, String errorMessage) {

        postEvent(type, errorMessage, AnulacionScreenEvent.VALOR_TRANSACCION_NO_VALIDO);

    }

}
