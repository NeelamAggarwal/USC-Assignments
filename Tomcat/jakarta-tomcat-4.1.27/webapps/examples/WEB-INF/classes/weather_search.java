
/* $Id: HelloWorldExample.java,v 1.2 2001/11/29 18:27:25 remm Exp $
*
*/

import java.io.*;
import java.lang.*;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import java.util.*;
import java.io.BufferedReader.*;
import java.net.*;
import org.w3c.dom.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.regex.*;
import java.lang.*;
import java.lang.Number;
import javax.xml.parsers.*;
import java.net.URL;
import java.io.InputStream;
import org.w3c.dom.*;
import java.io.BufferedReader;


public class weather_search extends HttpServlet {


   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
       throws IOException, ServletException
   {     
       
       String location = request.getParameter("location");
       String type = request.getParameter("type");
       String tempunit = request.getParameter("tempunit");
       PrintWriter out = response.getWriter();
       Document doc = null;
       
 //      try
  //     {
    	   request.setCharacterEncoding("UTF-8");    	   
    	   response.setContentType("text/html; charset=UTF-8");
    	   //String urlstr = "http://cs-server.usc.edu:13636/yahooweather.php?location="+location+"&type="+type+"&tempunit="+tempunit;
    	   String urlstr = "http://default-environment-bmacgdpmwq.elasticbeanstalk.com/?location="+location+"&type="+type+"&tempunit="+tempunit;
    	   //String urlstr = "http://cs-server.usc.edu:13636/yahooweather.php?location=60000&type=zip&tempunit=F";
    	   URL url = new URL(urlstr);
    	   URLConnection urlConnection = url.openConnection();
    	   urlConnection.setAllowUserInteraction(false);
    	   
    	   try {
    	   InputStream urlStream = url.openStream();
    	   DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
    	   domFactory.setNamespaceAware(true); 
    	   
    	   DocumentBuilder builder = domFactory.newDocumentBuilder();
    	   doc = builder.parse(urlStream);
    	   } 
    	   catch (ParserConfigurationException yahoo_e) {
    	    
    	        yahoo_e.printStackTrace();
    	   }
    	   catch (Exception yahoo_e) {
    	
         		yahoo_e.printStackTrace();
           }
    	   
    	   
    	   NodeList weatherList = doc.getElementsByTagName("forecast");
    	   Node nodeTimepass = weatherList.item(0);
    	   Element weatherTimepass = (Element)nodeTimepass;
    	   String eday = weatherTimepass.getAttribute("day");
    	   String elow = weatherTimepass.getAttribute("low");
    	   
    	   if(eday.equals("Empty") && elow.equals("Empty")) {
      	       String code_fault = "{\"code\":602 }";    	       
    	       out.println(code_fault);
    	   } else {    	   
				String jasonString = "{\"weather\":{\"forecast\":[";
    
				Node node = weatherList.item(0);
				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
    			   Element weather = (Element)node;
    			   String w_text = weather.getAttribute("text");
    			   String w_high = weather.getAttribute("high");
    			   String w_day = weather.getAttribute("day");
    			   String w_low = weather.getAttribute("low");
    			  // jasonString = jasonString + "],";
    			   jasonString+="{\"text\":\""+w_text+"\",\"high\":"+w_high+",\"day\":\""+w_day+"\",\"low\":"+w_low+"},";    			  
    		    }
    		    NodeList weatherList1 = doc.getElementsByTagName("forecast1");
        	  
        	    Node node1 = weatherList1.item(0);
        		if (node1.getNodeType() == Node.ELEMENT_NODE)
        		{
        			   Element weather = (Element)node1;
        			   String w_text = weather.getAttribute("text");
        			   String w_high = weather.getAttribute("high");
        			   String w_day = weather.getAttribute("day");
        			   String w_low = weather.getAttribute("low");
        			   //jasonString = jasonString + "],";
        			   jasonString+="{\"text\":\""+w_text+"\",\"high\":"+w_high+",\"day\":\""+w_day+"\",\"low\":"+w_low+"},";
        		    
        		   
        		   }
        		   NodeList weatherList2 = doc.getElementsByTagName("forecast2");
             	  
        		   Node node2 = weatherList2.item(0);
        		   if (node2.getNodeType() == Node.ELEMENT_NODE)
        		   {
        			   Element weather = (Element)node2;
        			   String w_text = weather.getAttribute("text");
        			   String w_high = weather.getAttribute("high");
        			   String w_day = weather.getAttribute("day");
        			   String w_low = weather.getAttribute("low");
        			   //jasonString = jasonString + "],";
        			   jasonString+="{\"text\":\""+w_text+"\",\"high\":"+w_high+",\"day\":\""+w_day+"\",\"low\":"+w_low+"},";
        		   }
        		   NodeList weatherList3 = doc.getElementsByTagName("forecast3");
             	  
        		   Node node3 = weatherList3.item(0);
        		   if (node3.getNodeType() == Node.ELEMENT_NODE)
        		   {
        			   Element weather = (Element)node3;
        			   String w_text = weather.getAttribute("text");
        			   String w_high = weather.getAttribute("high");
        			   String w_day = weather.getAttribute("day");
        			   String w_low = weather.getAttribute("low");
        			  // jasonString = jasonString + "],";
        			   jasonString+="{\"text\":\""+w_text+"\",\"high\":"+w_high+",\"day\":\""+w_day+"\",\"low\":"+w_low+"},";
        		   }
        		   NodeList weatherList4 = doc.getElementsByTagName("forecast4");
             	  
        		   Node node4 = weatherList4.item(0);
        		   if (node4.getNodeType() == Node.ELEMENT_NODE)
        		   {
        			   Element weather = (Element)node4;
        			   String w_text = weather.getAttribute("text");
        			   String w_high = weather.getAttribute("high");
        			   String w_day = weather.getAttribute("day");
        			   String w_low = weather.getAttribute("low");
        			   //jasonString = jasonString + "],";
        			   jasonString+="{\"text\":\""+w_text+"\",\"high\":"+w_high+",\"day\":\""+w_day+"\",\"low\":"+w_low+"}";
        		   }
        		   
        		   NodeList conditionList = doc.getElementsByTagName("condition");
        		   Node node5 = conditionList.item(0);
        		   if (node5.getNodeType() == Node.ELEMENT_NODE)
        		   {
        		      Element weather = (Element)node5;
        		   	jasonString = jasonString+ "],\"condition\":{";
        		   
        		   String w_ctext = weather.getAttribute("text");
        		   String w_ctemp = weather.getAttribute("temp");
        		   
        		   //jasonString = jasonString + "},";
        		   jasonString+="\"text\":\""+w_ctext+"\",\"temp\":"+w_ctemp+"},";
        		   }
        		   
        		   
        		   
        		   NodeList locationList = doc.getElementsByTagName("location");
        		   Node node6 = locationList.item(0);
        		   if (node6.getNodeType() == Node.ELEMENT_NODE)
        		   {
        		      Element weather = (Element)node6;
        		   jasonString = jasonString+ "\"location\":";
        		   
        		   String w_region = weather.getAttribute("region");
        		   String w_country = weather.getAttribute("country");
        		   String w_city = weather.getAttribute("city");
        		   //jasonString = jasonString + "},";
        		   jasonString+="{\"region\":\""+w_region+"\",\"country\":\""+w_country+"\",\"city\":\""+w_city+"\"},";
        		   }
        		   
        		   NodeList linkList = doc.getElementsByTagName("link");
        		   Node node7 = linkList.item(0);
        		   //out.println(node7);
        		   if (node7.getNodeType() == Node.ELEMENT_NODE)
        		   {
        		      Element weather = (Element)node7;
        		   //jasonString = jasonString+ "\"link\":";
        		   String w_link = weather.getAttribute("link1");
        		   //out.println(node7.getNodeValue());
        		   //jasonString = jasonString + ",";
        		   jasonString+="\"link\":\""+w_link+"\",";
        		   }
		   
		   
        		   NodeList imgList = doc.getElementsByTagName("img");
        		   Node node8 = imgList.item(0);
        		   if (node8.getNodeType() == Node.ELEMENT_NODE)
        		   {
        		      Element weather = (Element)node8;
        		     
        		   //jasonString = jasonString+ "\"img\":";
        		   String w_img = weather.getAttribute("img1");
        		   //jasonString = jasonString + ",";
        		   jasonString+="\"img\":\""+w_img+"\",";
        		   }
        		   
        		   
        		   NodeList feedList = doc.getElementsByTagName("feed");
        		   Node node9 = feedList.item(0);
        		   if (node9.getNodeType() == Node.ELEMENT_NODE)
        		   {
        		      Element weather = (Element)node9;
        		   //jasonString = jasonString+ "\"feed\":";
        		   String w_feed = weather.getAttribute("feed1");
        		   //jasonString = jasonString + ",";
        		   jasonString+="\"feed\":\""+w_feed+"\",";
        		   }
        		   
        		   //out.println(jasonString);
        		   NodeList unitList = doc.getElementsByTagName("units");
        		   Node noded = unitList.item(0);
        		   
        		   if (noded.getNodeType() == Node.ELEMENT_NODE)
        		   {
        		      Element weather = (Element)noded;
        		   jasonString = jasonString+ "\"units\":";
        		   
        		    String w_temp = weather.getAttribute("units1");
        		    jasonString+="{\"temperature\":\""+w_temp+"\"";
        		   }
        		   jasonString = jasonString + "}}}";
        		   out.println(jasonString);
        	}
  /*     }
       catch(MalformedURLException e)
       {
        out.println("body");
       	System.out.println(e.getMessage());
       }
       catch(IOException e)
       {        
               out.println("body1");
       	System.out.println(e.getMessage());
       }
       catch(Exception e)
       {        
               out.println("body2");
       	System.out.println(e.getMessage());
       }
  */ }
}



