package com.softxperttask.app.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.softxperttask.app.R;
import com.softxperttask.app.data.model.CarModel;

import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private Context context;
    ArrayList<CarModel> mData;

    public CarsAdapter(Context context, ArrayList<CarModel> responses) {
        this.context = context;
        this.mData = responses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsAdapter.ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context).load(mData.get(position).getImageUrl())
                .apply(options)
                .into(holder.carLogo);
        holder.carBrand.setText(mData.get(position).getBrand());
        holder.carYear.setText(mData.get(position).getYear());
        if (mData.get(position).getUsed())
            holder.carUsed.setText(context.getResources().getString(R.string.used_car));
        else
            holder.carUsed.setText(context.getResources().getString(R.string.new_car));


    }

//    public void renderData(ArrayList<CarModel> data) {
//        mData.addAll(data);
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView carBrand;
        TextView carYear;
        TextView carUsed;
        ImageView carLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carBrand = itemView.findViewById(R.id.brand_txt);
            carYear = itemView.findViewById(R.id.year_txt);
            carUsed = itemView.findViewById(R.id.used_Tv);
            carLogo = itemView.findViewById(R.id.car_icon);
        }

    }

}