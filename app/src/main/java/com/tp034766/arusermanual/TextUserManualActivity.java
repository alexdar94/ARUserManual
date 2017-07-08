package com.tp034766.arusermanual;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TextUserManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_user_manual);

        Product product = (Product) getIntent().getSerializableExtra("PRODUCT");

        TextView textUserManual = (TextView)findViewById(R.id.text_user_manual);
        textUserManual.setText(product.textBasedUserManual);
    }
}
