package com.cofrem.transacciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cofrem.transacciones.models.Reports;
import com.cofrem.transacciones.modules.moduleReports.reimpresionScreen.ui.ReimpresionScreenActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_report_screen)
public class ReportScreenActivity extends Activity {

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */

    /**
     * #############################################################################################
     * Constructor  de  la clase
     * #############################################################################################
     */
    @AfterViews
    void ReportInit() {

    }

    /**
     * #############################################################################################
     * Metodos sobrecargados del sistema
     * #############################################################################################
     */

    /**
     * Metodo que interfiere la presion del boton "Back"
     */
    @Override
    public void onBackPressed() {
        String mensajeRegresar = getString(R.string.general_message_press_back) + getString(R.string.general_text_button_regresar);
        Toast.makeText(this, mensajeRegresar, Toast.LENGTH_SHORT).show();
    }

    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Click(R.id.btnReportScreenModuleReimpresion)
    public void navigateToReportsReimpresion() {

        Bundle args = new Bundle();

        args.putInt(Reports.keyReport, Reports.reportReimpresionRecibo);

        Intent intent = new Intent(this, ReimpresionScreenActivity_.class);

        intent.putExtras(args);

        startActivity(intent);
    }

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Click(R.id.btnReportScreenModuleDetalle)
    public void navigateToReportsDetalle() {

        Bundle args = new Bundle();

        args.putInt(Reports.keyReport, Reports.reportReporteDetalle);

        Intent intent = new Intent(this, ReimpresionScreenActivity_.class);

        intent.putExtras(args);

        startActivity(intent);
    }

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Click(R.id.btnReportScreenModuleGeneral)
    public void navigateToReportsGeneral() {

        Bundle args = new Bundle();

        args.putInt(Reports.keyReport, Reports.reportReporteGeneral);

        Intent intent = new Intent(this, ReimpresionScreenActivity_.class);

        intent.putExtras(args);

        startActivity(intent);
    }

    /**
     * Metodo para navegar a la ventana de reportes
     */
    @Click(R.id.btnReportScreenModuleCierreLote)
    public void navigateToCierreLote() {

        Bundle args = new Bundle();

        args.putInt(Reports.keyReport, Reports.reportCierreLote);

        Intent intent = new Intent(this, ReimpresionScreenActivity_.class);

        intent.putExtras(args);

        startActivity(intent);
    }

    /**
     * Metodo para navegar a la ventana principal
     */
    @Click(R.id.btnReportScreenBack)
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, MainScreenActivity_.class);
        startActivity(intent);
    }

}