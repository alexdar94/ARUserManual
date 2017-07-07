package com.tp034766.arusermanual;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchProductActivity extends AppCompatActivity {
    private List<Product> products = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.searchProduct_recyclerView);
        final SearchProductAdapter adapter = new SearchProductAdapter(getApplicationContext(), products);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setAdapter(adapter);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference searchTagsRef = mDatabase.child("SearchTags");
        EditText searchET = (EditText)findViewById(R.id.searchProduct_editText);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            private Timer timer=new Timer();
            private final long DELAY = 500; // milliseconds
            @Override
            public void afterTextChanged(final Editable editable) {
                products.clear();
                adapter.notifyDataSetChanged();
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {

                                String searchText = editable.toString();
                                if(!searchText.equals("")){
                                    searchTagsRef.orderByChild(searchText.toLowerCase()).equalTo(true)
                                            .addListenerForSingleValueEvent(new ValueEventListener(){
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    for (final DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                                        mDatabase.child("Products/"+snapshot.getKey())
                                                                .addListenerForSingleValueEvent(new ValueEventListener(){
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        Product product = new Product(snapshot.getKey(), (String)dataSnapshot.child("name").getValue()
                                                                                , (String)dataSnapshot.child("productImgUrl").getValue()
                                                                                , (String)dataSnapshot.child("brandName").getValue()
                                                                                , (String)dataSnapshot.child("modelCode").getValue()
                                                                                , (String)dataSnapshot.child("onlineTutorialLink").getValue()
                                                                                , (String)dataSnapshot.child("textBasedUserManual").getValue()
                                                                                , (String)dataSnapshot.child("videoTutorialLink").getValue()
                                                                                , new ArrayList<AugmentedRealityInstruction>());
                                                                        products.add(product);
                                                                        adapter.notifyDataSetChanged();
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(DatabaseError databaseError) {

                                                                    }
                                                                });
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });
                                }
                            }
                        },
                        DELAY
                );
            }
        });
    }


}
