package com.example.spring_111.service;

import com.example.spring_111.dao.QuestionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import static com.example.spring_111.util.QuestionWithAnswersUtil.getQuestionWithAnswersFromLine;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    private QuestionServiceImpl sut;

    @BeforeEach
    void setup() {
        this.sut = new QuestionServiceImpl(questionDao, "/example.csv", 1);
    }

    @Test
    void shouldAddQuestionsFromFile() throws IOException, URISyntaxException {
        //when
        sut.addQuestions();

        //then
        verify(questionDao, times(3)).addQuestion(any());
    }

    @Test
    void shouldPrintAllQuestions() {
        //given
        var question = getQuestionWithAnswersFromLine("question,answer");
        when(questionDao.getAllQuestions()).thenReturn(List.of(question));
        var printStream = mock(PrintStream.class);

        //when
        sut.printAllQuestionsWithAnswers(printStream);

        //then
        verify(questionDao, times(1)).getAllQuestions();
        verify(printStream, times(1)).println(question);
    }

    @Test
    void shouldReturnTrue_whenTestingPassed() {
        //given
        var question = getQuestionWithAnswersFromLine("question,answer1, answer2");
        when(questionDao.getAllQuestions()).thenReturn(List.of(question));
        var printStream = mock(PrintStream.class);
        var scanner = new Scanner(new ByteArrayInputStream("First Last\nanswer1".getBytes()));

        //when
        var result = sut.conductTesting(printStream, scanner);

        //then
        assertTrue(result);
        verify(questionDao, times(1)).getAllQuestions();
    }

    @Test
    void shouldReturnFalse_whenTestingNotPassed() {
        //given
        var question = getQuestionWithAnswersFromLine("question,answer1, answer2");
        when(questionDao.getAllQuestions()).thenReturn(List.of(question));
        var printStream = mock(PrintStream.class);
        var scanner = new Scanner(new ByteArrayInputStream("First Last\nanswer2".getBytes()));

        //when
        var result = sut.conductTesting(printStream, scanner);

        //then
        assertFalse(result);
        verify(questionDao, times(1)).getAllQuestions();
    }
}