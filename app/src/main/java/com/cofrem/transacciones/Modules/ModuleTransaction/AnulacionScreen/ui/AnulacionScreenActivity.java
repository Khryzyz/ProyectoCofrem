package com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreen.ui;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreen.AnulacionScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleTransaction.AnulacionScreen.AnulacionScreenPresenterImpl;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.models.Configurations;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_transaction_anulacion_screen)
public class AnulacionScreenActivity extends Activity implements AnulacionScreenView {

    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */

    /**
     * Declaracion de los Contoles
     */

    // Contents del modulo
    @ViewById
    RelativeLayout bodyContentConfigurationPassTecnico;
    @ViewById
    RelativeLayout bodyContentConfigurationHost;
    @ViewById
    RelativeLayout bodyContentConfigurationPort;
    @ViewById
    RelativeLayout bodyContentConfigurationDispositivo;
    @ViewById
    RelativeLayout bodyContentConfigurationExito;
    @ViewById
    FrameLayout frlPgbHldRegisterScreen;


    //Paso configuracion_register_paso_pass_tecnico
    @ViewById
    Button btnConfiguracionRegisterPassTecnicoBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterPassTecnicoContenidoClave;

    //Paso configuracion_register_paso_pass_host
    @ViewById
    Button btnConfiguracionRegisterHostBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterHostContenidoValor;

    //Paso configuracion_register_paso_pass_port
    @ViewById
    Button btnConfiguracionRegisterPortBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterPortContenidoValor;

    //Paso configuracion_register_paso_pass_dispositivo
    @ViewById
    Button btnConfiguracionRegisterDispositivoBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterDispositivoContenidoValor;

    /**
     * Model que almacena la configuracion del dispositivo
     */
    Configurations modelConfiguration = new Configurations();

    /**
     * Pasos definidos
     */
    int pasoRegisterConfiguration = 0; // Define el paso actual

    final static int PASO_PASS_TECNICO = 0;
    final static int PASO_HOST = 1;
    final static int PASO_PORT = 2;
    final static int PASO_DISPOSITIVO = 3;

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
