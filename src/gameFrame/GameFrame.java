package gameFrame;

import core.Core;
import gamePanel.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sarb on 7/13/17.
 */
public class GameFrame extends JFrame
{
    private Core core;

    private GamePanel gamePanel;

    public GameFrame(Core core, String title, int width, int height) throws HeadlessException
    {
        super(title);

        this.core = core;

        this.gamePanel = new GamePanel(core, getWidth(), getHeight());
        addKeyListener(gamePanel.getGamePanelMover());
        add(gamePanel);

        setSize(width,height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public GamePanel getGamePanel()
    {
        return gamePanel;
    }
}
