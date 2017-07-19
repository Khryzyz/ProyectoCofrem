package com.cofrem.transacciones.Modules.ModuleTransaction.SaldoScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleTransaction.SaldoScreen.events.SaldoScreenEvent;
import com.cofrem.transacciones.Modules.ModuleTransaction.SaldoScreen.ui.SaldoScreenView;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.models.Transaccion;

public class SaldoScreenPresenterImpl implements SaldoScreenPresenter {


    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */
    //Declaracion del bus de eventos
    EventBus eventBus;

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface saldoScreenView
    private SaldoScreenView saldoScreenView;

    //Instanciamiento de la interface SaldoScreenInteractor
    private SaldoScreenInteractor saldoScreenInteractor;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     *
     * @param saldoScreenView
     */
    public SaldoScreenPresenterImpl(SaldoScreenView saldoScreenView) {
        this.saldoScreenView = saldoScreenView;
        this.saldoScreenInteractor = new SaldoScreenInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    /**
     * Sobrecarga del metodo onCreate de la interface AnulacionScreenPresenter "crear" el registro al bus de eventos
     */
    @Override
    public void onCreate() {

        eventBus.register(this);

    }

    /**
     * Sobrecarga del metodo onDestroy de la interface AnulacionScreenPresenter para "eliminar"  el registro al bus de eventos
     */
    @Override
    public void onDestroy() {
        saldoScreenView = null;
        eventBus.unregister(this);
    }

    /**
     * Metodo para la verificacion de los datos
     */
    @Override
    public void registrarTransaccion(Context context, Transaccion transaccion) {
        if (saldoScreenView != null) {
            saldoScreenInteractor.validateAccess();
        }
    }

    /**
     * Sobrecarga del metodo onEventMainThread de la interface AnulacionScreenPresenter para el manejo de eventos
     *
     * @param saldoScreenEvent
     */
    @Override
    public void onEventMainThread(SaldoScreenEvent saldoScreenEvent) {
        switch (saldoScreenEvent.getEventType()) {

            case SaldoScreenEvent.onVerifySuccess:
                onVerifySuccess();
                break;

        }
    }


    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo para manejar la verificacion exitosa
     */
    private void onVerifySuccess() {
        if (saldoScreenView != null) {
            saldoScreenView.handleVerifySuccess();
        }
    }

}
