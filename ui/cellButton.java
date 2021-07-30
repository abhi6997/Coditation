package ui;

import java.awt.*;  
import java.awt.event.*;
import back.*;


public class cellButton extends Button implements ActionListener
{
    // width of cell in pixels
    public static int cellWidth = 100, cellHeight = 100;
    
    //  color of cells in different states
    public static Color aliveColor, deadColor;
    
    static
    {
        aliveColor = new Color(255, 255, 255);
        deadColor = new Color(127, 127, 255);
    }

    // grid cordinates in pixels
    public static int gridX = 0, gridY = mainWindow.titleBarHeight;
    // back cell
    public cell Cell;
    public mainWindow parent;

    // coordinates of this cellButton ui- grid
    public int uiX, uiY;
    
    public cellButton(mainWindow parent, int uiX, int uiY)
    {
        this.parent = parent;
        configure(uiX, uiY);
        addActionListener(this);
        setBackground(deadColor);
    }
    
    // below is called when cell is clicked
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(Cell != null)
        {
            if(Cell.alive) { Cell.alive = false; setBackground(deadColor);}
            else {Cell.alive = true; setBackground(aliveColor); }
        }

    }
    
    // changes the back cell to provided cell
    //  and redraw it
    public void setCell(cell cell)
    {
        Cell = cell;
        setBackground(cell.alive ? aliveColor : deadColor);
    }
    
    // configure the ui of the button 
    public void configure(int uiX,int uiY)
    {
        this.uiX = uiX;
        this.uiY = uiY;
        
        setBounds(gridX +  cellWidth * uiX,   gridY + parent.height - cellHeight * (uiY + 1),  cellWidth,  cellHeight);
        
        // set the cordinates of the cell as name
        setLabel(String.valueOf(uiX) + "-" +String.valueOf(uiY));
    }
}
