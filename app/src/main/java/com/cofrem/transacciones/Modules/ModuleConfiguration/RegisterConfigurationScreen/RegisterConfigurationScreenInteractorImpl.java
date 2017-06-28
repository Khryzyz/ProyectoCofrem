package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

class RegisterConfigurationScreenInteractorImpl implements RegisterConfigurationScreenInteractor {
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
    private RegisterConfigurationScreenRepository registerConfigurationScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public RegisterConfigurationScreenInteractorImpl() {

        registerConfigurationScreenRepository = new RegisterConfigurationScreenRepositoryImpl();

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
        registerConfigurationScreenRepository.validateAcces();
    }
}
