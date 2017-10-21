package com.example.sai.survey.Model;

import java.util.ArrayList;

/**
 * Created by sai on 20/09/2017.
 */

public class Questions {
    public static ArrayList<String> getQuestionsList() {

        ArrayList<String> questionsList = new ArrayList<String>();
        questionsList.add("For lunch today how would you rate the food choice?");
        questionsList.add("For lunch today how would you rate the flavour?");
        questionsList.add("For lunch today how would you rate the food quality?");
        return questionsList;
    }


}
