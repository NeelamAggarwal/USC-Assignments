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
	private static final long serialVersionUID = 1L;
      
    public HomeWork8() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{			
			String CompanyName = request.getParameter("CompanyName");
			PrintWriter out = response.getWriter();
			Document document = null;
			
			request.setCharacterEncoding("UTF-8");    	   
    	    response.setContentType("text/html; charset=UTF-8");
    	    String urlstr = "http://cs-server.usc.edu:20513/HomeWork8.php?CompanyName="+CompanyName;
			//String urlstr = "http://default-environment-wx2u78waju.elasticbeanstalk.com/?CompanyName="+CompanyName;
			//http://default-environment-wx2u78waju.elasticbeanstalk.com/?CompanyName=GOOG
			URL url = new URL(urlstr);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setAllowUserInteraction(false);
		
			
			
			try 
			{
			   InputStream urlStream = url.openStream();
			   SAXBuilder Document_Builder = new SAXBuilder();
			   document = Document_Builder.build(url);
			   
			   XMLOutputter OutputXML = new XMLOutputter();
			String Sxml = OutputXML.outputString(document);
			String JSON = XML.toJSONObject(Sxml).toString();
			
			out.println(JSON);
			
			  
			} 
    	    
    	    catch (Exception yahoo_e) 
		    {
				yahoo_e.printStackTrace();
			}
	
	}
}
