package com.example.dell.dss.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dell.dss.Modal.Stats;
import com.example.dell.dss.R;
import com.example.dell.dss.adapter.StatsAdapter;
import com.example.dell.dss.utilities.SharedPref;

import java.util.ArrayList;
import java.util.List;



public class StatsActivity extends AppCompatActivity {


    public static void startIntent(Context context) {

        Intent intent = new Intent(context, StatsActivity.class);
        context.startActivity(intent);

    }

    public static void startIntent(Context context, int flags) {
        Intent intent = new Intent(context, StatsActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private StatsAdapter statsAdapter;
    private SharedPref sharedPref = new SharedPref(this);


    private String menu[] = {"1.Hostels", "2.Disabled", "3.Gender", "4.Ages" };
    private List<Stats> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.statsRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        for (String title : menu ){
            arrayList.add(new Stats(title));
        }

        statsAdapter = new StatsAdapter(arrayList, this);
        recyclerView.setAdapter(statsAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater=  getMenuInflater();
        menuInflater.inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if ( id == R.id.nav_logout){

            sharedPref.clear();
            LoginActivity.startIntent(this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


        }

        return super.onOptionsItemSelected(item);
    }
}
