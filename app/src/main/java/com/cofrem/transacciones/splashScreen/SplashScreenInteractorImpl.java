package com.cofrem.transacciones.splashScreen;

import android.content.Context;

public class SplashScreenInteractorImpl implements SplashScreenInteractor {

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
    private SplashScreenRepository splashScreenRepository;

    /**
     * #############################################################################################
     * Constructor de la clase
     * #############################################################################################
     */
    public SplashScreenInteractorImpl() {

        splashScreenRepository = new SplashScreenRepositoryImpl();

    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo que verifica:
     * - La existencia de la configuración inicial
     * - En caso de no existir mostrará la vista de configuración
     * - En caso de existir validara el acceso
     *
     * @param context
     */
    @Override
    public void validateInitialConfig(Context context) {
        splashScreenRepository.validateInitialConfig(context);
    }

    /**
     * Metodo que verifica:
     * - Conexion a internet
     * - Existencia datos en DB interna
     * - Coherencia de datos con el servidor
     *
     * @param context
     */
    @Override
    public void validateAccess(Context context) {
        //Valida el acceso a la app
        splashScreenRepository.validateAcces(context);
    }

    /**
     * Metodo que consulta la informacion del header
     *
     * @param context
     */
    @Override
    public void setInfoHeader(Context context) {
        splashScreenRepository.setInfoHeader(context);
    }
}
