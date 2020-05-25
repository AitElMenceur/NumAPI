package com.example.projetandroid.presentation.view;


import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.projetandroid.R;
import com.example.projetandroid.presentation.Singletons;
import com.example.projetandroid.presentation.controller.MainController;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;

    private MainController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Resfreshdata();
            }
        });
        controller = new MainController(this, Singletons.getSharedPreferencesInstance(this));
        controller.onStart();

    }

    public void ShowList() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            input.add(Integer.toString(i));
        }
        mAdapter = new ListAdapter(input, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                controller.onItemClick(item);

            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    private void Resfreshdata() {
        refreshLayout.setRefreshing(true);
        controller.saveAllFact();
        refreshLayout.setRefreshing(false);
    }

    public void showAPIError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_LONG).show();
    }
}
