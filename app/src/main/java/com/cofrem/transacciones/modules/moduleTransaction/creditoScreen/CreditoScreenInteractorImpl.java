package com.cofrem.transacciones.modules.moduleTransaction.creditoScreen;

import android.content.Context;

import com.cofrem.transacciones.models.Transaccion;

class CreditoScreenInteractorImpl implements CreditoScreenInteractor {
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
    private CreditoScreenRepository creditoScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public CreditoScreenInteractorImpl() {

        creditoScreenRepository = new CreditoScreenRepositoryImpl();

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
        //Valida el acceso a la app
        creditoScreenRepository.registrarTransaccion(context, transaccion);
    }

    @Override
    public void imprimirRecibo(Context context) {
        //Imprime recibo
        creditoScreenRepository.imprimirRecibo(context);
    }
}
