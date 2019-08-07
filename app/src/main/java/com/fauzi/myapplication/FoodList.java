package com.fauzi.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fauzi.myapplication.Interface.ItemClickListerner;
import com.fauzi.myapplication.Model.Category;
import com.fauzi.myapplication.Model.Food;
import com.fauzi.myapplication.ViewHolder.FoodViewHolder;
import com.fauzi.myapplication.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String categoryId="";

    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent Here
        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        assert categoryId != null;
        if(!categoryId.isEmpty())
        {
            loadListFood(categoryId);
        }
    }

    private void loadListFood(String categoryId) {

        FirebaseRecyclerOptions<Food> options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(foodList.orderByChild("MenuId").equalTo(categoryId),Food.class)
                .setLifecycleOwner(this)
                .build();

        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options)
         {

             @NonNull
             @Override
             public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 return new FoodViewHolder(LayoutInflater.from(parent.getContext())
                         .inflate(R.layout.food_item, parent, false));
             }

            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i, @NonNull Food food) {
                foodViewHolder.food_name.setText(food.getName());
                Picasso.with(getBaseContext()).load(food.getImage()).into(foodViewHolder.food_image);

                final Food local = food;
                foodViewHolder.setItemClickListerner(new ItemClickListerner() {
                    @Override
                    public void onClik(View view, int position, boolean isLongClick) {
                       // Toast.makeText(FoodList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();
                        //Start New Activity
                        Intent foodDetail = new Intent(FoodList.this,FoodDetail.class);
                        foodDetail.putExtra("FoodId",adapter.getRef(position).getKey()); // Send Food ID to new Activity
                        startActivity(foodDetail);

                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);

    }
}
