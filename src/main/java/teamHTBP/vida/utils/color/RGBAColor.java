package teamHTBP.vida.utils.color;

/**
 * 一个immutable的RGBA颜色实例
 * 基于RGB实现,在minecraft中加上alpha通道就是#{alpha}{red}{green}{blue}的格式
 * */
public class RGBAColor {
    public  final static RGBAColor BLACK = RGBAColor.fromRGBA(0,0,0,255);
    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;
    public RGBAColor(int red, int green, int blue, int alpha) {
        this.red = red % 256;
        this.green = green % 256;
        this.blue = blue % 256;
        this.alpha = alpha % 256;
    }

    public RGBAColor() {
        this(0,0,0,0);
    }

    /**
     * 将RGBA转为8位码
     * #{alpha}{red}{green}{blue}
     *
     * @param r 红
     * @param g 绿
     * @param b 蓝
     * @param a 透明度
     * @return 18位码
     * */
    public static int getColorCodeFromRGBA(int r,int g,int b,int a) {
        return (a << 24) | (r << 16 ) | (g << 8) | b;
    }

    public int toHex() {
        return (alpha << 24) | (red << 16 ) | (green << 8) | blue;
    }


    public static RGBAColor fromRGBA(int r,int g,int b,int a){
        return new RGBAColor(r,g,b,a);
    }

    /**
     * 十六进制RBG转RGBAColor，透明度默认255
     * */
    public static RGBAColor fromHexRGB(int hexcode){
        return new RGBAColor((hexcode & 0xFF0000) >> 16, (hexcode & 0xFF00) >> 8, (hexcode & 0xFF),255);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

    @Override
    public String toString() {
        return "RGBAColor{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", alpha=" + alpha +
                '}';
    }
}
