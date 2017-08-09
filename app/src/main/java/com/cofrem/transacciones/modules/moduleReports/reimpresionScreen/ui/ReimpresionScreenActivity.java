package com.cofrem.transacciones.modules.moduleReports.reimpresionScreen.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cofrem.transacciones.ReportScreenActivity_;
import com.cofrem.transacciones.models.InfoHeaderApp;
import com.cofrem.transacciones.models.Reports;
import com.cofrem.transacciones.modules.moduleReports.reimpresionScreen.ReimpresionScreenPresenter;
import com.cofrem.transacciones.modules.moduleReports.reimpresionScreen.ReimpresionScreenPresenterImpl;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.models.Transaccion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_report_reimpresion_screen)
public class ReimpresionScreenActivity extends Activity implements ReimpresionScreenView {

    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */

    /**
     * Declaracion de los Contoles
     */

    // Controles del header

    @ViewById
    TextView txvHeaderIdDispositivo;
    @ViewById
    TextView txvHeaderIdPunto;
    @ViewById
    TextView txvHeaderEstablecimiento;
    @ViewById
    TextView txvHeaderPunto;

    private static final int PASO_ULTIMO_RECIBO = 1;
    private static final int PASO_NUMERO_CARGO = 2;
    private static final int PASO_DETALLE = 3;
    private static final int PASO_GENERAL = 4;

    private int paso;

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
    @ViewById
    RelativeLayout bodyContentReimpresionReciboClaveAdministrador;

    @ViewById
    EditText edtReportReimprimeonReciboNummeroCargoContenidoClave;
    @ViewById
    TextView txvReportReimprimeonReciboImpresionSaldoCantidad;
    @ViewById
    EditText edtReportReimpresionReciboClaveAdministradorContenidoClave;

    @ViewById
    Button btnReportReimpresionReciboImprimirRecibo;


    Transaccion modelTransaccion;

    /**
     * #############################################################################################
     * Instanciamientos de las clases
     * #############################################################################################
     */
    //Instanciamiento de la interface SaldoScreenPresenter
    private ReimpresionScreenPresenter reimpresionScreenPresenter;

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

        // Metodo para colocar la orientacion de la app
        setOrientation();

        // Metodo que oculta por defecto los include de la vista
        inicializarOcultamientoVistas();

        // Metodo que llena el header de la App
        setInfoHeader();

        Bundle args = getIntent().getExtras();

        switch (args.getInt(Reports.keyReport)) {
            case Reports.reportReimpresionRecibo:
                bodyContentReimpresionRecibo.setVisibility(View.VISIBLE);
                break;
            case Reports.reportReporteDetalle:
                paso = PASO_DETALLE;
                reimpresionScreenPresenter.validarExistenciaDetalleRecibos(this);
                break;
            case Reports.reportReporteGeneral:
                paso = PASO_GENERAL;
                bodyContentReimpresionReciboClaveAdministrador.setVisibility(View.VISIBLE);
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
     * Metodo que interfiere la presion del boton "Back"
     */
    @Override
    public void onBackPressed() {
        String mensajeRegresar = getString(R.string.general_message_press_back) + getString(R.string.general_text_button_regresar);
        Toast.makeText(this, mensajeRegresar, Toast.LENGTH_SHORT).show();
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */


    /**
     * Metodo para manejar la existencia de un Ultimo recibo para reimprimir
     */
    @Override
    public void handleVerifyExistenceUltimoReciboSuccess(Transaccion modelTransaccion) {
        this.modelTransaccion = modelTransaccion;
        bodyContentReimpresionRecibo.setVisibility(View.GONE);
        bodyContentReimpresionReciboClaveAdministrador.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo para manejar la NO existencia de un Ultimo recibo para reimprimir
     */
    @Override
    public void handleVerifyExistenceUltimoReciboError() {
// texto quemado hay que pitarlo
        Toast.makeText(this, "no existen registros", Toast.LENGTH_LONG).show();

    }


    /**
     * Metodo para manejar la existencia de un recibo por numero de cargo
     */
    @Override
    public void handleVerifyExistenceReciboPorNumCargoSuccess(Transaccion modelTransaccion) {
        this.modelTransaccion = modelTransaccion;
        bodyContentReimpresionReciboNumeroCargo.setVisibility(View.GONE);
        bodyContentReimpresionReciboClaveAdministrador.setVisibility(View.VISIBLE);

    }


    /**
     * Metodo para manejar la NO existencia de un recibo por numero de cargo
     */
    @Override
    public void handleVerifyExistenceReciboPorNumCargoError() {
        Toast.makeText(this, this.getString(R.string.report_text_message_No_existen_recibo_num_cargo), Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleVerifyClaveAdministradorSuccess() {
        bodyContentReimpresionReciboClaveAdministrador.setVisibility(View.GONE);
        switch (paso) {
            case PASO_ULTIMO_RECIBO:
                bodyContentReimpresionReciboUltimo.setVisibility(View.VISIBLE);
                break;
            case PASO_NUMERO_CARGO:
                bodyContentReimpresionReciboImpresion.setVisibility(View.VISIBLE);
                txvReportReimprimeonReciboImpresionSaldoCantidad.setText(String.valueOf(modelTransaccion.getNumero_cargo()));
                break;
            case PASO_DETALLE:
                bodyContentReporteDetallesImpresion.setVisibility(View.VISIBLE);
                break;
            case PASO_GENERAL:
                bodyContentReporteGeneralImpresion.setVisibility(View.VISIBLE);
                break;
        }
        edtReportReimpresionReciboClaveAdministradorContenidoClave.setText("");
    }

    // texto quemado hay que pitarlo
    @Override
    public void handleVerifyClaveAdministradorError() {
        Toast.makeText(this, this.getString(R.string.report_text_message_clave_admin_incorrecta), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleVerifyExistenceReporteDetalleSuccess() {
        bodyContentReimpresionReciboClaveAdministrador.setVisibility(View.VISIBLE);
    }


    @Override
    public void handleVerifyExistenceReporteDetalleError() {
        Toast.makeText(this, this.getString(R.string.report_text_message_No_existen_recibos), Toast.LENGTH_LONG).show();
        regresarDesdeReimpimirRecibo();
    }

    @Override
    public void handleImprimirUltimoReciboSuccess() {
        regresarDesdeReimpimirRecibo(2000);
    }

    @Override
    public void handleImprimirUltimoReciboError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleImprimirReciboPorNumCargoSuccess() {
        regresarDesdeReimpimirRecibo(2000);
    }

    @Override
    public void handleImprimirReciboPorNumCargoError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleImprimirReporteDetalleSuccess() {

    }

    @Override
    public void handleImprimirReporteDetalleError(String error) {

    }

    @Override
    public void handleImprimirReporteGeneralSuccess() {

    }

    @Override
    public void handleImprimirReporteGeneralError(String error) {

    }

    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que coloca la orientacion de la App de forma predeterminada
     */
    private void setOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

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
     * Metodo que llena el header de la App
     */
    private void setInfoHeader() {

        txvHeaderIdDispositivo.setText(
                String.format(
                        getString(R.string.header_text_id_dispositivo_registrado)
                        , InfoHeaderApp.getInstance().getIdDispositivo()
                )
        );

        txvHeaderIdPunto.setText(
                String.format(
                        getString(R.string.header_text_id_punto_registrado)
                        , InfoHeaderApp.getInstance().getIdPunto()
                )
        );

        txvHeaderEstablecimiento.setText(
                String.format(
                        getString(R.string.header_text_nombre_establecimiento_registrado)
                        , InfoHeaderApp.getInstance().getNombreEstablecimiento()
                )
        );

        txvHeaderPunto.setText(
                String.format(
                        getString(R.string.header_text_nombre_punto_registrado)
                        , InfoHeaderApp.getInstance().getNombrePunto()
                )
        );

    }

    /**
     * Metodo para validar la existencia de un ultimo recibo para Reimprimir
     */
    @Click(R.id.btnReportReimpresionReciboUltimoRecibo)
    public void validarExistenciaUltimoRecibo() {
        paso = PASO_ULTIMO_RECIBO;
        reimpresionScreenPresenter.validarExistenciaUltimoRecibo(this);
    }

    /**
     * Metodo que se encargara de validar la clave del administrador
     */
    @Click(R.id.btnReportReimpresionReciboClaveAdministradorBotonAceptar)
    public void validarClaveAdministrador() {
        reimpresionScreenPresenter.validarClaveAdministrador(this,
                edtReportReimpresionReciboClaveAdministradorContenidoClave.getText().toString());
    }

    /**
     * Metodo que se encargara de Reimprimir el recibo
     */
    @Click(R.id.btnReportReimpresionReciboImprimirRecibo)
    public void imprimirUltimoRecibo() {
        btnReportReimpresionReciboImprimirRecibo.setEnabled(false);
        reimpresionScreenPresenter.imprimirUltimoRecibo(this);

    }

    /**
     * Metodo para salir de la vista de Reimpresion Ultimo
     */
    @Click(R.id.btnReportReimpresionReciboSalir)
    public void salirDelContentReimprimirUltimo() {
        bodyContentReimpresionReciboUltimo.setVisibility(View.GONE);
        bodyContentReimpresionRecibo.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo para iniciar el proceso de Reimprimir recibo por numero de cargo
     */
    @Click(R.id.btnReportReimpresionReciboNumeroDeCargo)
    public void navigateToContentReimprimirNumCargo() {
        bodyContentReimpresionRecibo.setVisibility(View.GONE);
        bodyContentReimpresionReciboNumeroCargo.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo para validad si el nuero de cargo existe para imprimir el recibo
     */
    @Click(R.id.btnReportReimprimeonReciboNummeroCargoBotonAceptar)
    public void acceptReimprimirNumCargo() {
        paso = PASO_NUMERO_CARGO;
        reimpresionScreenPresenter.validarExistenciaReciboConNumCargo(this, edtReportReimprimeonReciboNummeroCargoContenidoClave.getText().toString());
    }


    @Click(R.id.btnReportReimprimeonReciboImpresionBotonImprimir)
    public void imprimirReimprimirNumCargo() {

        reimpresionScreenPresenter.imprimirReciboConNumCargo(this);
        edtReportReimprimeonReciboNummeroCargoContenidoClave.setText("");

    }

    @Click(R.id.btnReportReimprimeonReciboImpresionBotonSalir)
    public void cancelReimprimirNumCargo() {
        bodyContentReimpresionReciboImpresion.setVisibility(View.GONE);
        bodyContentReimpresionRecibo.setVisibility(View.VISIBLE);

    }


    @Click(R.id.btnReportReporteDetallesImpresionBotonImprimir)
    public void imprimirReporteDetalle() {
        reimpresionScreenPresenter.imprimirReporteDetalle(this);
    }

    @Click(R.id.btnReportReporteGeneralImpresionBotonImprimir)
    public void imprimirReporteGeneral() {
        reimpresionScreenPresenter.imprimirReporteGeneral(this);
    }


    @Click({R.id.btnTransactionScreenBack
            , R.id.btnReportReporteDetallesImpresionBotonSalir
            , R.id.btnReportReporteGeneralImpresionBotonSalir
            , R.id.btnReportCierreLoteClaveDispositivoBotonCancelar
            , R.id.btnReportCierreLoteImpresionBotonSalir
            , R.id.btnReportReimprimeonReciboNummeroCargoBotonCancelar})
    public void regresarDesdeReimpimirRecibo() {
        Intent intent = new Intent(this, ReportScreenActivity_.class);
        startActivity(intent);
    }

    public void regresarDesdeReimpimirRecibo(int timer) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                regresarDesdeReimpimirRecibo();
            }
        }, timer);
    }


}
