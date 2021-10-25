package com.example.pierwszaapka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "pl.edu.pb.wi.quiz.correctAnswer";

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG, "Wywołana została metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }


    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;
    private int currentIndex = 0;
    private static final String QUIZ_TAG = "MainActivity";

    private Question[] questions = new Question[] {
            new Question(R.string.q_activity, true),
            new Question(R.string.q_version, false),
            new Question(R.string.q_szkolna, false),
            new Question(R.string.q_comparission, true),
            new Question(R.string.q_guitar, true)
    };

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onDestroy");
    }

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
        } else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onCreate");
            setContentView(R.layout.activity_main);

            if(savedInstanceState != null){
                currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
            }
            trueButton = findViewById(R.id.true_button);
            falseButton = findViewById(R.id.false_button);
            nextButton = findViewById(R.id.next_button);
            questionTextView = findViewById(R.id.question_text_view);
            promptButton = findViewById(R.id.prompt_button);

            trueButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    checkAnswerCorrectness(true);
                }
            });

            falseButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    checkAnswerCorrectness(false);
                }
            });


            falseButton.setOnClickListener(v -> checkAnswerCorrectness(false));

            promptButton.setOnClickListener((v) -> {
                Intent intent = new Intent(MainActivity.this, PromptActivity.class);
                boolean correctAnswer = questions[currentIndex].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                startActivity(intent);
            });

            nextButton.setOnClickListener(v -> {
                currentIndex = (currentIndex+1)%questions.length;
                setNextQuestion();
            });
            setNextQuestion();




    }
}