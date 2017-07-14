package map.building;

import Utils.ImageReader;
import core.Core;
import map.terrain.TerrainType;

import javax.swing.*;
import java.io.Serializable;

/**
 * Created by bobvv on 7/9/17.
 */
public class Building extends JLabel implements Serializable
{
    Core core;

    private BuildingType buildingType;
    private ImageIcon imageIcon;
    private int x,y;

    public Building(Core core,BuildingType buildingType,int x,int y)
    {
        this.core = core;

        this.buildingType = buildingType;
        this.x = x;
        this.y = y;
        this.imageIcon = ImageReader.createImage("/Assets/buildings/" + buildingType.toString().toLowerCase() + ".png");
    }

    public BuildingType getBuildingType()
    {
        return buildingType;
    }

    public boolean isAvailableTerrain(TerrainType terrain)
    {
        return terrain == buildingType.getAvailableTerrain();
    }

}
