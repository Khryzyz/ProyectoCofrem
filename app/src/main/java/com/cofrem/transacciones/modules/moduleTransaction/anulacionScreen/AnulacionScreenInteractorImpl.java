package com.cofrem.transacciones.modules.moduleTransaction.anulacionScreen;

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
    public void validateAccess() {
        //Valida el acceso a la app
        anulacionScreenRepository.validateAcces();
    }
}
