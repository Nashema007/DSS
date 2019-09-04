package com.example.dell.dss.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dell.dss.Modal.Stats;
import com.example.dell.dss.R;
import com.example.dell.dss.activities.AgesActivity;
import com.example.dell.dss.activities.DisabledActivity;
import com.example.dell.dss.activities.GenderActivity;
import com.example.dell.dss.activities.HostelActivity;

import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.myViewHolder> {

    List<Stats> arrayList;
    private Context mContext;


    public StatsAdapter(List<Stats> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stat_item,parent, false);
        return new StatsAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {

        holder.txtMenu.setText(arrayList.get(position).getStatDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class [] classList = new Class[]{HostelActivity.class, DisabledActivity.class, GenderActivity.class, AgesActivity.class};
                for(int i = 0; i < arrayList.size(); i++) {
                    if (holder.txtMenu.getText().equals(arrayList.get(i).getStatDescription())) {
                        Intent intent = new Intent(mContext, classList[i]);
                        mContext.startActivity(intent);
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        private TextView txtMenu;

        public myViewHolder(View itemView) {
            super(itemView);

            txtMenu = itemView.findViewById(R.id.statDescription);
        }
    }


    }
