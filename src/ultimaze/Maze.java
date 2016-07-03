
package ultimaze;

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
   
   boolean hasUnvisitedNeighbor(int X,int Y)
   {
      return (!cells[X][Y-1].visited || !cells[X-1][Y].visited || !cells[X][Y+1].visited || !cells[X+1][Y].visited);
    }
   
}
