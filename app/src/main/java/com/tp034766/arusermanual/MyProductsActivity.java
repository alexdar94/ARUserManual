package com.tp034766.arusermanual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyProductsActivity extends AppCompatActivity {
    List<Product> products= new ArrayList<>();
    private SharedPreferences mPrefs;
    Gson gson = new Gson();
    String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        String key = mDatabase.child("Products").push().getKey();
        //Log.e("fb",key+"");
//        mDatabase.child("Products/"+key+"/name").setValue("Deep Hydration Sheet Mask");
//        mDatabase.child("Products/"+key+"/productImgUrl").setValue("https://www.nerdfitness.com/blog/what-do-you-bench-strength-training-101-the-bench-press/");
//        mDatabase.child("Products/"+key+"/brandName").setValue("Dr. Jart+");
//        mDatabase.child("Products/"+key+"/modelCode").setValue("FYP01234");
//        mDatabase.child("Products/"+key+"/onlineTutorialLink").setValue("https://www.wellandgood.com/good-looks/how-to-use-natural-koran-sheet-masks/");
//        mDatabase.child("Products/"+key+"/textBasedUserManual").setValue("Deep Hydration Sheet Mask official text manual");
//        mDatabase.child("Products/"+key+"/videoTutorialLink").setValue("https://www.youtube.com/watch?v=3IvBGQg8ats");

//        mDatabase.child("ARInstructions/-KoSK3SiB9fsxDgMK1PJ/stepNo").setValue(0);
//        mDatabase.child("ARInstructions/-KoSK3SiB9fsxDgMK1PJ/textInstruction").setValue(0);
//        int stepNo;
//        String textInstruction;
//        String haarClassifierName;
//        int x;
//        int y;
//        double scaleFactor;

        mPrefs = getSharedPreferences("APPSP",MODE_PRIVATE);
        json = mPrefs.getString("MYPRODUCTS", "");

//        List<AugmentedRealityInstruction> instructions = new ArrayList<>();
//        products.add(new Product("Face product","lbpcascade_frontalface.xml",R.raw.lbpcascade_frontalface));
//        products.add(new Product("Teeth product","haarcascade_smile.xml",R.raw.haarcascade_smile));
//        products.add(new Product("Nose product","haarcascade_mcs_nose.xml",R.raw.haarcascade_mcs_nose));
//        instructions.add(new AugmentedRealityInstruction(0, "instruction 0", "haarcascade_remotecontrol.xml", 1,20,20));
//        instructions.add(new AugmentedRealityInstruction(1, "instruction 1", "haarcascade_remotecontrol.xml", 1,200,30));
//        instructions.add(new AugmentedRealityInstruction(2, "instruction 2", "haarcascade_remotecontrol.xml", 1,40,40));
//        instructions.add(new AugmentedRealityInstruction(3, "instruction 3", "haarcascade_remotecontrol.xml", 1,100,100));
//        products.add(new Product("1", "name", "https://cdn.solarbotics.com/products/photos/4e724da8938450a66e5025ccc7d638ae/52225-img_7393.JPG"
//                ,"brand", "modelcode", "onlineTutorialLink"
//                , "textBasedUserManual", "videoTutorialLink", instructions));

        if(json!=""){
            Type type = new TypeToken<List<Product>>(){}.getType();
            products= gson.fromJson(json, type);
            Log.e("json new",products.size()+"");
        }

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProductAdapter adapter = new ProductAdapter(this, products);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_products_activity, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(this, SearchProductActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
