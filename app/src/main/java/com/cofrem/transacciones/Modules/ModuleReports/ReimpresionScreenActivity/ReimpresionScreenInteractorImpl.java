package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

import android.content.Context;
import android.util.Log;

class ReimpresionScreenInteractorImpl implements ReimpresionScreenInteractor {
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
    private ReimpresionScreenRepository reimpresionScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public ReimpresionScreenInteractorImpl() {

        reimpresionScreenRepository = new ReimpresionScreenRepositoryImpl();

    }

    @Override
    public void imprimirUltimoRecibo(Context context) {
        reimpresionScreenRepository.imprimirUltimoRecibo(context);
    }

    @Override
    public void imprimirConNumCargo(Context context, String numCargo) {
        reimpresionScreenRepository.imprimirConNumCargo(context,numCargo);
    }
    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */




}
