package com.sexology.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abhimanyus on 12/26/17.
 */

public class Question implements Serializable{




    String questionId = null;
    String userId = null;
    StringBuilder questionString = null;
    List<String> comments = new ArrayList<String>();
    String iPAddress = null;
    String answerString = null;
    Date createdDate = null;
    Date modifiedDate = null;


    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public StringBuilder getQuestionString() {
        return questionString;
    }

    public void setQuestionString(StringBuilder questionString) {
        this.questionString = questionString;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getiPAddress() {
        return iPAddress;
    }

    public void setiPAddress(String iPAddress) {
        this.iPAddress = iPAddress;
    }

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void addComment(String comment){
        comments.add(comment);
    }

}
