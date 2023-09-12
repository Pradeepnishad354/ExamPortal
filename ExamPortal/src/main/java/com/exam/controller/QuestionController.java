package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;



@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {

		return ResponseEntity.ok(questionService.addQuestion(question));

	}

	@PutMapping("/")
	public ResponseEntity<?> updateQuestion(@RequestBody Question question) {

		return ResponseEntity.ok(questionService.updateQuestion(question));

	}

	@GetMapping("/")
	public ResponseEntity<?> getQuestion() {

		return ResponseEntity.ok(questionService.getQuestions());

	}
//get Single Question 
	
	
	@GetMapping("/{quesId}")
	public ResponseEntity<?> getQuestions(@PathVariable Long quesId) {

		return ResponseEntity.ok(questionService.getQuestion(quesId));

	}

	
	// get all question of quiz admin
	@GetMapping("quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
		Quiz quiz=new Quiz();
		quiz.setqId(qid);
		Set<Question> questionsOfQuiz = questionService.getQuestionsOfQuiz(quiz);
       return ResponseEntity.ok(questionsOfQuiz);
		
	}
	
	
	
	
	
	// get all question of quiz

	@GetMapping("quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {

//		Quiz quiz=new Quiz();
//		quiz.setqId(questionId);
//		
//		Set<Question> questionsOfQuiz = questionService.getQuestionsOfQuiz(quiz);

		Quiz quiz = this.quizService.getQuiz(qid);

		Set<Question> questions = quiz.getQuestions();

		List<Question> list = new ArrayList<>(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {

			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		} else {

			Collections.shuffle(list);
		}

		return ResponseEntity.ok(list);

	}
	
	// delete question 
	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable Long quesId) {

		questionService.deleteQuestion(quesId);;

	}
	
	
	// evaluate quiz
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		System.out.println(questions);
		
		double marksGot=0;
		Integer correctAnswers=0;
		Integer attemted=0;
		
		
		for(Question q:questions) {
			//single question 
			//this question id is come from client which is submit by user 
			Question question = this.questionService.get(q.getQuestionId());
			
			// if match both this is correct 
			if(question.getAnswer().equals(q.getGivenAnswer())) {
				//correct
				correctAnswers++;	
				
			double marksSingle=Double.parseDouble( questions.get(0).getQuiz().getMaxMarks())/questions.size();
				
					marksGot=marksGot+marksSingle;
			}
			if(q.getGivenAnswer()!=null) {
				attemted++;
				
			}
			
			System.out.println(q.getGivenAnswer());
			
			
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("markGot",marksGot);
		map.put("correctAnswers", correctAnswers);
		map.put("attemted",attemted);
		//System.out.println(map);
		return ResponseEntity.ok(map);
	}
	
	

}
