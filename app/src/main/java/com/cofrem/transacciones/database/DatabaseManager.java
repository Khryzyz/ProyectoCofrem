package com.cofrem.transacciones.database;

/**
 * Clase que maneja:
 * - Scripts de la base de datos
 * - Atributos de la base de datos
 * - DDL de la base de datos
 */

public class DatabaseManager {

    //Etiqueta para Depuración
    private static final String TAG = DatabaseManager.class.getSimpleName();

    // Informacion de los tipos de los campos
    public static final String STRING_TYPE = "TEXT";
    public static final String INT_TYPE = "INTEGER";
    public static final String TIMESTAMP_TYPE = "TIMESTAMP";

    //Informacion de las  caracteristicas de los campos
    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String AUTOINCREMENT = "AUTOINCREMENT";
    public static final String ATTR_NULL = "NULL";
    public static final String ATTR_NOT_NULL = "NOT NULL";

    /**
     * Informacion de la base de datos
     */
    public static class DatabaseApp {

        public static final String DATABASE_NAME = "app_cofre_transactions.db";
        public static final int DATABASE_VERSION = 7;

    }

    /**
     * Tabla Producto:
     * - Modelado de la tabla producto
     * - Scripts de la tabla producto
     */
    public static class TableProducto {

        /**
         * Modelado de la tabla producto
         * Nombre de la tabla
         */
        public static final String TABLE_NAME_PRODUCTO = "producto";

        /**
         * Modelado de la tabla productos
         * Columnas de la tabla
         */
        public static final String COLUMN_PRODUCTO_ID = "id";
        public static final String COLUMN_PRODUCTO_NOMBRE = "nombre";
        public static final String COLUMN_PRODUCTO_DESCRIPCION = "descripcion";
        public static final String COLUMN_PRODUCTO_REGISTRO = "registro";
        public static final String COLUMN_PRODUCTO_ESTADO = "estado";

        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        public static final String CREATE_TABLE_PRODUCTO =
                "CREATE TABLE " + TABLE_NAME_PRODUCTO + "(" +
                        COLUMN_PRODUCTO_ID + " " + INT_TYPE + " " + PRIMARY_KEY + " " + AUTOINCREMENT + "," +
                        COLUMN_PRODUCTO_NOMBRE + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_PRODUCTO_DESCRIPCION + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_PRODUCTO_REGISTRO + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_PRODUCTO_ESTADO + " " + INT_TYPE + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        public static final String DROP_TABLE_PRODUCTO =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_PRODUCTO + "'";
    }

    /**
     * Tabla:
     * - Modelado de la tabla
     * - Scripts de la tabla
     */
    public static class TableTransacciones {

        /**
         * Modelado de la tabla
         * Nombre de la tabla
         */
        public static final String TABLE_NAME_TRANSACCIONES = "transacciones";

        /**
         * Modelado de la tabla
         * Columnas de la tabla
         */
        public static final String COLUMN_TRANSACCIONES_ID = "id";
        public static final String COLUMN_TRANSACCIONES_PRODUCTO_ID = "producto_id";
        public static final String COLUMN_TRANSACCIONES_NUMERO_CARGO = "numero_cargo";
        public static final String COLUMN_TRANSACCIONES_NUMERO_TARJETA = "numero_tarjeta";
        public static final String COLUMN_TRANSACCIONES_VALOR = "valor";
        public static final String COLUMN_TRANSACCIONES_REGISTRO = "registro";
        public static final String COLUMN_TRANSACCIONES_ESTADO = "estado";

        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        public static final String CREATE_TABLE_TRANSACCIONES =
                "CREATE TABLE " + TABLE_NAME_TRANSACCIONES + "(" +
                        COLUMN_TRANSACCIONES_ID + " " + INT_TYPE + " " + PRIMARY_KEY + " " + AUTOINCREMENT + "," +
                        COLUMN_TRANSACCIONES_PRODUCTO_ID + " " + INT_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_NUMERO_CARGO + " " + INT_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_NUMERO_TARJETA + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_VALOR + " " + INT_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_REGISTRO + " " + TIMESTAMP_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_TRANSACCIONES_ESTADO + " " + INT_TYPE + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        public static final String DROP_TABLE_TRANSACCIONES =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_TRANSACCIONES + "'";
    }

    /**
     * Tabla:
     * - Modelado de la tabla
     * - Scripts de la tabla
     */
    public static class TableEstablecimiento {

        /**
         * Modelado de la tabla
         * Nombre de la tabla
         */
        public static final String TABLE_NAME_ESTABLECIMIENTO = "establecimiento";

        /**
         * Modelado de la tabla
         * Columnas de la tabla
         */
        public static final String COLUMN_ESTABLECIMIENTO_ID = "id";
        public static final String COLUMN_ESTABLECIMIENTO_CODIGO = "codigo";
        public static final String COLUMN_ESTABLECIMIENTO_NOMBRE = "nombre";
        public static final String COLUMN_ESTABLECIMIENTO_DIRECCION = "direccion";
        public static final String COLUMN_ESTABLECIMIENTO_PUNTO = "punto";
        public static final String COLUMN_ESTABLECIMIENTO_REGISTRO = "registro";
        public static final String COLUMN_ESTABLECIMIENTO_ESTADO = "estado";

        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        public static final String CREATE_TABLE_ESTABLECIMIENTO =
                "CREATE TABLE " + TABLE_NAME_ESTABLECIMIENTO + "(" +
                        COLUMN_ESTABLECIMIENTO_ID + " " + INT_TYPE + " " + PRIMARY_KEY + " " + AUTOINCREMENT + "," +
                        COLUMN_ESTABLECIMIENTO_CODIGO + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_NOMBRE + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_DIRECCION + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_PUNTO + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_REGISTRO + " " + TIMESTAMP_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_ESTABLECIMIENTO_ESTADO + " " + INT_TYPE + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        public static final String DROP_TABLE_ESTABLECIMIENTO =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_ESTABLECIMIENTO + "'";
    }

    /**
     * Tabla:
     * - Modelado de la tabla
     * - Scripts de la tabla
     */
    public static class TableConfiguracionConexion {

        /**
         * Modelado de la tabla
         * Nombre de la tabla
         */
        public static final String TABLE_NAME_CONFIGURACION_CONEXION = "configuracion";

        /**
         * Modelado de la tabla
         * Columnas de la tabla
         */
        public static final String COLUMN_CONFIGURACION_CONEXION_HOST= "host";
        public static final String COLUMN_CONFIGURACION_CONEXION_PUERTO = "puerto";
        public static final String COLUMN_CONFIGURACION_CONEXION_ESTABLECIMIENTO = "establecimiento";
        public static final String COLUMN_CONFIGURACION_CONEXION_LOCAL = "local";
        public static final String COLUMN_CONFIGURACION_CONEXION_REGISTRO = "registro";
        public static final String COLUMN_CONFIGURACION_CONEXION_ESTADO = "estado";

        /**
         * Scripts de la tabla producti
         * Comando CREATE para la tabla Producto de la base de datos
         */
        public static final String CREATE_TABLE_CONFIGURACION_CONEXION =
                "CREATE TABLE " + TABLE_NAME_CONFIGURACION_CONEXION + "(" +
                        COLUMN_CONFIGURACION_CONEXION_HOST + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_PUERTO + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_ESTABLECIMIENTO + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_LOCAL + " " + STRING_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_REGISTRO + " " + TIMESTAMP_TYPE + ATTR_NOT_NULL + "," +
                        COLUMN_CONFIGURACION_CONEXION_ESTADO + " " + INT_TYPE + ATTR_NOT_NULL + ")";

        /**
         * Scripts de la tabla registro
         * Comando DROP para la tabla Producto de la base de datos
         */
        public static final String DROP_TABLE_CONFIGURACION_CONEXION =
                "DROP TABLE IF EXISTS '" + TABLE_NAME_CONFIGURACION_CONEXION + "'";
    }
}
