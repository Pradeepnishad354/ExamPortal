package com.exam.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.repository.QuizRepository;

@Service
public class QuizServiceImpl  implements QuizService{

	
	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
	
		return quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizes() {
		
		return new HashSet<>(quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		
		return quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		
		
		quizRepository.deleteById(quizId);
	}

	@Override
	public List<Quiz> getQuizzesOfcategory(Category category) {
		
		return quizRepository.findBycategory(category);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		
		return quizRepository.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category category) {
		return quizRepository.findByCategoryAndActive(category, true);
	}
	
	
	

}
