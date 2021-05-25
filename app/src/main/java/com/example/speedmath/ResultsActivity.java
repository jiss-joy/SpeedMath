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

public class ResultsActivity extends AppCompatActivity {

    private ArrayList<OperationModel> questionList = new ArrayList<>();
    private ResultAdapter mAdapter;
    private int additionCounter;
    private int multiplicationCounter;
    private int subtractionCounter;
    private int overallCounter;

    private RecyclerView resultRecycler;
    private ExtendedFloatingActionButton finishBTN;
    private TextView addAcc;
    private TextView mulAcc;
    private TextView subAcc;
    private TextView overAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        questionList = bundle.getParcelableArrayList("Questions");
        additionCounter = intent.getIntExtra("Add Counter", 00);
        multiplicationCounter = intent.getIntExtra("Multiplication Counter", 00);
        subtractionCounter = intent.getIntExtra("Subtraction Counter", 00);
        overallCounter = intent.getIntExtra("Overall Counter", 00);

        initValues();

        displayResults();

        setUpRecycler();

        finishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsActivity.this, MainActivity.class));
            }
        });

    }

    private void setUpRecycler() {
        mAdapter = new ResultAdapter(ResultsActivity.this, questionList);
        resultRecycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void displayResults() {
        addAcc.setText(((double) additionCounter * 100) / 10 + "%");
        subAcc.setText(((double) multiplicationCounter * 100) / 10 + "%");
        mulAcc.setText(((double) subtractionCounter * 100) / 10 + "%");
        overAcc.setText(((double) overallCounter * 100) / 30 + "%");
    }

    private void initValues() {
        finishBTN = (ExtendedFloatingActionButton) findViewById(R.id.finish_btn);
        resultRecycler = (RecyclerView) findViewById(R.id.result_recycler);
        addAcc = (TextView) findViewById(R.id.addition_accuracy);
        subAcc = (TextView) findViewById(R.id.multiplication_accuracy);
        mulAcc = (TextView) findViewById(R.id.subtraction_accuracy);
        overAcc = (TextView) findViewById(R.id.overall_accuracy);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        resultRecycler.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onBackPressed() {

    }
}