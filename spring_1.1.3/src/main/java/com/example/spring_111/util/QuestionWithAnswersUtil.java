package com.example.spring_111.util;

import com.example.spring_111.domain.QuestionWithAnswers;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class QuestionWithAnswersUtil {

    public static QuestionWithAnswers getQuestionWithAnswersFromLine(String line) {
        String[] separatedStrings = line.split(",");
        var question = separatedStrings[0];
        var answers = Arrays.stream(separatedStrings).skip(1).collect(Collectors.toList());
        return new QuestionWithAnswers(question, answers.get(0), answers);
    }
}
