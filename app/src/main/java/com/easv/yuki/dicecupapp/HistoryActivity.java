package com.easv.yuki.dicecupapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.view.View;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<BERoll> diceList;
    private RecyclerView mDiceList;
    private RollListAdapter rollListAdapter;
    private Button clearButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        clearButton = findViewById(R.id.clearButton);
        backButton = findViewById(R.id.backButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDiceList.setAdapter(null);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        diceList = (ArrayList<BERoll>) getIntent().getSerializableExtra("dicelist");
        rollListAdapter = new RollListAdapter(diceList);

        mDiceList = findViewById(R.id.dice_list);
        mDiceList.setHasFixedSize(true);
        mDiceList.setLayoutManager(new LinearLayoutManager(this));
        mDiceList.setAdapter(rollListAdapter);
    }
}
