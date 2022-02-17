package teamHTBP.vida.helper.util;

import java.awt.*;

public class RGBAColor {
    public static int getColorCodeFromRGBA(int r,int g,int b,int a){
        return ((a << 24) | 0xFF) + ((r << 16) | 0xFF) + ((g << 8) | 0xFF) + (b | 0xFF);
    }
}
