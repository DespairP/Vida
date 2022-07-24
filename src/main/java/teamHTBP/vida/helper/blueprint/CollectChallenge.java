package teamHTBP.vida.helper.blueprint;

import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class CollectChallenge implements IChallenge{
    /**达成目标所需要的材料*/
    private List<ItemStack> goal = Collections.emptyList();

    @Override
    public boolean isComplete() {
        //?
        return false;
    }

    @Override
    public void startChallenge() {

    }

    @Override
    public void endChallenge() {

    }
}
