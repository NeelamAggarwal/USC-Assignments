/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI_HW2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class AI_HW2 {
    
//checking function
    private static String print_queue(LinkedList<Node> pq) 
	{
         String s  = new String();
         for(Node n : pq) {
             s += "(" + n.display() + ")- "+n.heu_diss+"," ;
         }
         return s;
     }
 // printing Traversal Path of AStar (this function is called below for printing)
     private static String print_visited(ArrayList<Node> pq) 
	 {
         String s  = new String();
         for(Node n : pq) {
             s += "" + n.display() + " , " ;
         }
         return s;
     }
    
   
    
    public static void main(String[] args) {
	
                int [][] data = {
            {1,0,1,0,0,0,0,0,0,0,0,0}, {0,1,0,0,0,0,0,1,0,0,0,0}, {0,0,0,1,0,0,0,1,0,0,0,0}, {0,0,1,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,0,0,1,0,0,0}, {0,0,0,1,0,0,1,0,0,0,0,0}, {1,1,0,0,0,0,0,0,0,0,0,0}, {0,0,1,0,0,0,1,0,0,0,0,0},
            {0,0,0,1,1,0,0,0,0,0,0,0}, {0,1,0,0,0,1,0,0,0,0,0,0}, {0,0,0,0,1,0,0,0,1,0,0,0}, {0,0,0,1,0,0,1,0,0,0,0,0},
            {1,0,1,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,1,0,0,0,1,0,0}, {0,1,0,0,1,0,0,0,0,0,0,0}, {0,0,0,0,0,0,1,0,1,0,0,0},
            {0,0,1,0,0,1,0,0,0,0,0,0}, {0,0,0,1,1,0,0,0,0,0,0,0}, {0,1,0,0,0,0,0,1,0,0,0,0}, {1,0,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,0,1,0,0}, {1,0,0,1,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,1,0,0,0,1,0}, {1,0,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0}, {1,1,0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,1,0,0,1}, {0,0,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1}, {0,0,0,0,0,0,0,0,0,1,0,1}
    };
                
                int heuristic_value[]={12,10,9,10,7,6,7,6,4,4,3,0};
                              
                Node all_movies[]= new Node[12];
                for(int i=0;i<12;i++)
                     all_movies[i]= new Node();
                             
                Node root= new Node();
                Node goal= new Node();
                int t0=5;
                
                root.movie=1;
                root.user=1;
                           
                goal.movie=12;
                goal.user=1;
                            
                Boolean found=false;
                goal = all_movies[11];
                root= all_movies[0];
                int count;
                
 // this queue is for Greedy Search  (just declared the queue.. while loop for this is below)        
                PriorityQueue<Node> queue = new PriorityQueue<Node>(40, new Comparator<Node>()
				{

					@Override
					public int compare(Node t, Node t1) {
						
						return t.heuristic - t1.heuristic;
					}
				});
                           
                 for(int i=0;i<12;i++)
                 {
                     all_movies[i].heuristic=heuristic_value[i];
                     all_movies[i].movie=i+1; 
                 }
				 
// this loops calculates the neighbours and add the neighbours               
                 for(int i=0;i<30;i++)
                 {
                     for(int j=0;j<12;j++)
                     {
                         if(data[i][j]==1)
                         {
                             for(int k=0;k<12;k++)
                             {
                                 if(k!=j && data[i][k]==1)
                                 {
                                    Node n= new Node();
                                    Node n1= new Node();
                                    n.dissimilarity=all_movies[k].dissimilarity; n.heuristic=all_movies[k].heuristic; n.movie=all_movies[k].movie;
                                    n1.dissimilarity=all_movies[j].dissimilarity; n1.heuristic=all_movies[j].heuristic; n1.movie=all_movies[j].movie; 
                                    all_movies[j].neighbours.add(n);
                                    all_movies[k].neighbours.add(n1);
                                  
                                    found=true;
                                 }
                                 if(found==true)
                                     break;       
                             }
                         }
                         if(found==true)
                             break;  
                     }
                     found=false;
                 }
                 
// this calculates the dissimilarity between two nodes and removes the duplicate neighbours               
                 
                 for(int i=0;i<all_movies.length;i++)
                 {
                     for(int j=0;j<all_movies[i].neighbours.size();j++)
                     {
                         count=1;
                         for(int k=j+1;k<all_movies[i].neighbours.size();k++)
                         {
                             if(all_movies[i].neighbours.get(j).movie==all_movies[i].neighbours.get(k).movie)
                             {
                                 all_movies[i].neighbours.remove(k);
                                 k--;  
                                 count++;
                             }
                         }
                         all_movies[i].neighbours.get(j).dissimilarity=(((all_movies[i].movie+all_movies[i].neighbours.get(j).movie)%t0)+1)*(t0-count);
                       
                         all_movies[i].neighbours.get(j).heu_diss= all_movies[i].neighbours.get(j).dissimilarity+ all_movies[i].neighbours.get(j).heuristic;
                              
                     }
                 }
                              
// Performing Greedy Search                 
                ArrayList<Node> visited1= new ArrayList<Node>();
                Node temp= new Node(); 
                queue.add(root);
                visited1.add(root);
                int total=0;
                boolean visit1=false;
                System.out.println("");
                System.out.println("Greedy Search");
                System.out.println("Traversal Path:");
// Greedy Serach while loop                
                while(temp.movie!=goal.movie)
                {
                    temp=(Node) queue.remove();
                    System.out.print("m"+temp.movie+" , ");
                    total+=temp.dissimilarity;
                     
                    for(int i=0; i<all_movies[temp.movie-1].neighbours.size();i++)
                    {
                        visit1=false;
                        for(int j=0;j<visited1.size();j++)
                          {
                              if(all_movies[temp.movie-1].neighbours.get(i).movie == visited1.get(j).movie)
                              {
                                  visit1=true;
                                  break;
                              }
                          }
                          
                          if(visit1!=true)
						  {                    
							queue.add(all_movies[temp.movie-1].neighbours.get(i));
							all_movies[temp.movie-1].neighbours.get(i).parent= all_movies[temp.movie-1];
							visited1.add(all_movies[temp.movie-1].neighbours.get(i));                      
                          }
                    }              
                }
                System.out.println("");
                System.out.println("Dissimilarity : "+total);
 // for (Propogating Path) parent printing  loop and some temporary variables              
                int n1=12;
         ArrayList<Integer> printparent1= new ArrayList<Integer>();
        
         while(n1!=1)
		 {
			 for(int i=0;i<visited1.size();i++)
			 {
				 if(visited1.get(i).movie==n1)
				 {
					 printparent1.add(n1);
					 n1 = visited1.get(i).parent.movie;  
				 }
				
			 } 			 
		}
 // actually print the ArrayList of parents        
          System.out.println("Propogating Path:");
          System.out.print("m"+n1 +" ,");
         for(int i=printparent1.size()-1;i>=0;i--)
        {
            System.out.print("m"+printparent1.get(i)+" , ");              
        }                  
                    
 // For AStar Search                
         
                int total1=0;
                LinkedList<Node> queue1 = new LinkedList<Node>();
                       
                ArrayList<Node> visited= new ArrayList<Node>();
                ArrayList<Node> removed= new ArrayList<Node>();
                queue1.add(root);
                Node temp1= new Node();
                Boolean visit=false;             
                visited.add(root);
// while loop for ASTAR Search                 
              while(temp1.movie !=goal.movie)
              {
                  
                  Collections.sort(queue1, new Comparator<Node>(){

                @Override
                public int compare(Node t, Node t1) {
                     return t.heu_diss - t1.heu_diss;
                }
                   
               });
                  
                  temp1= queue1.remove();
                  removed.add(temp1);
                  
                  
                  for(int i=0;i<all_movies.length;i++)
                  {
                      if(all_movies[i].movie==temp1.movie)
                      {
                          all_movies[i].dissimilarity=temp1.dissimilarity; 
                      }
                      
                  }
                    total1+=temp1.dissimilarity;
              for(int i=0; i<all_movies[temp1.movie-1].neighbours.size();i++)
                    {
                          visit=false;
                          for(int j=0;j<visited.size();j++)
                          {
                              if(all_movies[temp1.movie-1].neighbours.get(i).movie == visited.get(j).movie)
                              {
                                  visit=true;
                                  break;
                              }
                          }
                          
                          if(visit!=true)
                          {    
                            all_movies[temp1.movie-1].neighbours.get(i).parent= all_movies[temp1.movie-1];
                        
                            all_movies[temp1.movie-1].neighbours.get(i).dissimilarity += all_movies[temp1.movie-1].dissimilarity;
                            all_movies[temp1.movie-1].neighbours.get(i).heu_diss = (all_movies[temp1.movie-1].dissimilarity) + (all_movies[temp1.movie-1].neighbours.get(i).heu_diss);
                            all_movies[(all_movies[temp1.movie-1].neighbours.get(i).movie)-1].heu_diss= all_movies[temp1.movie-1].neighbours.get(i).heu_diss;
                            all_movies[(all_movies[temp1.movie-1].neighbours.get(i).movie)-1].dissimilarity= all_movies[temp1.movie-1].neighbours.get(i).dissimilarity;
                            queue1.add(all_movies[temp1.movie-1].neighbours.get(i));
                       
                            visited.add(all_movies[temp1.movie-1].neighbours.get(i));
                          } 
                          
                    }
              
                }
              System.out.println("");
              System.out.println("");
               System.out.println("A* (Star) Search");
              System.out.println("Traversal Path:");
              //
              System.out.print(print_visited(removed));
                  
         
         System.out.println("");
         System.out.print("Dissimilarity :");
                System.out.println(" "+temp1.dissimilarity);
        
//  ASTAR parent printing and calculation              
         int n=12;
         ArrayList<Integer> printparent= new ArrayList<Integer>();
        
         while(n!=1){
         for(int i=0;i<visited.size();i++)
         {
             if(visited.get(i).movie==n)
             {
                 printparent.add(n);
                 n = visited.get(i).parent.movie;  
             }
            
         } }
         System.out.println("Propogating Path:");
         System.out.print("m"+n +" , ");
         for(int i=printparent.size()-1;i>=0;i--)
        {
            System.out.print("m"+printparent.get(i)+" , ");
              
        }
         System.out.println("");
         
                
    }
}                       
               