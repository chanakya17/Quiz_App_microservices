package com.github.chanakya17.Quiz.service.Repo;


import com.github.chanakya17.Quiz.service.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
