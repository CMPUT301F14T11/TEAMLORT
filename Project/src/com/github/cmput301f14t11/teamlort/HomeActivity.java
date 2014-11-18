package com.github.cmput301f14t11.teamlort;



import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.NetworkController;
import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Controller.UserController;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.PersistentDataManager;
import com.github.cmput301f14t11.teamlort.Model.Question;

/**
 * used to display list of {@link Question} when the app starts
 * user should be able to look through question they're interested, perform search
 * and refine search results on this activity
 * 
 * covers usecases 1,14,15
 * 
 * 
 * @author sbao
 * @issues: search functionality requires other parts to be completed and coordination with other team members 
 */
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
	ProfileController pc = new ProfileController();
	AppCache appCache = new AppCache();
	boolean loadingMore = false;
	ArrayList<Question> getmoar = new ArrayList<Question>();
	   //Runnable to load the items
	private Runnable doUpdateGUIList = new Runnable() {
		public void run() {
			adapter.updatelist(qlc.getQuestionlist().getModellist());
		}
	};

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);     
        //View footer = (TextView)findViewById(R.id.endoflist);
        questionlistview = (ListView)findViewById(R.id.expandableListView1);
        //questionlistview.addFooterView(footer);
        adapter = new customadapter(getApplicationContext(), qlc.getQuestionlist().getModellist());
//        for(int i = 0; i<=19; i++)
//        {
//        	Question t = dt.initQuestion("sam'squestion", "test some more", "sam");
//        	t.setID();
//        	Answer answer = new Answer();
//        	answer.setBody("dsadasd");
//    		answer.setAuthor("asdsadas");
//    		
//    		t.addAnswer(answer);
//        	qlc.add(t);
//        	//dt.addQuestions(listofquestions);
//        }
        /**
         * screw locally generated question, we be grabbin shit online like real Gs
         */
       //Intent intent = getIntent();
       //String searchstring = intent.getStringExtra("searchstring");
       String searchstring = "*";
       SearchThread search = new SearchThread(searchstring);
	   search.start();
       //qlc.questionlist.modellist = qlc.questionlist.elasticmanager.search(searchstring, null);
    	

	   
        
        
        
        
        
        
        
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
				appCache = AppCache.getInstance(); //written the question in appcachefile
				appCache.setQuestion(temp);
				startActivity(intent);
			}
			
		});
        //http://mobile.dzone.com/news/android-tutorial-dynamicaly
        questionlistview.setOnScrollListener(new OnScrollListener()
        {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) 
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount)
			{
				// TODO Auto-generated method stub
				//what is the bottom iten that is visible
//				int lastInScreen = firstVisibleItem + visibleItemCount;
//				//is the bottom item visible & not loading more already ? Load more !
//				if((lastInScreen == totalItemCount) && !(loadingMore))
//				{
//					Thread thread =  new Thread(null, loadMoreListItems);
//					thread.start();
//				}
				if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0)
	            {
	                if(loadingMore == false)
	                {
	                	loadingMore = true;
	                	qlc.getMore(qlc.getQuestionlist().getModellist().get(qlc.getQuestionlist().getModellist().size()-1).getID(), 10);
	                	Toast.makeText(getApplicationContext(), "get More called", Toast.LENGTH_SHORT).show();
	                	adapter.updatelist(qlc.getQuestionlist().getModellist());
	                }
	            }


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

	/**
	 * Adds the {@link Question} to the FavedQuestionList in ProfileController
	 * @param v
	 */
	public void Favorite(View v)
    {
    	//gets the question from list
		
		Single_Home_Question holder = (Single_Home_Question) v.getTag();
		Question temp = (Question) holder.title.getTag();
		
		pc.addFavedQuestion(temp);
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
		adapter.updatelist(qlc.getQuestionlist().getModellist());
	}
	class SearchThread extends Thread {
		// TODO: Implement search thread
		private String search;
		public SearchThread(String s)
		{
			search = s;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			qlc.getQuestionlist().getModellist().clear();
			qlc.getQuestionlist().getModellist().addAll(qlc.getElc().search(search, null));
			//Toast.makeText(getApplicationContext(), "search result in "+ qlc.questionlist.modellist.size()+" finds", Toast.LENGTH_SHORT).show();	
				runOnUiThread(doUpdateGUIList);
			}
		
		
	}
}

