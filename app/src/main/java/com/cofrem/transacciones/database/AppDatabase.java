package com.cofrem.transacciones.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cofrem.transacciones.models.Transaccion;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase que maneja:
 * - DML de la base de datos
 * - instancia general de acceso a datos
 */
public final class AppDatabase extends SQLiteOpenHelper {

    //Instancia singleton
    private static AppDatabase singleton;

    /**
     * Constructor de la clase
     * Crea la base de datos si no existe
     *
     * @param context
     */
    private AppDatabase(Context context) {
        super(context,
                DatabaseManager.DatabaseApp.DATABASE_NAME,
                null,
                DatabaseManager.DatabaseApp.DATABASE_VERSION);
    }

    /**
     * Retorna la instancia unica del singleton
     *
     * @param context contexto donde se ejecutarán las peticiones
     * @return Instancia
     */
    public static synchronized AppDatabase getInstance(Context context) {

        if (singleton == null) {
            singleton = new AppDatabase(context.getApplicationContext());
        }
        return singleton;
    }

    /**
     * Metodo ejecutado en el evento de la instalacion de la aplicacion
     * Crea las tablas necesarias para el funcionamiento de la aplicacion
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatabaseManager.TableProducto.CREATE_TABLE_PRODUCTO);
        db.execSQL(DatabaseManager.TableTransacciones.CREATE_TABLE_TRANSACCIONES);
        db.execSQL(DatabaseManager.TableEstablecimiento.CREATE_TABLE_ESTABLECIMIENTO);
        db.execSQL(DatabaseManager.TableConfiguracionConexion.CREATE_TABLE_CONFIGURACION_CONEXION);
        db.execSQL(DatabaseManager.TableConfiguracionAcceso.CREATE_TABLE_CONFIGURACION_ACCESO);

    }

    /**
     * Metodo ejecutado en la actualizacion de la aplicacion
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // Drop todas las tablas 
        db.execSQL(DatabaseManager.TableProducto.DROP_TABLE_PRODUCTO);
        db.execSQL(DatabaseManager.TableTransacciones.DROP_TABLE_TRANSACCIONES);
        db.execSQL(DatabaseManager.TableEstablecimiento.DROP_TABLE_ESTABLECIMIENTO);
        db.execSQL(DatabaseManager.TableConfiguracionConexion.DROP_TABLE_CONFIGURACION_CONEXION);
        db.execSQL(DatabaseManager.TableConfiguracionAcceso.DROP_TABLE_CONFIGURACION_ACCESO);

        onCreate(db);
    }

    /**
     * Metodo para insertar registro inicial en la Base de Datos de la clave de acceso a dispositivo
     */
    public boolean insertConfiguracionAcceso() {

        // Inicializacion de la variable de contenidos del registro
        ContentValues contentValues = new ContentValues();

        // Almacena los valores a insertar
        contentValues.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_VALOR, 8717);
        contentValues.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_REGISTRO, getDateTime());
        contentValues.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_REGISTRO, 1);

        // Insercion del registro en la base de datos
        Long count = getWritableDatabase().insert(
                DatabaseManager.TableConfiguracionAcceso.TABLE_NAME_CONFIGURACION_ACCESO,
                null,
                contentValues
        );
        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Metodo para validar si existe registro inicial en la Base de Datos de la clave de acceso a dispositivo
     */
    public int conteoConfiguracionAcceso() {

        int count;

        Cursor cursorQuery;

        cursorQuery = getWritableDatabase().rawQuery(
                "SELECT COUNT(1) FROM " +
                        DatabaseManager.TableConfiguracionAcceso.TABLE_NAME_CONFIGURACION_ACCESO,
                null
        );

        cursorQuery.moveToFirst();

        count = cursorQuery.getInt(0);

        return count;

    }

    /**
     * Metodo para validar si existe registro inicial en la Base de Datos de la clave de acceso a dispositivo
     */
    public int conteoConfiguracionAccesoByValorAcceso(int valorAcceso) {

        int count;

        Cursor cursorQuery;

        cursorQuery = getWritableDatabase().rawQuery(
                "SELECT COUNT(1) FROM " +
                        DatabaseManager.TableConfiguracionAcceso.TABLE_NAME_CONFIGURACION_ACCESO +
                        " WHERE " + DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_VALOR + " = '" + valorAcceso + "'",
                null
        );

        cursorQuery.moveToFirst();

        count = cursorQuery.getInt(0);

        return count;

    }

    /**
     * Metodo para insertar registro inicial en la Base de Datos
     */
    public boolean insertRegistroInicialProductos() {

        // Inicializacion de la variable de contenidos del registro
        ContentValues contentValues = new ContentValues();

        // Almacena los valores a insertar
        contentValues.put(DatabaseManager.TableProducto.COLUMN_PRODUCTO_NOMBRE, "CREDITO ROTATIVO");
        contentValues.put(DatabaseManager.TableProducto.COLUMN_PRODUCTO_DESCRIPCION, "Credito rotativo");
        contentValues.put(DatabaseManager.TableProducto.COLUMN_PRODUCTO_REGISTRO, "time('now')");
        contentValues.put(DatabaseManager.TableProducto.COLUMN_PRODUCTO_ESTADO, 1);

        // Insercion del registro en la base de datos
        int count = (int) getWritableDatabase().insert(
                DatabaseManager.TableProducto.TABLE_NAME_PRODUCTO,
                null,
                contentValues
        );
        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Metodo para insertar registro inicial en la Base de Datos
     */
    //TODO: METODO DE PRUEBA HAY QUE BORRAR ESTA VUELTA
    public boolean insertRegistroPruebaTransaction(int producto_id) {

        // Inicializacion de la variable de contenidos del registro
        ContentValues contentValues = new ContentValues();

        // Almacena los valores a insertar
        contentValues.put(DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_PRODUCTO_ID, producto_id);
        contentValues.put(DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_NUMERO_CARGO, 25639687);
        contentValues.put(DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_NUMERO_TARJETA, "256389562154");
        contentValues.put(DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_VALOR, 140000);
        contentValues.put(DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_REGISTRO, getDateTime());
        contentValues.put(DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_ESTADO, 1);

        // Insercion del registro en la base de datos
        int count = (int) getWritableDatabase().insert(
                DatabaseManager.TableTransacciones.TABLE_NAME_TRANSACCIONES,
                null,
                contentValues
        );
        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Metodo para Obtener todos los registros de la tabla base_financiera
     *
     * @return cursor con los registros
     */
    public int obtenerProductoIdByNombre(String NOMBRE_PRODUCTO) {

        int producto_id;

        Cursor queryIdProducto;

        queryIdProducto = getWritableDatabase().rawQuery(
                "SELECT " + DatabaseManager.TableProducto.COLUMN_PRODUCTO_ID + " AS ID " +
                        " FROM " + DatabaseManager.TableProducto.TABLE_NAME_PRODUCTO +
                        " WHERE " + DatabaseManager.TableProducto.COLUMN_PRODUCTO_NOMBRE + " = '" + NOMBRE_PRODUCTO + "'",
                null
        );

        queryIdProducto.moveToFirst();

        producto_id = queryIdProducto.getInt(0);

        return producto_id;
    }

    /**
     * Metodo para Obtener el conteo de los registros de la tabla base_financiera
     *
     * @return conteo de los registros
     */
    public int obtenerConteoRegistroProductos() {

        int count;

        Cursor cursorQuery;

        cursorQuery = getWritableDatabase().rawQuery(
                "SELECT COUNT(1) FROM " +
                        DatabaseManager.TableProducto.TABLE_NAME_PRODUCTO,
                null
        );

        cursorQuery.moveToFirst();

        count = cursorQuery.getInt(0);

        return count;
    }

    /**
     * Metodo para Obtener el conteo de los registros de la tabla ConfiguracionConexion
     *
     * @return conteo de los registros
     */
    public int conteoConfiguracionConexion() {

        int count;

        Cursor cursorQuery;

        cursorQuery = getWritableDatabase().rawQuery(
                "SELECT COUNT(1) FROM " +
                        DatabaseManager.TableConfiguracionConexion.TABLE_NAME_CONFIGURACION_CONEXION,
                null
        );

        cursorQuery.moveToFirst();

        count = cursorQuery.getInt(0);

        return count;
    }

    /**
     * Metodo para Obtener ultima  transaccion
     */
    public Transaccion obtenerUltimaTransaccion() {

        Transaccion modelTransaccion = new Transaccion();

        Cursor cursorQuery;

        Cursor cursor;

        cursor = getWritableDatabase().rawQuery(
                "SELECT * FROM " + DatabaseManager.TableTransacciones.TABLE_NAME_TRANSACCIONES + " ORDER BY " + DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_REGISTRO + " ASC LIMIT 1", null
        );

        if(cursor.moveToFirst()){
            modelTransaccion.setId(cursor.getInt(0));
            modelTransaccion.setProducto_id(cursor.getInt(1));
            modelTransaccion.setNumero_cargo(cursor.getInt(2));
            modelTransaccion.setNumero_tarjeta(cursor.getString(3));
            modelTransaccion.setValor(cursor.getInt(4));
            modelTransaccion.setRegistro(cursor.getString(5));
            modelTransaccion.setEstado(cursor.getInt(6));
        }




        return modelTransaccion;
    }

    /**
     * Metodo para Obtener una  transaccion segun el numero de cargo
     */
    public ModelTransaccion obtenerTransaccion(String numCargo) {
        ModelTransaccion modelTransaccion = new ModelTransaccion();

        Cursor cursor;

        cursor = getWritableDatabase().rawQuery(
                "SELECT * FROM " + DatabaseManager.TableTransacciones.TABLE_NAME_TRANSACCIONES + " WHERE " + DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_NUMERO_CARGO + " = " +numCargo, null
        );

        if(cursor.moveToFirst()){
            modelTransaccion.setId(cursor.getInt(0));
            modelTransaccion.setProducto_id(cursor.getInt(1));
            modelTransaccion.setNumero_cargo(cursor.getInt(2));
            modelTransaccion.setNumero_tarjeta(cursor.getString(3));
            modelTransaccion.setValor(cursor.getInt(4));
            modelTransaccion.setRegistro(cursor.getString(5));
            modelTransaccion.setEstado(cursor.getInt(6));
        }

        return modelTransaccion;

    }

    /**
     * #############################################################################################
     * Metodos privados auxiliares
     * #############################################################################################
     */


    /**
     * Metodo para Obtener el String de fecha y hora
     *
     * @return String fecha
     */
    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);
    }


}