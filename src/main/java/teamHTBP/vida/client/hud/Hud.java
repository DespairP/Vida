package teamHTBP.vida.client.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.animation.property.Property;

/**
 * @author DustW
 */
public abstract class Hud implements IIngameOverlay {

    protected final Minecraft mc = Minecraft.getInstance();

    private boolean init;

    protected float alpha;

    protected static final Property<Hud, Float> ALPHA = new Property<>() {
        @Override
        public void set(@NotNull Hud object, Float value) {
            object.alpha = value;
        }

        @Override
        public Float get(@NotNull Hud object) {
            return object.alpha;
        }
    };

    @Override
    public final void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        if (!init) {
            init();
            init = true;
        }

        Minecraft mc = Minecraft.getInstance();

        if (!mc.options.hideGui) {
            renderInner(gui, poseStack, partialTick, width, height);
        }
    }

    @NotNull
    protected LocalPlayer player() {
        return mc.player;
    }

    protected abstract void renderInner(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height);

    protected abstract void init();
}
