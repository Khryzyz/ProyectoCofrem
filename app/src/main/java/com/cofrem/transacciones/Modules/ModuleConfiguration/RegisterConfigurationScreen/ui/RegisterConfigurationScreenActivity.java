package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui;

import android.app.Activity;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterRegisterConfigurationScreenPresenterImpl;
import com.cofrem.transacciones.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_configuration_screen)
public class RegisterConfigurationScreenActivity extends Activity implements RegisterConfigurationScreenView {

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SaldoScreenPresenter
    private RegisterConfigurationScreenPresenter registerConfigurationScreenPresenter;


    /**
     * #############################################################################################
     * Constructor  de  la clase
     * #############################################################################################
     */
    @AfterViews
    void ConfigurationInit() {

        /**
         * Instanciamiento e inicializacion del presentador
         */
        registerConfigurationScreenPresenter = new RegisterRegisterConfigurationScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        registerConfigurationScreenPresenter.onCreate();

        /**
         * metodo verificar acceso
         */
        registerConfigurationScreenPresenter.VerifySuccess();

    }

    /**
     * #############################################################################################
     * Metodos sobrecargados del sistema
     * #############################################################################################
     */

    /**
     * Metodo sobrecargado de la vista para la destruccion de la activity
     */
    @Override
    public void onDestroy() {
        registerConfigurationScreenPresenter.onDestroy();
        super.onDestroy();
    }
    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo para manejar la verificacion exitosa
     */
    public void handleVerifySuccess() {

    }

}
