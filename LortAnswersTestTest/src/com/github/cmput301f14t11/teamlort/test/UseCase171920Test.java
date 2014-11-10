package com.github.cmput301f14t11.teamlort.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;
import android.content.Context;

import com.github.cmput301f14t11.teamlort.Answer;
import com.github.cmput301f14t11.teamlort.Question;
import com.github.cmput301f14t11.teamlort.Reply;

public class UseCase171920Test extends TestCase {

	// UseCase 17
	public void testSaveQuestionLocally() {
		Question question = new Question(title);
		File file = new File(context.getFilesDir(), filename);

		String filename = "myfile";
		FileOutputStream outputStream;

		outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		outputStream.write(question.getBytes());
		outputStream.close();

		ByteArrayInputStream inputStream = new ByteArrayInputStream(
				str.getBytes("myfile"));
		InputStreamReader isr = new InputStreamReader(inputStream, "myfile");

		assertTrue(isr.getEncoding().equalsIgnoreCase("myfile"));
		isr.close();
	}

	// UseCase 19
	public void testSaveFavoriteLocally() {
        Question question = new Question(title);
        File file = new File(context.getFilesDir(), filename);
        
        String filename = "myfile";
        FileOutputStream outputStream;
      
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(question.getBytes());
        outputStream.close();
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes("myfile"));
        InputStreamReader isr = new InputStreamReader(inputStream, "myfile");
  
        assertTrue(isr.getEncoding().equalsIgnoreCase("myfile"));
        assertTrue(author.favorites.contains(question);
        isr.close();
}

	// UseCase 20
	public void testSaveAuthReplyLocally() {
        Question question = new Question(title);
        Reply reply = qestion.addReply()
        File file = new File(context.getFilesDir(), filename);
        
        String filename = "myfile";
        FileOutputStream outputStream;
      
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(reply.getBytes());
        outputStream.close();
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes("myfile"));
        InputStreamReader isr = new InputStreamReader(inputStream, "myfile");
  
        assertTrue(isr.getEncoding().equalsIgnoreCase("myfile"));
        isr.close();
}

	public void testSaveAuthAnswerLocally() {
        Question question = new Question(title);
        Answer answer = question.addAnswer()
        File file = new File(context.getFilesDir(), filename);
        
        String filename = "myfile";
        FileOutputStream outputStream;
      
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(answer.getBytes());
        outputStream.close();
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes("myfile"));
        InputStreamReader isr = new InputStreamReader(inputStream, "myfile");
  
        assertTrue(isr.getEncoding().equalsIgnoreCase("myfile"));
        isr.close();
}
}
