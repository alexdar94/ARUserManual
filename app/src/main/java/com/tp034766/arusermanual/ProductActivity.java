package com.tp034766.arusermanual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private Product product;
    private SharedPreferences mPrefs;
    Gson gson = new Gson();
    String json;
    List<Product> products=new ArrayList<>();
    MenuItem addButton;
    boolean isSaved = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mPrefs = getSharedPreferences("APPSP",MODE_PRIVATE);
        json = mPrefs.getString("MYPRODUCTS", "");
        product = (Product) getIntent().getSerializableExtra("PRODUCT");
        if(json!=""){
            Log.e("json",json);
            Type type = new TypeToken<List<Product>>(){}.getType();
            products= gson.fromJson(json, type);
            for(Product product: products){
                Log.e("product","found");
                if(product.id.equals(this.product.id)){
                    Log.e("product","saved");
                    isSaved = true;
                    break;
                }
            }
        }

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
        addButton = menu.findItem(R.id.add);
        if(isSaved){addButton.setIcon(R.drawable.ic_check);}
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                isSaved = !isSaved;
                refreshAddMenuButton(isSaved);
                json = new Gson().toJson(products);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("MYPRODUCTS", json);
                prefsEditor.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onlineTut(View view){

    }

    public void videoTut(View view){

    }

    public void textTut(View view){

    }

    public void ARTut(View view){
        Intent intent = new Intent(this, ClassifierActivity.class);
        intent.putExtra("PRODUCT",product);
        startActivity(intent);
    }

    public void refreshAddMenuButton(boolean isSaved){
        if(isSaved){
            addButton.setIcon(R.drawable.ic_check);
            products.add(product);
        }else {
            addButton.setIcon(R.drawable.ic_add);
            for(Product product:products) {
                if (product.id.equals(this.product.id)) {
                    products.remove(product);
                    break;
                }
            }
        }
    }
}
