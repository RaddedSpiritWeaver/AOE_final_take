package map.map;

import core.Core;
import interFaces.Mobile;
import interFaces.Observable;
import map.terrain.TerrainType;
import map.terrain.Tile;

import javax.swing.*;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by sarb on 5/29/17.
 */

public class Map extends JLabel implements Serializable
{
    private Core core;

    private int widthTiles;
    private int heightTiles;

    private Tile tiles[][];
    private Vector<Mobile> mobiles;
    private Vector<Observable> observables;

    public Map(Core core, int widthTiles, int heightTiles)
    {
        this.core = core;

        this.widthTiles = widthTiles;
        this.heightTiles = heightTiles;

        mobiles = new Vector<>();
        observables = new Vector<>();

        tiles = new Tile[widthTiles][heightTiles];
        for (int i = 0; i < widthTiles; i++)
            for (int j = 0; j < heightTiles; j++)
                tiles[i][j] = new Tile(core,TerrainType.GRASS,i,j);

        updateAll();
    }

    public void updateAll()
    {
        for (int i = 0; i < widthTiles; i++)
            for (int j = 0; j < heightTiles; j++)
                tiles[i][j].update();
    }

    public void replace(Map newMap)
    {
        this.tiles = newMap.getTiles();
        this.widthTiles = newMap.getWidthTiles();
        this.heightTiles = newMap.getHeightTiles();
    }

    public void addMobile(Mobile mobile)
    {
        mobiles.add(mobile);
    }

    public Vector<Mobile> getMobiles()
    {
        return mobiles;
    }

    public int getWidthTiles()
    {
        return widthTiles;
    }

    public int getHeightTiles()
    {
        return heightTiles;
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
