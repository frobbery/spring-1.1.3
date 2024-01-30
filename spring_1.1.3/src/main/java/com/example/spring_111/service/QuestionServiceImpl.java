package com.example.spring_111.service;

import com.example.spring_111.dao.QuestionDao;
import com.example.spring_111.domain.QuestionWithAnswers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Scanner;

import static com.example.spring_111.util.QuestionWithAnswersUtil.getQuestionWithAnswersFromLine;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao questionDao;

    private final String questionPath;

    private final Integer passingNumber;

    public QuestionServiceImpl(QuestionDao questionDao,
                               @Value("${questions.path}") String questionPath,
                               @Value("${questions.number}") Integer passingNumber) {
        this.questionDao = questionDao;
        this.questionPath = questionPath;
        this.passingNumber = passingNumber;
    }

    public void addQuestions() throws IOException, URISyntaxException {
        var uri = Objects.requireNonNull(getClass().getResource(questionPath)).toURI();
        Files.readAllLines(Paths.get(uri)).forEach(line -> questionDao.addQuestion(getQuestionWithAnswersFromLine(line)));
    }

    @Override
    public void printAllQuestionsWithAnswers(PrintStream printStream) {
        questionDao.getAllQuestions()
                .forEach(printStream::println);
    }

    @Override
    public Boolean conductTesting(PrintStream printStream, Scanner scanner) {
        var studentName = getStudentLastAndFirstName(printStream, scanner);
        var questions = questionDao.getAllQuestions();
        int numberOfPoints = 0;
        for (QuestionWithAnswers question : questions) {
            printStream.println(question);
            var answer = scanner.nextLine();
            if (question.getRightAnswer().equals(answer)) {
                numberOfPoints++;
                printStream.println("Your answered right!");
            } else {
                printStream.println("Your answered wrong!");
            }
        }
        printStream.println(MessageFormat.format("Result of student {0}:", studentName));
        if (numberOfPoints >= passingNumber) {
            printStream.println("Congratulations!!! You passed the test.");
            return true;
        } else {
            printStream.println("You did not pass the test :(");
            return false;
        }
    }

    private String getStudentLastAndFirstName(PrintStream printStream, Scanner scanner) {
        printStream.println("Print your first and lastname");
        var firstAndLastName = scanner.nextLine();
        while (!firstAndLastName.contains(" ") && firstAndLastName.split(" ").length != 2) {
            printStream.println("Wrong format. Print your first and lastname");
            firstAndLastName = scanner.nextLine();
        }
        return firstAndLastName;
    }
}
