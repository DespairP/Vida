package teamHTBP.vida.Block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import teamHTBP.vida.Block.function.BlockPurfiedCauldron;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.gui.HUD.PurfiedCauldronHUD;

@Mod.EventBusSubscriber
public class BlockEventLoader {

    //锅的HUD
    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null ) {
            return;
        }
        PlayerEntity player = Minecraft.getInstance().player;
        RayTraceResult objectMouseOver = Minecraft.getInstance().objectMouseOver;
        BlockPos pos = new BlockPos(objectMouseOver.getHitVec());
        Block block = player.world.getBlockState(pos).getBlock();
        if(block instanceof BlockPurfiedCauldron){
            TileEntityPurfiedCauldron tileEntityPurfiedCauldron = (TileEntityPurfiedCauldron)player.world.getTileEntity(pos);
            PurfiedCauldronHUD purfiedCauldronHUD = new PurfiedCauldronHUD(tileEntityPurfiedCauldron);
            purfiedCauldronHUD.render();
        }
    }
}
