package edu.usc.HomeWork9;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Response;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

import edu.usc.HomeWork9.R;


public class MainActivity extends Activity {
	  private AutoCompleteTextView auto_Complete;
	    
	    private Button search_Button;
	    private Button news_Button;
	    private Button facebook_Button;
	    
		private TextView company_Name;private TextView lastTradePrice_Only;private TextView percentage_Change;
		private TextView previous_Close;	private TextView open_Value;		private TextView bid_Value;
		private TextView ask_Value;		private TextView oneYearTarget_Price;	private TextView day_Range;
		
		private ArrayAdapter<String> adapter;
		List<String> autoComplete_List = null;
		Context context = null;
		private TextView year_Range;	private TextView volume_Value;		private TextView avgDaily_Volume;
		private TextView market_Cap;	private ImageView arrow_Image; private ImageView chart_URL;
		
		RelativeLayout resultView, errorView;
		ScrollView main;
		
//		private JSONObject jsonObject = null;
		// ending variable declaration
		private static JSONObject jObject = null;
		
	
	//private AutoCompleteTextView auto_Complete; 
	//private Button search_Button, news_Button, facebook_Button;
	//private ArrayAdapter<String> adapter;
	//List<String> autoComplete_List = null;
	//Context context = null;
	Activity activity = null;
	
	
	private static String APP_ID = "620746088014046";
	protected static final String TAG = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	try {
	        PackageInfo info = getPackageManager().getPackageInfo(
	                "com.facebook.samples.hellofacebook", 
	                PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	            MessageDigest md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	           
	            }
	    } catch (NameNotFoundException e) {

	    } catch (NoSuchAlgorithmException e) {

	    }
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        activity = this;
        
        context = this;
      
        main = (ScrollView) findViewById(R.id.container);

        auto_Complete = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
		search_Button = (Button) findViewById(R.id.searchButton);
		news_Button = (Button) findViewById(R.id.news_button);
        
        facebook_Button = (Button) findViewById(R.id.fb_button);
     
        
        resultView = (RelativeLayout) findViewById(R.id.result_view);
       
        errorView = (RelativeLayout) findViewById(R.id.error_view);
        

		
        
		company_Name = (TextView) findViewById(R.id.stock_name);
        lastTradePrice_Only = (TextView) findViewById(R.id.stock_value);
        percentage_Change = (TextView) findViewById(R.id.change_value_percentage);
        previous_Close = (TextView) findViewById(R.id.prev_close_val);
        open_Value = (TextView) findViewById(R.id.open_val);
        bid_Value = (TextView) findViewById(R.id.bid_val);
        ask_Value = (TextView) findViewById(R.id.ask_val);
        oneYearTarget_Price = (TextView) findViewById(R.id.year_target_val);
        day_Range = (TextView) findViewById(R.id.day_range_val);
        year_Range = (TextView) findViewById(R.id.week_range_val);
        volume_Value = (TextView) findViewById(R.id.volume_val);
        avgDaily_Volume = (TextView) findViewById(R.id.avg_volume_val);
        market_Cap = (TextView) findViewById(R.id.market_cap_val);
        
        arrow_Image = (ImageView) findViewById(R.id.indicator);
        chart_URL = (ImageView) findViewById(R.id.chart);
        
        resultView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        
        facebook_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onCreateDialog1(null);
			}
		});

		
        
        // Text Fields
      
        
        autoComplete_List = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,autoComplete_List );
        auto_Complete.setAdapter(adapter);
        int abs=0;
        auto_Complete.setThreshold(abs);
        
		auto_Complete.addTextChangedListener(new TextWatcher() 
		{         
	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) 
	        {
	        
	        	String abcdsaa;
	        	abcdsaa= s.toString();
	        	if(abcdsaa.isEmpty()){
	        		//new asyncfunction().execute(a);
		        	
	        	}
	        	else if(!abcdsaa.isEmpty()){
	        		String exc;
	        		exc = abcdsaa;
	        		if(exc!=null)
	        			new asyncfunction().execute(abcdsaa);
		        	
	        	}
	        	
	        }

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,
	                int after) {                

	        }

			@Override
			public void afterTextChanged(Editable s) {
				
			}
	    });
		
		auto_Complete.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String ci;
				ci= (String)parent.getItemAtPosition(position);
				String onlysymbol= (ci.split(","))[0];
				auto_Complete.setText(onlysymbol);
				if(onlysymbol!=null)
				{}
				AlertDialog.Builder alertDialoggg;
		    	auto_Complete.clearFocus();
		    	search_Button.requestFocus();
				String symbol = "";
				symbol = auto_Complete.getText().toString();
				symbol = symbol.trim();
				if(symbol.isEmpty())
				{
					alertDialoggg = new AlertDialog.Builder(MainActivity.this);
					  alertDialoggg.setTitle("Enter Company Name");
				        
				        alertDialoggg.show();
				}
				else
				{
				//	Log.d(TAG, "getStockData(): Text= " + symbol);
					if(!symbol.isEmpty()){
			    		new RetrieveStockDataTask().execute(symbol);
			        	
			    	}
				}
			}
		});
		
		
		search_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder alertDialogg;
		    	auto_Complete.clearFocus();
		    	search_Button.requestFocus();
				String symbol = "";
				symbol = auto_Complete.getText().toString();
				symbol = symbol.trim();
				if(symbol.isEmpty())
				{
					alertDialogg = new AlertDialog.Builder(MainActivity.this);
					  alertDialogg.setTitle("Enter Company Name");
				        
				        alertDialogg.show();
				}
				else
				{
					//Log.d(TAG, "getStockData(): Text= " + symbol);
					if(!symbol.isEmpty()){
			    		new RetrieveStockDataTask().execute(symbol);
			        	
			    	}
				}
			}
		});
		
    }


    protected void getStockData() {
    	AlertDialog.Builder alertDialogg;
    	auto_Complete.clearFocus();
    	search_Button.requestFocus();
		String symbol = "";
		symbol = auto_Complete.getText().toString();
		symbol = symbol.trim();
		if(symbol.isEmpty())
		{
			alertDialogg = new AlertDialog.Builder(MainActivity.this);
			  alertDialogg.setTitle("Enter Company Name");
		        
		        alertDialogg.show();
		}
		else
		{
			
			if(!symbol.isEmpty()){
	    		new RetrieveStockDataTask().execute(symbol);
	        	
	    	}
		}
	}

    
    
/*public void FBFeed() {
		
		Session.openActiveSession(this, true, new StatusCallback() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				// TODO Auto-generated method stub
			
				if(session.isOpened())
				{
					
					Request.executeMeRequestAsync(session,new GraphUserCallback() {
						
						@Override
						public void onCompleted(GraphUser user, Response response) {
							// TODO Auto-generated method stub
							
							Bundle params = new Bundle();
							
							name: Name_co,
						      caption: 'Stock Information of '+Name_co+'('+Sym_co+')',
						      description: (
						         'Last Trade Price:' +Ltp_co+', Change:'+Chtype_co+Ch_co+'('+Cip_co+')'
						        
						      ),
						      link: 'http://finance.yahoo.com/q?s='+Sym_co,
						      picture: Img_co
							
							    params.putString("name", (String)map1.get("Name"));
							    params.putString("caption", "Stock Information of "+ (String)map1.get("Name")+ "("+(String)map1.get("Symbol")+")");
							    params.putString("description", "Last Trade Price:"+(String)map1.get("LastTradePriceOnly")+", Change:"+(String)map1.get("Change"));
							    params.putString("link", "http://finance.yahoo.com/q?s="+(String)map1.get("Symbol"));
							    params.putString("picture", (String)map1.get("StockChartImageURL"));
							
						currentFb=Session.getActiveSession();
						
						WebDialog feedDialogue =(new WebDialog.FeedDialogBuilder(MainActivity.this, currentFb, params))
								.setOnCompleteListener(new OnCompleteListener() {
							
							@Override
							public void onComplete(Bundle values, FacebookException error) {
								// TODO Auto-generated method stub
								
								if (error == null) {
				                    // When the story is posted, echo the success
				                    // and the post Id.
				                    final String postId = values.getString("post_id");
				                    if (postId != null) {
				                        Toast.makeText(getApplicationContext(),
				                            "Posted succesfully:\npostId",
				                            Toast.LENGTH_SHORT).show();
				                    } else {
				                        // User clicked the Cancel button
				                        Toast.makeText(getApplicationContext(), 
				                            "Publish cancelled", 
				                            Toast.LENGTH_SHORT).show();
				                    }
				                } else if (error instanceof FacebookOperationCanceledException) {
				                    // User clicked the "x" button
				                    Toast.makeText(getApplicationContext(), 
				                        "Publish cancelled", 
				                        Toast.LENGTH_SHORT).show();
				                } else {
				                    // Generic, ex: network error
				                    Toast.makeText(getApplicationContext(), 
				                        "Error posting story", 
				                        Toast.LENGTH_SHORT).show();
				                }
				            }
	
							
						}).build();	
						feedDialogue.show();
							    
						}
					});
				}
			}
		});
  }*/

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id ;
        id = item.getItemId();
        if (R.id.action_settings== id ) {
            return true;
        }
        else if (R.id.action_settings!= id)
        {
        	System.out.println("no tok");
        }
        return super.onOptionsItemSelected(item);
    }
    
    class asyncfunction extends AsyncTask<String, Void, List<String>> {

        protected List<String> doInBackground(String... urls) {
            try {
            	System.out.println("returned");
            	int a=1;
            	if(a==1)
            	{
            		System.out.println(a);
            	}
            	return PopulateData.populatingData(urls[0]);
            	
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(List<String> ret) {
        	
        	if (!ret.isEmpty() && ret != null ) {
        		Log.d(TAG, "return server Call with size ="+ret.size());
        		if(true)
				autoComplete_List.clear();
				adapter.clear();
				autoComplete_List = new ArrayList<String>();
				if(true)
				autoComplete_List.addAll(ret);
		        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,
						autoComplete_List );
		        auto_Complete.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
        }
    }
    
    class RetrieveStockDataTask extends AsyncTask<String, Void, HashMap<String, Object>> {

        protected HashMap<String, Object> doInBackground(String... symbol) {
            try {
            	//Log.d(TAG, "RetrieveStockDataTask(): start server Call");
            	return PopulateData.JsontoHashMap(symbol[0]);
            } catch (Exception e) {
            	e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(HashMap<String, Object> ret) {
        	
        	
        	//map= ret
        	// map1 = map;
     		if(null != ret && !ret.isEmpty()){
     			
     			Log.d(TAG, "chnge typr ="+(String)ret.get("ChangeType"));
     			if(((String)ret.get("ChangeType")).equalsIgnoreCase("+")){
     				percentage_Change.setTextColor(Color.parseColor("#00ff00"));
     				arrow_Image.setImageResource(R.drawable.up_g);
     			} else if(((String)ret.get("ChangeType")).equalsIgnoreCase("-")){
     				percentage_Change.setTextColor(Color.parseColor("#ff0000"));
     				arrow_Image.setImageResource(R.drawable.down_r);
     			} else {
     				percentage_Change.setTextColor(Color.parseColor("#000000"));
     				arrow_Image.setImageResource(R.drawable.up_g);
     			}
     			company_Name.setText((String)ret.get("Name")+"("+(String)ret.get("Symbol")+")");
     	        lastTradePrice_Only.setText((String)ret.get("LastTradePriceOnly"));
     	        percentage_Change.setText((String)ret.get("Change")+" ("+(String)ret.get("ChangeInPercent")+")");
     	        previous_Close.setText((String)ret.get("PreviousClose"));
     	        open_Value.setText((String)ret.get("Open"));
     	        bid_Value.setText((String)ret.get("Bid"));
     	        ask_Value.setText((String)ret.get("Ask"));
     	        oneYearTarget_Price.setText((String)ret.get("OneYearTargetPrice"));
     	        day_Range.setText((String)ret.get("DaysLow")+" - "+(String)ret.get("DaysHigh"));
     	        year_Range.setText((String)ret.get("YearLow")+" - "+(String)ret.get("YearHigh"));
     	        volume_Value.setText((String)ret.get("Volume"));
     	        avgDaily_Volume.setText((String)ret.get("AverageDailyVolume"));
     	        market_Cap.setText((String)ret.get("MarketCapitalization"));
     	        new LoadImageTask().execute(ret);
     	        if((Boolean)ret.get("hasNews")){
     	        	news_Button.setEnabled(true);
     	        	news_Button.setOnClickListener(new OnClickListener() {
     					
     					@Override
     					public void onClick(View v) {
     						loadNews(map1);
     					}
     				});
     	        }
     	        else
     	        	news_Button.setEnabled(false);
     	        
     	        
     	        errorView.setVisibility(View.GONE);
     	        resultView.setVisibility(View.VISIBLE);
     		} else {
     			resultView.setVisibility(View.GONE);
     			errorView.setVisibility(View.VISIBLE);
     			
     		}

        }
    }
    
    class LoadImageTask extends AsyncTask<HashMap<String, Object>, Void, Void> {

        protected Void doInBackground(HashMap<String, Object>... map) {
            try {
            	Log.d(TAG, "LoadImageTask(): start server Call");
            	setImage(map[0]);
            } catch (Exception e) {
            	e.printStackTrace();
            }
			return null;
        }

        protected void onPostExecute( ) {
        	Log.d(TAG, "RetrieveStockDataTask(): return server Call");
        }
    }
    HashMap<String, Object> map1;

	public void SettingtheData(HashMap<String, Object> map) {
		 map1 = map;
		if(null != map && !map.isEmpty()){
			
			//Log.d(TAG, "chnge typr ="+(String)map.get("ChangeType"));
			//Color 1= 
			String chantype = "ChangeType";
			if(((String)map.get(chantype)).equalsIgnoreCase("+")){
				
				arrow_Image.setImageResource(R.drawable.up_g);
				percentage_Change.setTextColor(Color.parseColor("#00ff00"));
				
			} else if(((String)map.get("ChangeType")).equalsIgnoreCase("-")){
				arrow_Image.setImageResource(R.drawable.down_r);
				percentage_Change.setTextColor(Color.parseColor("#ff0000"));
				
			} else {arrow_Image.setImageResource(R.drawable.up_g); 
			String color2= "#000000";
				percentage_Change.setTextColor(Color.parseColor(color2));
				
			}
			String cName = (String)map.get("Name")+"("+(String)map.get("Symbol")+")";
			company_Name.setText(cName);
			String lOnly = (String)map.get("LastTradePriceOnly");
	        lastTradePrice_Only.setText(lOnly);
	        String chan = (String)map.get("Change")+" ("+(String)map.get("ChangeInPercent")+")";
	        percentage_Change.setText(chan);
	        String close = (String)map.get("PreviousClose");
	        previous_Close.setText(close);
	        String op= (String)map.get("Open");
	        open_Value.setText(op);
	        String bd= (String)map.get("Bid");
	        bid_Value.setText(bd);
	        String ak= (String)map.get("Ask");
	        ask_Value.setText(ak);
	        String oytp = (String)map.get("OneYearTargetPrice");
	        oneYearTarget_Price.setText(oytp);
	        String dhdl = (String)map.get("DaysLow")+" - "+(String)map.get("DaysHigh");
	        day_Range.setText(dhdl);
	        String ylyh = (String)map.get("YearLow")+" - "+(String)map.get("YearHigh");
	        year_Range.setText(ylyh);
	        String vm = (String)map.get("Volume");
	        volume_Value.setText(vm);
	        String adv = (String)map.get("AverageDailyVolume");
	        avgDaily_Volume.setText(adv);
	        String mc = (String)map.get("MarketCapitalization");
	        market_Cap.setText(mc);
	        new LoadImageTask().execute(map);
	        if((Boolean)map.get("Newsispreset")){
	        	news_Button.setEnabled(true);
	        	news_Button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						loadNews(map1);
					}
				});
	        }
	        else
	        	news_Button.setEnabled(false);
	        
	        
	        errorView.setVisibility(View.GONE);
	        resultView.setVisibility(View.VISIBLE);
		} else {
			resultView.setVisibility(View.GONE);
			errorView.setVisibility(View.VISIBLE);
			
		}
	}


	protected void loadNews(HashMap<String, Object> map1) {
		Toast.makeText(getApplicationContext(),
				"Showing "+(Integer)map1.get("Lengthofnews")+" headlines", Toast.LENGTH_LONG).show();
		Intent inmy;
		inmy = new Intent(MainActivity.this, NewsActivity.class);
		if(inmy!=null)
			inmy.putExtra("news", (String[])map1.get("Titleofnews"));
		inmy.putExtra("links", (String[])map1.get("Linkofnews"));//Optional parameters
		MainActivity.this.startActivity(inmy);
	}


	private void setImage(HashMap<String, Object> map) {
		try {
			  Bitmap bitmap;
			  InputStream is =  (InputStream)new URL((String)map.get("StockChartImageURL")).getContent();
			  bitmap= BitmapFactory.decodeStream(is);
			  chart_URL.setImageBitmap(bitmap); 
			} catch (MalformedURLException e) {
			  e.printStackTrace();
			} catch (IOException e) {
			  e.printStackTrace();
			}
	}
	
	public void onCreateDialog1(Bundle savedInstanceState) {
		com.facebook.Session.openActiveSession(MainActivity.this, true, 
	    		new com.facebook.Session.StatusCallback() {

					@SuppressWarnings("deprecation")
					@Override
					public void call(
							com.facebook.Session session,
							SessionState state,
							Exception exception) {
						
				        if (session.isOpened()) {
				        	com.facebook.Request.executeMeRequestAsync(session, 
				        			new com.facebook.Request.GraphUserCallback() 
				        	{

								@Override
								public void onCompleted(
										GraphUser user,
										Response response) {
									// TODO Auto-generated method stub
									if(user!=null)
									{
										FBFeed();
									}
									
								}
				        	});
				        }
				        
						
					}
	    });
	}
	
	private void FBFeed() {
	    Bundle params;
	    params = new Bundle();
	    String nameFB= (String)map1.get("Name");
	    params.putString("name", nameFB);
	    String captionFB =  "Stock Information of "+ (String)map1.get("Name")+ "("+(String)map1.get("Symbol")+")";
	    params.putString("caption",captionFB);
	    String desc =  "Last Trade Price:"+(String)map1.get("LastTradePriceOnly")+", Change:"+(String)map1.get("Change");
	    params.putString("description",desc);
	    String link= "http://finance.yahoo.com/q?s="+(String)map1.get("Symbol");
	    params.putString("link", link);
	    String picturep= (String)map1.get("StockChartImageURL");
	    params.putString("picture",picturep );
	     
	    WebDialog feedDialog = (
	        new WebDialog.FeedDialogBuilder(MainActivity.this,
	            com.facebook.Session.getActiveSession(),
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {

	            @Override
	            public void onComplete(Bundle values,
	                FacebookException error) {
	                if (error == null) {
	                    // When the story is posted, echo the success
	                    // and the post Id.
	                    final String postId ;
	                    postId= values.getString("post_id");
	                    if (postId != null) {
	                        Toast.makeText(MainActivity.this,
	                            "Posted story, id: "+postId,  Toast.LENGTH_SHORT).show();
	                    } else {
	                        // User clicked the Cancel button
	                        Toast.makeText(MainActivity.this.getApplicationContext(), 
	                            "Publish cancelled",  Toast.LENGTH_SHORT).show();
	                    }
	                } else if (error instanceof FacebookOperationCanceledException) {
	                    // User clicked the "x" button
	                    Toast.makeText(MainActivity.this.getApplicationContext(),    "Publish cancelled", 
	                        Toast.LENGTH_SHORT).show();
	                } else {
	                    // Generic, ex: network error
	                    Toast.makeText(MainActivity.this.getApplicationContext(),   "Error posting story", 
	                        Toast.LENGTH_SHORT).show();
	                }
	            }

	        })
	        .build();
	    feedDialog.show();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		  super.onActivityResult(requestCode, resultCode, data);
		  com.facebook.Session.getActiveSession().onActivityResult(MainActivity.this, requestCode, resultCode, data);
		  
	}

}
