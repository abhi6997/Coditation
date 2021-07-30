package ui;

import back.*;
import java.awt.event.*;
import  java.awt.*;

public class searchBox extends TextField implements KeyListener
{

    mainWindow parent;
    searchBox(mainWindow parent)
    {
        this.parent = parent;
        addKeyListener(this);
        setText("enter cell name and hit enter to find value");
    }


    public void process()
    {
        String[] cords = getText().strip().split("-");
        int uiX = 0, uiY = 0;
        try
        {
            uiX = Integer.parseInt(cords[0]);
            uiY = Integer.parseInt(cords[1]);
            cell target = parent.backGrid.getCell(uiX, uiY);
            setText( (target != null && target.alive) ? "ALIVE" : "DEAD" );
        }
        catch(Exception exception) {return;}
    }
    
    
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10) process();
    }


    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public synchronized void keyTyped(KeyEvent e) {}
}
