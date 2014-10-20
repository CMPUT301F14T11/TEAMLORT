package com.github.cmput301f14t11.teamlort;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReplyActivity extends AppBaseActivity{
	
	
	
	protected void onCreate(Bundle inState) {
		// TODO Auto-generated method stub
		super.onCreate(inState);
		setContentView(R.layout.activity_reply);
		//http://stackoverflow.com/questions/7873480/android-one-onclick-method-for-multiple-buttons
		Button ok = (Button)findViewById(R.id.replyok);
		Button cancel=(Button)findViewById(R.id.replycancel);
		OnClickListener onClickListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId() == R.id.replyok)
				{
					finish();
				}
				else if(v.getId() == R.id.replycancel);
				{
					finish();
				}
			}
			
		};
		ok.setOnClickListener(onClickListener);
		cancel.setOnClickListener(onClickListener);
		
	}
	
}
