package com.example.spring_111.dao;

import com.example.spring_111.domain.QuestionWithAnswers;

import java.util.List;

public interface QuestionDao {

    void addQuestion(QuestionWithAnswers question);

    List<QuestionWithAnswers> getAllQuestions();
}
