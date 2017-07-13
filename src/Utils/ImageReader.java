package Utils;

import javax.swing.*;
import java.net.URL;

/**
 * Created by sarb on 4/28/17.
 */
public class ImageReader
{

    public  static ImageIcon createImage(String path)
    {
        URL url = System.class.getClass().getResource(path);
        if( url == null )
        {
            url = System.class.getClass().getResource("/Utils/CantLoadImage.gif");
        }
        return new ImageIcon(url);
    }

}
