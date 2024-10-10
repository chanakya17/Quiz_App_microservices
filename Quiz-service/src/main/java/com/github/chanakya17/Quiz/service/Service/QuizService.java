package com.github.chanakya17.Quiz.service.Service;


import com.github.chanakya17.Quiz.service.Feing.QuizInterface;
import com.github.chanakya17.Quiz.service.Model.QuestionWrapper;
import com.github.chanakya17.Quiz.service.Model.Quiz;
import com.github.chanakya17.Quiz.service.Model.Response;
import com.github.chanakya17.Quiz.service.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {


    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuizQuestions(String category, int numQ, String title){
        List<Integer> questionList = quizInterface.generateQuestions(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionList);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Quiz created", HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id){
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questions = quiz.getQuestionIds();
        return quizInterface.getQuestions(questions);
    }


    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        return quizInterface.getScore(responses);

    }
}
