
package ultimaze;

import java.awt.Color;
import java.util.Random;
import library.*;

/**
 *
 * @author DhiRaj
 */
public class Maze 
{
   public Cell[][] cells;
   final int dimension;
   
    
   Maze(int  N)
   {
        this.dimension = N;
        cells = new Cell[dimension+2][dimension+2]; //+2 for the borders
        give_life_to_cells();
        init_border_cells();                        //set border cells as VISITED..becos we never want to go there..
                                                    //Border cells act as BLOCKAGE or DEAD_END
                                                    //..and we dont re-visit visited cells...so ..all cool
        generate_maze(1,1);
    }
   
   void give_life_to_cells()
   {
       for(int i=0;i<dimension+2;i++)
        {
            for(int j=0;j<dimension+2;j++)
                cells[i][j]=new Cell();
        }
   }
   
   void init_border_cells()
   {
       for(int i=0;i<dimension+2;i++)
       {
           cells[i][0].setVisited(true);              //Left border
           cells[0][i].setVisited(true);              //Top border
           cells[i][dimension+1].setVisited(true);    //Right border
          cells[dimension+1][i].setVisited(true);    //Bottom border
       }
   }
   
   
   //generate the Randomized maze(with given specification(DIMENSION))
   //NOTICE: this is actually DFS!!!...(remember that in recursion..we do backtrack!)
   void generate_maze(int X,int Y)
   {
       cells[X][Y].setVisited(true);
       
       while(hasUnvisitedNeighbor(X,Y))
       {
          int i=StdRandom.uniform(4);
          //Left cell is unvisited 
          if(i==0 && !cells[X-1][Y].visited)
           {
               cells[X][Y].breakWall(0);
               cells[X-1][Y].breakWall(2);
               generate_maze(X-1, Y);
            }
          //Top cell is unvisited
          else if(i==1 && !cells[X][Y+1].visited)
          {
              cells[X][Y].breakWall(1);
              cells[X][Y+1].breakWall(3);
              generate_maze(X, Y+1);
          }
          //Right cell is unvisited
          else if(i==2 && !cells[X+1][Y].visited)
          {
              cells[X][Y].breakWall(2);
              cells[X+1][Y].breakWall(0);
              generate_maze(X+1, Y);
           }
          //Bottom cell is unvisited
          else if(i==3 && !cells[X][Y-1].visited)
          {
              cells[X][Y].breakWall(3);
              cells[X][Y-1].breakWall(1);
              generate_maze(X, Y-1);
          }
      }
   }
   
   
   public void solve(int X,int Y)
   {
       if(X==0 || Y==0 || X == (dimension+1) || Y == (dimension+1))
           return;
       if(cells[X][Y].visited)
           return;
       
       if(X==(dimension+1) && (Y==dimension+1))
           return;
       
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(X + 0.5, Y + 0.5, 0.25);
        StdDraw.show();
        //StdDraw.pause(30);
       
        cells[X][Y].visited=true;
        if(cells[X][Y].left==false)
        {
            if(!cells[X-1][Y].visited)
              cells[X-1][Y].parent=Direction.RIGHT;
            solve(X-1, Y);
            
        }
        
        if(cells[X][Y].top ==false)
        {   if(!cells[X][Y+1].visited)
             cells[X][Y+1].parent=Direction.DOWN;
            solve(X, Y+1);
        }
        if(cells[X][Y].right==false)
        {
            if(!cells[X+1][Y].visited)
              cells[X+1][Y].parent = Direction.LEFT;
            solve(X+1, Y);
        }
        if(cells[X][Y].bottom == false)
        {
            if(!cells[X][Y-1].visited)
              cells[X][Y-1].parent=Direction.TOP;
            solve(X, Y-1);
        }
        
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle(X + 0.5, Y + 0.5, 0.30);
        StdDraw.show();
        //StdDraw.pause(30);
       
   }
   
   public void trace_shortest_path()
   {
       int X = dimension;
       int Y = dimension;
       
       while(true)
       {
           StdDraw.setPenColor(Color.BLACK);
           StdDraw.filledCircle(X + 0.5, Y + 0.5, 0.30);
           StdDraw.show();
           StdDraw.pause(75);
           
           if(cells[X][Y].parent==Direction.LEFT)
               X--;
           else if(cells[X][Y].parent==Direction.TOP)
               Y++;
           else if(cells[X][Y].parent==Direction.RIGHT)
               X++;
           else if(cells[X][Y].parent==Direction.DOWN)
               Y--;
           
       }
       
   }
   
   
   public void setAllUnvisited()
   {
       for(int i=1;i<=dimension;i++)
         for(int j=1;j<=dimension;j++)
             cells[i][j].visited=false;
   }
   
   
   
   
   boolean hasUnvisitedNeighbor(int X,int Y)
   {
      return (!cells[X][Y-1].visited || !cells[X-1][Y].visited || !cells[X][Y+1].visited || !cells[X+1][Y].visited);
    }
   
}
