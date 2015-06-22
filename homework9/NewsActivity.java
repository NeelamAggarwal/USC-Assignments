package edu.usc.HomeWork9;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsActivity extends ListActivity  {
	
	String Links_News[];
	String News_Title[];
	AlertDialog.Builder DialogBox;
	protected ArrayAdapter<String> adapter;
	int positonValue;
	String names[] =  new String[2]; 
	Bundle Bundle_extras;
	ListView list_View;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.activity_news);
		//Bundle_extras= null;
		Bundle_extras = getIntent().getExtras();
		if (Bundle_extras != null) {
			String news_title ="news";
			String news_links = "links";
			News_Title = Bundle_extras.getStringArray(news_title);
			Links_News = Bundle_extras.getStringArray(news_links);
		}
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.news_item, News_Title));
		 
		list_View = getListView();
		list_View.setTextFilterEnabled(true);
 
		if(list_View!=null)
		{
			System.out.println("list ok");
		}
		list_View.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
				
				positonValue = position;
				names[0] ="View";
				names[1]= "Cancel";
				adapter = new ArrayAdapter<String>(NewsActivity.this,android.R.layout.simple_list_item_1,
						names );
				
			    if(positonValue <-1)
			    {
			    	System.out.println("problem with pos");
			    }
			    	
				DialogBox = new AlertDialog.Builder(NewsActivity.this);
				DialogBox.setAdapter(adapter, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int select) {
						if(select == 0){
							String url;
							url = Links_News[positonValue];
							Intent intenti;
							intenti= new Intent(Intent.ACTION_VIEW);
							
							intenti.setData(Uri.parse(url));
							startActivity(intenti);
						}
						if(select ==1)
						{
							System.out.println("Cancelled");
						}
					}
				});
				DialogBox.setTitle("");
				DialogBox.setTitle("View News");
		        
				DialogBox.show();
			}
		});

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}

	
}
