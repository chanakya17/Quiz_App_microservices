package com.github.chanakya17.Quiz.service.Contoller;


import com.github.chanakya17.Quiz.service.Dto.QuizDto;
import com.github.chanakya17.Quiz.service.Model.QuestionWrapper;
import com.github.chanakya17.Quiz.service.Model.Response;
import com.github.chanakya17.Quiz.service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuizQuestions(quizDto.getCategory(),quizDto.getNumQ(), quizDto.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> returnScore(@RequestBody List<Response> responses){
        return quizService.calculateScore(responses);
    }

}
