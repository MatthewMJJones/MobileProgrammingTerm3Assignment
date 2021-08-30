package com.example.fruitveginventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button btnLog, btnReg;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        btnLog = findViewById(R.id.btnSignIn);
        btnReg = findViewById(R.id.btnRegister1);

        openHelper = new DBHelper(this);
        db = openHelper.getReadableDatabase();

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                cursor = db.rawQuery("SELECT * FROM "+ DBHelper.TABLE_NAME+ " WHERE " + DBHelper.COL_EMAIL + "=? AND " + DBHelper.COL_PASSWORD + "=?", new String[]{txtEmail, txtPassword});

                if(cursor!=null)
                {
                    if(cursor.getCount()>0)
                    {
                        cursor.moveToNext();
                        Intent intent = new Intent(getApplicationContext(), Inventory.class);
                        startActivity(intent);
                    }
                    else
                        {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });
    }
}