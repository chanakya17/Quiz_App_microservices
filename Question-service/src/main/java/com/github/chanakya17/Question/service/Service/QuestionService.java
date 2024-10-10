package com.github.chanakya17.Question.service.Service;


import com.github.chanakya17.Question.service.Model.Question;
import com.github.chanakya17.Question.service.Repo.QuestionRepo;
import com.github.chanakya17.Quiz.app.Model.QuestionWrapper;
import com.github.chanakya17.Quiz.app.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {
	@Autowired
	QuestionRepo questionRepo;

	public ResponseEntity<List<Question>> getAllQuestions() {

		return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionRepo.save(question);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	public ResponseEntity<String> deleteQuestion(int id){
		questionRepo.deleteById(id);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, int numQ) {
		List<Integer> questions = questionRepo.getRandomQuestions(category,numQ);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> question = new ArrayList<>();

		for(Integer id: questionIds){
			question.add(questionRepo.findById(id).get());

		}
		for(Question q: question){
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(q.getId());
			wrapper.setQuestionTitle(q.getQuestionTitle());
			wrapper.setOption1(q.getOption1());
			wrapper.setOption2(q.getOption2());
			wrapper.setOption3(q.getOption3());
			wrapper.setOption4(q.getOption4());
			wrappers.add(wrapper);

		}

		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScoreForQuiz(List<Response> responses) {

		int right=0;
		int i=0;
		for(Response r : responses){
			Question question = questionRepo.findById(r.getId()).get();
			if(r.getResponse().equals(question.getRightAnswer()))
				right++;
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
}
