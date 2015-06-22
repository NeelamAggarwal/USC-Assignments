package edu.usc.HomeWork9;

/**
 * Yahoo! Web Services Example: search API via POST
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007 Daniel Jones
 *
 * This example illustrates how to perform a web service request via HTTP POST.
 * See the YahooWebServiceGet example if you want to include all named parameters 
 * in the URL as a GET request.
 */

import java.io.*;
import java.util.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PopulateData {	
	 
	private static JSONObject json_Object = null;
	
	
	public static List<String> populatingData(String symbolCompany)
	{
		List<String> tempCompanyList;
		String request;
		tempCompanyList = new ArrayList<String>();
		InputStream data_Stream = null;
		String firstline="";
		int index_Start=0;
		JSONObject result_Root;
		JSONArray child_Root;
		BufferedReader reader_Data = null ;
		String symbol, name, exchange;
        request = "http://autoc.finance.yahoo.com/autoc?query="+symbolCompany+"&callback=YAHOO.Finance.SymbolSuggest.ssCallback"; 
        
        try {
			HttpClient client = new HttpClient();
			PostMethod postMethod = new PostMethod(request);
		
			// Send POST request
			int statusCode = client.executeMethod(postMethod);
			
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + postMethod.getStatusLine());
			}
			else
			{
				System.out.println("Method passed");
			}
			// Get the response body
			data_Stream = postMethod.getResponseBodyAsStream();
			if(data_Stream != null)
			{
			// Process the response from Yahoo! Web Services
				reader_Data = new BufferedReader(new InputStreamReader(data_Stream));
			}
			else
			{
				reader_Data = new BufferedReader(new InputStreamReader(data_Stream));
			}
			//reader_Data = new BufferedReader(new InputStreamReader(data_Stream));
			firstline = reader_Data.readLine();
			reader_Data.close();
			if(firstline!=null)
			{
				index_Start = firstline.indexOf("{");
			}
			else
			{
				index_Start = firstline.indexOf("{");
			}
			int lengthfirstline =  firstline.length()-1;
			if(lengthfirstline!=-1)
			{
				firstline=firstline.substring(index_Start, lengthfirstline);
			}
			if(index_Start != -1)
			{
				//firstline= firstline+index_Start;
			}
			//firstline= firstline+index_Start;
			//json_Object = null;
			boolean condition = true;
			json_Object = new JSONObject(firstline);
			
			//result_Root = jObject.getJSONObject("ResultSet");
			
			//child_Root = result_Root.getJSONArray("Result");
			
			child_Root = (json_Object.getJSONObject("ResultSet")).getJSONArray("Result");
			symbol = "symbol";
			name= "name";
			exchange = "exch";
			do
			{
				for (int i = 0; i < child_Root.length(); ++i) 
				{
					if(condition)
					{
					  JSONObject child_Json = child_Root.getJSONObject(i);
					  String output="";
					  
					  output+=child_Json.getString(symbol);
					  output+=", "+child_Json.getString(name);
					  output+="("+child_Json.getString(exchange)+")";
					  tempCompanyList.add(output);
					}
					else
					{
						System.out.println("ELSE PART");
					}
				
				}
			}while(false);
			
		} catch (HttpException e) {
			try
			{
				System.out.println("HTTPEXCEPTION");
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(true)
			{
				e.printStackTrace();
			}
		} catch (JSONException e) {
			try
			{
				System.out.println("JSONException");
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return tempCompanyList;
	}
	
	
	
	
	public static HashMap JsontoHashMap(String Company_Symbol) throws Exception
	{
		String links_News[];
		HashMap<String, Object> hash_Mapping;
		String title_News[];
		String request;
		Boolean is_News=false; 
		int length;
		int statusCode;
		hash_Mapping = new HashMap<String, Object>();
	    HttpClient client =  null;
	    InputStream data_Stream = null;
	    GetMethod post_Method = null;
	    BufferedReader read_Databuffer = null;
	    String firstline=  null;
	    firstline = "";
	    JSONObject result_Root;
	    JSONObject quoteinfo;
	    
        request = "http://cs-server.usc.edu:20514/examples/servlet/HomeWork8?CompanyName="+Company_Symbol; 
        
        if(request != null)
    	{
        	client = new HttpClient();
        	post_Method = new GetMethod(request);
    	}
      
        statusCode = client.executeMethod(post_Method);
        
        if (statusCode != HttpStatus.SC_OK) {
        	System.err.println("Method failed: " + post_Method.getStatusLine());
        }
        else
        {
        	System.out.println("method success");
        }
        
        data_Stream = post_Method.getResponseBodyAsStream();
        
        if(data_Stream != null)
        {
          read_Databuffer  = new BufferedReader(new InputStreamReader(data_Stream));
        }
      
        if(read_Databuffer != null)
        	firstline = read_Databuffer.readLine();
    
        read_Databuffer.close();
        //System.out.println(line);
        //json_Object = null;
        json_Object = new JSONObject(firstline);
        
        result_Root = json_Object.getJSONObject("result");
        quoteinfo= result_Root.getJSONObject("Quote");
        
        String Namestring = result_Root.getString("Name");
        hash_Mapping.put("Name",Namestring);
        
        String SymbolString = result_Root.getString("Symbol");
        hash_Mapping.put("Symbol",SymbolString);
        
        //hash_Mapping.put("Symbol", new String(result_Root.getString("Symbol")));
        
        String changetype =  quoteinfo.getString("ChangeType");
        hash_Mapping.put("ChangeType", changetype);
        
        String change =  quoteinfo.getString("Change");
        hash_Mapping.put("Change",(change));
        
        String changeInPercent =  quoteinfo.getString("ChangeInPercent");
        hash_Mapping.put("ChangeInPercent",changeInPercent);
        
        String lastTradePriceOnly =  quoteinfo.getString("LastTradePriceOnly");
        hash_Mapping.put("LastTradePriceOnly",lastTradePriceOnly);
        
        String open =  quoteinfo.getString("Open");
        hash_Mapping.put("Open",open);
        
        String yearLow =  quoteinfo.getString("YearLow");
        hash_Mapping.put("YearLow", yearLow);
        
        String yearHigh =  quoteinfo.getString("YearHigh");
        hash_Mapping.put("YearHigh", (yearHigh));
        
        String volume =  quoteinfo.getString("Volume");
        hash_Mapping.put("Volume", volume);
        
        String oneYearTargetPrice =  quoteinfo.getString("OneYearTargetPrice");
        hash_Mapping.put("OneYearTargetPrice",oneYearTargetPrice);
        
        String bid =  quoteinfo.getString("Bid");
        hash_Mapping.put("Bid",bid);
        
        String daysLow =  quoteinfo.getString("DaysLow");
        hash_Mapping.put("DaysLow", daysLow);
        
        String daysHigh =  quoteinfo.getString("DaysHigh");
        hash_Mapping.put("DaysHigh",daysHigh);
        
        String ask =  quoteinfo.getString("Ask");
        hash_Mapping.put("Ask",ask);
        
        String averageDailyVolume =  quoteinfo.getString("AverageDailyVolume");
        hash_Mapping.put("AverageDailyVolume", averageDailyVolume);
        
        String previousClose =  quoteinfo.getString("PreviousClose");
        hash_Mapping.put("PreviousClose",previousClose);
        
        String marketCapitalization =  quoteinfo.getString("MarketCapitalization");
        hash_Mapping.put("MarketCapitalization", marketCapitalization);
        
        String stockChartImageURL =  result_Root.getString("StockChartImageURL");
        hash_Mapping.put("StockChartImageURL",stockChartImageURL);
        
      
        JSONObject news;
        news = result_Root.getJSONObject("News");
        JSONArray item;
        item = news.getJSONArray("Item");
        
        //int n = item.length();
        //length=n;
        
        links_News = new String[item.length()];
        title_News = new String[item.length()];
 
        do{
	        for (int i = 0; i < item.length(); ++i) 
	        {
	        	JSONObject news_Link;
	        	news_Link = item.getJSONObject(i);
	        	if(news_Link != null)
	        	{
		        	title_News[i]=news_Link.getString("Title");
		        	links_News[i]=news_Link.getString("Link");
	        	}
	        	do
	        	{
		        	if(links_News[i].equals("No Link"))
		        	{
		        		is_News = false;
		        		is_News = false;
		        	}
		        	else
		        	{
		        		is_News = true;
		        		is_News = true;
		        	}
	        	}while(false);
	        }
        }while(false);
        
        
        hash_Mapping.put("Newsispreset", is_News);
        hash_Mapping.put("Lengthofnews",item.length());
        if(true)
        {
	        hash_Mapping.put("Linkofnews", links_News);
	        hash_Mapping.put("Titleofnews", title_News);
        }
        
        return hash_Mapping;
        
          
	}
	
}