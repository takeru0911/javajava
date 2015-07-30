package libs.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import libs.img.exception.IllegalImageSizeException;

public class ImageProcessings {
    public final static int CONV_GRAY_MEAN = 0;
    public final static int CONV_GRAY_NTSC_MEAN = 1;

    public int rgbTogray(int rgb, int CONV_GRAY_METHOD){
        double r = rgb >> 16 & 0xff;
        double g = rgb >>  8 & 0xff;
        double b = rgb       & 0xff;

        if(CONV_GRAY_METHOD== ImageProcessings.CONV_GRAY_MEAN){
            return (int)Math.round((r + g + b) / 3);
        }else if(CONV_GRAY_METHOD == ImageProcessings.CONV_GRAY_NTSC_MEAN){
            return (int)(0.298912 * r + 0.586611 * g + 0.114478 * b);
        }

        throw new IllegalArgumentException();
    }

    public BufferedImage fillMatchImageArea(BufferedImage compareImg, BufferedImage matchImg, int collectColor, int trueFalseColor, int falseTrueColor) throws IllegalImageSizeException{
       if(compareImg.getWidth() != matchImg.getWidth() || compareImg.getHeight() != matchImg.getHeight() ){
           throw new IllegalImageSizeException();
       }
       BufferedImage matchResultImage = new BufferedImage(compareImg.getWidth(), compareImg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

       for(int x = 0; x < compareImg.getWidth(); x++){
           for(int y = 0; y < compareImg.getHeight(); y++){

               if(compareImg.getRGB(x, y) != matchImg.getRGB(x, y)){
                   if((matchImg.getRGB(x, y) & 0xffffff) == 0xffffff){
                       matchResultImage.setRGB(x, y, falseTrueColor);
                   }else if((matchImg.getRGB(x, y) & 0xffffff) == 0){
                       matchResultImage.setRGB(x, y, trueFalseColor);
                   }
               }else{
                   matchResultImage.setRGB(x, y, collectColor);
               }
           }
       }
       return matchResultImage;
    }

    public BufferedImage trim(BufferedImage objectImg, int x, int y, int width, int height){
        return objectImg.getSubimage(x, y, width, height);
    }

    public BufferedImage simpleZoom(BufferedImage objectImg, int zoomRate){
        BufferedImage zoomImg = new BufferedImage(objectImg.getWidth(), objectImg.getHeight(), objectImg.getType());

        for(int x = 0; x < objectImg.getWidth(); x++){
            for(int y = 0; y < objectImg.getHeight(); y++){
                for(int i = x * zoomRate; i < (x + 1) * zoomRate; i++){
                    for(int j = y * zoomRate; j < (y + 1) * zoomRate; j++){
                        zoomImg.setRGB(i, j, objectImg.getRGB(x, y));
                    }
                }
            }
        }
        return zoomImg;
    }
}
