package com.tp034766.arusermanual;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 5/25/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> products;
    private Context context;

    public ProductAdapter(Context context, List<Product> products) {
        this.products = products;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName,productBrand, productModel;
        ImageView productPic;
        public ViewHolder(View v) {
            super(v);

            productName = (TextView) v.findViewById(R.id.myProducts_textView_productName);
            productBrand = (TextView) v.findViewById(R.id.myProducts_textView_productBrand);
            productModel = (TextView) v.findViewById(R.id.myProducts_textView_productModel);
            productPic = (ImageView) v.findViewById(R.id.myProducts_imageView_productPic);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra("PRODUCT",products.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myproduct, parent, false);
        return new ProductAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        holder.productName.setText(products.get(position).name);
        holder.productBrand.setText(products.get(position).brandName);
        holder.productModel.setText(products.get(position).modelCode);
        Picasso.with(context).load(products.get(position).productImgUrl).placeholder(R.mipmap.ic_launcher).into(holder.productPic);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}