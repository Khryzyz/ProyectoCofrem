package com.cofrem.transacciones.modules.moduleTransaction.saldoScreen;

import android.content.Context;

import com.cofrem.transacciones.models.Transaccion;

class SaldoScreenInteractorImpl implements SaldoScreenInteractor {
    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    private SaldoScreenRepository saldoScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public SaldoScreenInteractorImpl() {

        saldoScreenRepository = new SaldoScreenRepositoryImpl();

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
    public void registrarTransaccion(Context context, Transaccion transaccion) {
        //Valida el acceso a la app
        saldoScreenRepository.registrarTransaccion(context, transaccion);
    }

    @Override
    public void imprimirRecibo(Context context) {
        saldoScreenRepository.imprimirRecibo(context);
    }
}
