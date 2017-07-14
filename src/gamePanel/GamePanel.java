package gamePanel;

import core.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by sarb on 7/13/17.
 */
public class GamePanel extends JPanel
{
    private Core core;

    private GamePanelMover gamePanelMover;

    private int width;
    private int height;

    private int xRoot;
    private int yRoot;
    private int tileSize;
    private int cotang;
    {
        xRoot = 1;
        yRoot = 1;
        tileSize = 200;
        cotang = 2;
    }

    public GamePanel(Core core, int width, int height)
    {
        this.core = core;

        this.width = width;
        this.height = height;
        setSize(width,height);

        this.gamePanelMover = new GamePanelMover(core);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        for (int j = 0; j < core.getMap().getWidthTiles(); j++)
            for (int i = 0; i < core.getMap().getHeightTiles(); i++)
                if (i > xRoot - 2 && j > yRoot - 2 && i < xRoot + getHorizontalTiles()
                        && j < yRoot + getVerticalTiles())
                        core.getMap().getTile(i, j).draw(g2, xRoot, yRoot, tileSize, cotang);
    }

    public int getVSize()
    {
        return tileSize/(2*cotang);
    }

    public int getHSize()
    {
        return tileSize;
    }

    public int getVerticalTiles()
    {
        return getHeight()/ getVSize() +1;
    }

    public int getHorizontalTiles()
    {
        return getWidth()/ getHSize() +1;
    }

    public GamePanelMover getGamePanelMover()
    {
        return gamePanelMover;
    }

    public int getxRoot()
    {
        return xRoot;
    }

    public int getyRoot()
    {
        return yRoot;
    }

    public void setxRoot(int xRoot)
    {
        this.xRoot = xRoot;
    }

    public void setyRoot(int yRoot)
    {
        this.yRoot = yRoot;
    }
}
