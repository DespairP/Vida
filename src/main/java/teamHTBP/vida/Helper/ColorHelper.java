package teamHTBP.vida.Helper;

public class ColorHelper {
    public int r;
    public int g;
    public int b;
    public static final ColorHelper DARK_BLUE = new ColorHelper(35,202,249);
    public static final ColorHelper DARK_RED = new ColorHelper(255,71,71);
    public static final ColorHelper DARK_BROWN = new ColorHelper(249,212,0);
    public static final ColorHelper CYAN_GREEN = new ColorHelper(35, 202, 249);
    public static final ColorHelper PURPLR = new ColorHelper(247, 81,236);
    public static final ColorHelper LIGHT_BROWN = new ColorHelper(255,200,145);



    public ColorHelper(int r, int g, int b) {
        this.r = r % 255;
        this.g = g % 255;
        this.b = b % 255;
    }


}
