package teamHTBP.vida.common.TileEntity.SlotNumberArray;

import net.minecraft.util.IIntArray;

public class OreReactionMachineArray implements IIntArray {
    private int burnTime = 0;
    private int cookTime = 0;

    @Override
    public int get(int index) {
        switch (index) {
            case 0:
                return this.burnTime;
            case 1:
                return this.cookTime;
        }
        return 0;
    }

    @Override
    public void set(int index, int value) {
        switch (index) {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.cookTime = value;
                break;
        }
    }

    @Override
    public int size() {
        return 2;
    }
}
