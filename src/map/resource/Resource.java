package map.resource;


import Utils.ImageReader;
import core.Core;

import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by sarb on 6/6/17.
 */
public class Resource extends JLabel implements  Serializable
{
    Core core;

    private ResourceType resourceType;
    private ImageIcon imageIcon;
    private int x,y;

    private static Random random;
    static
    {
        random = new Random(System.currentTimeMillis());
    }

    public Resource(Core core, ResourceType resourceType, int x, int y)
    {
        this.core = core;

        this.resourceType = resourceType;
        this.x = x;
        this.y = y;

        int i = random.nextInt(resourceType.getDifferentType());
        this.imageIcon = ImageReader.createImage("/Assets/resources/" + resourceType.toString().toLowerCase() + "/"+ resourceType.toString().toLowerCase() + (i+1) + resourceType.getExtension());
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

}
