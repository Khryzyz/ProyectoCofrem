package com.cofrem.transacciones.global;

public class InfoGlobalSettingsPrint {

    //estados de la impresora

    public final static int PRINTER_DISCONNECT = -1004;
    public final static int PRINTER_OUT_OF_PAPER = -1001;
    public final static int PRINTER_OVER_FLOW = -1003;
    public final static int PRINTER_OVER_HEAT = -1002;
    public final static int PRINTER_ERROR = -9999;
    public final static int PRINTER_OK = 0;

    //0 - 255
    public final static int LEFT_DISTANCE = 1;

    //0 - 255
    public final static int LINE_DISTANCE = 1;

    //1 - 4
    public final static int FONT_SIZE = 2;
    public final static int FONT_SIZE_1 = 1;
    public final static int FONT_SIZE_2 = 2;
    public final static int FONT_SIZE_3 = 3;
    public final static int FONT_SIZE_4 = 4;


    //0 - 12
    public final static int GRAY_LEVEL = 12;

    /**
     * Alguna especie de codigos???
     */

    public final static int CODE_PRINTIT = 1;
    public final static int CODE_ENABLE_BUTTON = 2;
    public final static int CODE_NOPAPER = 3;
    public final static int CODE_LOWBATTERY = 4;
    public final static int CODE_PRINTVERSION = 5;
    public final static int CODE_PRINTBARCODE = 6;
    public final static int CODE_PRINTQRCODE = 7;
    public final static int CODE_PRINTPAPERWALK = 8;
    public final static int CODE_PRINTCONTENT = 9;
    public final static int CODE_CANCELPROMPT = 10;
    public final static int CODE_PRINTERR = 11;
    public final static int CODE_OVERHEAT = 12;
    public final static int CODE_MAKER = 13;
    public final static int CODE_PRINTPICTURE = 14;
    public final static int CODE_EXECUTECOMMAND = 15;
    public final static int CODE_PRINTCERIBO = 16;

    public static final int ALGIN_LEFT = 0;
    public static final int ALGIN_MIDDLE = 1;
    public static final int ALGIN_RIGHT = 2;


}
