package libs.img;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import libs.img.exception.IllegalImageSizeException;

import org.junit.Test;

public class ImageProccesingsTest {
    private ImageProcessings proc = new ImageProcessings();
    private Class resouce = getClass();
    @Test
    public void testrgbTogray(){
        assertEquals(6 , proc.rgbTogray(0x050309, ImageProcessings.CONV_GRAY_MEAN));
        assertEquals(4 , proc.rgbTogray(0x050309, ImageProcessings.CONV_GRAY_NTSC_MEAN));
    }

    @Test
    public void testfillMatchImageArea(){
        try{
            //2画像でRGBが異なるピクセルをカウントする。
            //動作がおかしいなら異なるピクセルが存在するのでその数でtestする
            BufferedImage correctImg = ImageIO.read(new File("correct.png"));
            BufferedImage testImg = proc.fillMatchImageArea(ImageIO.read(new File("test.png")), ImageIO.read(new File("match.png")), 0xff0000, 0x00ff00, 0x0000ff);
            ImageIO.write(testImg, "png", new File("hoge.png"));
            int diffPixs = 0;
            for(int x = 0; x < correctImg.getWidth(); x++){
                for(int y = 0; y < correctImg.getHeight(); y++){
                    if(correctImg.getRGB(x, y) != testImg.getRGB(x, y)){
                        diffPixs++;
                    }
                }
            }
            assertEquals(0, diffPixs);
        }catch(IOException e){
            e.printStackTrace();
        } catch (IllegalImageSizeException e) {
            e.printStackTrace();
        }
    }
}
