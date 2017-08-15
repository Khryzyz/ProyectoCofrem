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
    private int lineSpace;
    private int gray;


    public PrintRow() {
    }

    public PrintRow(Bitmap logo, StyleConfig.Align align,int gray,int lineSpace) {
        this.logo = logo;
        this.align = align;
        this.lineSpace = lineSpace;
        this.gray = gray;
    }

    public PrintRow(String msg1, StyleConfig styleConfig) {
        this.msg1 = msg1;
        this.styleConfig = styleConfig;
    }

    public PrintRow(String msg1, String msg2, StyleConfig styleConfig) {
        this.msg1 = msg1;
        this.msg2 = msg2;
        this.styleConfig = styleConfig;

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

    public int getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(int lineSpace) {
        this.lineSpace = lineSpace;
    }

    public int getGray() {
        return gray;
    }

    public void setGray(int gray) {
        this.gray = gray;
    }
}
