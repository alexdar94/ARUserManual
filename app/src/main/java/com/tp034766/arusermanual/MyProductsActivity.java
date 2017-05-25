package com.tp034766.arusermanual;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyProductsActivity extends AppCompatActivity {
    List<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        products = new ArrayList<>();
        products.add(new Product("Face product","lbpcascade_frontalface.xml",R.raw.lbpcascade_frontalface));
        products.add(new Product("Teeth product","haarcascade_smile.xml",R.raw.haarcascade_smile));
        products.add(new Product("Remote Controller","haarcascade_remotecontrol.xml",R.raw.haarcascade_remotecontrol));
        products.add(new Product("Nose product","haarcascade_mcs_nose.xml",R.raw.haarcascade_mcs_nose));

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProductAdapter adapter = new ProductAdapter(this, products);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setAdapter(adapter);
    }
}
