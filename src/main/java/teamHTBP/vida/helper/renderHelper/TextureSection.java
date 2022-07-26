package teamHTBP.vida.helper.renderHelper;

public class TextureSection {
    private int minU;
    private int minV;
    private int width;
    private int height;

    public TextureSection(int minU, int minV, int width, int height) {
        this.minU = minU;
        this.minV = minV;
        this.width = width;
        this.height = height;
    }

    /**开始的U*/
    public int mu() {
        return minU;
    }

    /**开始的V*/
    public int mv() {
        return minV;
    }

    /**长度*/
    public int w(){
        return width;
    }

    /**宽度*/
    public int h(){
        return height;
    }

    /**获取中央位置*/
    public int cw(){
        return width / 2;
    }

    /**获取中央位置*/
    public int ch(){
        return height / 2;
    }
}
