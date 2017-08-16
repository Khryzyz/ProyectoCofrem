package com.cofrem.transacciones.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cofrem.transacciones.R;
import com.cofrem.transacciones.database.AppDatabase;
import com.cofrem.transacciones.lib.StyleConfig;

import java.util.ArrayList;

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


    public static PrintRow printLogo(Context context, int gray){

        //logo de COFREM que se imprime al inicio del recibo
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);

        return new PrintRow(logo, StyleConfig.Align.CENTER,gray,2);
    }

    public static void printEstablecimiento(Context context,ArrayList<PrintRow> printRows, int gray){

        Establishment modelEstablishment = AppDatabase.getInstance(context).getEstablecimiento();

        printRows.add(new PrintRow(context.getResources().getString(
                R.string.recibo_nit, modelEstablishment.getNit()), context.getResources().getString(
                R.string.recibo_codigo, modelEstablishment.getCodigo()),new StyleConfig(StyleConfig.Align.CENTER, gray ,StyleConfig.FontSize.F1)));
        printRows.add(new PrintRow(modelEstablishment.getNombre(), new StyleConfig(StyleConfig.Align.CENTER, gray,StyleConfig.FontSize.F2)));
        printRows.add(new PrintRow(modelEstablishment.getDireccion(), new StyleConfig(StyleConfig.Align.CENTER, gray,StyleConfig.FontSize.F2)));
        printRows.add(new PrintRow(modelEstablishment.getCiudad(), new StyleConfig(StyleConfig.Align.CENTER, gray,StyleConfig.FontSize.F2)));

    }

    public static void printFirma(Context context,ArrayList<PrintRow> printRows, int gray){

        printRows.add(new PrintRow(context.getResources().getString(
                R.string.recibo_ingresa_firma), new StyleConfig(StyleConfig.Align.LEFT, gray)));
        printRows.add(new PrintRow(context.getResources().getString(
                R.string.recibo_ingresa_cc), new StyleConfig(StyleConfig.Align.LEFT, gray)));
        printRows.add(new PrintRow(context.getResources().getString(
                R.string.recibo_ingresa_tel), new StyleConfig(StyleConfig.Align.LEFT, gray,50)));

    }


}
