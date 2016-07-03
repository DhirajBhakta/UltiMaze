/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimaze;

import library.StdDraw;

/**
 *
 * @author SathyaSai
 */
public class RunHere {
    
    public static void main(String args[])
    {
        int n=50;
        Maze maze = new Maze(50);
        
        StdDraw.setXscale(0, n+5);
        StdDraw.setYscale(0,n+5);
          StdDraw.enableDoubleBuffering();
         

     // draw the maze
      StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(n/2.0 + 0.5, n/2.0 + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);
        StdDraw.line(1, 0, 30, 30);

        StdDraw.setPenColor(StdDraw.BLACK);
        
        
       
        
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                if (maze.cells[x][y].left) StdDraw.line(x, y, x, y+1);
               if (maze.cells[x][y].top) StdDraw.line(x, y+1, x+1, y+1);
                if (maze.cells[x][y].right)  StdDraw.line(x+1, y, x+1, y+1);
               if (maze.cells[x][y].bottom)  StdDraw.line(x, y, x+1, y);
              
              
            }
        }
        StdDraw.pause(500);
        StdDraw.show();
        
    }
    
}
