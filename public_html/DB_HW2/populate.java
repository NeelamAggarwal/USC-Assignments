
package databasehw2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.util.StringTokenizer;

public class populate {
    
    public Connection mainCon;
	
	public void connectDB()
	{
		try {
			// loading Oracle Driver
    		System.out.print("Looking for Oracle's jdbc-odbc driver ... ");
	    	DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
	    	System.out.print("Loaded.");

			//url = "jdbc:oracle:thin:@localhost:1521:SWAPNIL";
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	    	String userId = "SYSTEM";
	    	String password = "neelam";

	    	System.out.print("Connecting to DB...");
	    	mainCon = DriverManager.getConnection(url, userId, password);
	    	System.out.println("connected !!");

   		} catch (Exception e) {
     		System.out.println( "Error while connecting to DB: "+ e.toString() );
     		e.printStackTrace();
     		System.exit(-1);
   		}
	}
        public void readfile(String s1, String s2, String s3) throws SQLException 
        {
            //
            
         //   String s1="building.xy"; String s2="hydrant.xy";String s3="firebuilding.txt";
            String line, token, delimiter = ",";  
               BufferedReader input = null;  
               StringTokenizer tokenizer; 
               String temp="";
               int count=0;
               int no_of_cord=0;
               
               String line1, token1, delimiter1 = ",";  
               BufferedReader input1 = null;  
               StringTokenizer tokenizer1; 
               String temp1="";
               int count1=0;
               int no_of_cord1=0;
               
               Statement mainStat = this.mainCon.createStatement();
               // System.out.println("linooo");
//                 mainStat.executeUpdate("UPDATE building SET building_on_fire='y' where building_name = ' GFS'");
               // System.out.println("linooo9");
                 mainStat.executeUpdate("delete from hydrant");
                 
               mainStat.executeUpdate("delete from building");
               
               try{
                    String filename1 = "C:\\Users\\user\\Desktop\\Database 585 syllabus and notes\\homework\\homework2_final\\"+s2;  
                    input1 = new BufferedReader(new FileReader(filename1));  
                    line1 = input1.readLine(); // when printed gives first line in file  
                    
                    // outer while (process lines)  
                    while (line1 != null)  
                    { // doesn't seem to start from first line  
                      tokenizer1 = new StringTokenizer(line1, delimiter1);  

                      System.out.println("Input Line: \t" + line1);  
                      System.out.print("Tokens: \t");  
                      count1=0;
                      temp1="";
                      while (tokenizer1.hasMoreTokens())  
                      {// process tokens in line  
                        token1 = tokenizer1.nextToken(); 
                       
                        count1++;
                        if(count1==1)
                            temp1+="INSERT INTO hydrant values("+"'"+token1+"'"+",";
                        //System.out.print(token +"   -"); 
                        if(count1==2)
                            temp1+="SDO_Geometry (2001,null,SDO_POINT_TYPE("+token1+",";         
                        if(count1==3)
                            temp1+=token1+",null),null,null))";
                             
                      }// close inner while  
                        
                      System.out.println(temp1);
                      mainStat.executeUpdate(temp1);
      
                      line1 = input1.readLine(); // next line  
                    }// close outer while  


                        }catch (IOException e) {
                                        e.printStackTrace();
                                } finally {
                                        try {
                                                if (input1 != null)input1.close();
                                        } catch (IOException ex) {
                                                ex.printStackTrace();
                                        }
                                }
                    
               try{
                    String filename = "C:\\Users\\user\\Desktop\\Database 585 syllabus and notes\\homework\\homework2_final\\"+s1;  
                    input = new BufferedReader(new FileReader(filename));  
                    line = input.readLine(); // when printed gives first line in file  
                    
                    // outer while (process lines)  
                    while (line != null)  
                    { // doesn't seem to start from first line  
                      tokenizer = new StringTokenizer(line, delimiter);  

                      System.out.println("Input Line: \t" + line);  
                      System.out.print("Tokens: \t");  
                      count=0;
                      temp="";
                      while (tokenizer.hasMoreTokens())  
                      {// process tokens in line  
                        token = tokenizer.nextToken(); 
                       
                        count++;
                        if(count==1)
                            temp+="INSERT INTO building values("+"'"+token+"'"+",";
                        //System.out.print(token +"   -"); 
                        if(count==2)
                            temp+="'"+token+"'"+",";
                        if(count==3){
                            temp+="SDO_Geometry (2003,null,null,SDO_ELEM_INFO_ARRAY(1,1003,1),SDO_ORDINATE_ARRAY(";
                            token=token.trim();
                            no_of_cord=Integer.parseInt(token);
//                            System.out.println("cord: "+no_of_cord);
                                    }        
                        if(count>3 && count<((2*no_of_cord)+3))
                            temp+=token+",";
                        if(count==((2*no_of_cord)+3))
                            temp+=token;
                             
                      }// close inner while  
                        temp+=")),'n')";
                        
                      System.out.println(temp);
                      mainStat.executeUpdate(temp);
      
                      line = input.readLine(); // next line  
                    }// close outer while  


                        }catch (IOException e) {
                                        e.printStackTrace();
                                } finally {
                                        try {
                                                if (input != null)input.close();
                                        } catch (IOException ex) {
                                                ex.printStackTrace();
                                        }
                                }
               String temp2="";
               try{
                    String filename2 = "C:\\Users\\user\\Desktop\\Database 585 syllabus and notes\\homework\\homework2_final\\"+s3;  
                    input = new BufferedReader(new FileReader(filename2));  
                    line = input.readLine();
                    // when printed
                    System.out.println("line 1");
//                     mainStat.executeUpdate("UPDATE building SET building_on_fire='y' where building_name=' OHE'");
                     System.out.println("line 2");
//                    mainStat.executeUpdate("UPDATE building SET building_on_fire='y' where building_name=' HAR'");
                    System.out.println("line 3");
//                    mainStat.executeUpdate("UPDATE building SET building_on_fire='y' where building_name=' TSC'");
                    System.out.println("line 4");
                     while (line != null)  
                    { // doesn't seem to start from first line  
                      tokenizer = new StringTokenizer(line);  

                      System.out.println("Input Line: \t" + line);  
                      System.out.print("Tokens: \t");  
                      while (tokenizer.hasMoreTokens())  
                      {// process tokens in line  
                        token = tokenizer.nextToken(); 
                        System.out.println(token);
                        
                        temp2="UPDATE building SET building_on_fire='y' where building_name=' "+token+"'";
                        
                        mainStat.executeUpdate(temp2);
                         System.out.println(temp2);
//                          this.mainCon.commit();
                      }// close inner while  
                        //temp2+=")))";
                        
                     
                      
      
                      line = input.readLine(); // next line  
                       System.out.println("end");
                    }
                  }catch (IOException e) {
                                        e.printStackTrace();
                                } finally {
                                        try {
                                                if (input != null)input.close();
                                        } catch (IOException ex) {
                                                ex.printStackTrace();
                                        }
                                }
               
        try {
            this.mainCon.commit();
             System.out.println("before final end");
            this.mainCon.close();
             System.out.println("final end");
        } catch (SQLException ex) {
            System.out.println("closing connection error");
            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
	
	
        public ArrayList<Integer> wholeRegion()
        {
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT t.X,t.Y FROM building Tb, TABLE(SDO_UTIL.GETVERTICES(Tb.shape)) t";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = this.mainCon.createStatement();
			ResultSet rs = s.executeQuery(sql);
                        //ResultSet rs2 = s.executeQuery(sql2);
                        
			while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                            wr.add(rs.getInt(2));
                            //pg=(Polygon) rs.getObject(2);
//                            System.out.println("helo");
                            //System.out.println(wr);
                            
                        }
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
//        try {
//            this.mainCon.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return wr;
        }
        
        public ArrayList<Integer> wholeRegion1()
        {
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT (SDO_UTIL.GETNUMVERTICES(shape)) from building ";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = this.mainCon.createStatement();
			ResultSet rs = s.executeQuery(sql);
                        //ResultSet rs2 = s.executeQuery(sql2);
                        
			while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                            //wr.add(rs.getInt(2));
                            //pg=(Polygon) rs.getObject(2);
//                            System.out.println("helo");
                            //System.out.println(wr);
                            
                        }
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
//        try {
//            this.mainCon.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return wr;
        }
        
         public ArrayList<Integer> WR_buildingfire()
        {
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT t.X,t.Y FROM building Tb, TABLE(SDO_UTIL.GETVERTICES(Tb.shape)) t where building_on_fire='y'";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = this.mainCon.createStatement();
			ResultSet rs = s.executeQuery(sql);
                        //ResultSet rs2 = s.executeQuery(sql2);
                        
			while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                            wr.add(rs.getInt(2));
                            //pg=(Polygon) rs.getObject(2);
//                            System.out.println("helo");
                            System.out.println(wr);
                            
                        }
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
             return wr;
        }
         
          public ArrayList<Integer> WR_buildingfire1()
        {
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT (SDO_UTIL.GETNUMVERTICES(shape)) from building where building_on_fire='y'";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = this.mainCon.createStatement();
			ResultSet rs = s.executeQuery(sql);
                        //ResultSet rs2 = s.executeQuery(sql2);
                        
			while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                           // wr.add(rs.getInt(2));
                            //pg=(Polygon) rs.getObject(2);
//                            System.out.println("helo");
                            System.out.println(wr);
                            
                        }
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
             return wr;
        }
         

        public ArrayList<Integer> WR_hydrant()
        {
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT t.X,t.Y FROM hydrant Tb, TABLE(SDO_UTIL.GETVERTICES(Tb.coord)) t  ";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = this.mainCon.createStatement();
			ResultSet rs = s.executeQuery(sql);
                        //ResultSet rs2 = s.executeQuery(sql2);
                        
			while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                            wr.add(rs.getInt(2));
                            //pg=(Polygon) rs.getObject(2);
//                            System.out.println("helo");
                            //System.out.println(wr);
                            
                        }
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
//        try {
//            this.mainCon.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return wr;
        }
        
         
         
   
    public static void main(String[] args) {
        
        populate t = new populate();
		t.connectDB();
//                t.WR_buildingfire();
//                t.WR_buildingfire1();
//                t.Range_Query();
               // HW2 h= new HW2();
             //   System.out.println(args[0]+args[1]+args[2]);
                
        //String poly_cord_func = h.poly_cord_func();
//            System.out.print("range query"+h.poly_cord);
           // System.out.print("range queryy"+poly_cord_func);
//        ArrayList<Integer> wholeRegion =new ArrayList<Integer>();
//        wholeRegion= t.wholeRegion();
      //  System.out.print(wholeRegion);
                try {
                   // t.readfile();
                    //t.readfile(args[0],args[1],args[2]);
                     t.readfile("building.xy", "hydrant.xy", "firebuilding.txt");
         System.out.println("last");
                    //t.findNearestPoint();
                    //t.findNearestPoint();
                } catch (SQLException ex) {
                    Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                }
         System.out.println("last last");
    }
}
