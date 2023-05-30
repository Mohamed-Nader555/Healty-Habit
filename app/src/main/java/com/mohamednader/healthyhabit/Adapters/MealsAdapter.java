package com.mohamednader.healthyhabit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder>{

    private List<Meal> meals = new ArrayList<>();
    private static final String TAG = "Meals_Adapter";
    private OnMealClickListener onMealClickListener;
    private Context context;

    public MealsAdapter(Context context, List<Meal> meals, OnMealClickListener onMealClickListener) {
        this.meals = meals;
        this.context = context;
        this.onMealClickListener = onMealClickListener;
    }

    public void setList(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycler_meal, recyclerView, false);
        MealsAdapter.ViewHolder viewHolder = new MealsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.mealNameTxtView.setText(meal.getStrMeal());
        Glide.with(context)
                .load(meal.getStrMealThumb())
                .into(holder.mealImg);

        holder.mealFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.onFavMealClick(meal);
            }
        });

        holder.mealCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.onMealClick(Integer.parseInt(meal.getIdMeal()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mealImg, mealFavImg;
        TextView mealNameTxtView;
        CardView mealCardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            mealImg = view.findViewById(R.id.meal_thumb);
            mealFavImg = view.findViewById(R.id.meal_fav_img);
            mealNameTxtView = view.findViewById(R.id.meal_name);
            mealCardView = view.findViewById(R.id.meal_card_view);
        }

    }
}