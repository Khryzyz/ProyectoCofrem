package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ReimpresionScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ReimpresionScreenPresenterImpl;
import com.cofrem.transacciones.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_report_reimpresion_screen)
public class ReimpresionScreenActivity extends Activity implements ReimpresionScreenView {

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SaldoScreenPresenter
    private ReimpresionScreenPresenter reimpresionScreenPresenter;

    @ViewById
    RelativeLayout bodyContentReimpresionRecibo;
    @ViewById
    RelativeLayout bodyContentReimpresionReciboUltimo;
    @ViewById
    RelativeLayout bodyContentReimpresionReciboNumeroCargo;
    @ViewById
    RelativeLayout bodyContentReimpresionReciboImpresion;
    @ViewById
    RelativeLayout bodyContentReporteDetallesImpresion;
    @ViewById
    RelativeLayout bodyContentReporteGeneralImpresion;
    @ViewById
    RelativeLayout bodyContentCierreLoteClaveDispositivo;
    @ViewById
    RelativeLayout bodyContentCierreLoteVerificacion;
    @ViewById
    RelativeLayout bodyContentCierreLoteImpresion;


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
        reimpresionScreenPresenter = new ReimpresionScreenPresenterImpl(this);

        /**
         * Llamada al metodo onCreate del presentador para el registro del bus de datos
         */
        reimpresionScreenPresenter.onCreate();

        /**
         * metodo verificar acceso
         */
        //TODO: crear metodos
        reimpresionScreenPresenter.VerifySuccess();

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
        reimpresionScreenPresenter.onDestroy();
        super.onDestroy();
    }
    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo para navegar a la ventana reimprecion ultimo recibo Imprimir
     */
    @Click(R.id.btnReportReimpresionReciboUltimoRecibo)
    public void navegateToContentReimprimirUltimo() {
        //reimpresionScreenPresenter.imprimir(this);
        bodyContentReimpresionRecibo.setVisibility(View.GONE);
        bodyContentReimpresionReciboUltimo.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo que se encargara de imprimir el recibo
     */
    @Click(R.id.btnReportReimpresionReciboImprimirRecibo)
    public void imprimirUltimoRecibo() {
        reimpresionScreenPresenter.imprimir(this);
    }


    @Click(R.id.btnReportReimpresionReciboSalir)
    public void salirDelContentReimprimirUltimo() {
        bodyContentReimpresionReciboUltimo.setVisibility(View.GONE);
        bodyContentReimpresionRecibo.setVisibility(View.VISIBLE);
    }

    @Click(R.id.btnReportReimpresionReciboNumeroDeCargo)
    public void navegateToContentReimprimirNumCargo() {
        bodyContentReimpresionRecibo.setVisibility(View.GONE);
        bodyContentReimpresionReciboNumeroCargo.setVisibility(View.VISIBLE);
    }

}
