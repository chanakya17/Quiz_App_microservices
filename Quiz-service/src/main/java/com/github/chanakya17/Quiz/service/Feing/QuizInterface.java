package com.github.chanakya17.Quiz.service.Feing;

import com.github.chanakya17.Quiz.service.Model.QuestionWrapper;
import com.github.chanakya17.Quiz.service.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> generateQuestions
            (@RequestParam String category, @RequestParam int numQ);

    @PostMapping("/question/get")
    public ResponseEntity<List<QuestionWrapper>> getQuestions (@RequestBody List<Integer> questions);

    @PostMapping("/question/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
