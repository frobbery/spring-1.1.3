package com.example.spring_111.dao;

import com.example.spring_111.domain.QuestionWithAnswers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final List<QuestionWithAnswers> questions = new ArrayList<>();

    @Override
    public void addQuestion(QuestionWithAnswers question) {
        questions.add(question);
    }

    @Override
    public List<QuestionWithAnswers> getAllQuestions() {
        return questions;
    }
}
