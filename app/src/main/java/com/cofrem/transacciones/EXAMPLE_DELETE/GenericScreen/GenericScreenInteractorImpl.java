package com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen;

class GenericScreenInteractorImpl implements GenericScreenInteractor {
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
    private GenericScreenRepository genericScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public GenericScreenInteractorImpl() {

        genericScreenRepository = new GenericScreenRepositoryImpl();

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
        genericScreenRepository.validateAcces();
    }
}
