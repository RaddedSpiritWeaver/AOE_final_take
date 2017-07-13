package map.terrain;

import Utils.ImageReader;
import mainFrame.Observable;
import map.map.Map;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by sarb on 5/27/17.
 */
public class Tile implements Serializable
{
    private TerrainType terrainType;
    private int x,y;
    private Observable filler;

    int i;

    private Map map;
    private String imageCode;

    private int[][][] neighbors = { {{-1, -1}, {0, -1}, {0, 1}, {-1, 1}} , {{0, -1}, {1, -1}, {1, 1}, {0, 1}}};

    public Tile(TerrainType terrainType, int x, int y, Map map)
    {
        this.terrainType = terrainType;
        this.x = x;
        this.y = y;
        this.map = map;
        this.filler = null;
    }

    public void draw(Graphics2D g2,int xRoot, int yRoot, int size, int cotang, boolean isCoordinated)
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

        if( filler != null)
            g2.drawImage(filler.getImageIcon().getImage(),relX - size/2 , relY - size/2, size, size, null);

        if(isCoordinated)
        {
            g2.setColor(Color.BLACK);
            Font font = new Font("Marker Felt", Font.PLAIN, size / 7);
            g2.setFont(font);
            g2.drawString(x + "," + y, relX - size / 6, relY + size / 3);
        }
    }

    public void simpleDraw(Graphics2D g2,int xRoot, int yRoot, int size, int cotang, boolean isCoordinated)
    {
        int relX = ( (x - xRoot) * size ) + size/2  + (y%2)*size/2 ;
        int relY = (y - yRoot) * size / (2* cotang) - (int)((size/2)*terrainType.getHeight());

        g2.setColor(terrainType.getColor());
        g2.fillPolygon(getPolygon(xRoot,yRoot,size,2));

        if( filler != null)
        {
            g2.setColor(filler.getColor());
            g2.fill(filler.getOval(xRoot,yRoot,size,2));
        }

        if (!isCoordinated)
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
                    if (map.getTile(x + neighbors[bin][i][0], y + neighbors[bin][i][1]).getTerrainType().getIndex() == terrainType.getIndex() + 1)
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
        if (x >= 0 && x < map.getWidthCoord() && y >= 0 && y < map.getHeightCoord())
            return true;
        return false;
    }

    public TerrainType getTerrainType()
    {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType)
    {
        this.terrainType = terrainType;
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
