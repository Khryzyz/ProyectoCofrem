package com.cofrem.transacciones.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.cofrem.transacciones.global.InfoGlobalSettingsPrint;
import com.cofrem.transacciones.models.PrintRow;
import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.printer.ThermalPrinter;
//import com.telpo.tps550.api.printer.Printer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by luispineda on 12/07/17.
 */

public class PrinterHandler {

    private static Context context;

    private Bitmap bitmapOrigen;

    private static ArrayList<PrintRow> Rows;


    public PrinterHandler() {

    }


    public boolean imprimerTexto(ArrayList<PrintRow> modelRow){
        Rows=modelRow;
        handleMessage(InfoGlobalSettingsPrint.CODE_PRINTCONTENT);
        return true;
    }

    public static void handleMessage(int msg) {

        switch (msg) {

            case InfoGlobalSettingsPrint.CODE_NOPAPER:
//                noPaperDlg();
                break;

            case InfoGlobalSettingsPrint.CODE_LOWBATTERY:
/*
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle(R.string.operation_result);
                alertDialog.setMessageWS(getString(R.string.LowBattery));
                alertDialog.setPositiveButton(getString(R.string.dlg_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.show();
                */
                break;

            case InfoGlobalSettingsPrint.CODE_PRINTVERSION:
                // dialog.dismiss();
//                if (msg.obj.equals("1")) {
//                    // txvPrintVersion.setText(printVersion);
//                } else {
//                    // Toast.makeText(context, R.string.operation_fail, Toast.LENGTH_LONG).show();
//                }
                break;

            case InfoGlobalSettingsPrint.CODE_PRINTPICTURE:
                // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.bl_dy),getString(R.string.printing_wait));
                // printting = CODE_PRINTPICTURE;
                //new PrintHandler.printPicture().start();
                break;

            case InfoGlobalSettingsPrint.CODE_PRINTCONTENT:
                // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.bl_dy),getString(R.string.printing_wait));
                // printting = CODE_PRINTCONTENT;
//                new contentPrintThread().start();




                imprimir ();




//                Boolean respuesta = true;
//
//                try {
//                    respuesta = new PrinterHandler.ImprimirTexto(new PrintHandler.ImprimirTexto.ResponseImprimirTexto(){
//
//                        @Override
//                        public boolean processFinish(boolean exito) {
//                            return exito;
//                        }
//                    }).execute(Rows).get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//                if(respuesta){
//                    Log.e("impriminso","exito") ;
//                }else{
//                    Log.e("impriminso","error") ;
//                }

                break;

            case InfoGlobalSettingsPrint.CODE_PRINTCERIBO:
                // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.bl_dy),getString(R.string.printing_wait));
                // printting = CODE_PRINTPICTURE;
                //new PrintHandler.PrintRecibo().start();
                break;

            case InfoGlobalSettingsPrint.CODE_CANCELPROMPT:
                /*
                if (progressDialog != null && !PrinterActivity.this.isFinishing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }*/
                break;

            case InfoGlobalSettingsPrint.CODE_EXECUTECOMMAND:
               // new PrintHandler.executeCommand().start();
                break;

            case InfoGlobalSettingsPrint.CODE_OVERHEAT:
               /* AlertDialog.Builder overHeatDialog = new AlertDialog.Builder(context);
                overHeatDialog.setTitle(R.string.operation_result);
                overHeatDialog.setMessageWS(getString(R.string.overTemp));
                overHeatDialog.setPositiveButton(getString(R.string.dlg_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                overHeatDialog.show();*/
                break;

            default:
                Toast.makeText(context, "Print Error!", Toast.LENGTH_LONG).show();
                break;
        }
    }


//    private boolean manejadorDeHilo(String msg,String fontzise,Strio){
//        Boolean respuesta = true;
//
//
//
//        return respuesta;
//    }


    public static void imprimir(){

        int res = Printer.connect();


        boolean bandera = true;

        for (PrintRow row : Rows) {
            if (row.getMsg1() != null && row.getMsg2() != null) {

//                    Printer.printText(row.getMsg1(),new StyleConfig(StyleConfig.Align.LEFT,false));
                Printer.printText(justificarTexto(row.getMsg1(),row.getMsg2()),new StyleConfig(StyleConfig.Align.LEFT,true));

//                    bandera = imprimir(row.getMsg1(),row.getFonzise1(),row.getPosition1(),row.getWalkPaper());
            }else if(row.getMsg1() != null){
                Printer.printText(row.getMsg1(),row.getStyleConfig());
            }else if (row.getLogo() != null){
                Printer.printImage(row.getLogo(),row.getAlign());
                Printer.printText("",new StyleConfig(StyleConfig.Align.LEFT,true));
            }

            Printer.commitOperation();
        }
//        Printer.feedPaper(100);
    }


    public static String justificarTexto(String msg1,String msg2){

        int lengthMsg1 = msg1.length();
        int lengthMsg2 = msg2.length();

        int lengthTotal =31- (lengthMsg1 + lengthMsg2) ;

        String resul = msg1;

        for(int i = 0; i < lengthTotal; i++){
            resul += " ";
        }

        resul += msg2;

        return resul;
    }



//    private static class ImprimirTexto extends AsyncTask<ArrayList<PrintRow>, Integer, Boolean> {
//
//        public interface ResponseImprimirTexto{
//            boolean processFinish(boolean exito);
//        }
//
//        public PrintHandler.ImprimirTexto.ResponseImprimirTexto delegate =null;
//
//        public ImprimirTexto(PrintHandler.ImprimirTexto.ResponseImprimirTexto response) {
//            this.delegate = response;
//        }
//
//        @Override
//        protected Boolean doInBackground(ArrayList<PrintRow>... arrayLists) {
//            Printer.connect();
//
//                        boolean bandera = true;
//
//            for (PrintRow row : arrayLists[0]) {
//                if (row.getMsg1() != null) {
//
////                    Printer.printText(row.getMsg1(),new StyleConfig(StyleConfig.Align.LEFT,false));
//                    Printer.printText(row.getMsg1(),new StyleConfig(StyleConfig.Align.LEFT,false));
//
////                    bandera = imprimir(row.getMsg1(),row.getFonzise1(),row.getPosition1(),row.getWalkPaper());
//                }
//
//                if (row.getMsg2() != null) {
//                    Printer.printText(row.getMsg2(),new StyleConfig(StyleConfig.Align.RIGHT,true));
//
//
////                    Printer.printText(row.getMsg2(),new StyleConfig());
//                    //bandera = imprimir(row.getMsg2()+"\n",row.getFonzise2(),row.getPosition2(),row.getWalkPaper());
//                }
//                Printer.commitOperation();
//            }
//
//
//
//
////            try {
////                ThermalPrinter.start();
////                ThermalPrinter.reset();
////            } catch (TelpoException e) {
////                e.printStackTrace();
////            }
////
////            boolean bandera = true;
////            for (PrintRow row : arrayLists[0]) {
////                if (row.getMsg1() != null) {
////                    bandera = imprimir(row.getMsg1(),row.getFonzise1(),row.getPosition1(),row.getWalkPaper());
////                }
////
////                if (row.getMsg2() != null) {
////                    bandera = imprimir(row.getMsg2()+"\n",row.getFonzise2(),row.getPosition2(),row.getWalkPaper());
////                }
////
////            }
//
////
////            try {
////                ThermalPrinter.clearString();
////                ThermalPrinter.walkPaper(100);
////            } catch (TelpoException e) {
////                e.printStackTrace();
////            }
//
//            return true;
//        }
//
//        protected boolean imprimir(String msg, int fonzise, int position, int walkPaper){
//            Boolean nopaper = false;
//            String Result;
//            try {
//                ThermalPrinter.setAlgin(position);
//                ThermalPrinter.setLeftIndent(InfoGlobalSettingsPrint.LEFT_DISTANCE);
//                ThermalPrinter.setLineSpace(InfoGlobalSettingsPrint.LINE_DISTANCE);
//                if (InfoGlobalSettingsPrint.FONT_SIZE == 4) {
//                    ThermalPrinter.setFontSize(2);
//                    ThermalPrinter.enlargeFontSize(2, 2);
//                } else if (InfoGlobalSettingsPrint.FONT_SIZE == 3) {
//                    ThermalPrinter.setFontSize(1);
//                    ThermalPrinter.enlargeFontSize(2, 2);
//                } else if (InfoGlobalSettingsPrint.FONT_SIZE == 2) {
//                    ThermalPrinter.setFontSize(2);
//                } else if (InfoGlobalSettingsPrint.FONT_SIZE == 1) {
//                    ThermalPrinter.setFontSize(1);
//                }
//                ThermalPrinter.setGray(InfoGlobalSettingsPrint.GRAY_LEVEL);
//                ThermalPrinter.addString(msg);
//                ThermalPrinter.printString();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Result = e.toString();
//                if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
//                    nopaper = true;
//                    // return;
//                } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
//                    handleMessage(InfoGlobalSettingsPrint.CODE_OVERHEAT);
//                    //singleton.sendMessage(singleton.obtainMessage(InfoGlobalSettingsPrint.CODE_OVERHEAT, 1, 0, null));
//                } else {
//                    handleMessage(InfoGlobalSettingsPrint.CODE_PRINTERR);
////                    singleton.sendMessage(singleton.obtainMessage(InfoGlobalSettingsPrint.CODE_PRINTERR, 1, 0, null));
//                }
//            } finally {
//                // lock.release();
//                handleMessage(InfoGlobalSettingsPrint.CODE_CANCELPROMPT);
//                //singleton.sendMessage(singleton.obtainMessage(InfoGlobalSettingsPrint.CODE_CANCELPROMPT, 1, 0, null));
//                if (nopaper)
//                    handleMessage(InfoGlobalSettingsPrint.CODE_NOPAPER);
//                //singleton.sendMessage(singleton.obtainMessage(InfoGlobalSettingsPrint.CODE_NOPAPER, 1, 0, null));
//                ThermalPrinter.stop();
//                nopaper = false;
//            }
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            delegate.processFinish(aBoolean);
//        }
//
//    }



}
