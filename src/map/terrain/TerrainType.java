package map.terrain;

import Utils.ImageReader;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sarb on 5/27/17.
 */
public enum TerrainType
{
    DEEP_WATER(new Color(0,0,255,255),0, 0),
    SHALLOW_WATER(new Color(0,204,204,255),1, 0),
    SAND(new Color(255,153,51,255),2, 0),
    GRASS(new Color(102,204,0,255),3, 0),
    MOUNTAIN(new Color(204,102,0,255),4, 0.5),
    PEAK(new Color(102,51,0,255),5, 1);

    private Color color;
    private ImageIcon imageIcon;
    private int index;
    private double height;

    TerrainType(Color color, int index, double height)
    {
        this.color = color;
        this.index = index;
        this.height = height;

        this.imageIcon = ImageReader.createImage( "/Assets/terrains/" + this.toString() + ".png");
    }

    public static TerrainType getTerrain(int index)
    {
        switch (index)
        {
            case 0:
                return DEEP_WATER;
            case 1:
                return SHALLOW_WATER;
            case 2:
                return SAND;
            case 3:
                return GRASS;
            case 4:
                return MOUNTAIN;
            case 5:
                return PEAK;
        }
        return null;
    }

    public double getHeight()
    {
        return height;
    }

    public Color getColor()
    {
        return color;
    }

    public ImageIcon getImageIcon()
    {
        return imageIcon;
    }

    public int getIndex()
    {
        return index;
    }
}
