package teamHTBP.vida.utils.color;

public class HSVColor {
    /**色调*/
    private Integer hue;
    /**饱和度*/
    private Integer saturation;
    /**明度*/
    private Integer value;

    public HSVColor(Integer hue, Integer saturation, Integer value) {
        this.hue = hue % 361;
        this.saturation = saturation % 101;
        this.value = value % 101;
    }

    public HSVColor(){}

    public static HSVColor fromRGB(int r,int g,int b){
        return null; //TODO
    }

    public static HSVColor fromRGBAColor(RGBAColor rgbaColor){
        return null; //TODO
    }

    public static HSVColor fromHSV(int h,int s,int v){
        return new HSVColor(h,s,v);
    }

    public RGBAColor toRGBAColor(){
        return null; //TODO
    }

    public HSVColor rotate(int degree){
        return new HSVColor(hue + degree,saturation,value);
    }

}
