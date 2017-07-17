package com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.events.CreditoScreenEvent;
import com.cofrem.transacciones.Modules.ModuleTransaction.CreditoScreen.ui.CreditoScreenView;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.models.Transaccion;

public class CreditoScreenPresenterImpl implements CreditoScreenPresenter {


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
    //Instanciamiento de la interface creditoScreenView
    private CreditoScreenView creditoScreenView;

    //Instanciamiento de la interface CreditoScreenInteractor
    private CreditoScreenInteractor creditoScreenInteractor;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     *
     * @param creditoScreenView
     */
    public CreditoScreenPresenterImpl(CreditoScreenView creditoScreenView) {
        this.creditoScreenView = creditoScreenView;
        this.creditoScreenInteractor = new CreditoScreenInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    /**
     * Sobrecarga del metodo onCreate de la interface SaldoScreenPresenter "crear" el registro al bus de eventos
     */
    @Override
    public void onCreate() {

        eventBus.register(this);

    }

    /**
     * Sobrecarga del metodo onDestroy de la interface SaldoScreenPresenter para "eliminar"  el registro al bus de eventos
     */
    @Override
    public void onDestroy() {
        creditoScreenView = null;
        eventBus.unregister(this);
    }

    /**
     * Metodo para obtener el numero de tarjeta desde el dispositivo
     *
     * @param context
     * @param transaccion
     */
    @Override
    public void registrarTransaccion(Context context, Transaccion transaccion) {
        if (creditoScreenView != null) {
            creditoScreenInteractor.registrarTransaccion(context, transaccion);
        }
    }

    /**
     * Sobrecarga del metodo onEventMainThread de la interface SaldoScreenPresenter para el manejo de eventos
     *
     * @param creditoScreenEvent
     */
    @Override
    public void onEventMainThread(CreditoScreenEvent creditoScreenEvent) {
        switch (creditoScreenEvent.getEventType()) {

            case CreditoScreenEvent.onVerifySuccess:
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
        if (creditoScreenView != null) {
            creditoScreenView.handleVerifySuccess();
        }
    }

}
