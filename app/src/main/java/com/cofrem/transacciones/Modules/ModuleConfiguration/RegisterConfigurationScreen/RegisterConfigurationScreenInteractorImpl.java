package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import android.content.Context;

import com.cofrem.transacciones.models.Configurations;

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
    public void validarPasswordTecnico(Context context, String passAdmin) {

        //Valida el acceso a la configuracion del dispositivo
        registerConfigurationScreenRepository.validarPasswordTecnico(context, passAdmin);

    }

    /**
     * Registra los parametros de conexion del dispositivo
     *
     * @param context
     * @param configurations
     */
    @Override
    public void registrarConfiguracionConexion(Context context, Configurations configurations) {
        registerConfigurationScreenRepository.registrarConfiguracionConexion(context, configurations);
    }
}
