package com.github.cmput301f14t11.teamlort;



import java.util.ArrayList;



import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class HomeActivity
extends AppBaseActivity {
	
	ListView questionlistview;
	private RelativeLayout tasklayout;
	static customadapter adapter; // since we are displaying question objects, the normal ArrayAdapter will not cut it, for right now I've modified the customer adapter I used for my assignment 1 and sticked it in here
								  // should anyone think something else should be used instead,feel free to bring it up in group discussion
	ArrayList<Question> listofquestions;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        questionlistview = (ListView)tasklayout.findViewById(R.id.expandableListView1);
        adapter = new customadapter(getApplicationContext(), listofquestions);
        questionlistview.setOnItemClickListener(new OnItemClickListener()//the core functionality of this app: did the user do the task?
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//JUMP TO QUESTION VIEW,FILLING DATA ACCORDING TO QUESTION ID - NEED MORE DISCUSSION
				//QUESTIONS: HOW TO ACCESS SAVE/FAVORITE BUTTON ON EACH SINGLE QUESTION?
			}
			
		});
    
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
