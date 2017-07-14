package map.terrain;

import Utils.ImageReader;
import core.Core;
import interFaces.Observable;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by sarb on 5/27/17.
 */
public class Tile extends JLabel implements Serializable
{
    Core core;

    private int x,y;
    private TerrainType terrainType;

    private Observable filler;
    private String imageCode;

    private int[][][] neighbors = { {{-1, -1}, {0, -1}, {0, 1}, {-1, 1}} , {{0, -1}, {1, -1}, {1, 1}, {0, 1}}};

    public Tile(Core core, TerrainType terrainType, int x, int y)
    {
        this.core = core;
        this.terrainType = terrainType;
        this.x = x;
        this.y = y;
        this.filler = null;
    }

    public void draw(Graphics2D g2,int xRoot, int yRoot, int size, int cotang)
    {
        int relX = ( (x - xRoot) * size ) + size/2  + (y%2)*size/2 ;
        int relY = ((y - yRoot) * size) / (2* cotang) - (int)((size/2)*terrainType.getHeight());

        Image image;
        if ( imageCode == null)
            image = ImageReader.createImage("/Assets/terrains/" + terrainType.toString().toLowerCase() + ".png").getImage();
        else
            image = ImageReader.createImage("/Assets/terrains/" + terrainType.toString().toLowerCase() + "/" + imageCode + ".png").getImage();

        int mag = size/20;
        g2.drawImage(image,relX-size/2, relY, size + mag, (int)((size/2)*(1+terrainType.getHeight())) + mag,null);

//        if( filler != null)
//            g2.drawImage(filler.getImageIcon().getImage(),relX - size/2 , relY - size/2, size, size, null);

        if(core.isCoordinated())
        {
            g2.setColor(Color.BLACK);
            Font font = new Font("Marker Felt", Font.PLAIN, size / 7);
            g2.setFont(font);
            g2.drawString(x + "," + y, relX - size / 6, relY + size / 3);
        }

    }

    public void update()
    {
        int bin = y%2;

        if( terrainType == TerrainType.MOUNTAIN || terrainType == TerrainType.PEAK || terrainType == TerrainType.GRASS )
            imageCode = null;
        else
        {
            imageCode = "";
            for (int i = 0; i < 4; i++)
                if (!checkInBoard(x + neighbors[bin][i][0], y + neighbors[bin][i][1]))
                    imageCode = imageCode + "0";
                else
                {
                    if (core.getMap().getTile(x + neighbors[bin][i][0], y + neighbors[bin][i][1]).getTerrainType().getIndex() == terrainType.getIndex() + 1)
                        imageCode = imageCode + "1";
                    else
                        imageCode = imageCode + "0";
                }
        }
    }

    public Polygon getPolygon(int xRoot, int yRoot, int size, int cotang)
    {
        Polygon polygon = new Polygon();

        int relX = ((x - xRoot) * size ) + size/2  + (y%2)*size/2 ;
        int relY = (y - yRoot) * size / (2* cotang) - (int)((size/2)*(terrainType.getHeight()));

        polygon.addPoint(relX, relY);
        polygon.addPoint( relX - size/2, relY + size/(2*cotang));
        polygon.addPoint(relX, relY + size/cotang);
        polygon.addPoint(relX + size/2, relY + size/(2*cotang));

        return polygon;
    }

    private boolean checkInBoard(int x, int y)
    {
        if (x >= 0 && x < core.getMap().getWidthTiles() && y >= 0 && y < core.getMap().getHeightTiles())
            return true;
        return false;
    }

    public TerrainType getTerrainType()
    {
        return terrainType;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Observable getFiller()
    {
        return filler;
    }

    public void setFiller(Observable filler)
    {
        this.filler = filler;
    }
}
