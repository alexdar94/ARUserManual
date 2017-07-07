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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/8/2017.
 */

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ViewHolder> {
    private List<Product> products = new ArrayList<>();
    private Context context;

    public SearchProductAdapter(Context context, List<Product> products) {
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
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public SearchProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myproduct, parent, false);
        return new SearchProductAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final SearchProductAdapter.ViewHolder holder, final int position) {

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