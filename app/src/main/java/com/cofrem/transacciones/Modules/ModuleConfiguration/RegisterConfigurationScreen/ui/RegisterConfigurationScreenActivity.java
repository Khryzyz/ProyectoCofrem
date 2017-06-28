package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenterImpl;
import com.cofrem.transacciones.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_configuration_register_screen)
public class RegisterConfigurationScreenActivity extends Activity implements RegisterConfigurationScreenView {

    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */
    //Declaracion de los Contoles
    @ViewById
    RelativeLayout bodyContentConfigurationHost;
    @ViewById
    RelativeLayout bodyContentConfigurationPort;
    @ViewById
    RelativeLayout bodyContentConfigurationEstablecimiento;
    @ViewById
    RelativeLayout bodyContentConfigurationLocal;
    @ViewById
    RelativeLayout bodyContentConfigurationExito;

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
        registerConfigurationScreenPresenter = new RegisterConfigurationScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        registerConfigurationScreenPresenter.onCreate();

        /**
         * Metodo que oculta por defecto los include de la vista
         */
        inicializarOcultamientoVistas();

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
        bodyContentConfigurationHost.setVisibility(View.VISIBLE);
    }
    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que oculta por defecto los include de la vista
     */
    private void inicializarOcultamientoVistas() {

        bodyContentConfigurationHost.setVisibility(View.GONE);
        bodyContentConfigurationPort.setVisibility(View.GONE);
        bodyContentConfigurationEstablecimiento.setVisibility(View.GONE);
        bodyContentConfigurationLocal.setVisibility(View.GONE);
        bodyContentConfigurationExito.setVisibility(View.GONE);

    }
}
