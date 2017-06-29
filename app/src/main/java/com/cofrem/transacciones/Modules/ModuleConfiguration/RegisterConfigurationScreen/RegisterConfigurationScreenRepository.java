package com.cofrem.transacciones.Modules.ModuleConfiguration.RegisterConfigurationScreen;

import android.content.Context;

public interface RegisterConfigurationScreenRepository {
    /**
     * Valida el acceso a la configuracion del dispositivo mediante la contraseña de administrador
     *
     * @param context
     * @param passAdmin
     */
    void validateAccessAdmin(Context context, int passAdmin);
}
