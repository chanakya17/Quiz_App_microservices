package com.github.chanakya17.Question.service.Repo;


import com.github.chanakya17.Question.service.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String Category);
    @Query(value = "SELECT q.id FROM question q WHERE q.category=:category ORDER BY random() LIMIT :numQ", nativeQuery = true)
    List<Integer> getRandomQuestions(String category, int numQ);
}
