package com.cofrem.transacciones.modules.moduleConfiguration.testCommunicationScreen;

class TestCommunicationScreenInteractorImpl implements TestCommunicationScreenInteractor {
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
    private TestCommunicationScreenRepository testCommunicationScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public TestCommunicationScreenInteractorImpl() {

        testCommunicationScreenRepository = new TestCommunicationScreenRepositoryImpl();

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
        testCommunicationScreenRepository.validateAcces();
    }
}
