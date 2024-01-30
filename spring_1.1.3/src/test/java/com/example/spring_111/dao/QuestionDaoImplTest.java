package com.example.spring_111.dao;

import org.junit.jupiter.api.Test;

import static com.example.spring_111.util.QuestionWithAnswersUtil.getQuestionWithAnswersFromLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionDaoImplTest {

    private final QuestionDaoImpl sut = new QuestionDaoImpl();

    @Test
    void shouldAddQuestion() {
        //when
        sut.addQuestion(getQuestionWithAnswersFromLine("someQuestion,someAnswer"));

        //then
        assertEquals(sut.getAllQuestions().size(), 1);
    }

    @Test
    void shouldGetAllQuestions() {
        //given
        sut.addQuestion(getQuestionWithAnswersFromLine("someQuestion,someAnswer"));

        //when
        var result = sut.getAllQuestions();

        //then
        assertEquals(result.size(), 1);
        var questionWithAnswers = result.get(0);
        assertEquals(questionWithAnswers.getQuestion(), "someQuestion");
        assertEquals(questionWithAnswers.getAnswers().size(), 1);
        assertEquals(questionWithAnswers.getAnswers().get(0), "someAnswer");
    }
}