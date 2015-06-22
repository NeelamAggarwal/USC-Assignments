
package databasehw2;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Queries {
   
    populate p= new populate();
    
    public ArrayList<Integer> Range_Query(ArrayList<Integer> al)
        {
             p.connectDB();
             System.out.println("inside range query step1"+al);
             String s1 = "";
             for(int i=0;i<al.size();i++)
             {
                 s1+=al.get(i)+",";
             }
             s1+=al.get(0)+","+al.get(1);
             System.out.println("inside range query step2"+s1);
             
            ArrayList<Integer> wr= new ArrayList<Integer>();
          
            try
		{	String sql = "SELECT t.X,t.Y FROM building B, TABLE(SDO_UTIL.GETVERTICES(B.shape)) t "
                        + "WHERE sdo_relate(B.shape, "
                        + "SDO_Geometry (2003, null, null,SDO_ELEM_INFO_ARRAY(1,1003,1),"
                        + "SDO_ORDINATE_ARRAY("+s1+")), "
                        + "'mask=ANYINTERACT') = 'TRUE'";
		
			
			System.out.println("inside range query step3"+sql);
                        Statement s = p.mainCon.createStatement();
                        System.out.println("inside range query step4");
			ResultSet rs = s.executeQuery(sql);
                        
                        while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                            wr.add(rs.getInt(2));
                           
                        }
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
            
                try {
                    p.mainCon.close();
                } catch (SQLException ex) {
                    Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                }
                return wr;
        }
    
    public ArrayList<Integer> Range_Query_vertex(ArrayList<Integer> al)
        {
             p.connectDB();
             System.out.println("inside range query step1"+al);
             String s1 = "";
             for(int i=0;i<al.size();i++)
             {
                 s1+=al.get(i)+",";
             }
             s1+=al.get(0)+","+al.get(1);
             System.out.println("inside range query step2"+s1);
             
             ArrayList<Integer> wr= new ArrayList<Integer>();
            
            try
		{	String sql = "SELECT (SDO_UTIL.GETNUMVERTICES(shape)) FROM building B "
                        + "WHERE sdo_relate(B.shape, "
                        + "SDO_Geometry (2003, null, null,SDO_ELEM_INFO_ARRAY(1,1003,1),"
                        + "SDO_ORDINATE_ARRAY("+s1+")), "
                        + "'mask=ANYINTERACT') = 'TRUE'";
                        
                        System.out.println("inside range query step3"+sql);
                        Statement s = p.mainCon.createStatement();
                        System.out.println("inside range query step4");
			ResultSet rs = s.executeQuery(sql);
                        
                        while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                        }
                        
                        System.out.println("step6:"+wr);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
            
                try {
                    p.mainCon.close();
                } catch (SQLException ex) {
                    Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                }
                return wr;
        }
    public ArrayList<Integer> Range_Query_fire(ArrayList<Integer> al)
        {
             p.connectDB();
             System.out.println("inside range query step1"+al);
             String s1 = "";
             for(int i=0;i<al.size();i++)
             {
                 s1+=al.get(i)+",";
             }
             s1+=al.get(0)+","+al.get(1);
             System.out.println("inside range query step2"+s1);
             
            ArrayList<Integer> wr= new ArrayList<Integer>();
          
            try
            {
			String sql = "Select t.X,t.Y FROM building B, TABLE(SDO_UTIL.GETVERTICES(B.shape)) t "
                                + "WHERE sdo_relate( b.shape,"
                                + "SDO_Geometry (2003, null, null,SDO_ELEM_INFO_ARRAY(1,1003,1),"
                                + "SDO_ORDINATE_ARRAY("+s1+")), "
                                + "'mask=ANYINTERACT') = 'TRUE' and "
                                + "b.building_on_fire ='y' ";
		
			
			System.out.println("inside range query step3"+sql);
                        Statement s = p.mainCon.createStatement();
                        System.out.println("inside range query step4");
			ResultSet rs = s.executeQuery(sql);
//                        
                        while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                            wr.add(rs.getInt(2));
                           
                        }
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
            
                try {
                    p.mainCon.close();
                } catch (SQLException ex) {
                    Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                }
                return wr;
        }
    
    public ArrayList<Integer> Range_Query_fire_vertex(ArrayList<Integer> al)
        {
             p.connectDB();
             System.out.println("inside range query step1"+al);
             String s1 = "";
             for(int i=0;i<al.size();i++)
             {
                 s1+=al.get(i)+",";
             }
             s1+=al.get(0)+","+al.get(1);
             System.out.println("inside range query step2"+s1);
             
             ArrayList<Integer> wr= new ArrayList<Integer>();
            
            try
		{	String sql = "SELECT (SDO_UTIL.GETNUMVERTICES(shape)) FROM building B "
                        + "WHERE sdo_relate(B.shape, "
                        + "SDO_Geometry (2003, null, null,SDO_ELEM_INFO_ARRAY(1,1003,1),"
                        + "SDO_ORDINATE_ARRAY("+s1+")), "
                        + "'mask=ANYINTERACT') = 'TRUE' and b.building_on_fire='y'";
                        
                        System.out.println("inside range query step3"+sql);
                        Statement s = p.mainCon.createStatement();
                        System.out.println("inside range query step4");
			ResultSet rs = s.executeQuery(sql);
                        
                        while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                        }
                        
                        System.out.println("step6:"+wr);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
            
                try {
                    p.mainCon.close();
                } catch (SQLException ex) {
                    Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                }
                return wr;
        }
    
    public ArrayList<Integer> Range_Query_hydrant(ArrayList<Integer> al)
        {
             p.connectDB();
             System.out.println("inside range query step1"+al);
             String s1 = "";
             for(int i=0;i<al.size();i++)
             {
                 s1+=al.get(i)+",";
             }
             s1+=al.get(0)+","+al.get(1);
             System.out.println("inside range query step2"+s1);
             
            ArrayList<Integer> wr= new ArrayList<Integer>();
          
            try
            {
			String sql = "select t.X,t.Y FROM hydrant h, TABLE(SDO_UTIL.GETVERTICES(h.coord)) t "
                                + "WHERE sdo_relate( h.coord,"
                                + "SDO_Geometry (2003, null, null,SDO_ELEM_INFO_ARRAY(1,1003,1),"
                                + "SDO_ORDINATE_ARRAY("+s1+")), "
                                + "'mask=ANYINTERACT') = 'TRUE'";
                                
		
			
			System.out.println("inside range query step3"+sql);
                        Statement s = p.mainCon.createStatement();
                        System.out.println("inside range query step4");
			ResultSet rs = s.executeQuery(sql);
//                        
                        while(rs.next())
			{
                            wr.add(rs.getInt(1));   
                            wr.add(rs.getInt(2));
                           
                        }
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
            
                try {
                    p.mainCon.close();
                } catch (SQLException ex) {
                    Logger.getLogger(populate.class.getName()).log(Level.SEVERE, null, ex);
                }
                return wr;
        }
    
    public ArrayList<Integer> neighbour_building()
        {
            p.connectDB();
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT t.X,t.Y FROM building B, building G, TABLE(SDO_UTIL.GETVERTICES(B.shape)) t "
                                + "WHERE SDO_WITHIN_DISTANCE "
                                + "(B.shape,G.shape,'DISTANCE=100')='TRUE' "
                                + "AND G.building_on_fire='y'";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = p.mainCon.createStatement();
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
    
        public ArrayList<Integer> neighbour_building_vertex()
        {
            p.connectDB();
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT (SDO_UTIL.GETNUMVERTICES(B.shape)) FROM building B, building G "
                                + "WHERE SDO_WITHIN_DISTANCE "
                                + "(B.shape,G.shape,'DISTANCE=100')='TRUE' "
                                + "AND G.building_on_fire='y'";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = p.mainCon.createStatement();
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
         
         public ArrayList<Integer> closest_fire_hydrant_building(String c)
        {
            p.connectDB();
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT t.X,t.Y FROM building B, TABLE(SDO_UTIL.GETVERTICES(B.shape)) t "
                                + "WHERE SDO_CONTAINS(b.shape, "
                                + "SDO_Geometry (2001,null,"
                                + "SDO_POINT_TYPE("+c+",null),null,null)) = 'TRUE'";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = p.mainCon.createStatement();
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
   
         public ArrayList<Integer> closest_fire_hydrant_building_vertex(String c)
        {
            p.connectDB();
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT (SDO_UTIL.GETNUMVERTICES(b.shape)) FROM building b "
                                + "WHERE SDO_CONTAINS(b.shape,"
                                + " SDO_Geometry (2001,null,"
                                + "SDO_POINT_TYPE("+c+",null),null,null)) = 'TRUE'";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = p.mainCon.createStatement();
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
    
         public ArrayList<Integer> closest_fire_hydrant(String c)
        {
            p.connectDB();
            ArrayList<Integer> wr= new ArrayList<Integer>();
            //ArrayList<Integer> wr2= new ArrayList<Integer>();
            //int count=1;
           // Polygon pg= new Polygon();
            try
		{
			String sql = "SELECT t.X,t.Y FROM hydrant h, TABLE(SDO_UTIL.GETVERTICES(h.coord)) t "
                                + " WHERE SDO_NN(h.coord,"
                                + " SDO_Geometry (2003,null,null,"
                                + "SDO_ELEM_INFO_ARRAY(1,1003,1),"
                                + "SDO_ORDINATE_ARRAY("+c+")),'sdo_num_res=1') = 'TRUE'";
			//String sql= "select shape from building where building_name=' OHE'";
			
                        Statement s = p.mainCon.createStatement();
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
}
