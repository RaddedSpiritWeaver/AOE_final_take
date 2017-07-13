package map.resource;


import Utils.ImageReader;
import mainFrame.Observable;
import map.terrain.TerrainType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by sarb on 6/6/17.
 */
public class Resource implements Observable, Serializable
{
    private ResourceType resourceType;
    private ImageIcon imageIcon;
    private int x,y;

    private static Random random;
    static
    {
        random = new Random(System.currentTimeMillis());
    }

    public Resource(ResourceType resourceType, int x, int y)
    {
        this.resourceType = resourceType;
        this.x = x;
        this.y = y;

        int i = random.nextInt(resourceType.getDifferentType());
        this.imageIcon = ImageReader.createImage("/Assets/resources/" + resourceType.toString().toLowerCase() + "/"+ resourceType.toString().toLowerCase() + (i+1) + resourceType.getExtension());
    }

    @Override
    public Shape getOval(int xRoot, int yRoot, int tileSize, int cotang)
    {
        int relX = ( (x - xRoot) * tileSize ) + tileSize/2  + (y%2)*tileSize/2 ;
        int relY = (y - yRoot) * tileSize / (2* cotang);
        return new Ellipse2D.Double(relX - tileSize/7, relY + tileSize/(4*cotang), tileSize/4,tileSize/4);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public Color getColor()
    {
        return resourceType.getColor();
    }
    public ResourceType getResourceType()
    {
        return resourceType;
    }

    @Override
    public ImageIcon getImageIcon()
    {
        return imageIcon;
    }

    @Override
    public boolean isAvailableTerrain(TerrainType terrain)
    {
        return terrain==resourceType.getAvailableTerrain();
    }


}
