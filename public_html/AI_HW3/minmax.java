
package AI_HW3;

public class minmax {
    
GameTree gt= new GameTree();
static int countprune=0;
static int count=0;
GameBoard gb;
GameBoard gb1;

///INITIALIZING THE BOARD
public GameBoard call()
{
    GameBoard root= new GameBoard(); 
    root.board=gt.initialboard1(root.board);   
    return root;    
}

/// MAX FUNCTION WITH PRUNING
public int maxprune(GameBoard temp, int level,int a, int b)
{
    
    if(level==4)
    {
      int value=eval(temp);
      return value;
    }
    else
    {
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(temp.board[i][j].stone=='E')
                {
                    countprune++;
                    GameBoard root= new GameBoard(); 
                    root.board= new Node[6][6];
                    root.x=j+1;
                    root.y=i+1;
                    temp.child.add(root);
                     
                    for(int m=0;m<6;m++)
                    {
                        for(int n=0;n<6;n++)
                        {
                            root.board[m][n]= new Node();
                            root.board[m][n].liberty=temp.board[m][n].liberty;
                            root.board[m][n].stone=temp.board[m][n].stone;
                            root.board[m][n].y=temp.board[m][n].y;
                            root.board[m][n].x=temp.board[m][n].x;
                        }
                    }
                    
                    root.board[i][j].stone='B';
                  
                    capture(root,'W');
                            
                    a= Math.max(a,minprune(root,level+1,a,b));
                    root.value=a;
               
                    /// PRUNING LOGIC
                    if(a>=b)
                    return a; 
                }
            }
        }
    }
    return a;  
}

/// MAX FUNCTION WITHOUT PRUNING
public int max(GameBoard temp, int level,int a, int b)
{
    
    if(level==4)
    {
        int value=eval(temp);
        return value;
    }
    else
    {
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(temp.board[i][j].stone=='E')
                {
                    count++;
                    GameBoard root= new GameBoard(); 
                    root.board= new Node[6][6];
                    root.x=j+1;
                    root.y=i+1;
                    temp.child.add(root);
                     
                    for(int m=0;m<6;m++)
                    {
                        for(int n=0;n<6;n++)
                        {
                            root.board[m][n]= new Node();
                            root.board[m][n].liberty=temp.board[m][n].liberty;
                            root.board[m][n].stone=temp.board[m][n].stone;
                            root.board[m][n].y=temp.board[m][n].y;
                            root.board[m][n].x=temp.board[m][n].x;
                        }
                    }
                    root.board[i][j].stone='B';
                  
                    capture(root,'W');
                    
                    a= Math.max(a,min(root,level+1,a,b));
                    root.value=a;   
                }
            }
        }
    }
    return a;  
}

/// MIN FUNCTION WITH PRUNING
public int minprune(GameBoard temp, int level, int a, int b)
{
      
    if(level==4)
    {
        int value=eval(temp);
        return value;
    }
    else 
    {
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(temp.board[i][j].stone=='E')
                { 
                    countprune++;
                    GameBoard root= new GameBoard();
                    root.board= new Node[6][6]; 
                    root.x=j+1;
                    root.y=i+1;
                    temp.child.add(root);
                 
                    for(int m=0;m<6;m++)
                    {
                        for(int n=0;n<6;n++)
                        {
                            root.board[m][n]= new Node();
                            root.board[m][n].liberty=temp.board[m][n].liberty;
                            root.board[m][n].stone=temp.board[m][n].stone;
                            root.board[m][n].y=temp.board[m][n].y;
                            root.board[m][n].x=temp.board[m][n].x;
                        }
                    }
                    
                    root.board[i][j].stone='W';
                    
                    capture(root,'B');
                    
                    b=Math.min(b,maxprune(root,level+1,a,b));
                   root.value=b;
                   
                   /// PRUNING LOGIC
                    if(b<=a)
                        return b;
                }
            }
        }
    } 
    return b;
}

/// MIN FUNCTION WITHOUT PRUNING
public int min(GameBoard temp, int level, int a, int b)
{
    
    if(level==4)
    {
      int value=eval(temp);
      return value;
    }
    
    else 
    {
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(temp.board[i][j].stone=='E')
                { 
                    count++;
                    GameBoard root= new GameBoard();
                    root.board= new Node[6][6]; 
                    root.x=j+1;
                    root.y=i+1;
                    temp.child.add(root);
                    
                    for(int m=0;m<6;m++)
                    {
                        for(int n=0;n<6;n++)
                        {
                            root.board[m][n]= new Node();
                            root.board[m][n].liberty=temp.board[m][n].liberty;
                            root.board[m][n].stone=temp.board[m][n].stone;
                            root.board[m][n].y=temp.board[m][n].y;
                            root.board[m][n].x=temp.board[m][n].x;  
                        }
                    }
                  
                    root.board[i][j].stone='W';
                    
                    capture(root,'B');
                  
                    b=Math.min(b,max(root,level+1,a,b));
                    root.value=b;  
                }
            }
        }
    } 
  return b;
}

/// CALCULATE UTILITY VALUE
public int eval(GameBoard root)
{
    int noofB=0;
    int noofW=0;
    int utility;
    for(int i=0;i<6;i++)
    {
        for(int j=0;j<6;j++)
        {
            if(root.board[i][j].stone=='B')
            {
                noofB++;
            }
            if(root.board[i][j].stone=='W')
            {
                noofW++;
            }
        }
    }
    utility=noofB-noofW;
    return utility;
}

/// CALLS LIBERTY VALUES CALCULATION FUNCTION AND MAKES NODE INVALID WITH LIBERTY ZERO
public void capture(GameBoard root, char ch)
{
    GameTree gb= new GameTree();
    gb.initialboard(root.board);
  
    for(int i=0;i<6;i++)
    {
        for(int j=0;j<6;j++)
        {
            if(root.board[i][j].liberty==0 && root.board[i][j].stone==ch && root.board[i][j].stone!='E')
            {
                root.board[i][j].stone='I';
            }
        }
    }
 }

 public static void main(String args[])
 {
      
    minmax mm= new minmax();
    
    /// WITHOUT PRUNING
    double start = System.currentTimeMillis();
    GameBoard root= new GameBoard();
    root =mm.call();
    int y= mm.max(root, 0, -100, 100);
     System.out.println();
    System.out.println("Best strategy:");
    
        
    for(int i=0;i<root.child.size();i++)
    { 
        if(root.child.get(i).value==y)
        {
            System.out.println("Depth 0: Player B places stone at ("+root.child.get(i).x+","+root.child.get(i).y+").");
                                    
            for(int j=0;j<root.child.get(i).child.size();j++)
            {
                if(root.child.get(i).child.get(j).value==y)
                {
                    System.out.println("Depth 1: Player W places stone at ("+root.child.get(i).child.get(j).x+","+root.child.get(i).child.get(j).y+").");
                                    
                    for(int k=0;k<root.child.get(i).child.get(j).child.size();k++)
                    {
                        if(root.child.get(i).child.get(j).child.get(k).value==y)
                        {
                            System.out.println("Depth 2: Player B places stone at ("+root.child.get(i).child.get(j).child.get(k).x+","+root.child.get(i).child.get(j).child.get(k).y+").");
                                    
                            for(int l=0;l<root.child.get(i).child.get(j).child.get(k).child.size();l++)
                            {
                                if(root.child.get(i).child.get(j).child.get(k).child.get(l).value==y)
                                {
                                    System.out.println("Depth 3: Player W places stone at ("+root.child.get(i).child.get(j).child.get(k).child.get(l).x+","+root.child.get(i).child.get(j).child.get(k).child.get(l).y+").");
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            break;
        }  
    }

    
       double end   = System.currentTimeMillis();
       double total = (end - start)/1000; 

       ///WITH PRUNING
       double startprune= System.currentTimeMillis();
       GameBoard rootprune= new GameBoard();
       rootprune= mm.call();
       mm.maxprune(rootprune, 0, -100, 100);
       double endprune= System.currentTimeMillis();
       double totalprune = (endprune - startprune)/1000;

       int nodespruned=count-countprune;
       System.out.println("Depth 4: utility value of current board configuration is "+y+".");
       System.out.println("\nComparison:");
       System.out.println("Minmax with pruning: running time "+ totalprune +" sec.");
       System.out.println("Minmax with pruning: pruned "+ nodespruned +" nodes");
       System.out.println("Minmax without pruning: running time "+ total +" sec.");
       System.out.println();
       System.out.println();
     
   }


}
