package com.example.tiagofragoso.quizapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tiagofragoso on 22/02/2017.
 */

public class QuestionAdapter extends ArrayAdapter<Question> {

    private int mFinished;

    public QuestionAdapter(Context context, ArrayList<Question> objects, int a){
        super(context, 0, objects);
        mFinished = a;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_questions, parent, false);
        }

        Question currentQuestion = getItem(position);

        TextView positionView = (TextView) listItemView.findViewById(R.id.positionView);
        positionView.setText(position + 1 + ".");

        TextView questionView = (TextView) listItemView.findViewById(R.id.questionView);
        questionView.setText(currentQuestion.getQuestion());

        LinearLayout answerCheck = (LinearLayout) listItemView.findViewById(R.id.answerCheck);
        LinearLayout correctAns = (LinearLayout) listItemView.findViewById(R.id.correctAns);
        LinearLayout item = (LinearLayout) listItemView.findViewById(R.id.item_layout);

        ImageView ansIcon = (ImageView) listItemView.findViewById(R.id.ansIcon);

        if (mFinished == 0){
            correctAns.setVisibility(View.GONE);
            answerCheck.setVisibility(View.VISIBLE);
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);
            TextView ansView = (TextView) listItemView.findViewById(R.id.answerView);
            ansIcon.setVisibility(View.GONE);

            if(currentQuestion.getAns() == 0){
                imageView.setImageResource(R.drawable.ic_clear_black_24dp);
                ansView.setText("UNANSWERED");
            } else {
                imageView.setImageResource(R.drawable.ic_check_black_24dp);
                ansView.setText("ANSWERED");
            }

            switch(currentQuestion.getCategory()){
                case "Arts":
                    item.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.ArtsTrans));
                    break;
                case "Sports":
                    item.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.SportsTrans));
                    break;
                case "Technology":
                    item.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.TechnologyTrans));
                    break;
                case "History":
                    item.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.HistoryTrans));
                    break;
                case "Geography":
                    item.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.GeographyTrans));
                    break;
            }
        } else {
            answerCheck.setVisibility(View.GONE);
            correctAns.setVisibility(View.VISIBLE);
            ansIcon.setVisibility(View.VISIBLE);

            TextView correctAnsText = (TextView) listItemView.findViewById(R.id.correctAnsText);
            correctAnsText.setText(currentQuestion.getCorrectAnsText());

            if (currentQuestion.getCorrectAns() == currentQuestion.getAns()){
                item.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.correctAns));
                ansIcon.setImageResource(R.drawable.ic_check_black_24dp);
            } else {
                item.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.wrongAns));
                ansIcon.setImageResource(R.drawable.ic_clear_black_24dp);
            }

        }


        return listItemView;
    }


}
