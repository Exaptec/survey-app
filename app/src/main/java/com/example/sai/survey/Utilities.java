package com.example.sai.survey;

import com.qihancloud.opensdk.function.beans.speech.Grammar;

/**
 * Created by sai on 21/09/2017.
 */

public class Utilities {


    static boolean  checkAnswers(Grammar grammar){
        String answer=grammar.getText();
        if(answer.equals("Yes")||answer.equals("No"))
            return true;
        else
            return false;
    }
}
