package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;
import android.util.Log;

import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.events.ReimpresionScreenEvent;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.lib.PrintHandler;
import com.cofrem.transacciones.models.Transaccion;

public class ReimpresionScreenRepositoryImpl implements ReimpresionScreenRepository {
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
    public ReimpresionScreenRepositoryImpl() {
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     *
     */
    @Override
    public void validateAcces() {

        postEvent(ReimpresionScreenEvent.onVerifySuccess);

    }

    @Override
    public void validadExistenciaReciboConNumCargo(Context context, String numCargo) {
        Transaccion modelTransaccion = AppDatabase.getInstance(context).obtenerTransaccion(numCargo);
        if (modelTransaccion.getNumero_tarjeta() == null) {
            postEvent(ReimpresionScreenEvent.onVerifyExistenceReciboPorNumCargoSuccess, modelTransaccion);
        } else {
            postEvent(ReimpresionScreenEvent.onVerifyExistenceReciboPorNumCargoError);
        }

    }

    @Override
    public void imprimirUltimoRecibo(Context context) {
        Transaccion modelTransaccion = AppDatabase.getInstance(context).obtenerUltimaTransaccion();
        String mensaje = "         COFREM \n" +
                "numero de la tarjeta:  $" + modelTransaccion.getNumero_tarjeta() + "\n" +
                "valor:  $" + modelTransaccion.getValor() + "\n" +
                "numero de cargo:  $" + modelTransaccion.getNumero_cargo() + "\n" +
                "Gracias por su compra...";

        PrintHandler.getInstance(context).printMessage(mensaje);
        Log.e("Reporte", mensaje);
    }

    @Override
    public void imprimirConNumCargo(Context context, String numCargo) {

        Transaccion modelTransaccion = AppDatabase.getInstance(context).obtenerTransaccion(numCargo);
        String mensaje = " vacio ";
        if (modelTransaccion.getNumero_tarjeta() == null) {
            mensaje = "         COFREM \n" +
                    "numero de la tarjeta: " + modelTransaccion.getNumero_tarjeta() + "\n" +
                    "valor: $" + modelTransaccion.getValor() + "\n" +
                    "numero de cargo: " + modelTransaccion.getNumero_cargo() + "\n" +
                    "Gracias por su compra...@ ? ¡ $ % & "
            ;

            PrintHandler.getInstance(context).printMessage(mensaje);
        }

        Log.e("Reporte", mensaje);
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
    private void postEvent(int type, Transaccion modalTransaccion, String errorMessage) {
        ReimpresionScreenEvent reimpresionScreenEvent = new ReimpresionScreenEvent();
        reimpresionScreenEvent.setEventType(type);
        if (errorMessage != null) {
            reimpresionScreenEvent.setErrorMessage(errorMessage);
        }

        if (modalTransaccion != null) {
            reimpresionScreenEvent.setModelTransaccion(modalTransaccion);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();

        eventBus.post(reimpresionScreenEvent);
    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, Transaccion modelTransaccion) {

        postEvent(type, modelTransaccion, null);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type) {

        postEvent(type, null, null);

    }
}
