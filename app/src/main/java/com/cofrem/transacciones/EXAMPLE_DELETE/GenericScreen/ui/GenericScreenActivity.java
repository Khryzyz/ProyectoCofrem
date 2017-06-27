package com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen.ui;

import android.app.Activity;

import com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen.GenericScreenPresenter;
import com.cofrem.transacciones.EXAMPLE_DELETE.GenericScreen.GenericScreenPresenterImpl;
import com.cofrem.transacciones.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main_screen)
public class GenericScreenActivity extends Activity implements GenericScreenView {

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SaldoScreenPresenter
    private GenericScreenPresenter genericScreenPresenter;


    /**
     * #############################################################################################
     * Constructor  de  la clase
     * #############################################################################################
     */
    @AfterViews
    void MainInit() {

        /**
         * Instanciamiento e inicializacion del presentador
         */
        genericScreenPresenter = new GenericScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        genericScreenPresenter.onCreate();

        /**
         * metodo verificar acceso
         */
        //TODO: crear metodos
        genericScreenPresenter.VerifySuccess();

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
        genericScreenPresenter.onDestroy();
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

    /**
     * Metodo para navegar a la ventana de transacciones
     */
    @Override
    public void navigateTransactionView() {

    }

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Override
    public void navigateToReportsView() {

    }

    /**
     * Metodo para navegar a la ventana de configuraciones
     */
    @Override
    public void navigateToConfigurationView() {

    }
}
