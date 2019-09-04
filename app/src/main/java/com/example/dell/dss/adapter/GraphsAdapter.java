package com.example.dell.dss.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dell.dss.Modal.Graphs;
import com.example.dell.dss.R;

import java.util.List;

public class GraphsAdapter extends RecyclerView.Adapter<GraphsAdapter.MyViewHolder> {

    private List<Graphs> arrayList;
    private Context mContext;

    public GraphsAdapter(List<Graphs> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.description.setText(arrayList.get(position).getDescription());
        holder.amount.setText(arrayList.get(position).getAmount());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView amount;
        private TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);


            amount = itemView.findViewById(R.id.amount);
            description = itemView.findViewById(R.id.description);

        }
    }
}
