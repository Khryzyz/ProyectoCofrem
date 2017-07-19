package com.cofrem.transacciones.models;

import android.graphics.Bitmap;

import com.cofrem.transacciones.lib.StyleConfig;

/**
 * Created by luispineda on 12/07/17.
 */

public class PrintRow {

    private String msg1, msg2;
    private Bitmap logo;
    private StyleConfig styleConfig;
    private StyleConfig.Align align;


    public PrintRow() {
    }

    public PrintRow(Bitmap logo, StyleConfig.Align align) {
        this.logo = logo;
        this.align = align;
    }

    public PrintRow(String msg1, StyleConfig styleConfig) {
        this.msg1 = msg1;
        this.styleConfig = styleConfig;
    }

    public PrintRow(String msg1, String msg2) {
        this.msg1 = msg1;
        this.msg2 = msg2;

    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public StyleConfig getStyleConfig() {
        return styleConfig;
    }

    public void setStyleConfig(StyleConfig styleConfig) {
        this.styleConfig = styleConfig;
    }

    public StyleConfig.Align getAlign() {
        return align;
    }

    public void setAlign(StyleConfig.Align align) {
        this.align = align;
    }
}
