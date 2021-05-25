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

public class SubtractionActivity extends AppCompatActivity {

    private ArrayList<OperationModel> questionList = new ArrayList<>();
    private int i = 20;
    private int userResult;
    private boolean indicator = false;
    private int additionCounter;
    private int multiplicationCounter;
    private int subtractionCounter = 0;
    private int overallCounter;

    private TextView num1;
    private TextView num2;
    private EditText result;
    private ExtendedFloatingActionButton nextBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        questionList = bundle.getParcelableArrayList("Questions");
        additionCounter = intent.getIntExtra("Add Counter", 00);
        multiplicationCounter = intent.getIntExtra("Multiplication Counter", 00);
        overallCounter = intent.getIntExtra("Overall Counter", 00);

        initValues();

        generateQuestions();

        displayQuestions();

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!indicator) {
                    if (result.getText().toString().equals("")) {
                        Toast.makeText(SubtractionActivity.this, "Enter answer", Toast.LENGTH_SHORT).show();
                    } else {
                        validate();
                    }
                } else {
                    Intent intent = new Intent(SubtractionActivity.this, ResultsActivity.class);
                    intent.putParcelableArrayListExtra("Questions", questionList);
                    startActivity(intent);
                }
            }
        });
    }

    private void validate() {
        userResult = Integer.parseInt(result.getText().toString());
        questionList.get(i).setUserResult(userResult);
        if (questionList.get(i).getResult() == userResult) {
            subtractionCounter++;
            overallCounter++;
        }
        i++;
        displayQuestions();
    }

    private void displayQuestions() {
        if (i < 30) {
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
            int f = random.nextInt(900) + 100;
            int s = random.nextInt(900) + 100;
            int r = f - s;
            OperationModel question = new OperationModel();
            question.setFirstTerm(f);
            question.setSecondTerm(s);
            question.setResult(r);
            questionList.add(question);
        }
    }

    private void initValues() {
        num1 = (TextView) findViewById(R.id.num_1_subtraction);
        num2 = (TextView) findViewById(R.id.num_2_subtraction);
        result = (EditText) findViewById(R.id.result_subtraction);
        nextBTN = (ExtendedFloatingActionButton) findViewById(R.id.next_btn_subtraction);
    }

    @Override
    public void onBackPressed() {

    }
}