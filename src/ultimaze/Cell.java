
package ultimaze;

/**
 *
 * @author DhiRaj
 */
public class Cell {
    // if these Boolean values(left,up,right,down) are true--> then the corresponding Wall exists.
    int i,j;
    
    boolean left;
    boolean top;
    boolean right;
    boolean bottom;
    
    boolean visited;
    
    //These are for recording the shortest path..
    //if youre traversing from final to initial cell..continuosly use parent.
    //if youre traversing from initial to final cell..continuously use child.
    //parent and child are just INTEGERS (enums)
    Direction parent;
    Direction child;
    
    //Whenever a Cell is created for the Purpose of MAZE GENERATION..
    //initially all its four walls are blocked...(Totally blocked MAZE)
    //..later on we break the walls (adjacent walls) one by one to make a maze
    Cell(int x,int y)
    {
        i=x;
        j=y;
        left = top = right = bottom = true;
    }
    
    
    
    public void breakWall(int w)
    {
        switch(w)
        {
            case 0:left = false;
                   break;
            case 1:top = false;
                   break; 
            case 2:right = false;
                   break;
            case 3:bottom = false;
                   break;
        }
    }
    
    void setVisited(boolean bool)
    {
        visited = true;
    }
  
}
