package ui;

import back.*;

import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;



public class mainWindow extends Frame implements ComponentListener
{
    
    public static int defaultWindowWidth = 600, defaultWindowHeight = 600, titleBarHeight = 80,
    // number of colomns and rows in the ui grid
    gridWidth = 0, gridheight = 0;
    public grid backGrid;
    
    // buttons on ui
    public Button tick = new Button(), plus = new Button(), minus = new Button();
    public searchBox box = new searchBox(this);

    // indexCell - bottom left cell
    // this are cordinates of index cell in back grid
    //  this enables scrolling
    int indexCellX = 0, indexCellY = 0;
    
    // stores all the buttons in the grid
    public ArrayList<cellButton> buttons = new ArrayList<cellButton>();

    public static String title = "Coditation Project";

    // height and width of this window
    public int width = defaultWindowWidth, height = defaultWindowHeight;



    public mainWindow(grid backGrid)
    {
        this.backGrid = backGrid;

        // trigger for closing the window
        addWindowListener
        (
            new WindowAdapter()
            { 
                @Override
                public void windowClosing(WindowEvent e) 
                {
                    close();
                    dispose();
                }
            }
        );

        // to enable resizing events
        addComponentListener(this);
        setSize(defaultWindowWidth, defaultWindowHeight + titleBarHeight);
        setBackground(new Color(50, 50, 50));
        setTitle(title);
        
        drawGrid();
        setVisible(true);
        
                
        tick.addActionListener(new ActionListener() {
            
            // 
            @Override
            public void actionPerformed(ActionEvent e) {
                backGrid.tick();
                drawGrid();
           }
        });

        plus.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cellButton.cellHeight < 200) { cellButton.cellHeight += 10; ; cellButton.cellWidth += 10; drawGrid();}
            }
        }
        );

        minus.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cellButton.cellHeight > 20) { cellButton.cellHeight -= 10; ; cellButton.cellWidth -= 10; drawGrid();}
            }
        }
        );


        Color buttonColor = new Color(40, 120, 150);
        tick.setLabel("TICK");
        tick.setBackground(buttonColor);

        plus.setLabel("+");
        plus.setBackground(buttonColor);

        minus.setLabel("-");
        minus.setBackground(buttonColor);

    }

    // clears all the ui elements on the
    public synchronized void drawGrid()
    {
        removeAll();

        // figure out current grid scales
        gridWidth =  width / cellButton.cellWidth;
        gridheight = height / cellButton.cellHeight;

        // set the backgrid dimensions accordingly
        {  
            int maxGridHeight = (backGrid.currentHeight > gridheight) ? backGrid.currentHeight : gridheight,
            maxGridWidth = (backGrid.currentWidth > gridWidth) ? backGrid.currentWidth : gridWidth;

            backGrid.setDimension(maxGridHeight, maxGridWidth);
        }

        // setup buttons array to contain buttons on screen
        cellButton targetCellButton;
        cell targetCell;
        int arrayCount = 0;
        for(int x = 0; x < gridWidth; x++)
        {
            for(int y = 0; y < gridheight; y++)
            {
                if( arrayCount >= buttons.size()) buttons.add(new cellButton(this , x, y));
                targetCellButton =  buttons.get(arrayCount);
                targetCell = backGrid.getCell(indexCellX + x, indexCellY + y);

                targetCellButton.parent = this;

                targetCellButton.configure(x, y);
                targetCellButton.setCell(targetCell);
                add(targetCellButton);
                arrayCount ++;
            }
        }

        tick.setBounds(0, 30, 100, 50);
        add(tick);

        plus.setBounds(100, 30, 50, 50);
        add(plus);

        minus.setBounds(150 , 30, 50, 50);
        add(minus);

        box.setBounds(200, 30, 250, 50);
        add(box);
    }


    public void close() { System.exit(0);};

    //  
    @Override
    public synchronized void componentResized(ComponentEvent e)
    {
        width = getWidth();
        height = getHeight() - titleBarHeight;

        drawGrid();
    }
    
    @Override
    public void componentMoved(ComponentEvent e) { }

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) { }

}
