package com.example.speedmath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class IntermediateResultsActivity extends AppCompatActivity {

    private ArrayList<OperationModel> questionList = new ArrayList<>();
    private IntermediateAdapter mAdapter;
    private int squareCounter;
    private int fractionCounter;
    private int overallCounter;

    private RecyclerView resultRecycler;
    private ExtendedFloatingActionButton finishBTN;
    private TextView squareAcc;
    private TextView fracAcc;
    private TextView overAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        questionList = bundle.getParcelableArrayList("Questions");
        squareCounter = intent.getIntExtra("Square Counter", 00);
        fractionCounter = intent.getIntExtra("Fraction Counter", 00);
        overallCounter = intent.getIntExtra("Overall Counter", 00);

        initValues();

        displayResults();

        setUpRecycler();

        finishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntermediateResultsActivity.this, MainActivity.class));
            }
        });
    }

    private void setUpRecycler() {
        mAdapter = new IntermediateAdapter(IntermediateResultsActivity.this, questionList);
        resultRecycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void displayResults() {
        squareAcc.setText(((double) squareCounter * 100) / 10 + "%");
        fracAcc.setText(((double) fractionCounter * 100) / 10 + "%");
        overAcc.setText(((double) overallCounter * 100) / 20 + "%");
    }

    private void initValues() {
        finishBTN = (ExtendedFloatingActionButton) findViewById(R.id.intermediate_finish_btn);
        resultRecycler = (RecyclerView) findViewById(R.id.intermediate_result_recycler);
        squareAcc = (TextView) findViewById(R.id.intermediate_square_accuracy);
        fracAcc = (TextView) findViewById(R.id.intermediate_fraction_accuracy);
        overAcc = (TextView) findViewById(R.id.intermediate_overall_accuracy);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        resultRecycler.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onBackPressed() {

    }
}