package com.cofrem.transacciones.SplashScreen;

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
     *
     * @param context
     */
    @Override
    public void validateInitialConfig(Context context) {

    }

    /**
     * Metodo que verifica:
     * - Existencia de datos
     * - Validez de datos
     *
     * @param context
     */
    @Override
    public void validateAccess(Context context) {
        //Valida el acceso a la app
        splashScreenRepository.validateAcces(context);
    }
}
