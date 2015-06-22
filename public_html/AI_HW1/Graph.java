/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AIHomework1;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Graph 
{
	int count=0;
	ArrayList<Node> visited_Nodes = new ArrayList<Node>();
	int k = 0;
	Node neighbour[]= new Node[4];
	int n;

	public Boolean search(Node temp)
	{
		for(int i=0;i<visited_Nodes.size();i++)
		{ 
			if(visited_Nodes.get(i).x==temp.x && visited_Nodes.get(i).y==temp.y ) 
			{
				return false;
			}
		}  
		return true; 
	}
    
	public ArrayList<Node> calculate_neighbours(Node temp)
	{
		n = -1;
		ArrayList<Node> n1 = new ArrayList<Node>();
		Node sm;
		
		if( (temp.x+1) <= 5)
		{
			sm = new Node(temp.x+1,temp.y);
			sm.parentx = temp.x; 
			sm.parenty = temp.y;
			n1.add(sm);
			count++; 
		} 
		if(temp.y-1 > 0)
		{
			sm = new Node(temp.x,temp.y-1);
			sm.parentx = temp.x; 
			sm.parenty = temp.y;
			n1.add(sm);
			count++;
		} 
		if(temp.x-1 > 0)
		{
			sm = new Node(temp.x-1,temp.y);
			sm.parentx = temp.x; 
			sm.parenty = temp.y;
			n1.add(sm);
			count++; 
		} 
		if(temp.y+1 <= 8)
		{
			sm= new Node(temp.x,temp.y+1);
			sm.parentx=temp.x; 
			sm.parenty=temp.y;
			n1.add(sm);
			count++;
		} 
		return n1;
	}
    
    public void search_parent(Node endNode)
    { 
        Node temp=new Node();
        Boolean found1=false;
		ArrayList <Node> printparent=new ArrayList <Node>(); 
		temp = endNode;

        while(temp.x!=0 && temp.y!=0 && found1!=true)
        {
            for(int i=0;i<visited_Nodes.size();i++)
            {
				if(temp.parentx == visited_Nodes.get(i).x && temp.parenty == visited_Nodes.get(i).y)
				{
				   temp = visited_Nodes.get(i);
				   printparent.add(temp);
				}           
            }
            if(temp.x==2 && temp.y==1)
			{
				found1 = true;
				break;
			}              
        }
        
        for(int i = printparent.size()-1; i >= 0; i--)
        {
            System.out.print("("+printparent.get(i).x+","+printparent.get(i).y+"),");
        }
        System.out.print("("+visited_Nodes.get(visited_Nodes.size()-1).x+","+visited_Nodes.get(visited_Nodes.size()-1).y+")");
        
		return; 
    }  
}
