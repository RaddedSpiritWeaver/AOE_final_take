package map.map;

import map.resource.Resource;
import map.resource.ResourceType;
import map.terrain.TerrainType;
import map.terrain.Tile;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by sarb on 5/29/17.
 */

public class Map implements Serializable
{
    private Tile tiles[][];
    private int widthCoord;
    private int heightCoord;

    private Vector<Mobile> mobiles;

    public Map(int widthCoord, int heightCoord)
    {
        this.widthCoord = widthCoord;
        this.heightCoord = heightCoord;

        mobiles = new Vector<>();

        tiles = new Tile[widthCoord][heightCoord];
        for (int i = 0; i < widthCoord; i++)
            for (int j = 0; j < heightCoord; j++)
                tiles[i][j] = new Tile(TerrainType.SAND,i,j, this);

        tiles[5][8].setFiller( new Resource(ResourceType.PALM,5,8) );

        updateAll();
    }

    public void updateAll()
    {
        for (int i = 0; i < widthCoord; i++)
            for (int j = 0; j < heightCoord; j++)
                tiles[i][j].update();
    }

    public void replace(Map newMap)
    {
        this.tiles = newMap.getTiles();
        this.widthCoord = newMap.getWidthCoord();
        this.heightCoord = newMap.getHeightCoord();
    }

    public void addMobile(Mobile mobile)
    {
//        canvas.addKeyListener(mobile);
        mobiles.add(mobile);
    }

    public Vector<Mobile> getMobiles()
    {
        return mobiles;
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public void setCanvas(Canvas canvas)
    {
        this.canvas = canvas;
    }

    public int getWidthCoord()
    {
        return widthCoord;
    }

    public int getHeightCoord()
    {
        return heightCoord;
    }

    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }

    public Tile[][] getTiles()
    {
        return tiles;
    }
}
