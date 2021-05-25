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

public class SquaresActivity extends AppCompatActivity {

    private ArrayList<OperationModel> questionList = new ArrayList<>();
    private int i = 0;
    private int userResult;
    private boolean indicator = false;
    private int squareCounter = 0;
    private int overallCounter = 0;

    private TextView num1;
    private EditText result;
    private ExtendedFloatingActionButton nextBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squares);

        initValues();

        generateQuestions();

        displayQuestions();

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!indicator) {
                    if (result.getText().toString().equals("")) {
                        Toast.makeText(SquaresActivity.this, "Enter answer", Toast.LENGTH_SHORT).show();
                    } else {
                        validate();
                    }
                } else {
                    Intent intent = new Intent(SquaresActivity.this, PercentageActivity.class);
                    intent.putParcelableArrayListExtra("Questions", questionList);
                    intent.putExtra("Squares Counter", squareCounter);
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
            squareCounter = squareCounter + 1;
            overallCounter = overallCounter + 1;
        }
        i++;
        displayQuestions();
    }

    private void displayQuestions() {
        if (i < 10) {
            num1.setText("" + questionList.get(i).getFirstTerm());
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
            int f = random.nextInt(40) + 10;
            int s = f;
            int r = f * s;
            OperationModel question = new OperationModel();
            question.setFirstTerm(f);
            question.setSecondTerm(s);
            question.setResult(r);
            questionList.add(question);
        }
    }

    private void initValues() {
        num1 = (TextView) findViewById(R.id.num_1_squares);
        result = (EditText) findViewById(R.id.result_squares);
        nextBTN = (ExtendedFloatingActionButton) findViewById(R.id.next_btn_squares);
    }

    @Override
    public void onBackPressed() {

    }
}