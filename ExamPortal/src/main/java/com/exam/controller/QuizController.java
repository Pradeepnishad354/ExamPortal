package com.exam.controller;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {

		return ResponseEntity.ok(quizService.addQuiz(quiz));
	}

	@PutMapping("/")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz) {

		Quiz updateQuiz = quizService.updateQuiz(quiz);

		return ResponseEntity.ok(updateQuiz);

	}

	@GetMapping("/")
	public ResponseEntity<?> getQuizes() {

		return ResponseEntity.ok(quizService.getQuizes());
	}

	@GetMapping("/{quizId}")
	public ResponseEntity<?> getQuiz(@PathVariable Long quizId) {

		return ResponseEntity.ok(quizService.getQuiz(quizId));
	}

	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable Long quizId) {

		quizService.deleteQuiz(quizId);

	}

	// get quizzes of category

	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable Long cid) {
		Category category = new Category();

		category.setCid(cid);

		return quizService.getQuizzesOfcategory(category);

	}

	// get active quizzess
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes() {

		return this.quizService.getActiveQuizzes();
	}

	// get active quizzess of category
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid) {
		Category category = new Category();

		category.setCid(cid);

		return this.quizService.getActiveQuizzesOfCategory(category);
	}
}
