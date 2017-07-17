package com.cofrem.transacciones.lib;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class KeyBoard {

    private final static String TAG_KEYBOARD_CLASS = "Exception Keyboard";

    public static void show(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            Log.e(TAG_KEYBOARD_CLASS, e.toString());
        }

    }

    public static void hide(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.e(TAG_KEYBOARD_CLASS, e.toString());
        }
    }
}