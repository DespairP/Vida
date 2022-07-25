package teamHTBP.vida.client.hud.element;

import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.animation.TimeInterpolator;
import teamHTBP.vida.animation.animator.Animator;
import teamHTBP.vida.animation.valueholder.FloatPropertyValueHolder;
import teamHTBP.vida.client.hud.Hud;
import teamHTBP.vida.item.staff.IElementLevelTools;

/**
 * @author DustW
 */
public abstract class ElementToolsHud extends Hud {
    protected ItemStack last = ItemStack.EMPTY;

    Animator in;
    Animator out;

    @Override
    protected void init() {
        in = Animator.of(this, new FloatPropertyValueHolder<>(ALPHA, 0f, 100f))
                .interpolator(TimeInterpolator.ACCELERATE).durationTick(20);

        out = Animator.of(this, new FloatPropertyValueHolder<>(ALPHA, 100f, 0f))
                .interpolator(TimeInterpolator.DECELERATE).durationTick(20);
    }

    void animTick(boolean isIncrease) {
        if (isIncrease) {
            out.cancel();
            in.start();
        } else {
            in.cancel();
            out.start();
        }
    }

    /**
     * @return changed
     */
    void checkLastAndAnimTick() {
        ItemStack itemStack = player().getMainHandItem();

        boolean isIncrease = itemStack.getItem() instanceof IElementLevelTools;

        if (isIncrease && last != itemStack) {
            last = itemStack;
        }

        if (alpha / 100F <= 0) {
            last = ItemStack.EMPTY;
        }

        animTick(isIncrease);
    }
}
