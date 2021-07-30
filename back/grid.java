package back;
import java.util.LinkedList;

public class grid
{ 
    public LinkedList<LinkedList<cell>> matrix = new LinkedList<LinkedList<cell>>();
    public static int defaultRow = 3 ,defaultColumn = 3;


    // 3X3 grid which appear on screen by-default//
    public grid()
    {
        LinkedList<cell> newColumn;
        for (int i = 0; i <defaultColumn ; i++) {
          newColumn = new LinkedList<cell>();
          for (int j = 0; j < defaultRow; j++) newColumn.add(new cell(this, i, j));

          matrix.add(newColumn);
       }

       currentHeight = defaultRow;
       currentWidth = defaultColumn;
        }


public int currentHeight, currentWidth;

/*adding of new cells
extension of grid by user
adding of rows and column */

//adding of Row//

    public void addRow(){
        cell target;
        for (int i = 0; i < matrix.size(); i++) {
            target = new cell(this, i, currentHeight);
            matrix.get(i).add(target);
        }
        currentHeight++;
    } 

    //adding of column

    public void addColumn(){
        LinkedList<cell> column =new LinkedList<cell>();
        matrix.add(column);
        cell target;
        for (int i = 0; i < currentHeight; i++) {
            target = new cell(this, currentWidth, i);
            column.add(target);
        }
        currentWidth++;
    }

    //Removal of unused rows and Columns  

    public void removeRow(){
        for (int i = 0; i < currentWidth; i++)    matrix.get(i).pop();
          currentHeight--;
     }
    public void removeColumn(){
        matrix.pop();
        currentWidth--;
    }

    /*Setting of Dimensions by user 
    adding of columnn and rows or extension of grid as decided by user */

    public void setDimension(int Height ,int Width)
    {
        int heightDifference = Height-currentHeight;
        int widthDifference = Width - currentWidth;

        while (heightDifference  != 0) {
            if (heightDifference > 0) { addRow(); heightDifference --; }
            else{removeRow(); heightDifference ++;}
        }
     

        while(widthDifference != 0)
        {
            if (widthDifference > 0) { addColumn(); widthDifference --;}
            else { removeColumn(); widthDifference ++ ;}
        }
    }

 /* code for each tick 
 status of every cell will be check and upadte */


    public synchronized void tick()
    {
        cell.gridTickCount ++;
        LinkedList <cell> column ;
        for(int i =0; i < matrix.size(); i++)
        {
            column = matrix.get(i);
            for(int j=0; j < column.size(); j++)  column.get(j).update();
       }
    }


    // returns the cell object in that position
    public cell getCell(int x ,int y) 
    { return (x > currentWidth || y > currentHeight) ? null : matrix.get(x).get(y);}



}