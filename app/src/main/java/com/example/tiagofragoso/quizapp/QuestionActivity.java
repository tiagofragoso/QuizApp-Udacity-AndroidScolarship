package com.example.tiagofragoso.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    final ArrayList<Question> questions = new ArrayList<Question>();
    private int questId;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private Question currentQuestion;
    private ProgressBar progBar;
    private TextView questCnt;
    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(listener2);
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questions.add(new Question("Arts", getString(R.string.question1), getString(R.string.question1_ans1),
                getString(R.string.question1_ans2), getString(R.string.question1_ans3), getString(R.string.question1_ans4), 2));

        questions.add(new Question("Sports", getString(R.string.question2), getString(R.string.question2_ans1),
                getString(R.string.question2_ans2), getString(R.string.question2_ans3), getString(R.string.question2_ans4), 3));

        questions.add(new Question("Technology", getString(R.string.question3), getString(R.string.question3_ans1),
                getString(R.string.question3_ans2), getString(R.string.question3_ans3), getString(R.string.question3_ans4), 1));

        questions.add(new Question("History", getString(R.string.question4), getString(R.string.question4_ans1),
                getString(R.string.question4_ans2), getString(R.string.question4_ans3), getString(R.string.question4_ans4), 4));

        questions.add(new Question("Geography", getString(R.string.question5), getString(R.string.question5_ans1),
                getString(R.string.question5_ans2), getString(R.string.question5_ans3), getString(R.string.question5_ans4), 4));

        questions.add(new Question("Arts", getString(R.string.question6), getString(R.string.question6_ans1),
                getString(R.string.question6_ans2), getString(R.string.question6_ans3), getString(R.string.question6_ans4), 3));

        questions.add(new Question("Sports", getString(R.string.question7), getString(R.string.question7_ans1),
                getString(R.string.question7_ans2), getString(R.string.question7_ans3), getString(R.string.question7_ans4), 1));

        questions.add(new Question("Technology", getString(R.string.question8), getString(R.string.question8_ans1),
                getString(R.string.question8_ans2), getString(R.string.question8_ans3), getString(R.string.question8_ans4), 4));

        questions.add(new Question("History", getString(R.string.question9), getString(R.string.question9_ans1),
                getString(R.string.question9_ans2), getString(R.string.question9_ans3), getString(R.string.question9_ans4), 2));

        questions.add(new Question("Geography", getString(R.string.question10), getString(R.string.question10_ans1),
                getString(R.string.question10_ans2), getString(R.string.question10_ans3), getString(R.string.question10_ans4), 2));


        questId = 0;

        progBar = (ProgressBar) findViewById(R.id.progressBar);
        progBar.setProgress(1);
        progBar.setMax(questions.size());

        questCnt = (TextView) findViewById(R.id.questionCnt);

        setView();

        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);

    }

    // SETS THE VIEW FOR EACH QUESTION OR SUMMARY VIEW
    public void setView() {
        LinearLayout actLayout = (LinearLayout) findViewById(R.id.actionLayout);
        View separator = findViewById(R.id.sep);
        ListView listView = (ListView) findViewById(R.id.activity_question_list);
        TextView finishBut = (TextView) findViewById(R.id.finishButton);
        TextView previousButton = (TextView) findViewById(R.id.prevBut);
        RelativeLayout appBar = (RelativeLayout) findViewById(R.id.appBar);
        TextView appBarTitle = (TextView) findViewById(R.id.catName);
        ImageView appBarIcon = (ImageView) findViewById(R.id.catIcon);
        TextView nextButton = (TextView) findViewById(R.id.nextBut);

        if (questId == questions.size()) {
            QuestionAdapter questionAdapter = new QuestionAdapter(this, questions, 0);
            listView.setAdapter(questionAdapter);
            listView.setDividerHeight(0);
            findViewById(R.id.activity_question1).setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            finishBut.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.GONE);
            previousButton.setVisibility(View.GONE);
            separator.setVisibility(View.GONE);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    questId = i;
                    setView();
                }
            });

            appBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, null));
            appBarTitle.setText("Summary");
            appBarIcon.setImageResource(R.drawable.summary);

            actLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark, null));


        } else {
            findViewById(R.id.activity_question1).setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            finishBut.setVisibility(View.GONE);
            nextButton.setVisibility(View.VISIBLE);


            if (questId == 0) {
                previousButton.setVisibility(View.GONE);
                separator.setVisibility(View.GONE);

            } else {
                separator.setVisibility(View.VISIBLE);
                previousButton.setVisibility(View.VISIBLE);
            }

            currentQuestion = questions.get(questId);
            int questN = questId + 1;
            progBar.setProgress(questN);
            questCnt.setText("Question " + questN + "/" + questions.size());

            String type = currentQuestion.getCategory();
            int color = 0;
            int questBorder = 0;
            int questNumBorder = 0;
            int icon = 0;
            String title = " ";

            switch (type) {
                case "Arts":
                    color = R.color.Arts;
                    title = "Arts";
                    questBorder = R.drawable.border_arts;
                    questNumBorder = R.drawable.border_question_arts;
                    icon = R.drawable.arts;
                    break;

                case "Sports":
                    color = R.color.Sports;
                    title = "Sports";
                    questBorder = R.drawable.border_sports;
                    questNumBorder = R.drawable.border_question_sports;
                    icon = R.drawable.soccer;
                    break;

                case "Technology":
                    color = R.color.Technology;
                    title = "Technology";
                    questBorder = R.drawable.border_tech;
                    questNumBorder = R.drawable.border_question_tech;
                    icon = R.drawable.tech;
                    break;

                case "History":
                    color = R.color.History;
                    title = "History";
                    questBorder = R.drawable.border_history;
                    questNumBorder = R.drawable.border_question_history;
                    icon = R.drawable.history;
                    break;

                case "Geography":
                    color = R.color.Geography;
                    title = "Geography";
                    questBorder = R.drawable.border_geography;
                    questNumBorder = R.drawable.border_question_geography;
                    icon = R.drawable.geography;
                    break;
            }

            appBar.setBackgroundColor(getResources().getColor(color, null));
            appBarTitle.setText(title);
            appBarIcon.setImageResource(icon);

            actLayout.setBackgroundColor(getResources().getColor(color, null));

            TextView questionName = (TextView) findViewById(R.id.questName);
            questionName.setText(currentQuestion.getQuestion());
            questionName.setBackground(getApplicationContext().getDrawable(questBorder));

            TextView questNum = (TextView) findViewById(R.id.questNum);
            questNum.setText(questId + 1 + "");
            questNum.setBackground(getApplicationContext().getDrawable(questNumBorder));

            RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
            RadioGroup rg2 = (RadioGroup) findViewById(R.id.radioGroup2);

            if (currentQuestion.getAns() != 0) {
                int id = 0;
                switch (currentQuestion.getAns()) {
                    case 1:
                        id = R.id.radioButton1;
                        rg1.check(id);
                        break;
                    case 2:
                        id = R.id.radioButton2;
                        rg2.check(id);
                        break;
                    case 3:
                        id = R.id.radioButton3;
                        rg1.check(id);
                        break;
                    case 4:
                        id = R.id.radioButton4;
                        rg2.check(id);
                        break;
                }

            } else {
                rg1.clearCheck();
                rg2.clearCheck();
            }


            RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);
            rb1.setText(currentQuestion.getAnsA());

            RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
            rb2.setText(currentQuestion.getAnsB());

            RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
            rb3.setText(currentQuestion.getAnsC());

            RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4);
            rb4.setText(currentQuestion.getAnsD());
        }
    }

    // NEXT QUESTION (SENDER: nextButton)
    public void next(View view) {
        if (getInt() != -1) {
            currentQuestion.setAns(getInt());
        }
        questId++;
        setView();
    }

    // PREVIOUS QUESTION (SENDER: previousButton)
    public void previous(View view) {
        if (getInt() != -1) {
            currentQuestion.setAns(getInt());
        }
        questId--;
        setView();
    }

    // GETS CHECKED RADIO BUTTON (RETURNS int OF CHECKED ANSWER)
    public int getInt() {
        int butid = -1;
        int chkId1 = rg1.getCheckedRadioButtonId();
        int chkId2 = rg2.getCheckedRadioButtonId();
        int realCheck = chkId1 == -1 ? chkId2 : chkId1;
        switch (realCheck) {
            case R.id.radioButton1:
                butid = 1;
                break;
            case R.id.radioButton2:
                butid = 2;
                break;
            case R.id.radioButton3:
                butid = 3;
                break;
            case R.id.radioButton4:
                butid = 4;
                break;
        }
        return butid;
    }

    // COMPUTES SCORE OF CORRECT ANSWERS (RETURNS INT = NUM OF CORRECT ANSWERS)
    public int getScore() {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question quest = questions.get(i);
            if (quest.getAns() == quest.getCorrectAns()) {
                score++;
            }
        }
        return score;
    }

    // COMPUTES NUMBER OF ANSWERED QUESTIONS (RETURNS INT = NUM OF ANSWERED QUESTIONS)
    public int getAnswered() {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question quest = questions.get(i);
            if (quest.getAns() != 0) {
                score++;
            }
        }
        return score;
    }

    // SETS THE FINAL VIEW  WITH SCORE AND CORRECT ANSWERS
    public void checkAnswers() {
        TextView appBarTitle = (TextView) findViewById(R.id.catName);
        ImageView appBarIcon = (ImageView) findViewById(R.id.catIcon);
        TextView finishBut = (TextView) findViewById(R.id.finishButton);
        finishBut.setVisibility(View.GONE);
        ListView listView = (ListView) findViewById(R.id.activity_question_list);
        QuestionAdapter questionAdapter = new QuestionAdapter(this, questions, 1);
        listView.setAdapter(questionAdapter);
        listView.setDividerHeight(2);
        listView.setOnItemClickListener(null);
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6));
        int score = getScore();
        int total = questions.size();

        appBarTitle.setText("Results");
        appBarIcon.setImageResource(R.drawable.results);

        TextView questScore = (TextView) findViewById(R.id.score);
        questScore.setText(score + "");

        TextView questTotal = (TextView) findViewById(R.id.total);
        questTotal.setText(total + "");

        TextView message = (TextView) findViewById(R.id.result);

        if (score <= (total / 2)) {
            message.setText("You can do better! Try again!");
        } else if (score <= (2 * total / 3)) {
            message.setText("Great job!");
        } else {
            message.setText("Awesome work! Congrats!");
        }
        findViewById(R.id.resetButton).setVisibility(View.VISIBLE);
        findViewById(R.id.scoreLayout).setVisibility(View.VISIBLE);
    }

    // SETS THE VIEW FOR QUESTION SUMMARY AND CHECKS IF ALL QUESTIONS WERE ANSWERED (SENDER: finishButton)
    public void finish(View view) {
        if (getAnswered() == questions.size()) {
            checkAnswers();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Incomplete quiz")
                    .setMessage("You have not answered all of the questions. Are you sure you want to submit your answers?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            checkAnswers();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(R.drawable.ic_warning_black_24dp)
                    .show();
        }
    }

    // RESETS THE GAME (SENDER: resetButton)
    public void reset(View view) {
        Intent i = new Intent(QuestionActivity.this, MainActivity.class);
        startActivity(i);
    }
}
