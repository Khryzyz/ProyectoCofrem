package com.cofrem.transacciones.database;

/**
 * Clase que maneja:
 * - Scripts de la base de datos
 * - Atributos de la base de datos
 * - DDL de la base de datos
 */

class DatabaseManager {

    // Informacion de los tipos de los campos
    private static final String STRING_TYPE = "TEXT";
    private static final String INT_TYPE = "INTEGER";
    private static final String TIMESTAMP_TYPE = "TIMESTAMP";

    //Informacion de las  caracteristicas de los campos
    private static final String PRIMARY_KEY = "PRIMARY KEY";
    private static final String AUTOINCREMENT = "AUTOINCREMENT";
    private static final String ATTR_NULL = "NULL";
    private static final String ATTR_NOT_NULL = "NOT NULL";

    /**
     * Informacion de la base de datos
     */
    static class DatabaseApp {

        static final String DATABASE_NAME = "app_cofrem_transactions.db";
        static final int DATABASE_VERSION = 62;

    }

    /**
     * #############################################################################################
     * Tabla Producto:
     * - Modelado de la tabla producto
     * - Scripts de la tabla producto
     * #############################################################################################
     */
    static class TableProducto {

        /**
         * Modelado de la tabla producto
         * Nombre de la tabla
         */
        static final String TABLE_NAME_PRODUCTO = "producto";

        /**
         * Modelado de la tabla productos
         * Columnas de la tabla
         */
        static final String COLUMN_PRODUCTO_ID = "id";
        static final String COLUMN_PRODUCTO_NOMBRE = "nombre";
        static final String COLUMN_PRODUCTO_DESCRIPCION = "descripcion";
        static final String COLUMN_PRODUCTO_REGISTRO = "registro";
        static final String COLUMN_PRODUCTO_ESTADO = "estado";

        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        static final String CREATE_TABLE_PRODUCTO =
                "CREATE TABLE " + TABLE_NAME_PRODUCTO + "(" +
                        COLUMN_PRODUCTO_ID + " " + INT_TYPE + " " + PRIMARY_KEY + "," +
                        COLUMN_PRODUCTO_NOMBRE + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_PRODUCTO_DESCRIPCION + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_PRODUCTO_REGISTRO + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_PRODUCTO_ESTADO + " " + INT_TYPE + " " + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        static final String DROP_TABLE_PRODUCTO =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_PRODUCTO + "'";
    }

    /**
     * #############################################################################################
     * Tabla Transacciones:
     * - Modelado de la tabla producto
     * - Scripts de la tabla producto
     * #############################################################################################
     */
    static class TableTransacciones {

        /**
         * Modelado de la tabla
         * Nombre de la tabla
         */
        static final String TABLE_NAME_TRANSACCIONES = "transacciones";

        /**
         * Modelado de la tabla
         * Columnas de la tabla
         */
        static final String COLUMN_TRANSACCIONES_ID = "id";
        static final String COLUMN_TRANSACCIONES_PRODUCTO_ID = "producto_id";
        static final String COLUMN_TRANSACCIONES_NUMERO_CARGO = "numero_cargo";
        static final String COLUMN_TRANSACCIONES_NUMERO_TARJETA = "numero_tarjeta";
        static final String COLUMN_TRANSACCIONES_VALOR = "valor";
        static final String COLUMN_TRANSACCIONES_REGISTRO = "registro";
        static final String COLUMN_TRANSACCIONES_ESTADO = "estado";

        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        static final String CREATE_TABLE_TRANSACCIONES =
                "CREATE TABLE " + TABLE_NAME_TRANSACCIONES + "(" +
                        COLUMN_TRANSACCIONES_ID + " " + INT_TYPE + " " + PRIMARY_KEY + " " + AUTOINCREMENT + "," +
                        COLUMN_TRANSACCIONES_PRODUCTO_ID + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_NUMERO_CARGO + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_NUMERO_TARJETA + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_VALOR + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_REGISTRO + " " + TIMESTAMP_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_ESTADO + " " + INT_TYPE + " " + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        static final String DROP_TABLE_TRANSACCIONES =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_TRANSACCIONES + "'";
    }

    /**
     * #############################################################################################
     * Tabla Establishment:
     * - Modelado de la tabla establecimiento
     * - Scripts de la tabla establecimiento
     * #############################################################################################
     */
    static class TableEstablecimiento {

        /**
         * Modelado de la tabla
         * Nombre de la tabla
         */
        static final String TABLE_NAME_ESTABLECIMIENTO = "establecimiento";

        /**
         * Modelado de la tabla
         * Columnas de la tabla
         */
        static final String COLUMN_ESTABLECIMIENTO_CODIGO = "codigo";
        static final String COLUMN_ESTABLECIMIENTO_NOMBRE = "nombre";
        static final String COLUMN_ESTABLECIMIENTO_DIRECCION = "direccion";
        static final String COLUMN_ESTABLECIMIENTO_CIUDAD = "ciudad";
        static final String COLUMN_ESTABLECIMIENTO_NIT = "nit";
        static final String COLUMN_ESTABLECIMIENTO_RAZON_SOCIAL = "razonsocial";
        static final String COLUMN_ESTABLECIMIENTO_REGISTRO = "registro";
        static final String COLUMN_ESTABLECIMIENTO_ESTADO = "estado";

        /**
         * Scripts de la tabla establecimineto
         * Comando CREATE para la tabla establecimineto de la base de datos
         */
        static final String CREATE_TABLE_ESTABLECIMIENTO =
                "CREATE TABLE " + TABLE_NAME_ESTABLECIMIENTO + "(" +
                        COLUMN_ESTABLECIMIENTO_CODIGO + " " + STRING_TYPE + " " + PRIMARY_KEY + "," +
                        COLUMN_ESTABLECIMIENTO_NOMBRE + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_DIRECCION + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_CIUDAD + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_NIT + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_RAZON_SOCIAL + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_REGISTRO + " " + TIMESTAMP_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_ESTADO + " " + INT_TYPE + " " + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla establecimineto de la base de datos
         */
        static final String DROP_TABLE_ESTABLECIMIENTO =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_ESTABLECIMIENTO + "'";
    }

    /**
     * #############################################################################################
     * Tabla ConfiguraciónConexión:
     * - Modelado de la tabla producto
     * - Scripts de la tabla producto
     * #############################################################################################
     */
    static class TableConfiguracionConexion {

        /**
         * Modelado de la tabla
         * Nombre de la tabla
         */
        static final String TABLE_NAME_CONFIGURACION_CONEXION = "configuracion";

        /**
         * Modelado de la tabla
         * Columnas de la tabla
         */
        static final String COLUMN_CONFIGURACION_CONEXION_HOST = "host";
        static final String COLUMN_CONFIGURACION_CONEXION_PORT = "puerto";
        static final String COLUMN_CONFIGURACION_CONEXION_DISPOSITIVO = "dispositivo";
        static final String COLUMN_CONFIGURACION_CONEXION_REGISTRO = "registro";
        static final String COLUMN_CONFIGURACION_CONEXION_ESTADO = "estado";

        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        static final String CREATE_TABLE_CONFIGURACION_CONEXION =
                "CREATE TABLE " + TABLE_NAME_CONFIGURACION_CONEXION + "(" +
                        COLUMN_CONFIGURACION_CONEXION_HOST + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_PORT + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_DISPOSITIVO + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_REGISTRO + " " + TIMESTAMP_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_ESTADO + " " + INT_TYPE + " " + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        static final String DROP_TABLE_CONFIGURACION_CONEXION =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_CONFIGURACION_CONEXION + "'";
    }

    /**
     * #############################################################################################
     * Tabla ConfiguraciónAcceso:
     * - Modelado de la tabla producto
     * - Scripts de la tabla producto
     * #############################################################################################
     */
    static class TableConfiguracionAcceso {

        /**
         * Modelado de la tabla
         * Nombre de la tabla
         */
        static final String TABLE_NAME_CONFIGURACION_ACCESO = "acceso";

        /**
         * Modelado de la tabla
         * Columnas de la tabla
         */
        static final String COLUMN_CONFIGURACION_ACCESO_CLAVE_TECNICA = "clavetecnica";
        static final String COLUMN_CONFIGURACION_ACCESO_CLAVE_ADMIN = "claveadmin";
        static final String COLUMN_CONFIGURACION_ACCESO_REGISTRO = "registro";
        static final String COLUMN_CONFIGURACION_ACCESO_ESTADO = "estado";

        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        static final String CREATE_TABLE_CONFIGURACION_ACCESO =
                "CREATE TABLE " + TABLE_NAME_CONFIGURACION_ACCESO + "(" +
                        COLUMN_CONFIGURACION_ACCESO_CLAVE_TECNICA + " " + STRING_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_ACCESO_CLAVE_ADMIN + " " + STRING_TYPE + " " + ATTR_NULL + "," +
                        COLUMN_CONFIGURACION_ACCESO_REGISTRO + " " + TIMESTAMP_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_ACCESO_ESTADO + " " + INT_TYPE + " " + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        static final String DROP_TABLE_CONFIGURACION_ACCESO =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_CONFIGURACION_ACCESO + "'";
    }

    /**
     * #############################################################################################
     * Tabla ConfiguraciónPrinter:
     * - Modelado de la tabla para la configuracion de la impresora
     * - Scripts de la tabla ConfiguraciónPrinter
     * #############################################################################################
     */
    static class TableConfigurationPrinter {

        /**
         * Modelado de la tabla
         * Nombre de la tabla
         */
        static final String TABLE_NAME_CONFIGURACION_PRINTER = "printer";

        /**
         * Modelado de la tabla
         * Columnas de la tabla
         */
        static final String COLUMN_CONFIGURACION_PRINTER_FONT_SIZE = "font_size";
        static final String COLUMN_CONFIGURACION_PRINTER_GRAY_LEVEL = "gray_level";
        static final String COLUMN_CONFIGURACION_PRINTER_ESTADO = "estado";


        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        static final String CREATE_TABLE_CONFIGURACION_PRINTER =
                "CREATE TABLE " + TABLE_NAME_CONFIGURACION_PRINTER + "(" +
                        COLUMN_CONFIGURACION_PRINTER_FONT_SIZE + " " + INT_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_PRINTER_ESTADO + " " + INT_TYPE + " " + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_PRINTER_GRAY_LEVEL + " " + INT_TYPE + " " + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        static final String DROP_TABLE_CONFIGURACION_PRINTER =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_CONFIGURACION_PRINTER + "'";
    }

}
