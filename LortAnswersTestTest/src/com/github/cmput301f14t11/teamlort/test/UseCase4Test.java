package com.github.cmput301f14t11.teamlort.test;

import com.github.cmput301f14t11.teamlort.ComposeQuestionActivity;
import com.github.cmput301f14t11.teamlort.Model.Question;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;

public class UseCase4Test extends ActivityInstrumentationTestCase2<ComposeQuestionActivity> {

	public UseCase4Test() {
		super(ComposeQuestionActivity.class);
	}
	
	public void testCaseFour(){
		//Test case for Use Case #4: Author makes a question
		
		//Test that user can enter title into question
		String title = "How do I fly?";
		Question question = new Question();
		question.setTitle(title);
		assertTrue("title doesn't match title", question.getTitle() == title);
		
		//Test that user can enter description into question
		String desc = "I have some feathers halp";
		assertTrue("Desc not empty by default", question.getBody() == "");
		question.setBody(desc);
		assertTrue("Description doesn't match desc", question.getBody() == desc);
		
		//Test add image to question.
		ComposeQuestionActivity activity = (ComposeQuestionActivity) getActivity();
		
		Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888 );
		Drawable drawable = new BitmapDrawable(activity.getResources(), bitmap);
		question.addPicture(drawable);
		assertTrue("Bitmap does not match bitmap or getImage failed", question.getPicture() == drawable);
		
	}

}
