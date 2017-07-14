package gamePanel;

import core.Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by sarb on 7/13/17.
 */
public class GamePanelMover implements KeyListener,MouseMotionListener
{
    private Core core;

    public GamePanelMover(Core core)
    {
        this.core = core;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println("there");
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if ( core.getGameFrame().getGamePanel().getyRoot() > 1 )
                    core.getGameFrame().getGamePanel().setyRoot(core.getGameFrame().getGamePanel().getyRoot() -1);
                break;
            case KeyEvent.VK_DOWN:
                if ( core.getGameFrame().getGamePanel().getyRoot() < core.getMap().getHeightTiles() - core.getGameFrame().getGamePanel().getVerticalTiles())
                    core.getGameFrame().getGamePanel().setyRoot(core.getGameFrame().getGamePanel().getyRoot() +1);
                break;
            case KeyEvent.VK_RIGHT:
                if( core.getGameFrame().getGamePanel().getxRoot() < core.getMap().getWidthTiles() - core.getGameFrame().getGamePanel().getHorizontalTiles())
                    core.getGameFrame().getGamePanel().setxRoot(core.getGameFrame().getGamePanel().getxRoot() +1);
                break;
            case KeyEvent.VK_LEFT:
                if ( core.getGameFrame().getGamePanel().getxRoot() > 1 )
                    core.getGameFrame().getGamePanel().setxRoot(core.getGameFrame().getGamePanel().getxRoot() -1);
                break;
        }

        core.getGameFrame().getGamePanel().repaint();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }
}
