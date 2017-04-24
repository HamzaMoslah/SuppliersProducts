package com.example.moslah_hamza.stam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moslah_Hamza on 17/04/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "suppliersManager";

    // Contacts table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_SUPPLIERS = "suppliers";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_SUPPROD = "supprod";

    private SQLiteDatabase db;

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PRODID = "id_prod";
    private static final String KEY_SUPID = "id_sup";
    private static final String KEY_USRID = "id_user";
    private static final String KEY_NAME = "name";
    private static final String KEY_MAIL = "email";
    private static final String KEY_PWD = "pwd";
    private static final String KEY_PU = "pu";
    private static final String KEY_QTE = "quantit";

    //create statements
    private static final String CREATE_SUPPLIERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SUPPLIERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT, " + KEY_USRID + " INTEGER)";
    private static final String CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_PU + " REAL" + ")";
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
            + KEY_MAIL + " TEXT," + KEY_PWD + " TEXT" + ")";
    private static final String CREATE_SUPPROD_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SUPPROD + "("
            + KEY_PRODID + " INTEGER," + KEY_SUPID + " INTEGER," + KEY_USRID + " INTEGER,"
            + KEY_QTE + " INTEGER," + " PRIMARY KEY (" + KEY_USRID + ", " + KEY_SUPID + ", " + KEY_PRODID + ") )";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_SUPPLIERS_TABLE);
        db.execSQL(CREATE_SUPPROD_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPROD);

        // Create tables again
        onCreate(db);
    }

    public void createDataBase() {
        this.db = getWritableDatabase();
        this.onCreate(db);
    }

    // Adding new contact
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.get_name()); // Contact Name
        values.put(KEY_MAIL, user.get_email()); // Contact Phone Number
        values.put(KEY_PWD, user.get_pwd()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new contact
    public void addSupplier(Supplier supplier) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, supplier.get_name()); // Contact Name
        values.put(KEY_USRID, supplier.getUsr_id());

        // Inserting Row
        db.insert(TABLE_SUPPLIERS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new contact
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.get_name()); // Contact Name
        values.put(KEY_PU, product.get_PU()); // Contact Name

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new contact
    public void addSupProd(int prod, int user, int sup, int qte) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODID, prod);
        values.put(KEY_SUPID, sup);
        values.put(KEY_USRID, user);
        values.put(KEY_QTE, qte);

        // Inserting Row
        db.insert(TABLE_SUPPROD, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID,
                        KEY_NAME, KEY_MAIL, KEY_PWD}, KEY_MAIL + "=?",
                new String[]{String.valueOf(email)}, null, null, null, null);

        User user = null;
        if (cursor.moveToFirst()) {
            user = new User(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));
            user.set_id(cursor.getInt(0));
        }

        // return contact
        return user;
    }

    // Getting single contact
    public Supplier getSupplier(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUPPLIERS, new String[]{KEY_ID,
                        KEY_NAME, KEY_USRID}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Supplier supplier = new Supplier(cursor.getString(1), cursor.getInt(2));
        supplier.set_id(cursor.getInt(0));

        // return contact
        return supplier;
    }

    // Getting single contact
    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PU}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Product prod = new Product(cursor.getString(1), cursor.getDouble(2));
        prod.set_id(cursor.getInt(0));

        // return contact
        return prod;
    }

    // Getting All Contacts
    public List<Supplier> getAllUserSuppliers(int user) {
        List<Supplier> supplierList = new ArrayList<Supplier>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_ID + " FROM " + TABLE_SUPPLIERS
                + " WHERE " + KEY_USRID + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();

        List<String> whereArgs = new ArrayList<>();
        whereArgs.add(String.valueOf(user));
        Cursor cursor = db.rawQuery(selectQuery, whereArgs.toArray(new String[0]));

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                Supplier supplier = this.getSupplier(cursor.getInt(0));
                supplierList.add(supplier);
            } while (cursor.moveToNext());
        }

        // return contact list
        return supplierList;
    }

    // Getting All Contacts
    public List<Supplier> getAllSuppliers() {
        List<Supplier> supplierList = new ArrayList<Supplier>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_SUPPLIERS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                Supplier supplier = this.getSupplier(cursor.getInt(0));
                supplierList.add(supplier);
            } while (cursor.moveToNext());
        }

        // return contact list
        return supplierList;
    }

    // Getting All Contacts
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                Product product = this.getProduct(cursor.getInt(0));
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }

    // Getting All Contacts
    public List<Product> getAllUserProduct(int user, int sup) {
        List<Product> productList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_PRODID + ", " + KEY_QTE + " FROM " + TABLE_SUPPROD
                + " WHERE " + KEY_USRID + " = ? AND " + KEY_SUPID + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();

        List<String> whereArgs = new ArrayList<>();
        whereArgs.add(String.valueOf(user));
        whereArgs.add(String.valueOf(sup));
        Cursor cursor = db.rawQuery(selectQuery, whereArgs.toArray(new String[0]));

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                Product prod = this.getProduct(cursor.getInt(0));
                prod.setQte(cursor.getInt(1));
                productList.add(prod);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }

    // Getting All Contacts
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.set_name(cursor.getString(1));
                user.set_email(cursor.getString(2));
                // Adding contact to list
                usersList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return usersList;
    }

    // Getting contacts Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single contact
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.get_name());
        values.put(KEY_MAIL, user.get_email());

        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.get_id())});
    }

    // Updating single contact
    public int updateSupplier(String supplier, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, supplier);

        // updating row
        return db.update(TABLE_SUPPLIERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    // Deleting single contact
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[]{String.valueOf(user.get_id())});
        db.close();
    }

    // Deleting single contact
    public void deleteSupplier(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUPPLIERS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
