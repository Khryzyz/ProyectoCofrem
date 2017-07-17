package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.events.ReimpresionScreenEvent;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.lib.PrintHandler;
import com.cofrem.transacciones.models.Transaccion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

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
    public void validarExistenciaUltimoRecibo(Context context) {
        Transaccion modelTransaccion = AppDatabase.getInstance(context).obtenerUltimaTransaccion();
        if (modelTransaccion.getNumero_tarjeta() != null) {
            postEvent(ReimpresionScreenEvent.onVerifyExistenceUltimoReciboSuccess, modelTransaccion);
        } else {
            postEvent(ReimpresionScreenEvent.onVerifyExistenceUltimoReciboError);
        }
    }

    @Override
    public void validarExistenciaDetalleRecibos(Context context) {
        ArrayList<Transaccion> listaDetalle = AppDatabase.getInstance(context).obtenerDetallesTransaccion();

        if(!listaDetalle.isEmpty()){
            postEvent(ReimpresionScreenEvent.onVerifyExistenceReporteDetalleSuccess,listaDetalle);
        }else{
            postEvent(ReimpresionScreenEvent.onVerifyExistenceReporteDetalleError);
        }



    }

    @Override
    public void validarClaveAdministrador(Context context, String clave) {
        if(clave.equals("123")){
            postEvent(ReimpresionScreenEvent.onVerifyClaveAdministradorSuccess);
        }else{
            postEvent(ReimpresionScreenEvent.onVerifyClaveAdministradorError);
        }
    }

    @Override
    public void validarExistenciaReciboConNumCargo(Context context, String numCargo) {
        Transaccion modelTransaccion = AppDatabase.getInstance(context).obtenerTransaccion(numCargo);
        if (modelTransaccion.getNumero_tarjeta() != null) {
            postEvent(ReimpresionScreenEvent.onVerifyExistenceReciboPorNumCargoSuccess, modelTransaccion);
        } else {
            postEvent(ReimpresionScreenEvent.onVerifyExistenceReciboPorNumCargoError);
        }

    }


    public void imprimirUltimoRecibo(Context context) {
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);

//        PrintHandler.getInstance(context).printPinture(logo);

        Transaccion modelTransaccion = AppDatabase.getInstance(context).obtenerUltimaTransaccion();

        String mensaje = context.getResources().getString(
                R.string.reimprimir_recibo,
                "hoy",
                modelTransaccion.getNumero_tarjeta(),
                String.valueOf(modelTransaccion.getValor()),
                String.valueOf(modelTransaccion.getNumero_cargo())
        );

        PrintHandler.getInstance(context).printRecibo(logo,mensaje);

    }


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
    private void postEvent(int type,String errorMessage ,Transaccion modalTransaccion ,ArrayList<Transaccion> lista) {
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
    private void postEvent(int type, ArrayList<Transaccion> lista) {

        postEvent(type,null , null,lista);

    }
    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type, Transaccion modelTransaccion) {

        postEvent(type,null , modelTransaccion,null);

    }

    /**
     * Sobrecarga del metodo postevent
     *
     * @param type
     */
    private void postEvent(int type) {

        postEvent(type, null, null,null);

    }
}
