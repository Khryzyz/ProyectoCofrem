package com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.cofrem.transacciones.Modules.ModuleReports.ReimpresionScreenActivity.ModelReport.Reports;
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

        inicializarOcultamientoVistas();

        Bundle args = getIntent().getExtras();

        switch (args.getInt(Reports.keyReport)) {
            case Reports.reportReimpresionRecibo:
                bodyContentReimpresionRecibo.setVisibility(View.VISIBLE);
                break;
            case Reports.reportReporteDetalle:
                bodyContentReporteDetallesImpresion.setVisibility(View.VISIBLE);
                break;
            case Reports.reportReporteGeneral:
                bodyContentReimpresionRecibo.setVisibility(View.VISIBLE);
                break;
            case Reports.reportCierreLote:
                bodyContentCierreLoteClaveDispositivo.setVisibility(View.VISIBLE);
                break;
        }

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
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */


    /**
     * Metodo que oculta por defecto los include de la vista
     */
    private void inicializarOcultamientoVistas() {

        bodyContentReimpresionRecibo.setVisibility(View.GONE);
        bodyContentReimpresionReciboUltimo.setVisibility(View.GONE);
        bodyContentReimpresionReciboNumeroCargo.setVisibility(View.GONE);
        bodyContentReimpresionReciboImpresion.setVisibility(View.GONE);
        bodyContentReporteDetallesImpresion.setVisibility(View.GONE);
        bodyContentReporteGeneralImpresion.setVisibility(View.GONE);
        bodyContentCierreLoteClaveDispositivo.setVisibility(View.GONE);
        bodyContentCierreLoteVerificacion.setVisibility(View.GONE);
        bodyContentCierreLoteImpresion.setVisibility(View.GONE);

    }

    /**
     * Metodo para navegar a la ventana reimprecion ultimo recibo Imprimir
     */
    @Click(R.id.btnReportReimpresionReciboUltimoRecibo)
    public void navigateToContentReimprimirUltimo() {
        //reimpresionScreenPresenter.imprimir(this);
        bodyContentReimpresionRecibo.setVisibility(View.GONE);
        bodyContentReimpresionReciboUltimo.setVisibility(View.VISIBLE);
    }

    @Click(R.id.btnReportReimpresionReciboNumeroDeCargo)
    public void navigateToContentReimprimirNumCargo() {
        bodyContentReimpresionRecibo.setVisibility(View.GONE);
        bodyContentReimpresionReciboNumeroCargo.setVisibility(View.VISIBLE);
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

}
