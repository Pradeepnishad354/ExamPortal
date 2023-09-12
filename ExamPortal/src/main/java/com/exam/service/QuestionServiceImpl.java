package com.exam.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.repository.QuestionRepository;

@Service
public class QuestionServiceImpl  implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
		
		return this. questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		
		return questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		
		return new HashSet<>(questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		
		return questionRepository.findById(questionId).get();
	}

	@Override
	public void deleteQuestion(Long questionId) {
		
		
		questionRepository.deleteById(questionId);
		
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
	
		return questionRepository.findByQuiz(quiz);
	}

	@Override
	public Question get(Long questionId) {
		
		return this.questionRepository.getById(questionId);
	}

}
