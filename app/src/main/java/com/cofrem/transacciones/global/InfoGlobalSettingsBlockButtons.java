package com.cofrem.transacciones.global;

import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfoGlobalSettingsBlockButtons {

    //Botones bloqueados del dispositivo

    public final static List<Integer> blockedKeys = new ArrayList<>(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP));

}
