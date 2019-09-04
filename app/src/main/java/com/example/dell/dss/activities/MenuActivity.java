package com.example.dell.dss.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dell.dss.Modal.MenuData;
import com.example.dell.dss.R;
import com.example.dell.dss.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MenuAdapter menuAdapter;

    public static void startIntent(Context context) {

        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);

    }

    public static void startIntent(Context context, int flags) {
        Intent intent = new Intent(context, MenuActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }



    private int icon[] = {R.drawable.icons8studentmale96, R.drawable.icons8graduate96};
    private String txt[] = {"Male", "Female"};

    private List<MenuData> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        int count = 0;

        for (String title : txt ){
            arrayList.add(new MenuData(title, icon[count]));
            count ++;
        }

        menuAdapter = new MenuAdapter(arrayList, this);
        recyclerView.setAdapter(menuAdapter);


    }


}
