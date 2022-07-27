package teamHTBP.vida.helper.client.render;

public record TextureSection(int minU, int minV, int width, int height) {
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
