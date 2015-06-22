import java.net.*;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewClass1 
{
    public static void main(String[] args) throws IOException 
    {
           Elements bigPrice,smallPrice;
           String s = ""; int pageno = 1; // default value is 1 (displaying first page)
           
           if(args.length == 0)
           {
               System.out.println("Enter atleast one argument, any string Ex: camera"); 
               System.exit(0);
           }
           if(args.length > 2)
           {
               System.out.println("Too many arguments, enter only one or two, Ex: camera 1 ");
               System.exit(0);
           }
           if(args.length == 1)
           {
               s = args[0];
           }
           if(args.length == 2)
           {
               s = args[0];
               try
               {
                    pageno = Integer.parseInt(args[1]);
               }
               catch(NumberFormatException e)
               {
                    System.out.println("User message- Not a number: \n"+e);
                    System.exit(0);
               }
           }
           if(s!=null && !s.isEmpty() && pageno!=0)
           {
                System.out.println("You entered String - "+s);
                System.out.println("You entered Pageno - "+pageno);
                 
                pageno = (pageno-1)*16;

                int count =0;
                Connection conn = null;   
                Connection.Response response = null;
                
                try
                {
                    conn = Jsoup.connect("http://www.walmart.com/search/search-ng.do?ic=16_"+pageno+"&Find=Find&search_query="+URLEncoder.encode(s, "UTF-8")+"&Find=Find&search_constraint=0").timeout(100000).ignoreHttpErrors(true);
                    response = conn.execute();
                } catch (IOException e) 
                {
                    System.out.println("io - "+e);
                }
                int statusCode = response.statusCode();
                if( statusCode == 200)  // check if page is ok
                {
                    Document doc = conn.get();
                    String selector = ".prodInfoBox";   // select element with class name "prodInforBox"
                     Elements link = doc.select(".floatLeft.numResults.mt5");   /* select element with class name "floatLeft numResults mt5"
                   This is for total no of results found*/


                    if(!link.isEmpty())
                    {
                         System.out.println("Total: "+link.text());  // This will print total result
                         Elements links = doc.select(selector);  // product list is selected
                         
                         for(Element e : links)  // iterate over all products in a page
                         {
                             count++;
                             System.out.println(count+") " +e.select("a.prodLink.GridItemLink").attr("title"));
                             //priting product title.
                             bigPrice = e.select(".PriceContent").select(".camelPrice").select(".bigPriceText2");
                             // price corresponding to it (dollars)
                             smallPrice = e.select(".PriceContent").select(".camelPrice").select(".smallPriceText2");
                             // price corresponding to it (cents)
                             if(!bigPrice.isEmpty() && !smallPrice.isEmpty()) // if price exists
                             {
                                 System.out.print("Price: " +bigPrice.text());
                                 System.out.println("" +smallPrice.text());
                             }
                             else
                                  System.out.println("Price: Not available");                                      
                         }

                          System.out.println("Count: "+count); 
                          //System.out.println(links.size()); 
                    }
                    else
                        System.out.println("Total: 0 Results\nPlease check your spelling or use different keywords and try again.");
                 }
                else // if page is not ok, 400 error or 500 error etc
                {
                     System.out.println("Status code = " + statusCode);   
                     System.out.println("Status msg  = " + response.statusMessage());
                }
           }
           else
           {
               System.out.print("Try Again with valid input Ex: camera 1");
           }
    }    
}

   