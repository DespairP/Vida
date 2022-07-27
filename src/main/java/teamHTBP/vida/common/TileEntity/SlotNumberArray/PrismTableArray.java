package teamHTBP.vida.common.TileEntity.SlotNumberArray;

import net.minecraft.util.IIntArray;

public class PrismTableArray implements IIntArray {
    //火焰的x，y位置
    private int fireposX;
    private int firepoxY;
    //收到的镜子的x，y位置
    private int mirrorX;
    private int mirrorY;


    /*public  PrismTableArray(){

    }*/
    @Override
    public int get(int index) {
        switch (index) {
            case 0:
                return this.fireposX;
            case 1:
                return this.firepoxY;
            case 2:
                return this.mirrorX;
            case 3:
                return this.mirrorY;
            default:
                return 0;
        }
    }

    @Override
    public void set(int index, int value) {
        switch (index) {
            case 0:
                this.fireposX = value;
                break;
            case 1:
                this.firepoxY = value;
                break;

            case 2:
                this.mirrorX = value;
                break;
            case 3:
                this.mirrorY = value;
                break;
            default:
                return;
        }

    }

    @Override
    public int size() {
        return 4;
    }
}
