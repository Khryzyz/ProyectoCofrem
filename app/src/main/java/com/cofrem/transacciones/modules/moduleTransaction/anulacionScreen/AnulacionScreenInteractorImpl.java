package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen;

import android.content.Context;

class AnulacionScreenInteractorImpl implements AnulacionScreenInteractor {
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
    private AnulacionScreenRepository anulacionScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public AnulacionScreenInteractorImpl() {

        anulacionScreenRepository = new AnulacionScreenRepositoryImpl();

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
    public void validarPasswordAdministrador(Context context, String pass) {
        //Valida el acceso a la app
        anulacionScreenRepository.validarPasswordAdministrador(context, pass);
    }
}
