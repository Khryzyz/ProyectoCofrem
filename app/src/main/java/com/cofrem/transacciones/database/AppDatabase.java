package com.cofrem.transacciones.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cofrem.transacciones.lib.MD5;
import com.cofrem.transacciones.models.Configurations;
import com.cofrem.transacciones.models.ModelsWS.ModelEstablecimiento.ConexionEstablecimiento;
import com.cofrem.transacciones.models.ModelsWS.ModelEstablecimiento.Establecimiento;
import com.cofrem.transacciones.models.ModelsWS.ModelEstablecimiento.InformacionEstablecimiento;
import com.cofrem.transacciones.models.Transaccion;

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
     * #############################################################################################
     * AREA REGISTROS INICIALES
     * #############################################################################################
     */

    /**
     * Metodo para insertar registro inicial en la Base de Datos
     */
    public boolean insertRegistroInicialProductos() {

        // Inicializacion de la variable de contenidos del registro
        ContentValues contentValues = new ContentValues();

        // Almacena los valores a insertar
        contentValues.put(DatabaseManager.TableProducto.COLUMN_PRODUCTO_NOMBRE, "CREDITO ROTATIVO");
        contentValues.put(DatabaseManager.TableProducto.COLUMN_PRODUCTO_DESCRIPCION, "Credito rotativo");
        contentValues.put(DatabaseManager.TableProducto.COLUMN_PRODUCTO_REGISTRO, getDateTime());
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
     * Metodo para insertar registro inicial en la Base de Datos de la clave de acceso a dispositivo
     * Usado en:
     * Inicio por primera vez de la APP
     */
    public boolean insertRegistroInicialConfiguracionAcceso() {

        // Inicializacion de la variable de contenidos del registro
        ContentValues contentValues = new ContentValues();

        // Almacena los valores a insertar
        contentValues.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_CLAVE_TECNICA, MD5.crypt("8717"));
        contentValues.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_REGISTRO, getDateTime());
        contentValues.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_ESTADO, 1);

        // Insercion del registro en la base de datos
        int count = (int) getWritableDatabase().insert(
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
     * #############################################################################################
     * AREA MODULE CONFIGURACION
     * #############################################################################################
     */

    /**
     * Metodo para validar si existe registro inicial en la Base de Datos de la clave de acceso a dispositivo
     * Usado en modulos:
     * - Configuracion
     */
    public int conteoConfiguracionAcceso() {

        int count;

        Cursor cursorQuery;

        cursorQuery = getWritableDatabase().rawQuery(
                "SELECT COUNT(1) FROM " +
                        DatabaseManager.TableConfiguracionAcceso.TABLE_NAME_CONFIGURACION_ACCESO +
                        " WHERE " + DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_ESTADO + " = '1'",
                null
        );

        cursorQuery.moveToFirst();

        count = cursorQuery.getInt(0);

        return count;

    }

    /**
     * Metodo para validar si existe registro inicial en la Base de Datos de la clave de acceso a dispositivo
     * Usado en modulos:
     * - Configuracion
     *
     * @param claveTecnica
     * @return
     */
    public int conteoConfiguracionAccesoByClaveTecnica(String claveTecnica) {

        int count;

        Cursor cursorQuery;

        cursorQuery = getWritableDatabase().rawQuery(
                "SELECT COUNT(1) FROM " +
                        DatabaseManager.TableConfiguracionAcceso.TABLE_NAME_CONFIGURACION_ACCESO +
                        " WHERE " + DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_CLAVE_TECNICA + " = '" + claveTecnica + "' " +
                        " AND " + DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_ESTADO + " = '1'",
                null
        );

        cursorQuery.moveToFirst();

        count = cursorQuery.getInt(0);

        return count;

    }

    /**
     * Metodo para Obtener el conteo de los registros de la tabla ConfiguracionConexion
     * Usado en modulos:
     * - Configuracion
     *
     * @return conteo de los registros
     */
    public int conteoConfiguracionConexion() {

        int count;

        Cursor cursorQuery;

        cursorQuery = getWritableDatabase().rawQuery(
                "SELECT COUNT(1) FROM " +
                        DatabaseManager.TableConfiguracionConexion.TABLE_NAME_CONFIGURACION_CONEXION +
                        " WHERE " + DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_ESTADO + " = '1'",
                null
        );

        cursorQuery.moveToFirst();

        count = cursorQuery.getInt(0);

        return count;
    }

    /**
     * Metodo para insertar registro de la configuracion de la conexion
     */
    public boolean insertConfiguracionConexion(Configurations configurations) {

        int count = 0;

        try {
            getWritableDatabase().beginTransaction();

            // Eliminacion de registros anteriores en la base de datos
            getWritableDatabase().delete(
                    DatabaseManager.TableConfiguracionConexion.TABLE_NAME_CONFIGURACION_CONEXION,
                    "",
                    null
            );

            // Inicializacion de la variable de contenidos del registro
            ContentValues contentValuesInsert = new ContentValues();

            // Almacena los valores a insertar
            contentValuesInsert.put(DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_HOST, configurations.getHost());
            contentValuesInsert.put(DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_PORT, configurations.getPort());
            contentValuesInsert.put(DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_DISPOSITIVO, configurations.getCodigoDispositivo());
            contentValuesInsert.put(DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_REGISTRO, getDateTime());
            contentValuesInsert.put(DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_ESTADO, 1);

            // Insercion del registro en la base de datos
            count = (int) getWritableDatabase().insert(
                    DatabaseManager.TableConfiguracionConexion.TABLE_NAME_CONFIGURACION_CONEXION,
                    null,
                    contentValuesInsert
            );


            getWritableDatabase().setTransactionSuccessful();

        } catch (SQLException e) {

        } finally {

            getWritableDatabase().endTransaction();

        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para insertar registro de la configuracion de la conexion
     */
    public String obtenerURLConfiguracionConexion() {

        String urlTransacciones = "";

        Cursor cursorQuery;

        cursorQuery = getWritableDatabase().rawQuery(
                "SELECT " + DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_HOST +
                        " , " + DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_PORT +
                        " FROM " + DatabaseManager.TableConfiguracionConexion.TABLE_NAME_CONFIGURACION_CONEXION +
                        " WHERE " + DatabaseManager.TableConfiguracionConexion.COLUMN_CONFIGURACION_CONEXION_ESTADO + " = '1' " +
                        " LIMIT 1", null
        );

        if (cursorQuery.moveToFirst()) {
            urlTransacciones = cursorQuery.getString(0) + ":" + cursorQuery.getString(1);
        }

        return urlTransacciones;

    }

    /**
     * Metodo para insertar registro de la configuracion de la conexion
     */
    public boolean processInfoEstablecimiento(Establecimiento establecimiento) {

        boolean transaction = false;

        try {
            getWritableDatabase().beginTransaction();

            InformacionEstablecimiento informacionEstablecimiento = establecimiento.getInformacionEstablecimiento();

            ConexionEstablecimiento conexionEstablecimiento = establecimiento.getConexionEstablecimiento();

            // Inicializacion de la variable de contenidos del registro
            ContentValues contentValuesInsertInformacionDispositivo = new ContentValues();

            // Almacena los valores a insertar
            contentValuesInsertInformacionDispositivo.put(DatabaseManager.TableEstablecimiento.COLUMN_ESTABLECIMIENTO_CODIGO, informacionEstablecimiento.getCodigoPunto());
            contentValuesInsertInformacionDispositivo.put(DatabaseManager.TableEstablecimiento.COLUMN_ESTABLECIMIENTO_NOMBRE, informacionEstablecimiento.getNombrePunto());
            contentValuesInsertInformacionDispositivo.put(DatabaseManager.TableEstablecimiento.COLUMN_ESTABLECIMIENTO_DIRECCION, informacionEstablecimiento.getDireccionPunto());
            contentValuesInsertInformacionDispositivo.put(DatabaseManager.TableEstablecimiento.COLUMN_ESTABLECIMIENTO_CIUDAD, informacionEstablecimiento.getCiudadPunto());
            contentValuesInsertInformacionDispositivo.put(DatabaseManager.TableEstablecimiento.COLUMN_ESTABLECIMIENTO_NIT, informacionEstablecimiento.getNitComercio());
            contentValuesInsertInformacionDispositivo.put(DatabaseManager.TableEstablecimiento.COLUMN_ESTABLECIMIENTO_RAZON_SOCIAL, informacionEstablecimiento.getRazonSocialComercio());
            contentValuesInsertInformacionDispositivo.put(DatabaseManager.TableEstablecimiento.COLUMN_ESTABLECIMIENTO_REGISTRO, getDateTime());
            contentValuesInsertInformacionDispositivo.put(DatabaseManager.TableEstablecimiento.COLUMN_ESTABLECIMIENTO_ESTADO, 1);

            // Insercion del registro en la base de datos
            int countInformacionDispositivo = (int) getWritableDatabase().insert(
                    DatabaseManager.TableEstablecimiento.TABLE_NAME_ESTABLECIMIENTO,
                    null,
                    contentValuesInsertInformacionDispositivo
            );


            if (countInformacionDispositivo == 1 &&
                    !conexionEstablecimiento.getClaveTecnivo().isEmpty() &&
                    conteoConfiguracionAccesoByClaveTecnica(conexionEstablecimiento.getClaveTecnivo()) == 0) {

                // Eliminacion de registros anteriores en la base de datos
                getWritableDatabase().delete(
                        DatabaseManager.TableConfiguracionAcceso.TABLE_NAME_CONFIGURACION_ACCESO,
                        "",
                        null
                );

                // Inicializacion de la variable de contenidos del registro de acceso
                ContentValues contentValuesInformacionAcceso = new ContentValues();

                // Almacena los valores a actualizar
                contentValuesInformacionAcceso.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_CLAVE_TECNICA, conexionEstablecimiento.getClaveTecnivo());
                contentValuesInformacionAcceso.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_CLAVE_ADMIN, conexionEstablecimiento.getClaveComercio());
                contentValuesInformacionAcceso.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_REGISTRO, getDateTime());
                contentValuesInformacionAcceso.put(DatabaseManager.TableConfiguracionAcceso.COLUMN_CONFIGURACION_ACCESO_ESTADO, 1);

                // Insercion del registro en la base de datos
                int countConfiguracionAcceso = (int) getWritableDatabase().insert(
                        DatabaseManager.TableConfiguracionAcceso.TABLE_NAME_CONFIGURACION_ACCESO,
                        null,
                        contentValuesInformacionAcceso
                );

                if (countConfiguracionAcceso == 1) {

                    getWritableDatabase().setTransactionSuccessful();
                    transaction = true;

                }
            }

        } catch (SQLException e) {

            transaction = false;

        } finally {

            getWritableDatabase().endTransaction();

        }
        return transaction;
    }

    /**
     * #############################################################################################
     * AREA MODULE REPORTES
     * #############################################################################################
     */


    /**
     * Metodo para Obtener ultima  transaccion
     */
    public Transaccion obtenerUltimaTransaccion() {

        Transaccion modelTransaccion = new Transaccion();

        Cursor cursorQuery;

        Cursor cursor;

        cursor = getWritableDatabase().rawQuery(
                "SELECT * FROM " + DatabaseManager.TableTransacciones.TABLE_NAME_TRANSACCIONES +
                        " ORDER BY " + DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_REGISTRO +
                        " ASC LIMIT 1", null
        );

        if (cursor.moveToFirst()) {
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
    public Transaccion obtenerTransaccion(String numCargo) {
        Transaccion modelTransaccion = new Transaccion();

        Cursor cursor;

        cursor = getWritableDatabase().rawQuery(
                "SELECT * FROM " + DatabaseManager.TableTransacciones.TABLE_NAME_TRANSACCIONES +
                        " WHERE " + DatabaseManager.TableTransacciones.COLUMN_TRANSACCIONES_NUMERO_CARGO + " = " + numCargo, null
        );

        if (cursor.moveToFirst()) {
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