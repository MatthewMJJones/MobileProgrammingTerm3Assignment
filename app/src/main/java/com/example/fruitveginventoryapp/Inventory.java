package com.example.fruitveginventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity {
    ArrayList<String> data1 = new ArrayList<String>();
    ArrayList<String> data2 = new ArrayList<String>();
    ArrayList<String> data3 = new ArrayList<String>();
    ArrayList<String> data4 = new ArrayList<String>();

    TableLayout table;

    EditText productName, price, quantity;
    Button btnADD;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        productName = findViewById(R.id.txtProd);
        price = findViewById(R.id.txtPrice);
        quantity = findViewById(R.id.txtQuantity);
        btnADD = findViewById(R.id.btnADD);
        db = new DBHelper(this);

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String prodName = productName.getText().toString();
                String prodPrice = price.getText().toString();
                String prodQuantity = quantity.getText().toString();

                if(!TextUtils.isEmpty(prodName) || !TextUtils.isEmpty(prodPrice) || !TextUtils.isEmpty(prodQuantity))
                {
                    db.insertData(prodName, prodPrice, prodQuantity);
                    add();
                }
                else
                    {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }

    public void add()
    {
        int tot;

        String prodName = productName.getText().toString();
        int prodPrice = Integer.parseInt(price.getText().toString());
        int prodQuantity = Integer.parseInt(quantity.getText().toString());
        tot = prodPrice * prodQuantity;

        data1.add(prodName);
        data2.add(String.valueOf(prodPrice));
        data3.add(String.valueOf(prodQuantity));
        data4.add(String.valueOf(tot));

        TableLayout table = (TableLayout) findViewById(R.id.table1);

        TableRow row = new TableRow(this);
        TextView row1 = new TextView(this);
        TextView row2 = new TextView(this);
        TextView row3 = new TextView(this);
        TextView row4 = new TextView(this);

        String total;

        for(int i = 0; i<data1.size(); i++)
        {
            String pName = data1.get(i);
            String prc = data2.get(i);
            String qty = data3.get(i);
            total = data4.get(i);

            row1.setText(pName);
            row2.setText(prc);
            row3.setText(qty);
            row4.setText(total);
        }

        row.addView(row1);
        row.addView(row2);
        row.addView(row3);
        row.addView(row4);
        table.addView(row);

        productName.setText("");
        price.setText("");
        quantity.setText("");
        productName.requestFocus();
    }
}