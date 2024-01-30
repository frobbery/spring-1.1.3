package com.example.spring_111;

import com.example.spring_111.service.QuestionService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

@SpringBootApplication
@PropertySource("/application.properties")
public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		QuestionService service = context.getBean(QuestionService.class);
		service.addQuestions();
		service.conductTesting(System.out, new Scanner(System.in));
	}

}
