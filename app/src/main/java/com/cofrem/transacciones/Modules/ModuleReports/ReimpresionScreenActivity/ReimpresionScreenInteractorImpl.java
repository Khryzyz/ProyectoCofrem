package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity;

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
        reimpresionScreenRepository.validateAcces();
    }
}
