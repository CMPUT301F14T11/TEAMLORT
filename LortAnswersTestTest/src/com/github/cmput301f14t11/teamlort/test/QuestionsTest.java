package com.github.cmput301f14t11.teamlort.test;

import java.util.ArrayList;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.Reply;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import junit.framework.TestCase;

public class QuestionsTest extends TestCase {

	public void testQuestionCreate(){
		Question question = new Question();
	}
	
	public void testSetTitle(){
		String title = "How do I fly?";
		Question question = new Question();
		question.setTitle(title);
		assertTrue("title doesn't match title", question.getTitle() == title);
	}
	
	public void testGetTitle(){
		String title = "How do I fly?";
		Question question = new Question();
		question.setTitle(title);
		assertTrue("title doesn't match title", question.getTitle() == title);
	}
	
	public void testSetDescription(){
		String desc = "I have some feathers halp";
		Question question = new Question();
		assertTrue("Desc not empty by default", question.getBody() == "");
		question.setBody(desc);
		assertTrue("Description doesn't match desc", question.getBody() == desc);
	}
	
	public void testGetDescription(){
		String title = "How do I fly?";
		String desc = "I have some feathers halp";
		Question question = new Question();
		question.setTitle(title);
		question.setBody(desc);
		assertTrue("Description doesn't match desc", question.getBody() == desc);
	}
	
	public void testAddReplies(){
		String title = "How do I fly?";
		String rep = "this is incredibly stupid";
		Question question = new Question();
		question.setTitle(title);
		Reply reply = new Reply();
		reply.setBody(rep);
		question.addReply(reply);
		assertTrue("Reply doesn't match rep", question.getReply(0) == reply);
	}
	
	public void testGetReplies(){
		String title = "How do I fly?";
		String rep = "this is incredibly stupid";
		String rep2 = "this is incredibly silly";
		Question question = new Question();
		question.setTitle(title);
		Reply reply = new Reply();
		reply.setBody(rep);
		Reply reply2 = new Reply();
		reply2.setBody(rep2);
		question.addReply(reply);
		question.addReply(reply2);
		assertTrue("Reply doesn't match rep", question.getReply(0) == reply);
		assertTrue("Reply2 doesn't match rep2", question.getReply(1) == reply2);
	}
	
	public void testSetAuthor(){
		String username = "SIVLEOL";
		Question question = new Question();
		question.setAuthor(username);
		assertTrue("Author doesn't match username", question.getAuthor() == username);
	}
	
	@SuppressWarnings("deprecation")
	public void testAddImage(){
		//Right now this also tests getImage
		Question question = new Question();
		Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888 );
		Drawable drawable = new BitmapDrawable(bitmap);
		question.addPicture(drawable);
		assertTrue("Bitmap does not match bitmap or getImage failed", question.getPicture() == drawable);
	}
	
	public void testUpvote(){
		String username = "SIVLEOL";
		Question question = new Question();
		assertTrue("question initialized with more than 0 upvotes", question.getScore() == 0);
		question.upVote(username);
		assertTrue("Upvote did not change score", question.getScore() == 1);
		question.upVote(username);
		assertTrue("Same user managed to upvote same question twice", question.getScore() == 1);
	}
	
	public void testDate(){
		Question question = new Question();
		assertTrue("question date is null", question.getTime() != null);
	}
	
	public void testAddAnswer(){
		Question question = new Question();
		Answer answer = new Answer();
		question.addAnswer(answer);
		ArrayList<Answer> list1 = question.getAnswerList();
		assertTrue("Question does not have answer", list1.get(0) == answer);
	}
	
	public void testRemoveAnswer(){
		Question question = new Question();
		Answer answer = new Answer();
		question.addAnswer(answer);
		question.deleteAnswer(answer);
		ArrayList<Answer> list1 = question.getAnswerList();
		assertTrue("Question remove failed", list1.size() == 0);
	}
	
	public void testGetAnswerList(){
		Question question = new Question();
		Answer answer = new Answer();
		ArrayList<Answer> list1 = question.getAnswerList();
		assertTrue("getAnswer() returned > 0 when should be empty", list1.size() == 0);
		question.addAnswer(answer);
		assertTrue("getAnswer() did not return list with answer", list1.get(0) == answer);
	}
	
	public void testQUEPIC() 
 	{ 
 		Question question = new Question();
		Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888 );
		Drawable drawable = new BitmapDrawable(bitmap);
		question.addPicture(drawable);
 		assertTrue("picture file size too large", ((BitmapDrawable) question.getPicture()).getBitmap().getByteCount() <= 64); 
 	} 

	public void testSort()
	{
		ArrayList<Question> questionlist;
		for (int i =0; x<=9; x++)
		{
			Question singlequestion = new Question()
			singlequestion.setvote(i);
			singlequestion
			questionlist.add(singlequestion);
		}
		//http://stackoverflow.com/questions/5815423/sorting-arraylist-in-alphabetical-order-case-insensitive
		// Collections.sort(questionlist, new Comparator<Question>() 
		// {
  //      		@Override
  //      		public int compare(Question s1, Question s2)
  //      		{
  //          			return s1.Upvote.compareToIgnoreCase(s2.Upvote);
  //      		}
  //  		});
  		//i suggest we provide constants to tell our sorthing method what mode of sorting we chose
  		//sort function should be defined in super class,answer and question should use the same function
  		questionlist.sort(CONSTANT_IMAGE);
    		for(int i=1;i<questionlist.size()-1;i++)
    		{
    			if(questionlist.get(i-1).haspic()!=questionlist.get(i).haspic()&&questionlist.get(i).haspic()!=questionlist.get(i+1).haspic())
    			{
    				fail("list not properly sorted by image");
    			
    			}
    			
    		}); 
    		questionlist.sort(CONSTANT_UPVOTE);//it makes no sense to me why user would want to see question with the least amount of upvotes(0) first, so we are sorthing this one way
    		for(int i=1;i<questionlist.size();i++)
    		{
    			if(questionlist.get(i-1).upvote<=questionlist.get(i).upvote)
    			{
    				fail("list not properly sorted by upvote");
    				
    			}
    			
    		}); 
    		questionlist.sort(CONSTANT_DATE);
    		for(int i=1;i<questionlist.size();i++)
    		{
    			if(questionlist.get(i-1).date<=questionlist.get(i).date)//needs to work on date, sorting it by oldest/newest
    			{
    				fail("list not properly sorted by date");
    				break();
    			}
    			
    		}); 
		//Sort by up votes, dates, images, and?(I'll just get these 3 done for now)
		// we will need a "cheating method" to superficially add upvote count inorder to test sort,this method should be deleted in the release version
	
	}
	
	
	
}
