package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import junit.framework.TestCase;

public class UseCase13Test extends TestCase {
	
	PersistentDataManager dataManager = new PersistentDataManager();
	ArrayList<Question> list1 = new ArrayList<Question>();
	ArrayList<Question> highestScoreQuestionList = new ArrayList<Question>();
	ArrayList<Answer> highestScoreAnswerList = new ArrayList<Answer>();
	
	Question question1 = new Question("Why are there two amulets?");
	Question question2 = new Question("Didn't Archer already return amulet?");
	Question question3 = new Question("How does Shirou have amulet?");
	list1.append(question1);
	list1.append(question2);
	list1.append(question3);
	dataManager.addQuestions(list1);
	
	Answer answer1 = new Answer("answer1 for question1");
	Answer answer2 = new Answer("answer2 for question1");
	Answer answer3 = new Answer("answer1 for question2");
	list1.get(0).addAnswer(answer1);
	list1.get(0).addAnswer(answer1);
	list1.get(1).addAnswer(answer2);
	
	ScoreController sc = new ScoreController();
	highestScoreQuestionList = dataManager.sortQuestions(sortByScore());
	highestScoreAnswerList = dataManager.sortAnswers(sortByScore());
	
	
	AssertTrue("question1 is missing",
			hightestScoreQuestionList.contain(dataManager.getQuestion(0)));
	AssertTrue("question2 is missing",
			hightestScoreQuestionList.contain(dataManager.getQuestion(1)));
	AssertTrue("question3 is missing",
			hightestScoreQuestionList.contain(dataManager.getQuestion(2)));
	AssertTrue("should display all three question because their scores are all 0",
			hightestScoreQuestionList.contain(dataManager.getQuestion(0))
			&& hightestScoreQuestionList.contain(dataManager.getQuestion(1)) 
			&& hightestScoreQuestionList.contain(dataManager.getQuestion(2)));

	AssertTrue("answer1 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(0)));
	AssertTrue("answer2 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(1)));
	AssertTrue("answer3 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(1).getAnswer(0)));
	AssertTrue("should display all three answer because their scores are all 0",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(0))
			&& hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(1))
			&& hightestScoreAnswerList.contain(dataManager.getQuestion(1).getAnswer(0)));
	
	
	// upvoted quesion1 and sort again
	sc.increaseScore(question1);
	sc.increaseScore(answer1);
	highestScoreAnswerList = dataManager.sortAnswers(sortByScore());
	highestScoreQuestionList = dataManager.sortQuestions(sortByScore());
	AssertTrue("question1 is missing",
			hightestScoreQuestionList.contain(dataManager.getQuestion(0)));
	AssertFalse("question2 should not exist",
			hightestScoreQuestionList.contain(dataManager.getQuestion(1)));
	AssertFalse("question3 should not exist",
			hightestScoreQuestionList.contain(dataManager.getQuestion(2)));
	
	AssertTrue("answer1 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(0)));
	AssertFalse("answer2 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(1)));
	AssertFalse("answer3 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(1).getAnswer(0)));
	
	

	sc.increaseScore(question2);
	sc.increaseScore(answer2);
	highestScoreAnswerList = dataManager.sortAnswers(sortByScore());
	highestScoreQuestionList = dataManager.sortQuestions(sortByScore());	
	AssertTrue("question1 is missing",
			hightestScoreQuestionList.contain(dataManager.getQuestion(0)));
	AssertTrue("question2 is missing",
			hightestScoreQuestionList.contain(dataManager.getQuestion(1)));
	AssertFalse("question3 should not exist",
			hightestScoreQuestionList.contain(dataManager.getQuestion(2)));
	AssertTrue("should display question1 and question2, both of them are 2 points",
			hightestScoreQuestionList.contain(dataManager.getQuestion(0))
			&& hightestScoreQuestionList.contain(dataManager.getQuestion(1)));
	
	AssertTrue("answer1 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(0)));
	AssertTrue("answer2 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(1)));
	AssertFalse("answer3 is should not exist",
			hightestScoreAnswerList.contain(dataManager.getQuestion(1).getAnswer(0)));
	AssertTrue("should display answer1 and answer2 because their scores are both 1 points",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(0))
			&& hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(1)));
	

	sc.increaseScore(question1);
	sc.increaseAnswer(answer3);
	sc.increaseAnswer(answer3);
	hightestScoreQuestionList = dataManager.sortQuestions(sortByScore());
	highestScoreAnswerList = dataManager.sortAnswers(sortByScore());
	printQuestionList(dataManager.getAllQuestions());	
	AssertTrue("question1 is missing",
			hightestScoreQuestionList.contain(dataManager.getQuestion(0)));
	AssertFalse("question2 should not exist",
			hightestScoreQuestionList.contain(dataManager.getQuestion(1)));
	AssertFalse("question3 should not exist",
			hightestScoreQuestionList.contain(dataManager.getQuestion(2)));
	
	AssertFalse("answer1 should not exist",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(0)));
	AssertFalse("answer2 should not exist",
			hightestScoreAnswerList.contain(dataManager.getQuestion(0).getAnswer(1)));
	AssertTrue("answer3 is missing",
			hightestScoreAnswerList.contain(dataManager.getQuestion(1).getAnswer(0)));

}





