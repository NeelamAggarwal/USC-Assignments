/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AIHomework1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class AIHomework1 
{
    static int global=0;
	
    public static void main(String[] args) throws IOException 
	{
        System.out.println("Enter the value 1 or 2 or 3 \n"+ "1.BFS    2.DFS    3.UCS");
        System.out.println("Enter your value here");
        
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int value = Integer.parseInt(br.readLine());
      

		switch(value)
		{
			case 1:        
			{   
				SearchTypes st= new SearchTypes();
				st.BreadthFirst();
				break;     
			}
				  
			case 2:
			{
				SearchTypes st= new SearchTypes();
				st.DepthFirst();
				break;
			}
			case 3:
			{
				SearchTypes UCS= new SearchTypes();
				UCS.UniformCost();
				break;
			}
			default: System.out.println("Bad Input... Enter 1 or 2 or 3");
		}
		System.out.println();
			
	}
}





  