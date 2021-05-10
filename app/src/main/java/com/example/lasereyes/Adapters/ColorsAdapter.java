package com.example.lasereyes.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lasereyes.R;

import java.util.List;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder>  {

    List<String> titles;
    LayoutInflater inflater;
    public ColorsAdapter(Context ctx, List<String> titles)
    {
        this.titles = titles;
        this.inflater = LayoutInflater.from(ctx);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String color= titles.get(position);
        holder.title.setCardBackgroundColor(Color.parseColor(color));
        Log.d("test","inside");
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView title;
        public  ViewHolder (@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.card);

        }
    }
}