import back.grid;
import ui.mainWindow;


public class Main
{
    public static void main(String[] args) 
    {
        grid backGrid = new grid();
        mainWindow win = new mainWindow(backGrid);
    }
}