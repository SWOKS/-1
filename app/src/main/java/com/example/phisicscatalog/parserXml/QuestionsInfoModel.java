package com.example.phisicscatalog.parserXml;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RadioButton;

import java.util.List;

public class QuestionsInfoModel {
    private String question;
    private List<String> answerList;
    private int rightAnswer;
    public int userAnswer=-1;

   

    public QuestionsInfoModel(String question, List<String> answerList, int rightAnswer) {
        this.question = question;
        this.answerList = answerList;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }



}
