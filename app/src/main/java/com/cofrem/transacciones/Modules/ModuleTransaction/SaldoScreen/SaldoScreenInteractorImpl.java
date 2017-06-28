package com.cofrem.transacciones.Modules.ModuleTransaction.SaldoScreen;

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
    public void validateAccess() {
        //Valida el acceso a la app
        saldoScreenRepository.validateAcces();
    }
}
