package back;

// rough class for cells in grid
public class cell
{
    // default constructor
    public cell(grid parent) {
        this.parent =parent;
    };

    public cell( grid parent ,int x, int y) { this.x = x; this.y = y;  this.parent = parent;}

    // set to true before each odd tick and false on even ticks
    // whether value is read from  alive or previous depends on this
    public static int gridTickCount = 0;
    public int tickCount = 0;

    /** alive -  current state
     *  previous - state before update call ( used if updated)
     *  updated - switches states between true and false with alternate ticks
    */
    public boolean alive, previous;

    /**pointer to all cells around this cell
     * for updating and logical grid formation
     * 
     *    2   4    7
     *    1        6
     *    0   3    5
    */
    // public cell[] around = new cell[8];
    grid parent;

    // cordinates of cell in 2D grid
    public int x, y;


    // update the value of cell
    public void update()
    {
        int total = 0;
        cell other;
        for (int i = -1 ;i < 2; i++) {
            for (int j = -1 ; j < 2; j++)
            {
                if(i==0 && j==0){ continue; }
                try { other = parent.getCell(x+i, y+j); }
                catch (Exception e) { continue ;}
                
                if( other.isUpdated())
                {
                    if(other.previous) total ++;
                }
                else if(other.alive) total ++;

            }
        }
    
        previous = alive;
        
        // prescribed conditions
        if( total < 2 || total > 3) alive = false;
        else if( ! alive && total == 3 ) alive = true; 

        tickCount = gridTickCount;
    };


    public boolean isUpdated()  { return tickCount == gridTickCount; };
}