package libs.img;

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
}
