package com.cofrem.transacciones.EXAMPLE_DELETE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.printer.ThermalPrinter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PrinterActivity extends Activity {
/*

    */
/**
     * Variable String que guarda la version de la impresora???
     *//*

    private static String printVersion;

    */
/**
     * Alguna especie de codigos???
     *//*


    private final int CODE_PRINTIT = 1;
    private final int CODE_ENABLE_BUTTON = 2;
    private final int CODE_NOPAPER = 3;
    private final int CODE_LOWBATTERY = 4;
    private final int CODE_PRINTVERSION = 5;
    private final int CODE_PRINTBARCODE = 6;
    private final int CODE_PRINTQRCODE = 7;
    private final int CODE_PRINTPAPERWALK = 8;
    private final int CODE_PRINTCONTENT = 9;
    private final int CODE_CANCELPROMPT = 10;
    private final int CODE_PRINTERR = 11;
    private final int CODE_OVERHEAT = 12;
    private final int CODE_MAKER = 13;
    private final int CODE_PRINTPICTURE = 14;
    private final int CODE_EXECUTECOMMAND = 15;

    */
/**
     * Flag para detener???
     *//*

    private boolean stop = false;

    */
/**
     * TAG de la activity???
     *//*

    private static final String TAG = "ConsoleTestActivity";

    */
/**
     * Preferencias compartidas del dispositivo???
     *//*

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    */
/**
     * Manejador????
     *//*

    MyHandler handler;

    */
/**
     * Instanciamiento de los linearlayouts
     *//*

    LinearLayout llyPrintText;
    LinearLayout llyPrintCodeAndPic;
    LinearLayout llyPrintComm;

    private LinearLayout linearLayout;

    */
/**
     * Instanciamiento de los TextViews
     *//*

    TextView txvTextIndex;
    TextView txvPicIndex;
    TextView txvCommIndex;

    private TextView txvPrintVersion;
    private TextView txvGray;

    */
/**
     * Instanciamiento de los EditTexts
     *//*

    private EditText edtSetLeftDistance;
    private EditText edtSetLineDistance;
    private EditText edtSetWordFont;
    private EditText edtSetPrintGray;

    private EditText edtBarcode;
    private EditText edtQrcode;
    private EditText edtPaperWalk;
    private EditText edtContent;
    private EditText edtMakerDirection;
    private EditText edtMakerSearchDistance;
    private EditText edtMakerWalkDistance;
    private EditText edtInputCommand;

    */
/**
     * Instanciamiento de los Buttons
     *//*

    private Button btnBarcodePrint;
    private Button btnPaperWalkPrint;
    private Button btnContentPrint;
    private Button btnQrcodePrint;
    private Button btnGetExampleText;
    private Button btnClearText;
    private Button btnMaker;
    private Button btnPapercut;
    private Button btnPrintPicture;
    private Button btnExecuteCommand;

    private int printting = 0;

    private String Result;
    public static String barcodeStr;
    public static String qrcodeStr;
    public static String printContent;

    private boolean nopaper = false;
    private boolean LowBattery = false;

    private final boolean isClose = false;// 关闭程序

    public static int paperWalk;
    private int leftDistance = 0;
    private int lineDistance;
    private int wordFont;
    private int printGray;

    private ProgressDialog progressDialog;

    private final static int MAX_LEFT_DISTANCE = 255;

    ProgressDialog dialog;

    */
/**
     * Subclase Myhandler que extiende de Handler
     *//*

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (stop == true)
                return;
            switch (msg.what) {

                case CODE_NOPAPER:
                    noPaperDlg();
                    break;

                case CODE_LOWBATTERY:

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(PrinterActivity.this);
                    alertDialog.setTitle(R.string.operation_result);
                    alertDialog.setMessage(getString(R.string.LowBattery));
                    alertDialog.setPositiveButton(getString(R.string.dlg_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog.show();
                    break;

                case CODE_PRINTVERSION:
                    dialog.dismiss();
                    if (msg.obj.equals("1")) {
                        txvPrintVersion.setText(printVersion);
                    } else {
                        Toast.makeText(PrinterActivity.this, R.string.operation_fail, Toast.LENGTH_LONG).show();
                    }
                    break;

                case CODE_PRINTBARCODE:

                    // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.bl_dy),getString(R.string.printing_wait));
                    // printting = CODE_PRINTBARCODE;
                    new barcodePrintThread().start();
                    break;

                case CODE_PRINTQRCODE:
                    // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.D_barcode_loading),getString(R.string.generate_barcode_wait));
                    // printting = CODE_PRINTQRCODE;
                    new qrcodePrintThread().start();
                    break;

                case CODE_PRINTPAPERWALK:
                    // printting = CODE_PRINTPAPERWALK;
                    // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.bl_dy),getString(R.string.printing_wait));
                    new paperWalkPrintThread().start();
                    break;

                case CODE_PRINTCONTENT:
                    // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.bl_dy),getString(R.string.printing_wait));
                    // printting = CODE_PRINTCONTENT;
                    new contentPrintThread().start();
                    break;
                case CODE_MAKER:
                    // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.maker),getString(R.string.printing_wait));
                    // printting = CODE_MAKER;
                    new MakerThread().start();
                    break;

                case CODE_PRINTPICTURE:
                    // progressDialog=ProgressDialog.show(PrinterActivity.this,getString(R.string.bl_dy),getString(R.string.printing_wait));
                    // printting = CODE_PRINTPICTURE;
                    new printPicture().start();
                    break;

                case CODE_CANCELPROMPT:
                    if (progressDialog != null && !PrinterActivity.this.isFinishing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                    break;

                case CODE_EXECUTECOMMAND:
                    new executeCommand().start();
                    break;

                case CODE_OVERHEAT:
                    AlertDialog.Builder overHeatDialog = new AlertDialog.Builder(PrinterActivity.this);
                    overHeatDialog.setTitle(R.string.operation_result);
                    overHeatDialog.setMessage(getString(R.string.overTemp));
                    overHeatDialog.setPositiveButton(getString(R.string.dlg_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    overHeatDialog.show();
                    break;

                default:
                    Toast.makeText(PrinterActivity.this, "Print Error!", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    */
/**
     * Inicializacion de una variable broadcastReceiver con una implementacion de un metodo onReceive
     *//*

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_NOT_CHARGING);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                if (status != BatteryManager.BATTERY_STATUS_CHARGING) {
                    if (Build.MODEL.toUpperCase().equals("TPS390")) {
                        if (level * 10 <= scale) {
                            LowBattery = true;
                        } else {
                            LowBattery = false;
                        }
                    } else {
                        if (level * 5 <= scale) {
                            LowBattery = true;
                        } else {
                            LowBattery = false;
                        }
                    }
                } else {
                    LowBattery = false;
                }
            }
        }
    };


    */
/**
     * Subclase initView
     * Para inicializar los elementos de la pantalla
     *//*

    private void initView() {

        llyPrintText = (LinearLayout) findViewById(R.id.llyPrintText);
        llyPrintCodeAndPic = (LinearLayout) findViewById(R.id.llyPrintCodeAndPic);
        llyPrintComm = (LinearLayout) findViewById(R.id.llyPrintComm);

        txvTextIndex = (TextView) findViewById(R.id.txvTextIndex);
        txvPicIndex = (TextView) findViewById(R.id.txvPicIndex);
        txvCommIndex = (TextView) findViewById(R.id.txvCommIndex);
    }

    */
/**
     * Sobrecarga del metodo del sistema que se usa en la creacion de la vista
     *
     * @param savedInstanceState
     *//*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "ConsoleTestActivity====onCreate");

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.print_text);

        */
/**
         * Llamado al metodo initView
         *//*

        initView();

        */
/**
         * Llamado al metodo savePic
         *//*

        savePic();

        */
/**
         * Llamado al metodo setTitle de la activity
         *//*

        this.setTitle("Serail Port Console");

        */
/**
         * Inicializacion del manejador
         *//*

        handler = new MyHandler();

        linearLayout = (LinearLayout) findViewById(R.id.makerLayout);

        */
/**
         * Valida el modelo del dispositivo en este caso TPS390
         *//*

        if (Build.MODEL.equals("TPS550") ||
                Build.MODEL.equals("TPS580") ||
                Build.MODEL.equals("TPS580C") ||
                Build.MODEL.equals("TPS586") ||
                Build.MODEL.equals("TPS550E") ||
                Build.MODEL.equals("TPS580D") ||
                Build.MODEL.equals("TPS390"))

            linearLayout.setVisibility(View.VISIBLE);

        btnPapercut = (Button) findViewById(R.id.button_papercut);

        if (Build.MODEL.equals("TPS617") || Build.MODEL.equals("rk3288"))

            btnPapercut.setVisibility(View.VISIBLE);

        txvGray = (TextView) findViewById(R.id.textview_gray);

        preferences = getSharedPreferences("logoStorePreferences", Context.MODE_PRIVATE);

        editor = preferences.edit();

        IntentFilter pIntentFilter = new IntentFilter();

        pIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(broadcastReceiver, pIntentFilter);

        */
/**
         * Inicializacion de componentes de interfaz
         *//*

        edtSetLeftDistance = (EditText) findViewById(R.id.set_leftDistance);
        edtSetLineDistance = (EditText) findViewById(R.id.set_lineDistance);
        edtSetWordFont = (EditText) findViewById(R.id.set_wordFont);
        edtSetPrintGray = (EditText) findViewById(R.id.set_printGray);
        edtBarcode = (EditText) findViewById(R.id.set_Barcode);
        edtPaperWalk = (EditText) findViewById(R.id.set_paperWalk);

        edtQrcode = (EditText) findViewById(R.id.set_Qrcode);
        edtMakerDirection = (EditText) findViewById(R.id.edittext_maker_direction);
        edtMakerSearchDistance = (EditText) findViewById(R.id.edittext_maker_search_distance);
        edtMakerWalkDistance = (EditText) findViewById(R.id.edittext_maker_walk_distance);
        edtInputCommand = (EditText) findViewById(R.id.edittext_input_command);

        txvPrintVersion = (TextView) findViewById(R.id.print_version);

        */
/**
         * Coloca el escuchador del click al boton de impresion de codigo QR
         *//*

        btnQrcodePrint = (Button) findViewById(R.id.print_qrcode);
        btnQrcodePrint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String exditText = edtSetPrintGray.getText().toString();
                if (exditText == null || exditText.length() < 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.gray_level) + getString(R.string.lengthNotEnougth), Toast.LENGTH_LONG).show();
                    return;
                }
                printGray = Integer.parseInt(exditText);
                if (printGray < 0 || printGray > 12) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.outOfGray), Toast.LENGTH_LONG).show();
                    return;
                }
                qrcodeStr = edtQrcode.getText().toString();
                if (qrcodeStr == null || qrcodeStr.length() == 0) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.input_print_data), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (LowBattery == true) {
                    handler.sendMessage(handler.obtainMessage(CODE_LOWBATTERY, 1, 0, null));
                } else {
                    if (!nopaper) {
                        setTitle("QRcode Print");
                        progressDialog = ProgressDialog.show(PrinterActivity.this, getString(R.string.D_barcode_loading), getString(R.string.generate_barcode_wait));
                        handler.sendMessage(handler.obtainMessage(CODE_PRINTQRCODE, 1, 0, null));
                    } else {
                        Toast.makeText(PrinterActivity.this, getString(R.string.ptintInit), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        */
/**
         * Coloca el escuchador del click al edit text edtContent
         *//*

        edtContent = (EditText) findViewById(R.id.set_content);
        edtContent.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de impresion de codigo de barras
         *//*

        btnBarcodePrint = (Button) findViewById(R.id.print_barcode);
        btnBarcodePrint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String exditText = edtSetPrintGray.getText().toString();
                if (exditText == null || exditText.length() < 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.gray_level) + getString(R.string.lengthNotEnougth), Toast.LENGTH_LONG).show();
                    return;
                }
                printGray = Integer.parseInt(exditText);
                if (printGray < 0 || printGray > 12) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.outOfGray), Toast.LENGTH_LONG).show();
                    return;
                }
                barcodeStr = edtBarcode.getText().toString();
                if (barcodeStr == null || barcodeStr.length() == 0) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.empty), Toast.LENGTH_LONG).show();
                    return;
                } else if (barcodeStr.length() < 11) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.lengthNotEnougth), Toast.LENGTH_LONG).show();
                    return;
                }
                if (LowBattery == true) {
                    handler.sendMessage(handler.obtainMessage(CODE_LOWBATTERY, 1, 0, null));
                } else {

                    if (!nopaper) {
                        setTitle("Barcode Print");
                        progressDialog = ProgressDialog.show(PrinterActivity.this, getString(R.string.bl_dy), getString(R.string.printing_wait));
                        handler.sendMessage(handler.obtainMessage(CODE_PRINTBARCODE, 1, 0, null));
                    } else {
                        Toast.makeText(PrinterActivity.this, getString(R.string.ptintInit), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de papel o algo
         *//*

        btnPaperWalkPrint = (Button) findViewById(R.id.print_paperWalk);
        btnPaperWalkPrint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String exditText;
                exditText = edtPaperWalk.getText().toString();
                if (exditText == null || exditText.length() == 0) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.empty), Toast.LENGTH_LONG).show();
                    return;
                }
                if (Integer.parseInt(exditText) < 1 || Integer.parseInt(exditText) > 255) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.walk_paper_intput_value), Toast.LENGTH_LONG).show();
                    return;
                }
                paperWalk = Integer.parseInt(exditText);
                if (LowBattery == true) {
                    handler.sendMessage(handler.obtainMessage(CODE_LOWBATTERY, 1, 0, null));
                } else {
                    if (!nopaper) {
                        setTitle("print character");
                        progressDialog = ProgressDialog.show(PrinterActivity.this, getString(R.string.bl_dy), getString(R.string.printing_wait));
                        handler.sendMessage(handler.obtainMessage(CODE_PRINTPAPERWALK, 1, 0, null));
                    } else {
                        Toast.makeText(PrinterActivity.this, getString(R.string.ptintInit), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de limpieza de texto
         *//*

        btnClearText = (Button) findViewById(R.id.clearText);
        btnClearText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                edtContent.setText("");
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de obtencion de texto de ejemplo
         *//*

        btnGetExampleText = (Button) findViewById(R.id.getPrintExample);
        btnGetExampleText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "Print Test:\n" + "Device Base Information\n" + "-----------------------------\n" + "Printer Version:\n" + "  V05.2.0.3\n" + "Printer Gray: 3\n" + "Soft Version:\n"
                        + "  TPDemo.G50.0.Build140313\n" + "Battery Level: 100%\n" + "CSQ Value: 24\n" + "IMEI:86378902177527" + "\n"
                        + getString(R.string.PrintTemp1)
                        + "\n"
                        + getString(R.string.PrintTemp2)
                        + "\n"
                        + "\n\n"
                        + "Device Base Information\n"
                        + "--------------0---------------\n"
                        + "Printer Version:\n"
                        + "  V05.2.0.3\n"
                        + "Printer Gray: 3\n"
                        + "Soft Version:\n"
                        + "  TPDemo.G50.0.Build140313\n"
                        + "Battery Level: 100%\n"
                        + "CSQ Value: 24\n"
                        + "IMEI:86378902177527"
                        + "\n"
                        + getString(R.string.PrintTemp1)
                        + "\n"
                        + getString(R.string.PrintTemp2)
                        + "\n"
                        + "Device Base Information\n"
                        + "--------------1---------------\n"
                        + "Printer Version:\n"
                        + "  V05.2.0.3\n"
                        + "Printer Gray: 3\n"
                        + "Soft Version:\n"
                        + "  TPDemo.G50.0.Build140313\n"
                        + "Battery Level: 100%\n"
                        + "CSQ Value: 24\n"
                        + "IMEI:86378902177527"
                        + "\n"
                        + getString(R.string.PrintTemp1)
                        + "\n"
                        + getString(R.string.PrintTemp2)
                        + "\n"
                        + "\n\n"
                        + "Device Base Information\n"
                        + "--------------2---------------\n"
                        + "Printer Version:\n"
                        + "  V05.2.0.3\n"
                        + "Printer Gray: 3\n"
                        + "Soft Version:\n"
                        + "  TPDemo.G50.0.Build140313\n"
                        + "Battery Level: 100%\n"
                        + "CSQ Value: 24\n"
                        + "IMEI:86378902177527"
                        + "\n"
                        + getString(R.string.PrintTemp1)
                        + "\n"
                        + getString(R.string.PrintTemp2) + "\n" + "Device Base Information\n" + "--------------3---------------\n";
                edtContent.setText(str);
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de maker???
         *//*

        btnMaker = (Button) findViewById(R.id.button_maker);
        btnMaker.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (edtMakerDirection.getText().length() == 0 || edtMakerSearchDistance.getText().length() == 0 || edtMakerWalkDistance.getText().length() == 0) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.maker_error), Toast.LENGTH_LONG).show();
                    return;
                }
                if (Integer.parseInt(edtMakerDirection.getText().toString()) < 0 || Integer.parseInt(edtMakerDirection.getText().toString()) > 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.maker_error), Toast.LENGTH_LONG).show();
                    return;
                }
                if (Integer.parseInt(edtMakerSearchDistance.getText().toString()) < 0 || Integer.parseInt(edtMakerSearchDistance.getText().toString()) > 255) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.maker_error), Toast.LENGTH_LONG).show();
                    return;
                }
                if (Integer.parseInt(edtMakerWalkDistance.getText().toString()) < 0 || Integer.parseInt(edtMakerWalkDistance.getText().toString()) > 255) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.maker_error), Toast.LENGTH_LONG).show();
                    return;
                }
                if (LowBattery == true) {
                    handler.sendMessage(handler.obtainMessage(CODE_LOWBATTERY, 1, 0, null));
                } else {
                    if (!nopaper) {
                        setTitle("maker");
                        progressDialog = ProgressDialog.show(PrinterActivity.this, getString(R.string.maker), getString(R.string.printing_wait));
                        handler.sendMessage(handler.obtainMessage(CODE_MAKER, 1, 0, null));
                    } else {
                        Toast.makeText(PrinterActivity.this, getString(R.string.ptintInit), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de cortar papel
         *//*

        btnPapercut.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ThermalPrinter.start();
                            ThermalPrinter.reset();
                            ThermalPrinter.paperCut();
                            ThermalPrinter.stop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de imprimir grafica
         *//*

        btnPrintPicture = (Button) findViewById(R.id.button_print_picture);
        btnPrintPicture.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String exditText = edtSetPrintGray.getText().toString();
                if (exditText == null || exditText.length() < 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.gray_level) + getString(R.string.lengthNotEnougth), Toast.LENGTH_LONG).show();
                    return;
                }
                printGray = Integer.parseInt(exditText);
                if (printGray < 0 || printGray > 12) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.outOfGray), Toast.LENGTH_LONG).show();
                    return;
                }
                if (LowBattery == true) {
                    handler.sendMessage(handler.obtainMessage(CODE_LOWBATTERY, 1, 0, null));
                } else {
                    if (!nopaper) {
                        setTitle("print picture");
                        progressDialog = ProgressDialog.show(PrinterActivity.this, getString(R.string.bl_dy), getString(R.string.printing_wait));
                        handler.sendMessage(handler.obtainMessage(CODE_PRINTPICTURE, 1, 0, null));
                    } else {
                        Toast.makeText(PrinterActivity.this, getString(R.string.ptintInit), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de ejecutar comando
         *//*

        btnExecuteCommand = (Button) findViewById(R.id.button_execute_command);
        btnExecuteCommand.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (edtInputCommand.getText().toString() == null || edtInputCommand.getText().toString().length() == 0) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.empty), Toast.LENGTH_LONG).show();
                    return;
                }
                if (LowBattery == true) {
                    handler.sendMessage(handler.obtainMessage(CODE_LOWBATTERY, 1, 0, null));
                } else {
                    if (!nopaper) {
                        setTitle("Execute Command");
                        progressDialog = ProgressDialog.show(PrinterActivity.this, getString(R.string.bl_dy), getString(R.string.printing_wait));
                        handler.sendMessage(handler.obtainMessage(CODE_EXECUTECOMMAND, 1, 0, null));
                    } else {
                        Toast.makeText(PrinterActivity.this, getString(R.string.ptintInit), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        */
/**
         * Coloca el escuchador del click al boton de impresion de contenido
         *//*

        btnContentPrint = (Button) findViewById(R.id.print_content);
        btnContentPrint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String exditText;

                exditText = edtSetLeftDistance.getText().toString();
                if (exditText == null || exditText.length() < 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.left_margin) + getString(R.string.lengthNotEnougth), Toast.LENGTH_LONG).show();
                    return;
                }
                leftDistance = Integer.parseInt(exditText);
                exditText = edtSetLineDistance.getText().toString();
                if (exditText == null || exditText.length() < 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.row_space) + getString(R.string.lengthNotEnougth), Toast.LENGTH_LONG).show();
                    return;
                }

                lineDistance = Integer.parseInt(exditText);
                printContent = edtContent.getText().toString();
                exditText = edtSetWordFont.getText().toString();
                if (exditText == null || exditText.length() < 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.font_size) + getString(R.string.lengthNotEnougth), Toast.LENGTH_LONG).show();
                    return;
                }

                wordFont = Integer.parseInt(exditText);
                exditText = edtSetPrintGray.getText().toString();
                if (exditText == null || exditText.length() < 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.gray_level) + getString(R.string.lengthNotEnougth), Toast.LENGTH_LONG).show();
                    return;
                }

                printGray = Integer.parseInt(exditText);
                if (leftDistance > MAX_LEFT_DISTANCE) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.outOfLeft), Toast.LENGTH_LONG).show();
                    return;
                } else if (lineDistance > 255) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.outOfLine), Toast.LENGTH_LONG).show();
                    return;
                } else if (wordFont > 4 || wordFont < 1) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.outOfFont), Toast.LENGTH_LONG).show();
                    return;
                } else if (printGray < 0 || printGray > 12) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.outOfGray), Toast.LENGTH_LONG).show();
                    return;
                }
                if (printContent == null || printContent.length() == 0) {
                    Toast.makeText(PrinterActivity.this, getString(R.string.empty), Toast.LENGTH_LONG).show();
                    return;
                }
                if (LowBattery == true) {
                    handler.sendMessage(handler.obtainMessage(CODE_LOWBATTERY, 1, 0, null));
                } else {
                    if (!nopaper) {
                        setTitle("print character");
                        progressDialog = ProgressDialog.show(PrinterActivity.this, getString(R.string.bl_dy), getString(R.string.printing_wait));
                        handler.sendMessage(handler.obtainMessage(CODE_PRINTCONTENT, 1, 0, null));
                    } else {
                        Toast.makeText(PrinterActivity.this, getString(R.string.ptintInit), Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        */
/**
         * Inicializa el cuadro de progreso
         *//*

        dialog = new ProgressDialog(PrinterActivity.this);
        dialog.setTitle(R.string.idcard_czz);
        dialog.setMessage(getText(R.string.watting));
        dialog.setCancelable(false);
        dialog.show();

        */
/**
         * Inicia la ejecucion de algo
         *//*

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ThermalPrinter.start();
                    ThermalPrinter.reset();
                    printVersion = ThermalPrinter.getVersion();
                } catch (TelpoException e) {
                    e.printStackTrace();
                } finally {
                    if (printVersion != null) {
                        Message message = new Message();
                        message.what = CODE_PRINTVERSION;
                        message.obj = "1";
                        handler.sendMessage(message);
                    } else {
                        Message message = new Message();
                        message.what = CODE_PRINTVERSION;
                        message.obj = "0";
                        handler.sendMessage(message);
                    }
                    ThermalPrinter.stop();
                }
            }
        }).start();

    }

    */
/**
     * Metodo sobrecargado de la aplicacion para cuando resume la app
     *//*

    @Override
    protected void onResume() {

        super.onResume();

    }

    */
/**
     * Metodo para dialogo de no papel
     *//*

    private void noPaperDlg() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(PrinterActivity.this);
        dlg.setTitle(getString(R.string.noPaper));
        dlg.setMessage(getString(R.string.noPaperNotice));
        dlg.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // if(!nopaper) {
                // handler.sendMessage(handler.obtainMessage(CODE_PRINTIT, 1, 0,
                // null));
                // }else{
                // Toast.makeText(PrinterActivity.this,getString(R.string.ptintInit),Toast.LENGTH_LONG).show();
                // handler.sendMessage(handler.obtainMessage(CODE_ENABLE_BUTTON, 1,
                // 0, null));
                // }
            }
        });
        // dlg.setNegativeButton(R.string.dialog_cancel, new
        // DialogInterface.OnClickListener() {
        // @Override
        // public void onClick(DialogInterface dialogInterface, int i) {
        // handler.sendMessage(handler.obtainMessage(CODE_ENABLE_BUTTON, 1, 0,
        // null));
        // }
        // });
        dlg.show();
    }

    */
/**
     * Metodo extendido de un hilo q hace algo
     *//*

    private class paperWalkPrintThread extends Thread {
        @Override
        public void run() {
            super.run();
            setName("paper walk Thread");
            try {
                ThermalPrinter.start();
                ThermalPrinter.reset();
                ThermalPrinter.walkPaper(paperWalk);
            } catch (Exception e) {
                e.printStackTrace();
                Result = e.toString();
                if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
                    nopaper = true;
                    // return;
                } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
                    handler.sendMessage(handler.obtainMessage(CODE_OVERHEAT, 1, 0, null));
                } else {
                    handler.sendMessage(handler.obtainMessage(CODE_PRINTERR, 1, 0, null));
                }
            } finally {
                handler.sendMessage(handler.obtainMessage(CODE_CANCELPROMPT, 1, 0, null));
                if (nopaper)
                    handler.sendMessage(handler.obtainMessage(CODE_NOPAPER, 1, 0, null));
                ThermalPrinter.stop();
                nopaper = false;
                // PrinterActivity.this.sleep(1500);
                // if(progressDialog != null &&
                // !PrinterActivity.this.isFinishing() ){
                // progressDialog.dismiss();
                // progressDialog = null;
                // }
                Log.v(TAG, "The Print Progress End !!!");
                if (isClose) {
                    // onDestroy();
                    finish();
                }
            }
            // handler.sendMessage(handler
            // .obtainMessage(CODE_ENABLE_BUTTON, 1, 0, null));
        }
    }

    */
/**
     * Metodo extendido de un hilo q hace algo
     *//*

    private class barcodePrintThread extends Thread {
        @Override
        public void run() {
            super.run();
            setName("Barcode Print Thread");
            try {
                ThermalPrinter.start();
                ThermalPrinter.reset();
                ThermalPrinter.setGray(printGray);
                ThermalPrinter.printBarcode(barcodeStr);
                ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                ThermalPrinter.addString(barcodeStr);
                ThermalPrinter.printString();
                ThermalPrinter.walkPaper(100);
            } catch (Exception e) {
                e.printStackTrace();
                Result = e.toString();
                if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
                    nopaper = true;
                    // return;
                } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
                    handler.sendMessage(handler.obtainMessage(CODE_OVERHEAT, 1, 0, null));
                } else {
                    handler.sendMessage(handler.obtainMessage(CODE_PRINTERR, 1, 0, null));
                }
            } finally {
                handler.sendMessage(handler.obtainMessage(CODE_CANCELPROMPT, 1, 0, null));
                if (nopaper)
                    handler.sendMessage(handler.obtainMessage(CODE_NOPAPER, 1, 0, null));
                ThermalPrinter.stop();
                nopaper = false;
                // PrinterActivity.this.sleep(1500);
                // if(progressDialog != null &&
                // !PrinterActivity.this.isFinishing() ){
                // progressDialog.dismiss();
                // progressDialog = null;
                // }
                Log.v(TAG, "The Print Progress End !!!");
                if (isClose) {
                    finish();
                }
            }
            // handler.sendMessage(handler
            // .obtainMessage(CODE_ENABLE_BUTTON, 1, 0, null));
        }
    }

    */
/**
     * Metodo extendido de un hilo q hace algo
     *//*

    private class qrcodePrintThread extends Thread {
        @Override
        public void run() {
            super.run();
            setName("Barcode Print Thread");
            try {
                ThermalPrinter.start();
                ThermalPrinter.reset();
                ThermalPrinter.setGray(printGray);
                printQrcode(qrcodeStr);
                ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                ThermalPrinter.addString(qrcodeStr);
                ThermalPrinter.printString();
                ThermalPrinter.walkPaper(100);
            } catch (Exception e) {
                e.printStackTrace();
                Result = e.toString();
                if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
                    nopaper = true;
                    // return;
                } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
                    handler.sendMessage(handler.obtainMessage(CODE_OVERHEAT, 1, 0, null));
                } else {
                    handler.sendMessage(handler.obtainMessage(CODE_PRINTERR, 1, 0, null));
                }
            } finally {
                // lock.release();
                handler.sendMessage(handler.obtainMessage(CODE_CANCELPROMPT, 1, 0, null));
                if (nopaper)
                    handler.sendMessage(handler.obtainMessage(CODE_NOPAPER, 1, 0, null));
                ThermalPrinter.stop();
                nopaper = false;
                // PrinterActivity.this.sleep(1500);
                // if(progressDialog != null &&
                // !PrinterActivity.this.isFinishing() ){
                // progressDialog.dismiss();
                // progressDialog = null;
                // }
                Log.v(TAG, "The Print Progress End !!!");
                if (isClose) {
                    finish();
                }
            }
            // handler.sendMessage(handler
            // .obtainMessage(CODE_ENABLE_BUTTON, 1, 0, null));
        }
    }

    */
/**
     * Metodo extendido de un hilo q hace algo
     * - al parecer imprime contenido
     * - este es el q importa
     *//*

    private class contentPrintThread extends Thread {
        @Override
        public void run() {
            super.run();
            setName("Content Print Thread");
            try {
                ThermalPrinter.start();
                ThermalPrinter.reset();
                ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_LEFT);
                ThermalPrinter.setLeftIndent(leftDistance);
                ThermalPrinter.setLineSpace(lineDistance);
                if (wordFont == 4) {
                    ThermalPrinter.setFontSize(2);
                    ThermalPrinter.enlargeFontSize(2, 2);
                } else if (wordFont == 3) {
                    ThermalPrinter.setFontSize(1);
                    ThermalPrinter.enlargeFontSize(2, 2);
                } else if (wordFont == 2) {
                    ThermalPrinter.setFontSize(2);
                } else if (wordFont == 1) {
                    ThermalPrinter.setFontSize(1);
                }
                ThermalPrinter.setGray(printGray);
                ThermalPrinter.addString(printContent);
                ThermalPrinter.printString();
                ThermalPrinter.clearString();
                ThermalPrinter.walkPaper(100);
            } catch (Exception e) {
                e.printStackTrace();
                Result = e.toString();
                if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
                    nopaper = true;
                    // return;
                } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
                    handler.sendMessage(handler.obtainMessage(CODE_OVERHEAT, 1, 0, null));
                } else {
                    handler.sendMessage(handler.obtainMessage(CODE_PRINTERR, 1, 0, null));
                }
            } finally {
                // lock.release();
                handler.sendMessage(handler.obtainMessage(CODE_CANCELPROMPT, 1, 0, null));
                if (nopaper)
                    handler.sendMessage(handler.obtainMessage(CODE_NOPAPER, 1, 0, null));
                ThermalPrinter.stop();
                nopaper = false;
                Log.v(TAG, "The Print Progress End !!!");
                if (isClose) {
                    // onDestroy();
                    finish();
                }
            }
        }
    }

    */
/**
     * Metodo extendido de un hilo q hace algo
     *//*

    private class MakerThread extends Thread {

        @Override
        public void run() {
            super.run();
            setName("Maker Thread");
            try {
                ThermalPrinter.start();
                ThermalPrinter.reset();
                ThermalPrinter.searchMark(Integer.parseInt(edtMakerDirection.getText().toString()), Integer.parseInt(edtMakerSearchDistance.getText().toString()),
                        Integer.parseInt(edtMakerWalkDistance.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
                Result = e.toString();
                if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
                    nopaper = true;
                    // return;
                } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
                    handler.sendMessage(handler.obtainMessage(CODE_OVERHEAT, 1, 0, null));
                } else {
                    handler.sendMessage(handler.obtainMessage(CODE_PRINTERR, 1, 0, null));
                }
            } finally {
                handler.sendMessage(handler.obtainMessage(CODE_CANCELPROMPT, 1, 0, null));
                if (nopaper)
                    handler.sendMessage(handler.obtainMessage(CODE_NOPAPER, 1, 0, null));
                ThermalPrinter.stop();
                nopaper = false;
                // PrinterActivity.this.sleep(1500);
                // if(progressDialog != null &&
                // !PrinterActivity.this.isFinishing() ){
                // progressDialog.dismiss();
                // progressDialog = null;
                // }
                Log.v(TAG, "The Maker Progress End !!!");
                if (isClose) {
                    finish();
                }
            }
            // handler.sendMessage(handler
            // .obtainMessage(CODE_ENABLE_BUTTON, 1, 0, null));
        }
    }

    */
/**
     * Metodo extendido de un hilo q hace algo
     * - al parecer imprime una figura
     * - este tambn importa
     *//*

    private class printPicture extends Thread {

        @Override
        public void run() {
            super.run();
            setName("PrintPicture Thread");
            try {
                ThermalPrinter.start();
                ThermalPrinter.reset();
                ThermalPrinter.setGray(printGray);
                ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                File file = new File("/mnt/sdcard/111.bmp");
                if (file.exists()) {
                    ThermalPrinter.printLogo(BitmapFactory.decodeFile("/mnt/sdcard/111.bmp"));
                    ThermalPrinter.walkPaper(100);
                } else {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(PrinterActivity.this, getString(R.string.not_find_picture), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Result = e.toString();
                if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
                    nopaper = true;
                    // return;
                } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
                    handler.sendMessage(handler.obtainMessage(CODE_OVERHEAT, 1, 0, null));
                } else {
                    handler.sendMessage(handler.obtainMessage(CODE_PRINTERR, 1, 0, null));
                }
            } finally {
                handler.sendMessage(handler.obtainMessage(CODE_CANCELPROMPT, 1, 0, null));
                if (nopaper)
                    handler.sendMessage(handler.obtainMessage(CODE_NOPAPER, 1, 0, null));
                ThermalPrinter.stop();
                nopaper = false;
                // PrinterActivity.this.sleep(1500);
                // if(progressDialog != null &&
                // !PrinterActivity.this.isFinishing() ){
                // progressDialog.dismiss();
                // progressDialog = null;
                // }
                Log.v(TAG, "The PrintPicture Progress End !!!");
                if (isClose) {
                    finish();
                }
            }
            // handler.sendMessage(handler
            // .obtainMessage(CODE_ENABLE_BUTTON, 1, 0, null));
        }
    }

    */
/**
     * Metodo extendido de un hilo q hace algo
     *//*

    private class executeCommand extends Thread {

        @Override
        public void run() {
            super.run();
            setName("ExecuteCommand Thread");
            try {
                ThermalPrinter.start();
                ThermalPrinter.reset();
                ThermalPrinter.sendCommand(edtInputCommand.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                Result = e.toString();
                if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
                    nopaper = true;
                    // return;
                } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
                    handler.sendMessage(handler.obtainMessage(CODE_OVERHEAT, 1, 0, null));
                } else {
                    handler.sendMessage(handler.obtainMessage(CODE_PRINTERR, 1, 0, null));
                }
            } finally {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(handler.obtainMessage(CODE_CANCELPROMPT, 1, 0, null));
                if (nopaper)
                    handler.sendMessage(handler.obtainMessage(CODE_NOPAPER, 1, 0, null));
                ThermalPrinter.stop();
                nopaper = false;
                // PrinterActivity.this.sleep(1500);
                // if(progressDialog != null &&
                // !PrinterActivity.this.isFinishing() ){
                // progressDialog.dismiss();
                // progressDialog = null;
                // }
                Log.v(TAG, "The ExecuteCommand Progress End !!!");
                if (isClose) {
                    finish();
                }
            }
        }

    }

    // private void sleep(int ms) {
    //
    // try {
    // Thread.sleep(ms);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    @Override
    protected void onDestroy() {
        if (progressDialog != null && !PrinterActivity.this.isFinishing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        stop = true;
        unregisterReceiver(broadcastReceiver);
        ThermalPrinter.stop();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    */
/**
     * Como que crea un codigo o algo
     *
     * @param str
     * @param type
     * @param bmpWidth
     * @param bmpHeight
     * @return
     * @throws WriterException
     *//*

    public Bitmap CreateCode(String str, com.google.zxing.BarcodeFormat type, int bmpWidth, int bmpHeight) throws WriterException {
        // 生成二维矩阵,编码时要指定大小,不要生成了图片以后再进行缩放,以防模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(str, type, bmpWidth, bmpHeight);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        // 二维矩阵转为一维像素数组（一直横着排）
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                } else {
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    */
/**
     * Function printBarcode
     *
     * @return None
     * @author zhouzy
     * @date 20141223
     * @note
     *//*

    private void printQrcode(String str) throws Exception {
        // Bitmap bitmap=
        // BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/time1.bmp"));
        Bitmap bitmap = CreateCode(str, BarcodeFormat.QR_CODE, 256, 256);
        if (bitmap != null) {
            Log.v(TAG, "Find the Bmp");
            ThermalPrinter.printLogo(bitmap);
        }
    }

    */
/**
     * Metodo misterioso
     *
     * @param view
     *//*

    public void selectIndex(View view) {
        switch (view.getId()) {
            case R.id.txvTextIndex:
                txvTextIndex.setEnabled(false);
                txvPicIndex.setEnabled(true);
                txvCommIndex.setEnabled(true);
                llyPrintText.setVisibility(View.VISIBLE);
                llyPrintCodeAndPic.setVisibility(View.GONE);
                llyPrintComm.setVisibility(View.GONE);

                break;

            case R.id.txvPicIndex:

                txvTextIndex.setEnabled(true);
                txvPicIndex.setEnabled(false);
                txvCommIndex.setEnabled(true);
                llyPrintText.setVisibility(View.GONE);
                llyPrintCodeAndPic.setVisibility(View.VISIBLE);
                llyPrintComm.setVisibility(View.GONE);
                break;
            case R.id.txvCommIndex:
                txvTextIndex.setEnabled(true);
                txvPicIndex.setEnabled(true);
                txvCommIndex.setEnabled(false);
                llyPrintText.setVisibility(View.GONE);
                llyPrintCodeAndPic.setVisibility(View.GONE);
                llyPrintComm.setVisibility(View.VISIBLE);

                break;
        }
    }

    */
/**
     * Metodo que guarda la imagen usada para la impresion
     *//*

    private void savePic() {
        File file = new File("/mnt/sdcard/111.bmp");
        if (!file.exists()) {
            InputStream inputStream = null;
            FileOutputStream fos = null;
            byte[] tmp = new byte[1024];
            try {

                inputStream = getApplicationContext().getAssets().open("syhlogo.png");
                fos = new FileOutputStream(file);

                while (inputStream.read(tmp) > 0) {
                    fos.write(tmp);
                }
                fos.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
*/

}
