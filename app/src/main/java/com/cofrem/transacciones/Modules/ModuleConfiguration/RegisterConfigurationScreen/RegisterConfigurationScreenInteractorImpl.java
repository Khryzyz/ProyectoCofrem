package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import android.content.Context;

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
     * Valida el acceso a la configuracion del dispositivo mediante la contraseña de administrador
     *
     * @param context
     * @param passAdmin
     */
    @Override
    public void validateAccessAdmin(Context context, int passAdmin) {

        //Valida el acceso a la configuracion del dispositivo
        registerConfigurationScreenRepository.validateAccessAdmin(context, passAdmin);

    }
}
