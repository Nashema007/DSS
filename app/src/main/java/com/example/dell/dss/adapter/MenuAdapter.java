package com.example.dell.dss.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dell.dss.Modal.MenuData;
import com.example.dell.dss.R;
import com.example.dell.dss.activities.DetailActivity;
import com.example.dell.dss.utilities.Constants;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.myViewHolder>{

    List<MenuData> arrayList;
    private Context mContext;


    public MenuAdapter(List<MenuData> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent, false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {

        holder.imgDescription.setImageResource(arrayList.get(position).getImgDescription());
        holder.txtDescription.setText(arrayList.get(position).getTxtDesctiption());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class [] classList = new Class[]{DetailActivity.class, DetailActivity.class};
                for(int i = 0; i < arrayList.size(); i++){
                    if(holder.txtDescription.getText().equals(arrayList.get(i).getTxtDesctiption())){
                        Intent intent = new Intent(mContext, classList[i]);
                       if(arrayList.get(i).getTxtDesctiption().equals(Constants.MALE)){
                            intent.putExtra(Constants.MALE, Constants.MALE);
                            mContext.startActivity(intent);
                            break;
                        }
                        else if(arrayList.get(i).getTxtDesctiption().equals(Constants.FEMALE)){
                            intent.putExtra(Constants.FEMALE, Constants.FEMALE);
                            mContext.startActivity(intent);
                        }
                        else
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

        private TextView txtDescription;
        private ImageView imgDescription;

        public myViewHolder(View itemView) {
            super(itemView);

            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgDescription = itemView.findViewById(R.id.imgDescription);
        }
    }
}
