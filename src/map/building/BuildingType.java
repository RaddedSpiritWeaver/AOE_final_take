package map.building;

import map.terrain.TerrainType;

import java.awt.*;

/**
 * Created by bobvv on 7/9/17.
 */
public enum BuildingType
{
    SAWMILL(new Color(0,139, 120), TerrainType.GRASS),
    QUARRY(new Color(51, 50, 41), TerrainType.SAND),
    CASTLE(new Color(31, 50, 41), TerrainType.MOUNTAIN),
    BARRAKS(new Color(21, 50, 41), TerrainType.GRASS),
    PORT(new Color(11, 50, 41), TerrainType.GRASS),
    NOTHING(new Color(85, 50, 41), TerrainType.GRASS);

    private Color color;
    private TerrainType availableTerrain;

    BuildingType(Color color, TerrainType terrainType)
    {
        this.color = color;
        this.availableTerrain = terrainType;
    }

    public TerrainType getAvailableTerrain() {
        return availableTerrain;
    }

    public Color getColor() {
        return color;
    }
}
