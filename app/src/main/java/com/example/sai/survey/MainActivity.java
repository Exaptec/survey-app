package com.example.sai.survey;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sai.survey.Model.Questions;
import com.qihancloud.opensdk.base.TopBaseActivity;
import com.qihancloud.opensdk.beans.FuncConstant;
import com.qihancloud.opensdk.function.beans.speech.Grammar;
import com.qihancloud.opensdk.function.unit.SpeechManager;
import com.qihancloud.opensdk.function.unit.interfaces.speech.RecognizeListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends TopBaseActivity {
    @Bind(R.id.tv_question)
    TextView questionTv;
    /* @Bind(R.id.chk_Yes)
     CheckBox yesChkbox;
     @Bind(R.id.chk_No)
     CheckBox noChkbox;*/
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.btn_startSurvey)
    Button startSurveyBtn;
    SpeechManager speechManager;
    private int count;
    private ArrayList<String> questionsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        speechManager = (SpeechManager) getUnitManager(FuncConstant.SPEECH_MANAGER);
        questionsList = Questions.getQuestionsList();
        /*setlistener(yesChkbox);
        setlistener(noChkbox);*/
        setlistener(ratingBar);
        count = 0;
        startSurveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askQuestions();
                Log.d("speechtotext", "listening");

            }
        });
        speechManager.setOnSpeechListener(new RecognizeListener() {
            @Override
            public boolean onRecognizeResult(Grammar grammar) {
                Log.d("speechtotext", grammar.getText());
                if (Utilities.checkAnswers(grammar))
                    askQuestions();
                return true;
            }

            @Override
            public void onRecognizeVolume(int i) {

            }
        });
        String matcher = "yes";
        String text = "of course yes for fy es";
        if (text.matches(matcher))
            Log.d("matched", "true");
    }

    private void checkAnswer() {
    }

    @Override
    protected void onMainServiceConnected() {

    }

    private void setlistener(RatingBar rBar) {
        rBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        askQuestions();
                    }
                });

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        askQuestions();
    }

    private void askQuestions() {
        Log.d("count", String.valueOf(count));
        String question = "Thank you for answering the survey";
        if (count < questionsList.size()) {
            question = questionsList.get(count);
            speechManager.startSpeak(question);
            questionTv.setText(question);
           /* yesChkbox.setChecked(false);
            noChkbox.setChecked(false);*/
            count++;
        } else {
            count = 0;
            questionTv.setText(question);

        }
    }


}
