package com.example.quizzer.Models;

public class QuestionModel {
    private String correctAns;
    private String optA;
    private String optB;
    private String optC;
    private String optD;
    private String question;
    int setNo;

    public QuestionModel(String question2, String optA2, String optB2, String optC2, String optD2, String correctAns2, int setNo2) {
        this.question = question2;
        this.optA = optA2;
        this.optB = optB2;
        this.optC = optC2;
        this.optD = optD2;
        this.correctAns = correctAns2;
        this.setNo = setNo2;
    }

    public QuestionModel() {
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question2) {
        this.question = question2;
    }

    public String getOptA() {
        return this.optA;
    }

    public void setOptA(String optA2) {
        this.optA = optA2;
    }

    public String getOptB() {
        return this.optB;
    }

    public void setOptB(String optB2) {
        this.optB = optB2;
    }

    public String getOptC() {
        return this.optC;
    }

    public void setOptC(String optC2) {
        this.optC = optC2;
    }

    public String getOptD() {
        return this.optD;
    }

    public void setOptD(String optD2) {
        this.optD = optD2;
    }

    public String getCorrectAns() {
        return this.correctAns;
    }

    public void setCorrectAns(String correctAns2) {
        this.correctAns = correctAns2;
    }

    public String getOptionByPosition(int position) {
        if (position == 0) {
            return this.optA;
        }
        if (position == 1) {
            return this.optB;
        }
        if (position == 2) {
            return this.optC;
        }
        if (position == 3) {
            return this.optD;
        }
        return "Error";
    }

    public int getSetNo() {
        return this.setNo;
    }

    public void setSetNo(int setNo2) {
        this.setNo = setNo2;
    }
}
