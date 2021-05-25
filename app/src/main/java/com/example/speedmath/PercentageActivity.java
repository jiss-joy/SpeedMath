package com.example.speedmath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class PercentageActivity extends AppCompatActivity {

    private ArrayList<OperationModel> questionList = new ArrayList<>();
    private ArrayList<Integer> fractionList = new ArrayList<>();
    private int i = 10;
    private double userResult;
    private boolean indicator = false;
    private int squareCounter;
    private int fractionCounter = 0;
    private int overallCounter;

    private TextView num1;
    private TextView num2;
    private EditText result;
    private ExtendedFloatingActionButton nextBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        questionList = bundle.getParcelableArrayList("Questions");
        squareCounter = intent.getIntExtra("Square Counter", 00);
        overallCounter = intent.getIntExtra("Overall Counter", 00);

        initValues();

        generateQuestions();

        displayQuestions();

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!indicator) {
                    if (result.getText().toString().equals("")) {
                        Toast.makeText(PercentageActivity.this, "Enter answer", Toast.LENGTH_SHORT).show();
                    } else {
                        validate();
                    }
                } else {
                    Intent intent = new Intent(PercentageActivity.this, IntermediateResultsActivity.class);
                    intent.putParcelableArrayListExtra("Questions", questionList);
                    intent.putExtra("Square Counter", squareCounter);
                    intent.putExtra("Fraction Counter", fractionCounter);
                    intent.putExtra("Overall Counter", overallCounter);
                    startActivity(intent);
                }
            }
        });
    }


    private void validate() {
        userResult = Double.parseDouble(result.getText().toString());
        questionList.get(i).setUserResult(userResult);
        if (questionList.get(i).getResult() == userResult) {
            fractionCounter++;
            overallCounter++;
        }
        i++;
        displayQuestions();
    }

    private void displayQuestions() {
        if (i < 20) {
            num1.setText("" + questionList.get(i).getFirstTerm());
            num2.setText("" + questionList.get(i).getSecondTerm());
            result.setText("");
            result.setFocusable(true);
        } else {
            result.setEnabled(false);
            nextBTN.setText("NEXT SECTION");
            nextBTN.setFocusable(true);
            indicator = true;
        }
    }

    private void generateQuestions() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int k = random.nextInt(19);
            int s = fractionList.get(k);
            int f = random.nextInt(9) + 1;
            double r = (double) (f * 100) / s;
            r = Double.parseDouble(String.format("%.2f%n", r));
            System.out.println(r);
            OperationModel question = new OperationModel();
            question.setFirstTerm(f);
            question.setSecondTerm(s);
            question.setResult(r);
            questionList.add(question);
        }
    }

    private void initValues() {
        num1 = (TextView) findViewById(R.id.fraction_num_1);
        num2 = (TextView) findViewById(R.id.fraction_num_2);
        result = (EditText) findViewById(R.id.result_percentage);
        nextBTN = (ExtendedFloatingActionButton) findViewById(R.id.next_btn_fractions);

        initFractions();
    }

    private void initFractions() {
        fractionList.add(2);
        fractionList.add(3);
        fractionList.add(4);
        fractionList.add(5);
        fractionList.add(6);
        fractionList.add(7);
        fractionList.add(8);
        fractionList.add(9);
        fractionList.add(10);
        fractionList.add(11);
        fractionList.add(12);
        fractionList.add(15);
        fractionList.add(16);
        fractionList.add(20);
        fractionList.add(24);
        fractionList.add(25);
        fractionList.add(30);
        fractionList.add(40);
        fractionList.add(60);
    }

    @Override
    public void onBackPressed() {

    }
}