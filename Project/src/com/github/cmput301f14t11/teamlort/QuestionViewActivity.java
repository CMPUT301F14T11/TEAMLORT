package com.github.cmput301f14t11.teamlort;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestionViewActivity
extends AppBaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_view);
		Button reply = (Button)findViewById(R.id.reply_button);
		
		reply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent intent = new Intent(QuestionViewActivity.this,ReplyActivity.class);
            	startActivity(intent);
            }
        });
	}
	
}
