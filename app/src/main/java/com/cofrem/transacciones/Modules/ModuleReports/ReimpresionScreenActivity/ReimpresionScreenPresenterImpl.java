package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.events.ReimpresionScreenEvent;
import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ui.ReimpresionScreenView;
import com.cofrem.transacciones.lib.EventBus;
import com.cofrem.transacciones.lib.GreenRobotEventBus;
import com.cofrem.transacciones.models.Transaccion;

public class ReimpresionScreenPresenterImpl implements ReimpresionScreenPresenter {


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
    //Instanciamiento de la interface reimpresionScreenView
    private ReimpresionScreenView reimpresionScreenView;

    //Instanciamiento de la interface ReimpresionScreenInteractor
    private ReimpresionScreenInteractor reimpresionScreenInteractor;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     *
     * @param reimpresionScreenView
     */
    public ReimpresionScreenPresenterImpl(ReimpresionScreenView reimpresionScreenView) {
        this.reimpresionScreenView = reimpresionScreenView;
        this.reimpresionScreenInteractor = new ReimpresionScreenInteractorImpl();
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
        reimpresionScreenView = null;
        eventBus.unregister(this);
    }

    @Override
    public void imprimirUltimoRecibo(Context context) {
        reimpresionScreenInteractor.imprimirUltimoRecibo(context);
    }

    @Override
    public void imprimirConNumCargo(Context context, String numCargo) {
        reimpresionScreenInteractor.imprimirConNumCargo(context, numCargo);
    }

    /**
     * Metodo para la verificacion de los datos
     */
    @Override
    public void VerifySuccess() {
        if (reimpresionScreenView != null) {
           // reimpresionScreenInteractor.validarPasswordTecnico();
        }
    }

    /**
     * Sobrecarga del metodo onEventMainThread de la interface SaldoScreenPresenter para el manejo de eventos
     *
     * @param reimpresionScreenEvent
     */
    @Override
    public void onEventMainThread(ReimpresionScreenEvent reimpresionScreenEvent) {
        switch (reimpresionScreenEvent.getEventType()) {

            case ReimpresionScreenEvent.onVerifyExistenceReciboPorNumCargoSuccess:
                onVerifyExistenceReciboPorNumCargoSuccess(reimpresionScreenEvent.getModelTransaccion());
                break;
            case ReimpresionScreenEvent.onVerifyExistenceReciboPorNumCargoError:
                onVerifyExistenceReciboPorNumCargoError();
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
        if (reimpresionScreenView != null) {
            //  reimpresionScreenView.handleVerifySuccess();
        }
    }

    public void onVerifyExistenceReciboPorNumCargoSuccess(Transaccion modeltgransaccion) {
        reimpresionScreenView.handleVerifyExistenceReciboPorNumCargoSuccess(modeltgransaccion);
    }


    public void onVerifyExistenceReciboPorNumCargoError() {
        reimpresionScreenView.handleVerifyExistenceReciboPorNumCargoError();
    }

}
