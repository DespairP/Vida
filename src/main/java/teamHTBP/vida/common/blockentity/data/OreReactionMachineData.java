package teamHTBP.vida.common.blockentity.data;


import net.minecraft.world.inventory.ContainerData;

public class OreReactionMachineData implements ContainerData {
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
    public int getCount() {
        return 2;
    }
}
