package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import android.content.Context;

import com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen.events.RegisterConfigurationScreenEvent;

public interface RegisterConfigurationScreenPresenter {

    /**
     * Valida el acceso a la configuracion del dispositivo mediante la contraseña de administrador
     *
     * @param context
     * @param passAdmin
     */
    void validateAccessAdmin(Context context, int passAdmin);

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
