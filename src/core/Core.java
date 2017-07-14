package core;

import gameFrame.GameFrame;
import gamePanel.GamePanel;
import gamePanel.GamePanelMover;
import map.map.Map;

import javax.swing.*;

/**
 * Created by sarb on 7/13/17.
 */
public class Core extends JLabel
{
    Map map;
    GameFrame gameFrame;
    private boolean coordinated = true;

    public Core()
    {
        map = new Map(this,50,100);
        gameFrame = new GameFrame(this,"AOE",1000,600);
    }

    public boolean isCoordinated()
    {
        return coordinated;
    }

    public static void main(String[] args)
    {
        new Core();
    }

    public GameFrame getGameFrame()
    {
        return gameFrame;
    }

    public Map getMap()
    {
        return map;
    }
}
