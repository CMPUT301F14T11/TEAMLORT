package com.github.cmput301f14t11.teamlort;



import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;





import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class HomeActivity extends AppBaseActivity implements Observer {
	
	ListView questionlistview;
	private RelativeLayout tasklayout;
	static customadapter adapter; // since we are displaying question objects, the normal ArrayAdapter will not cut it, for right now I've modified the customer adapter I used for my assignment 1 and sticked it in here
								  // should anyone think something else should be used instead,feel free to bring it up in group discussion
	// I will manually write down some questions to help implement the display for right now
	
	
    //initialize controllers
	PersistentDataManager pdm = PersistentDataManager.getInstance();//why isn't the contructor usable?
	Qlistcontroller qlc = new Qlistcontroller();
	UserController usecontrol = new UserController();//initialize user controller to use its functions
	//ScoreController sc = new ScoreController();
	ObjectFactory dt = new ObjectFactory();
	NetworkController ne = new NetworkController();
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);     
        questionlistview = (ListView)findViewById(R.id.expandableListView1);
        adapter = new customadapter(getApplicationContext(), qlc.questionlist.modellist);
        for(int i = 0; i<=9; i++)
        {
        	Question t = dt.initQuestion("sam'squestion", "test some more", "sam");
        	t.setID();
        	Answer answer = new Answer();
        	answer.setBody("dsadasd");
    		answer.setAuthor("asdsadas");
    		
    		t.addAnswer(answer);
        	qlc.add(t);
        	//dt.addQuestions(listofquestions);
        }
        questionlistview.setOnItemClickListener(new OnItemClickListener()//did the user press any questions?
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				/**
				 * once question is clicked, we extract the question from our question list 
				 * place it into appache, and pass to questionviewactivity, so it will display that single question
				 * 
				 */
				Intent intent = new Intent(getApplicationContext(),QuestionViewActivity.class);
				Single_Home_Question holder = (Single_Home_Question) view.getTag();
				Question temp = holder.thisquestion;
				Toast.makeText(getApplicationContext(), temp.getTitle(), Toast.LENGTH_SHORT).show();
				AppCache appCache = AppCache.getInstance(); //written the question in appcachefile
				appCache.setQuestion(temp);
				startActivity(intent);
			}
			
		});
        questionlistview.setAdapter(adapter);
    }
    
    
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// getlist of questions
		/**
		 * we have some code here that were supposed to refresh the question list on start/every restart of the app
		 * however that doesn't work right now
		 */
		/*if (ne.checkConnection(getApplicationContext()) == true)
		{
			ne.notifyAll();
			pdm.getMore();
			listofquestions = pdm.getQuestion();
		}*/
		
	}


	public void Favorite(View v)
    {
    	//gets the question from list
		
		Single_Home_Question holder = (Single_Home_Question) v.getTag();
		Question temp = (Question) holder.title.getTag();
		usecontrol.addFavorite(temp);
    }
	public void Save(View v)
	{
		Single_Home_Question holder = (Single_Home_Question) v.getTag();
		Question temp = (Question) holder.title.getTag();
		usecontrol.addCache(temp);
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


	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		adapter.updatelist(qlc.questionlist.modellist);
	}
}
