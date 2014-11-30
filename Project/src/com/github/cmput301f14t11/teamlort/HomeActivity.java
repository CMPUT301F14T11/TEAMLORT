package com.github.cmput301f14t11.teamlort;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.github.cmput301f14t11.teamlort.Controller.ProfileController;
import com.github.cmput301f14t11.teamlort.Controller.Qlistcontroller;
import com.github.cmput301f14t11.teamlort.Model.AppCache;
import com.github.cmput301f14t11.teamlort.Model.ObjectFactory;
import com.github.cmput301f14t11.teamlort.Model.Question;

/**
 * used to display list of {@link Question} when the app starts
 * user should be able to look through question they're interested, perform search
 * and refine search results on this activity
 * 
 * covers usecases 1,14,15
 * 
 * @author sbao
 * @issues: search functionality requires other parts to be completed and coordination with other team members 
 */
public class HomeActivity extends AppBaseActivity implements Observer, LocationListener
{
	
	
	
	ListView questionlistview;
	private int page = 0;
	private LinearLayout footerLayout;
	private String searchstring;
	static HomeAdapter adapter; // since we are displaying question objects, the normal ArrayAdapter will not cut it, for right now I've modified the customer adapter I used for my assignment 1 and sticked it in here
								  // should anyone think something else should be used instead,feel free to bring it up in group discussion
								  // I will manually write down some questions to help implement the display for right now
	
	
    //initialize controllers
	Qlistcontroller qlc = new Qlistcontroller();
	ObjectFactory dt = new ObjectFactory();
	ProfileController pc = new ProfileController();
	AppCache appCache = new AppCache();
	
	boolean loadingMore = false;
	boolean clicked = false;
	ArrayList<Question> getmoar = new ArrayList<Question>();
	   //Runnable to load the items
	private Runnable doUpdateGUIList = new Runnable() {
		public void run() {
			adapter.updatelist(qlc.getQuestionlist().getModellist());
			if(adapter.getCount() <1)
		    {
				Toast.makeText(getApplicationContext(), "Sorry, No Result Found", Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); 
        
        
        questionlistview = (ListView)findViewById(R.id.expandableListView1);
        
        qlc.getQuestionlist().getModellist().clear();
        
        AppCache.getInstance().InitProfile();
        
        adapter = new HomeAdapter(getApplicationContext(), qlc.getQuestionlist().getModellist());

        //
        // screw locally generated question, we be grabbin shit online like real Gs
        //
       
       Intent intent = getIntent();
       searchstring = intent.getStringExtra("searchstring");
      
       SearchThread search = new SearchThread(searchstring,0);
       search.start();
       
	   
	  
	   View view = getLayoutInflater().inflate(R.layout.footer_view,questionlistview , false);
	   footerLayout = (LinearLayout) view.findViewById(R.id.footer_layout);
	   
	   questionlistview.addFooterView(footerLayout);
       questionlistview.setOnItemClickListener(new OnItemClickListener()//did the user press any questions?
       {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) 
			{
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
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				boolean loadMore = /* maybe add a padding */
			            firstVisibleItem + visibleItemCount >= totalItemCount;
			            //http://stackoverflow.com/questions/1080811/android-endless-list
			        if(totalItemCount>5)
			        {
			        	if(loadMore) {
				            // or any other amount
				        	
				        	page += adapter.getCount();
			            	SearchThread search = new SearchThread(searchstring,page);
			           	   	search.start();
				        }
			        }
			        
			}
		});
        questionlistview.setOnItemLongClickListener(new OnItemLongClickListener()//did the user press any questions?
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Single_Home_Question holder = (Single_Home_Question) view.getTag();
				Question temp = holder.thisquestion;
				AlertDialog.Builder delete = builddelete(temp.getID(),position);
				alertDialog = delete.show();
				return true;
			}
		});
        view.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}});
        questionlistview.setAdapter(adapter);
        Log.i("LORTANSWERS","WE GRABBED :"+qlc.getQuestionlist().getModellist().size());
    }
    
    
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		adapter.updatelist(qlc.getQuestionlist().getModellist());
		
		// getlist of questions
		/**
		 * we have some code here that were supposed to refresh the question list on start/every restart of the app
		 * however that doesn't work right now
		 */

	}

	@Override
	protected void onResume(){
		super.onResume();
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * Adds the {@link Question} to the FavedQuestionList in ProfileController
	 * @param v
	 */

	
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_help) {
        	helpscreen = getResources().getDrawable(R.drawable.helpscreen_home);
        	AlertDialog.Builder alert = buildhelp(helpscreen);
			alertDialog = alert.show();
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
		private int from;
		public SearchThread(String s,int f)
		{
			search = s;
			from = f;
		}
		@Override
		public void run() 
		{
			// TODO Auto-generated method stub
			qlc.getQuestionlist().getModellist().addAll(qlc.getElc().search(search, "title",from));	
			runOnUiThread(doUpdateGUIList);
		}
	}
	class DeleteThread extends Thread {
		// TODO: Implement search thread
		private int id;
		private int local_id;
		public DeleteThread(int provided,int local_provided)
		{
			id = provided;
			local_id = local_provided;
		}
		@Override
		public void run() 
		{
			// TODO Auto-generated method stub
			qlc.getElc().deleteItem(id);
			qlc.getQuestionlist().getModellist().remove(local_id);
			//Toast.makeText(getApplicationContext(), "search result in "+ qlc.questionlist.modellist.size()+" finds", Toast.LENGTH_SHORT).show();	
			runOnUiThread(doUpdateGUIList);
		}
	}
	
	protected AlertDialog.Builder builddelete(final int qid,final int local_qid){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Delete This Question");
		alert.setMessage("Are you sure?");
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
		{
		
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				
				DeleteThread delete = new DeleteThread(qid,local_qid);
				delete.start();
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		return alert;
		
	}
	
	@Override
	protected boolean onSortMenuItemSelect(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId())
		{
		case (R.id.action_sort_by_date):
			if(clicked == false)
			{
				qlc.sortQuestions("date");
				clicked = true;
			}
			else if (clicked == true)
			{
				qlc.reverselist();
				clicked = false;
			}
			adapter.updatelist(qlc.getQuestionlist().getModellist());
			return true;
		
		case (R.id.action_sort_by_score):
			if(clicked == false)
			{
				qlc.sortQuestions("upvote");
				clicked = true;
			}
			else if (clicked == true)
			{
				qlc.reverselist();
				clicked = false;
			}
			adapter.updatelist(qlc.getQuestionlist().getModellist());
			return true;
		
		case (R.id.action_sort_by_pictures):
			if(clicked == false)
			{
				qlc.sortQuestions("image");
				clicked = true;
			}
			else if (clicked == true)
			{
				qlc.reverselist();
				clicked = false;
			}
			adapter.updatelist(qlc.getQuestionlist().getModellist());
			return true;
		
		case(R.id.action_sort_by_location):
			qlc.setprofile(AppCache.getInstance().getProfile(), null);
			if(clicked == false)
			{
				qlc.sortQuestions("location");
				clicked = true;
			}
			else if (clicked == true)
			{
				qlc.reverselist();
				clicked = false;
			}
			adapter.updatelist(qlc.getQuestionlist().getModellist());
			return true;
		case(R.id.action_sort_by_replies):
		{
			if(clicked == false)
			{
				qlc.sortQuestions("replies");
				clicked = true;
			}
			else if (clicked == true)
			{
				qlc.reverselist();
				clicked = false;
			}
			adapter.updatelist(qlc.getQuestionlist().getModellist());
			return true;
		}
		
		default:
			return true;
			
		}
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	
	
}

