package com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreen.ui;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreen.AnulacionScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreen.AnulacionScreenPresenterImpl;
import com.cofrem.transacciones.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_transaction_anulacion_screen)
public class AnulacionScreenActivity extends Activity implements AnulacionScreenView {

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface AnulacionScreenPresenter
    private AnulacionScreenPresenter anulacionScreenPresenter;

    @ViewById
    RelativeLayout bodyContentClaveDispositivo;
    RelativeLayout bodyContentNumeroCargo;

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
        anulacionScreenPresenter = new AnulacionScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        anulacionScreenPresenter.onCreate();

        /**
         * metodo verificar acceso
         */
        //TODO: crear metodos
        anulacionScreenPresenter.VerifySuccess();

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
        anulacionScreenPresenter.onDestroy();
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
        bodyContentClaveDispositivo.setVisibility(View.GONE);
        bodyContentNumeroCargo.setVisibility(View.VISIBLE);

    }

}
