
package AI_HW3;

import java.util.ArrayList;

public class GameTree
{
    ArrayList <Node> list= new ArrayList<Node>();
    Node n=new Node();
    int liberty=0;
    
        ///// INITIALISE ///////
        public Node[][] initialise(Node matrix1[][])
	{
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				matrix1[i][j] = new Node();
				matrix1[i][j].x=i;
				matrix1[i][j].y=j;
                                matrix1[i][j].visited=false;	
			}
		}
		matrix1[0][0].stone='W'; matrix1[0][1].stone='E'; matrix1[0][2].stone='B'; matrix1[0][3].stone='W'; matrix1[0][4].stone='E'; matrix1[0][5].stone='B';
		matrix1[1][0].stone='E'; matrix1[1][1].stone='W'; matrix1[1][2].stone='B'; matrix1[1][3].stone='E'; matrix1[1][4].stone='B'; matrix1[1][5].stone='E';
		matrix1[2][0].stone='W'; matrix1[2][1].stone='E'; matrix1[2][2].stone='W'; matrix1[2][3].stone='B'; matrix1[2][4].stone='E'; matrix1[2][5].stone='B';
		matrix1[3][0].stone='E'; matrix1[3][1].stone='B'; matrix1[3][2].stone='B'; matrix1[3][3].stone='B'; matrix1[3][4].stone='W'; matrix1[3][5].stone='B';
		matrix1[4][0].stone='W'; matrix1[4][1].stone='B'; matrix1[4][2].stone='W'; matrix1[4][3].stone='E'; matrix1[4][4].stone='W'; matrix1[4][5].stone='B';
		matrix1[5][0].stone='W'; matrix1[5][1].stone='E'; matrix1[5][2].stone='W'; matrix1[5][3].stone='B'; matrix1[5][4].stone='W'; matrix1[5][5].stone='W';

	return matrix1;
        }
        
        /////// CALCULATE///////
        public Node[][] calculate(Node matrix[][], int x, int y, char ch)
       {
           list.add(matrix[x][y]);
           matrix[x][y].visited=true;
          
                if(y+1<=5 && matrix[x][y+1].stone==ch && matrix[x][y+1].visited!=true)
	        {
                      
                    calculate(matrix,x,y+1,ch);
	        }
                if(x-1>=0 && matrix[x-1][y].stone==ch && matrix[x-1][y].visited!=true)
	        {
                     
                    calculate(matrix,x-1,y,ch);
	        }
                if(y-1>=0 && matrix[x][y-1].stone==ch && matrix[x][y-1].visited!=true)
		{
                     
                    calculate(matrix,x,y-1,ch);
		}
                if(x+1<=5 && matrix[x+1][y].stone==ch && matrix[x+1][y].visited!=true)
		{
                  
                    calculate(matrix,x+1,y,ch);
		}
                
                return matrix;
       }
        
       ///CLEARS LIST CONTAINING GROUPS 
       public void lnull()
       {
           list.removeAll(list);
       }
       
       /// CLEARS EMPTY SPACES
       public void free(Node matrix[][])
       {
           for(int i=0;i<=5;i++)
           { 
               for(int j=0;j<=5;j++)
               {
                   if(matrix[i][j].stone=='E')
                   {
                       matrix[i][j].visited=false;
                   }
               }
           }             
       }
       
       /// CALCULATES LIBERTY
       public Node[][] liberty(Node matrix[][])
       {
           liberty=0;
           for(int i=0;i<list.size();i++)
           {
               n=list.get(i);
              
               if(n.y+1<=5 && matrix[n.x][n.y+1].stone=='E' && matrix[n.x][n.y+1].visited!=true)
	        {
                    liberty++;
                    matrix[n.x][n.y+1].visited=true;
	        }
                if(n.x-1>=0 && matrix[n.x-1][n.y].stone=='E' && matrix[n.x-1][n.y].visited!=true)
	        {
                   liberty++;
                   matrix[n.x-1][n.y].visited=true;
	        }
                if(n.y-1>=0 && matrix[n.x][n.y-1].stone=='E' && matrix[n.x][n.y-1].visited!=true)
		{
                   liberty++;
                   matrix[n.x][n.y-1].visited=true;
		}
                if(n.x+1<=5 && matrix[n.x+1][n.y].stone=='E' && matrix[n.x+1][n.y].visited!=true)
		{
                   liberty++; 
                   matrix[n.x+1][n.y].visited=true;
		}
               
           }
           /// ASSIGN SAME LIBERTY TO EVERY MEMBER OF A GROUP
           for(int i=0;i<list.size();i++)
           {
               list.get(i).liberty=liberty;
           }
           return matrix;
       }
       
       /// PREPARE INITIAL BOARD WITH INTIAL VALUES
       public Node [][] initialboard1(Node matrix[][])
       {
            matrix= new Node [6][6];
            initialise(matrix);
            char ch;
            
               for(int i=0;i<=5;i++)
                {
                    for(int j=0;j<=5;j++)
                    {
                        if( matrix[i][j].visited!=true && matrix[i][j].stone!='E')
                        {
                            ch= matrix[i][j].stone;
                            calculate(matrix,i,j,ch); 
                            liberty(matrix);
                            lnull();
                            free(matrix);
                        }
                    }
                }
               
           return matrix;
       }
       
       /// PREPARE BOARD WITH GIVEN CHILD BOARD
       public Node [][] initialboard(Node matrix[][])
       {
           char ch; 
           
           for(int i=0;i<6;i++)
           {
               for(int j=0;j<6;j++)
               {
                   matrix[i][j].visited=false;
               }
           } 
                
           for(int i=0;i<=5;i++)
           {
               for(int j=0;j<=5;j++)
                {
                    if( matrix[i][j].visited!=true && matrix[i][j].stone!='I')
                    {
                         ch= matrix[i][j].stone;
                         calculate(matrix,i,j,ch); 
                         liberty(matrix);
                         lnull();
                         free(matrix);
                     }
                 }
            }
           
            return matrix;
       }
       
}

