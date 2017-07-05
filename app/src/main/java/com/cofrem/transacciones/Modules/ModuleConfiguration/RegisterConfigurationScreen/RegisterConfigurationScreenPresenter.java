package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.events.RegisterConfigurationScreenEvent;
import com.cofrem.transacciones.models.Configurations;

public interface RegisterConfigurationScreenPresenter {

    /**
     * Valida el acceso a la configuracion del dispositivo mediante la contraseña de administrador
     *
     * @param context
     * @param passAdmin
     */
    void validateAccessAdmin(Context context, int passAdmin);

    /**
     * Registra los parametros de conexion del dispositivo
     *
     * @param context
     * @param configurations
     */
    void registerConexion(Context context, Configurations configurations);

    /**
     * Valida el codigo del dispositivo con la informacion del WS
     *
     * @param context
     * @param configurations
     */
    void validateCodigoDispositivo(Context context, Configurations configurations);

    /**
     * Metodo para la creacion del presentador
     */
    void onCreate();

    /**
     * Metodo para la destruccion del presentador
     */
    void onDestroy();


    /**
     * Metodo para recibir los eventos generados
     *
     * @param splashScreenEvent
     */
    void onEventMainThread(RegisterConfigurationScreenEvent splashScreenEvent);

}
