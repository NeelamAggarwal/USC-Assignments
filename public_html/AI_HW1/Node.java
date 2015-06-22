/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AIHomework1;

/**
 *
 * @author user
 */
public class Node  
{
	int x;
	int y;
	int parentx;
	int parenty;
	int total_cost=0;
	int cost;

	public String toString()
	{
		return "" + x + "," + y + "cost" + total_cost;
	}

	public Node( int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	 
	public String display ()
	{
		return (this.x + ", " + this.y );
	}
}
