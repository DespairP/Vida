package teamHTBP.vida.event.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.block.function.crystal.BlockElementCrystal;
import teamHTBP.vida.blockentity.crystal.IElementCrystal;
import teamHTBP.vida.gui.HUD.ElementCrystalHUD;

/**
 * 用于渲染元素武器与工具HUD的事件侦听
 *
 * @author DespairP
 * */
@Mod.EventBusSubscriber(Dist.CLIENT)
public class BlockEnergyCrystalHUDEventLoader extends HudHandler {

    //元素的张开的效果
    private static int element_Progress = 0;
    //元素花纹的帧图
    private static int element_fragment_tick = 0;
    //
    private static int offset_frame = 0;


    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null) {
            return;
        }
        if (Minecraft.getInstance().level == null) {
            return;
        }
        offset_frame = (offset_frame + 1) % 64;
        Player player = Minecraft.getInstance().player;
        HitResult objectMouseOver = Minecraft.getInstance().hitResult;
        BlockPos pos = new BlockPos(objectMouseOver.getLocation());

        Vec3 vec3d = player.getViewVector(event.getPartialTicks());
        Vec3 vec3d1 = objectMouseOver.getLocation();

        BlockPos newpos = new BlockPos(vec3d1);
        Block block = player.level.getBlockState(newpos).getBlock();
        Level world = player.level;

        if (block instanceof BlockElementCrystal) {
            BlockEntity tileEntityCrystal = world.getBlockEntity(pos);
            if (tileEntityCrystal instanceof IElementCrystal) {
                ElementCrystalHUD hud = new ElementCrystalHUD((IElementCrystal) tileEntityCrystal, element_fragment_tick, element_Progress);
                setupShader();
                hud.render(event.getMatrixStack());
                if (offset_frame % 8 == 0) {
                    final int MAX_PROGRESS = (int) (15.0f * ((IElementCrystal) tileEntityCrystal).getEnergyStored() / ((IElementCrystal) tileEntityCrystal).getMaxEnergy());
                    if (element_Progress < MAX_PROGRESS) {
                        element_Progress += 1;
                    } else if (element_Progress > MAX_PROGRESS) {
                        element_Progress = 0;
                    }
                }
            }
            if (offset_frame % 16 == 0) {
                element_fragment_tick += 1;
                element_fragment_tick %= 32;
            }
        } else {
            element_fragment_tick = 0;
            element_Progress = 0;
        }

    }
}
