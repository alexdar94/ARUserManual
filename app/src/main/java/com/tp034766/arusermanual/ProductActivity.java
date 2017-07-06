package com.tp034766.arusermanual;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        product = (Product) getIntent().getSerializableExtra("PRODUCT");
        TextView tv_productName= (TextView)findViewById(R.id.product_textView_productName);
        TextView tv_productBrand= (TextView)findViewById(R.id.product_textView_productBrand);
        TextView tv_productModel= (TextView)findViewById(R.id.product_textView_productModel);
        ImageView iv_productImage= (ImageView)findViewById(R.id.product_imageView_productPic);

        tv_productName.setText(product.name);
        tv_productBrand.setText(product.brandName);
        tv_productModel.setText(product.modelCode);
        Picasso.with(this).load(product.productImgUrl).placeholder(R.mipmap.ic_launcher).into(iv_productImage);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_activity, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
