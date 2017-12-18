package com.caizilong.android.geoquiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;


    private Question[] mQuestionBank  = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)


    };

    private int mCurrentIndex = 0;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }


        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        updateQuestion();
        mNextButton = (Button)findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
               updateQuestion();

            }
        });


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });


        Log.d(TAG, "onCreate: ");
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Log.i(TAG, "onSaveInstanceState: ");

        outState.putInt(KEY_INDEX, mCurrentIndex);


    }

    private void checkAnswer(boolean userPressedTrue){

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;


        }else {

            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();

    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();

        mQuestionTextView.setText(question);
    }

}
