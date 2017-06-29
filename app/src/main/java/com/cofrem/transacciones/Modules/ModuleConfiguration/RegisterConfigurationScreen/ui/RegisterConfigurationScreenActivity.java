package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenter;
import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.RegisterConfigurationScreenPresenterImpl;
import com.cofrem.transacciones.R;
import com.cofrem.transacciones.lib.KeyBoard;
import com.cofrem.transacciones.models.Configurations;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static android.view.KeyEvent.KEYCODE_ENTER;

@EActivity(R.layout.activity_configuration_register_screen)
public class RegisterConfigurationScreenActivity extends Activity implements RegisterConfigurationScreenView {

    /**
     * #############################################################################################
     * Declaracion de componentes y variables
     * #############################################################################################
     */
    //Declaracion de los Contoles
    @ViewById
    RelativeLayout bodyContentConfigurationPassTecnico;
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
    @ViewById
    Button btnConfiguracionRegisterPassTecnicoBotonCancelar;
    @ViewById
    EditText edtConfiguracionRegisterPassTecnicoContenidoClave;

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

        //Primera ventana visible
        bodyContentConfigurationPassTecnico.setVisibility(View.VISIBLE);

        Bundle args = getIntent().getExtras();

        if (args.getInt(Configurations.keyConfiguration) == Configurations.configuracionRegistrarConfigInicial) {
            btnConfiguracionRegisterPassTecnicoBotonCancelar.setVisibility(View.GONE);
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
        registerConfigurationScreenPresenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Meotodo que intercepta las pulsaciones de las teclas del teclado fisico
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /**
         * Keycodes disponibles
         *
         * 4: Back
         * 66: Enter
         * 67: Delete
         *
         */
        switch (keyCode) {

            case KEYCODE_ENTER:

                //Metodo para ocultar el teclado
                hideKeyBoard();

                //Metodo para validar la contraseña
                validatePasswordAdmin();

                break;

            default:
                Log.i("Key Pressed", String.valueOf(keyCode));
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Metodo que interfiere la presion del boton "Back"
     */
    @Override
    public void onBackPressed() {
        //Vacia la caja de contraseña
        edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");
    }

    /**
     * #############################################################################################
     * Metodos sobrecargados de la interface
     * #############################################################################################
     */

    /**
     * Metodo para manejar el valor de acceso valido
     */
    @Override
    public void handleValorAccesoValido() {
        
        //Oculta la vista de la contraseña
        bodyContentConfigurationPassTecnico.setVisibility(View.GONE);

        //Muestra la vista del Host
        bodyContentConfigurationHost.setVisibility(View.VISIBLE);

    }

    /**
     * Metodo para manejar el valor de acceso NO valido
     */
    @Override
    public void handleValorAccesoNoValido() {

        //Vacia la caja de contraseña
        edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");

        //Muestra el mensaje de error en la contraseña
        Toast.makeText(this, R.string.configuration_text_valor_acceso_no_valido, Toast.LENGTH_SHORT).show();

    }

    /**
     * Metodo para manejar el error en la configuracion de valor de acceso
     */
    @Override
    public void handleValorAccesoError() {

        //Vacia la caja de contraseña
        edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");

        //Muestra el mensaje de error en la configuracion de la contraseña
        Toast.makeText(this, R.string.configuration_text_valor_acceso_error, Toast.LENGTH_SHORT).show();

    }


    /**
     * #############################################################################################
     * Metodo propios de la clase
     * #############################################################################################
     */

    /**
     * Metodo que oculta el teclado al presionar el EditText
     */
    @Click(R.id.edtConfiguracionRegisterPassTecnicoContenidoClave)
    public void hideKeyBoard() {

        //Oculta el teclado
        KeyBoard.hide(this);

    }

    /**
     * Metodo que envia la contraseña ingresada para su validacion
     */
    @Click(R.id.btnConfiguracionRegisterPassTecnicoBotonAceptar)
    public void validatePasswordAdmin() {

        // Se obtiene el texto de la contraseña
        String passwordAdmin = edtConfiguracionRegisterPassTecnicoContenidoClave.getText().toString();

        if (passwordAdmin.length() == 4) {

            registerConfigurationScreenPresenter.validateAccessAdmin(this, Integer.valueOf(passwordAdmin));

        } else {

            //Vacia la caja de contraseña
            edtConfiguracionRegisterPassTecnicoContenidoClave.setText("");

            //Muestra el mensaje de error de formato de la contraseña
            Toast.makeText(this, R.string.configuration_error_format_valor_acceso, Toast.LENGTH_SHORT).show();

        }

    }

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
