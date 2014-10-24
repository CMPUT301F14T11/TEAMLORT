package com.github.cmput301f14t11.teamlort;



import java.util.ArrayList;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;


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
