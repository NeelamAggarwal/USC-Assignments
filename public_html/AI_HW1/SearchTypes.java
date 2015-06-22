/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AIHomework1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SearchTypes 
{
	private String print_queue(LinkedList<Node> pq) 
	{
		String s  = new String();
		for(Node n : pq) 
		{
			s += n.total_cost + "-(" + n.display() + "), ";
		}
		return s;
    }
     
    private String print_visited(ArrayList<Node> pq) 
	{
        String s  = new String();
        for(Node n : pq)
		{
            s += n.total_cost + "-(" + n.display() + "), ";
        }
        return s;
    }
     
    public void UniformCost()
    {
        int [][] cost = 
		{
		   {0,0,0,0,0,0,0,0,0},
		   {0,12,11,17,16,7,13,12,15},
		   {0,16,17,9,15,15,7,6,12},
		   {0,20,10,14,8,17,9,13,16},
		   {0,19,20,13,7,6,9,15,9},
		   {0,18,15,19,10,8,9,14,12}
        };
           
        Node nodes[][] = new Node[6][9];
        
		for(int i = 0; i<6; i++) 
		{
            for(int j = 0; j<9; j++) 
			{
                nodes[i][j] = new Node(i, j);
                nodes[i][j].cost = cost[i][j];
            }
        }

		LinkedList<Node> pqueue = new LinkedList<Node>();

		ArrayList<Node> all_neighbours= new ArrayList<Node>();
		
		int total_latest_cost=0;

		Graph theGraph= new Graph();

		Node root= nodes[2][1]; 
		Node end= nodes[4][8];
		root.parentx = 0;
		root.parenty = 0;
		root.total_cost=cost[2][1];

		Node temp= new Node();

		pqueue.add(root);

		while(!temp.equals(end)) 
		{
			Collections.sort(pqueue, new Comparator<Node>()
			{
				@Override
				public int compare(Node t, Node t1) 
				{
					return t.total_cost - t1.total_cost;
				}

			});

			temp = pqueue.remove();
			
			theGraph.visited_Nodes.add(temp);

			all_neighbours= theGraph.calculate_neighbours(temp);

			for(Node n1: all_neighbours)
			{
				Node n = nodes[n1.x][n1.y];
				
				if(!theGraph.visited_Nodes.contains(n))
				{
				   total_latest_cost = temp.total_cost + cost[n.x][n.y];
				   if(pqueue.contains(n))
				   {
					   if(total_latest_cost < n.total_cost)
					   {
						   n.total_cost = total_latest_cost;
						   n.parentx = temp.x;
						   n.parenty = temp.y;
					   }  
				   }
				   else
				   {
					   n.total_cost = total_latest_cost;
					   n.parentx = temp.x;
					   n.parenty = temp.y;
					   pqueue.add(n);						   
				   }
				}
			}
		}

		System.out.println("\nUCS "+ " Traversal Order");

		for(int i = 0; i < theGraph.visited_Nodes.size(); i++)
			System.out.print("("+theGraph.visited_Nodes.get(i).x+","+theGraph.visited_Nodes.get(i).y+"),");

		System.out.println("\nStitching Curve");
		theGraph.search_parent(end);

	}
       
	public void BreadthFirst()
	{
		Queue queue = new LinkedList<Node>();
		Graph theGraph= new Graph();
		Node front= new Node();
		Node end= new Node();
		Node root= new Node();

		ArrayList<Node> n3= new ArrayList<Node>();

		Node neighbour_temp[]=new Node[4];
		Boolean found=false;
		root.x = 2;
		root.y = 1;
		root.parentx = 0;
		root.parenty = 0;
		end.x = 4;
		end.y = 8;
		
		queue.add(root);
		theGraph.visited_Nodes.add(root);

		Node endNode= new Node();

		while(!queue.isEmpty())
		{ 
			front = (Node)queue.peek();
			ArrayList<Node> n2 = theGraph.calculate_neighbours(front);
			n2.toArray(neighbour_temp);
			for(int i = 0; i < n2.size(); i++)
			{   
				if(theGraph.search(neighbour_temp[i]) && neighbour_temp[i]!=end)
				{
					Node temp= new Node();
					temp = neighbour_temp[i];
					queue.add(temp);
					theGraph.visited_Nodes.add(temp);
				}
				if(neighbour_temp[i].x==4 && neighbour_temp[i].y==8)
				{
					found=true;
					endNode=neighbour_temp[i];
					break;
				}
			}   
			queue.remove();
			if(found == true)
				break;
		}

		System.out.println("\nBFS "+ " Traversal Order");

		for(int i=0;i<theGraph.visited_Nodes.size();i++)
			System.out.print("("+theGraph.visited_Nodes.get(i).x+","+theGraph.visited_Nodes.get(i).y+"),");

		System.out.println("\nStitching Curve");
		theGraph.search_parent(endNode);
	}
       
	public void DepthFirst()
	{
		Stack stack = new Stack<Node>();        
		Graph theGraphs= new Graph();
		
		Node temp1s= new Node();
		Node fronts= new Node();
		Node ends= new Node();
		Node roots= new Node();
		
		ArrayList<Node> n3s= new ArrayList<Node>();

		ArrayList<Node> neighbour_temps =new ArrayList<Node>();
		Boolean founds=false;
		roots.x = 2;
		roots.y = 1;
		roots.parentx = 0;
		roots.parenty = 0;
		ends.x = 4;
		ends.y = 8;
		
		stack.push(roots);
		
		Node endNodes= new Node();
		endNodes = ends;
		Node temp=new Node();
		
		while(!stack.isEmpty() && temp!=ends )
		{ 
			temp= (Node) stack.pop();
			endNodes=temp;
			theGraphs.visited_Nodes.add(temp);

			if(temp.x == ends.x && temp.y == ends.y)
			{
				founds = true;
				break;
			}
			for(int i=0;i<theGraphs.visited_Nodes.size();i++)
			{
			// System.out.println("visited node"+theGraphs.visited_Nodes.get(i).x+theGraphs.visited_Nodes.get(i).y);
			}

			ArrayList<Node> n3= theGraphs.calculate_neighbours(temp);
			int i = n3.size()-1;
			
			while(i >= 0)
			{
				Node abiii = new Node();
				abiii = (Node) n3.get(i);
				if(abiii.equals(ends))
				{
					stack.push(abiii);
					temp = ends;
					break;
				}
				if(theGraphs.search(abiii)) 
				{
					stack.push(abiii);
				}
				i--;
			}
		}
		     
		System.out.println("\nDFS "+ " Traversal Order");

		for(int i=0;i<theGraphs.visited_Nodes.size();i++)
		{
			System.out.print("("+theGraphs.visited_Nodes.get(i).x+ "," +theGraphs.visited_Nodes.get(i).y+") ");
		}

		System.out.println("\nStitching Curve");
		theGraphs.search_parent(endNodes);
	}
}
