package com.example.fruitveginventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    Context context;
    public static final String DATABASE_NAME = "Log_Reg.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Users";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "fullName";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";

    public static final String TABLE_INVENTORY_NAME = "inventory";
    public static final String COL_IN_ID = "ID";
    public static final String COL_IN_PRODUCT = "productName";
    public static final String COL_IN_PRICE = "price";
    public static final String COL_IN_QUANTITY = "quantity";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String userQuery = "CREATE TABLE " + TABLE_NAME +
                "(" + COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_PASSWORD + " TEXT);";

        String Query = "CREATE TABLE " + TABLE_INVENTORY_NAME +
                "(" + COL_IN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_IN_PRODUCT + " TEXT, " +
                COL_IN_PRICE + " TEXT, " +
                COL_IN_QUANTITY + " TEXT);";

        db.execSQL(userQuery);
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_NAME);
        onCreate(db);
    }

    public void insertUser(String txtFName, String txtEmail, String txtPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, txtFName);
        contentValues.put(COL_EMAIL, txtEmail);
        contentValues.put(COL_PASSWORD, txtPassword);
        long id = db.insert(TABLE_NAME, null, contentValues);

        if(id == -1)
        {
            Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
        }else
            {
                Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
            }
    }

    public void insertData(String productName, String price, String quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_IN_PRODUCT, productName);
        contentValues.put(COL_IN_PRICE, price);
        contentValues.put(COL_IN_QUANTITY, quantity);
        long id = db.insert(TABLE_NAME, null, contentValues);

        if(id == -1)
        {
            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
