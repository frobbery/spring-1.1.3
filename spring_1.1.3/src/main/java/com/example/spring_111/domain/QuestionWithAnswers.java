package com.example.spring_111.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Data
public class QuestionWithAnswers {
    private final String question;

    private final String rightAnswer;

    private final List<String> answers;

    @Override
    public String toString() {
        StringBuilder printedQuestion = new StringBuilder();
        printedQuestion.append(this.question).append("?\n");
        shuffleAnswers();
        for (int i = 0; i < answers.size(); i++) {
            printedQuestion.append((i + 1)).append(". ").append(answers.get(i)).append("\n");
        }
        return printedQuestion.toString();
    }

    private void shuffleAnswers() {
        var random = new Random();
        answers.sort(Comparator.comparingInt(s -> random.nextInt(3) - 1));
    }
}
