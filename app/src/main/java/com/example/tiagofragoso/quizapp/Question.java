package com.example.tiagofragoso.quizapp;

import java.util.ArrayList;

/**
 * Created by tiagofragoso on 21/02/2017.
 */

public class Question extends ArrayList {

    private String mCategory;

    private String mQuest;

    private String mAnsA;

    private String mAnsB;

    private String mAnsC;

    private String mAnsD;

    private int mCorrectAns;

    private int mAns;

   public Question(String category, String quest, String ansA, String ansB, String ansC, String ansD, int correctAns){
       mCategory = category;
       mQuest = quest;
       mAnsA = ansA;
       mAnsB = ansB;
       mAnsC = ansC;
       mAnsD = ansD;
       mCorrectAns = correctAns;
       mAns = 0;
   }

    public String getCategory(){
        return mCategory;
    }

    public String getQuestion() {
        return mQuest;
    }

    public String getAnsA() {
        return mAnsA;
    }

    public String getAnsB() {
        return mAnsB;
    }

    public String getAnsC() {
        return mAnsC;
    }

    public String getAnsD() {
        return mAnsD;
    }

    public int getCorrectAns(){
        return mCorrectAns;
    }

    public int getAns(){
        return mAns;
    }

    public void setAns(int ans) {
        mAns = ans;
    }

    public String getCorrectAnsText(){
        String ans = " ";
        switch (mCorrectAns){
            case 1:
                ans = getAnsA();
                break;
            case 2:
                ans = getAnsB();
                break;
            case 3:
                ans = getAnsC();
                break;
            case 4:
                ans = getAnsD();
                break;
        }
        return ans;
    }

}
