package libs.img;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImageProccesingsTest {

    @Test
    public void testrgbTogray(){
        ImageProcessings proc = new ImageProcessings();
        assertEquals(6 , proc.rgbTogray(0x050309, ImageProcessings.CONV_GRAY_MEAN));
        assertEquals(4 , proc.rgbTogray(0x050309, ImageProcessings.CONV_GRAY_NTSC_MEAN));
    }
}
