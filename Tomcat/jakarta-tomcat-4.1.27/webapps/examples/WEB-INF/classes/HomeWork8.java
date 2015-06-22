import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.* ;
import org.json.* ;
import java.text.*;
import java.util.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.XML;


/**
 * Servlet implementation class Second_Servlet
 */
//@WebServlet("/Second_Servlet")
public class HomeWork8 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{			
			String CompanyName = request.getParameter("CompanyName");
			 try
		  {
		  request.setCharacterEncoding("UTF-8");
   	      
		  PrintWriter out = response.getWriter();
   	      response.setContentType("text/html; charset=UTF-8");
			String urlstr = "http://default-environment-wx2u78waju.elasticbeanstalk.com/?CompanyName="+URLEncoder.encode(CompanyName,"UTF-8");
			//http://default-environment-wx2u78waju.elasticbeanstalk.com/?CompanyName=GOOG
		
   	      URL url = new URL(urlstr);
   	      URLConnection urlConnection = url.openConnection();
   	      urlConnection.setAllowUserInteraction(false);
   	      InputStream urlStream = url.openStream();
   	      
   	      
   	      DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
   	      domFactory.setNamespaceAware(true); 
   	      DocumentBuilder builder = domFactory.newDocumentBuilder();
   	      Document doc = builder.parse(urlStream);
   	   
   	      doc.getDocumentElement().normalize();	      
	      String jsonString = "{\"result\":";
	      
  	      Element s_name = (Element)(doc.getElementsByTagName("Name")).item(0);
  	      Element s_symbol = (Element)(doc.getElementsByTagName("Symbol")).item(0);
  	      
  	      jsonString+="{\"Name\":\""+s_name.getFirstChild().getNodeValue()+"\",\"Symbol\":\""+s_symbol.getFirstChild().getNodeValue()+"\",\"Quote\":";
  	      
  	      NodeList stockList = doc.getElementsByTagName("Quote");
   	      
   	      Element quote = (Element) stockList.item(0);
   	      
   	      Element s_changetype1 = (Element) quote.getElementsByTagName("ChangeType").item(0);
   	      
   	      
   	      Element s_change = (Element) quote.getElementsByTagName("Change").item(0);
	      
	      
	      Element s_changeinpercent = (Element) quote.getElementsByTagName("ChangeInPercent").item(0);
	      Element s_lasttradepriceonly = (Element) quote.getElementsByTagName("LastTradePriceOnly").item(0);
	      Element s_previousclose = (Element) quote.getElementsByTagName("PreviousClose").item(0);
	      Element s_dayslow = (Element) quote.getElementsByTagName("DaysLow").item(0);
	      Element s_dayshigh = (Element) quote.getElementsByTagName("DaysHigh").item(0);
	      Element s_open = (Element) quote.getElementsByTagName("Open").item(0);
	      Element s_yearlow = (Element) quote.getElementsByTagName("YearLow").item(0);
	      Element s_yearhigh = (Element) quote.getElementsByTagName("YearHigh").item(0);
	      Element s_bid = (Element) quote.getElementsByTagName("Bid").item(0);
	      Element s_volume = (Element) quote.getElementsByTagName("Volume").item(0);
	      Element s_ask = (Element) quote.getElementsByTagName("Ask").item(0);
	      Element s_averagedailyvolume = (Element) quote.getElementsByTagName("AverageDailyVolume").item(0);
	      Element s_oneyeartargetprice = (Element) quote.getElementsByTagName("OneYearTargetPrice").item(0);
	      Element s_marketcapitalization = (Element) quote.getElementsByTagName("MarketCapitalization").item(0);
   	      
	      jsonString+="{\"ChangeType\":\""+s_changetype1.getFirstChild().getNodeValue()+"\",\"Change\":\""+s_change.getFirstChild().getNodeValue()+"\",\"ChangeInPercent\":\""+s_changeinpercent.getFirstChild().getNodeValue()+"\",\"LastTradePriceOnly\":\""+s_lasttradepriceonly.getFirstChild().getNodeValue()+"\",\"Open\":\""+s_open.getFirstChild().getNodeValue()+"\",\"YearLow\":\""+s_yearlow.getFirstChild().getNodeValue()+"\",\"YearHigh\":\""+s_yearhigh.getFirstChild().getNodeValue()+"\",\"Volume\":\""+s_volume.getFirstChild().getNodeValue()+"\",\"OneYearTargetPrice\":\""+s_oneyeartargetprice.getFirstChild().getNodeValue()+"\",\"Bid\":\""+s_bid.getFirstChild().getNodeValue()+"\",\"DaysLow\":\""+s_dayslow.getFirstChild().getNodeValue()+"\",\"DaysHigh\":\""+s_dayshigh.getFirstChild().getNodeValue()+"\",\"Ask\":\""+s_ask.getFirstChild().getNodeValue()+"\",\"AverageDailyVolume\":\""+s_averagedailyvolume.getFirstChild().getNodeValue()+"\",\"PreviousClose\":\""+s_previousclose.getFirstChild().getNodeValue()+"\",\"MarketCapitalization\":\""+s_marketcapitalization.getFirstChild().getNodeValue()+"\"";
	      jsonString+="},\"News\":{\"Item\":[";
	      NodeList newsList = doc.getElementsByTagName("News");
     	  Element itemnode = (Element)newsList.item(0);
		  
     	  NodeList itemlist = (NodeList) itemnode.getElementsByTagName("Item");
		  int newslength1 = itemlist.getLength();

             
		     int i;  
     	     for(i=0;i<newslength1;i++)
                 {
     		   Element itemnd = (Element)itemlist.item(i);
     		   Element s_title = (Element) itemnd.getElementsByTagName("Title").item(0);
  	           Element s_link = (Element) itemnd.getElementsByTagName("Link").item(0);
  	           if(i==newslength1-1)
  	           {
  	        	 jsonString+="{\"Link\":\""+s_link.getFirstChild().getNodeValue()+"\", \"Title\": \""+s_title.getFirstChild().getNodeValue()+"\"}";
  	           
  	           }
  	           else
  	           {
  	           	jsonString+="{\"Link\":\""+s_link.getFirstChild().getNodeValue()+"\", \"Title\": \""+s_title.getFirstChild().getNodeValue()+"\"},";
  	           }
			   }
	    Element s_stkchrturl = (Element)(doc.getElementsByTagName("StockChartImageURL")).item(0);
     	jsonString+="]},\"StockChartImageURL\":\""+s_stkchrturl.getFirstChild().getNodeValue()+"\"}}";
        	              out.println(jsonString);
      }
      catch(MalformedURLException e)
           {
             	System.out.print(e.getMessage());
           }
      catch(IOException e)
           {
              	System.out.print(e.getMessage());
           }
      catch(Exception e)
           {
               	System.out.print(e.getMessage());
           }
	}
}
