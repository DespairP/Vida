package teamHTBP.vida.block.blockHUDRenderEvent;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.block.function.blockTools.BlockElementCrystal;
import teamHTBP.vida.TileEntity.IElementCrystal;
import teamHTBP.vida.gui.HUD.ElementCrystalHUD;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class BlockEnergyCrystalHUDEventLoader {

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
        if (Minecraft.getInstance().world == null) {
            return;
        }
        offset_frame = (offset_frame + 1) % 64;
        PlayerEntity player = Minecraft.getInstance().player;
        RayTraceResult objectMouseOver = Minecraft.getInstance().objectMouseOver;
        BlockPos pos = new BlockPos(objectMouseOver.getHitVec());

        Vec3d vec3d = player.getLook(event.getPartialTicks());
        Vec3d vec3d1 = objectMouseOver.getHitVec();

        BlockPos newpos = new BlockPos(vec3d1);
        Block block = player.world.getBlockState(newpos).getBlock();
        World world = player.world;

        if(block instanceof BlockElementCrystal){
            TileEntity tileEntityCrystal = world.getTileEntity(pos);
            if(tileEntityCrystal instanceof IElementCrystal){
                ElementCrystalHUD hud = new ElementCrystalHUD((IElementCrystal)tileEntityCrystal,element_fragment_tick,element_Progress);
                hud.render();
                if(offset_frame % 8 == 0){
                       final int MAX_PROGRESS = (int)(15.0f * ((IElementCrystal) tileEntityCrystal).getEnergyStored() / ((IElementCrystal) tileEntityCrystal).getMaxEnergy());
                       if(element_Progress < MAX_PROGRESS) element_Progress += 1;
                       else if(element_Progress > MAX_PROGRESS) element_Progress = 0;
                }
            }
            if(offset_frame % 16 == 0){
            element_fragment_tick += 1;
            element_fragment_tick %= 32;
            }
        }else{
            element_fragment_tick = 0;
            element_Progress = 0;
        }

    }
}
