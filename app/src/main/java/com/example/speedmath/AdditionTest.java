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

public class AdditionTest extends AppCompatActivity {

    private ArrayList<OperationModel> questionList = new ArrayList<>();
    private int i = 0;
    private int userResult;
    private boolean indicator = false;
    private int additionCounter = 0;
    private int overallCounter = 0;

    private TextView num1;
    private TextView num2;
    private EditText result;
    private ExtendedFloatingActionButton nextBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_test);

        initValues();

        generateQuestions();

        displayQuestions();

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!indicator) {
                    if (result.getText().toString().equals("")) {
                        Toast.makeText(AdditionTest.this, "Enter answer", Toast.LENGTH_SHORT).show();
                    } else {
                        validate();
                    }
                } else {
                    Intent intent = new Intent(AdditionTest.this, MultiplicationActivity.class);
                    intent.putParcelableArrayListExtra("Questions", questionList);
                    intent.putExtra("Add Counter", additionCounter);
                    intent.putExtra("Overall Counter", overallCounter);
                    startActivity(intent);
                }
            }
        });

    }

    private void validate() {
        userResult = Integer.parseInt(result.getText().toString());
        questionList.get(i).setUserResult(userResult);
        if (questionList.get(i).getResult() == userResult) {
            additionCounter++;
            overallCounter++;
        }
        i++;
        displayQuestions();
    }


    private void displayQuestions() {
        if (i < 10) {
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
            int r = f + s;
            OperationModel question = new OperationModel();
            question.setFirstTerm(f);
            question.setSecondTerm(s);
            question.setResult(r);
            questionList.add(question);
        }
    }

    private void initValues() {
        num1 = (TextView) findViewById(R.id.num_1);
        num2 = (TextView) findViewById(R.id.num_2);
        result = (EditText) findViewById(R.id.result);
        nextBTN = (ExtendedFloatingActionButton) findViewById(R.id.next_btn);
    }


    @Override
    public void onBackPressed() {

    }
}