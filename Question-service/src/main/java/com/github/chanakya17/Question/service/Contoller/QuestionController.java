package com.github.chanakya17.Question.service.Contoller;


import com.github.chanakya17.Question.service.Model.Question;
import com.github.chanakya17.Question.service.Service.QuestionService;
import com.github.chanakya17.Quiz.app.Model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import com.github.chanakya17.Quiz.app.Model.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    Environment environment;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> allQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> questionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

    @GetMapping ("/generate")
    public ResponseEntity<List<Integer>> generateQuestions
            (@RequestParam String category, @RequestParam int numQ){
        return questionService.generateQuestionsForQuiz(category,numQ);

    }

    @PostMapping ("/get")
    public ResponseEntity<List<QuestionWrapper>> getQuestions (@RequestBody List<Integer> questions){
        return questionService.getQuestionsForQuiz(questions);
    }

    @PostMapping("/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getScoreForQuiz(responses);
    }
}
