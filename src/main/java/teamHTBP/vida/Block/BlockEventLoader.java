package teamHTBP.vida.Block;

import net.minecraft.block.Block;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import teamHTBP.vida.Block.function.BlockElementCoreAltar;
import teamHTBP.vida.Block.function.BlockPurfiedCauldron;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.gui.HUD.ElementCoreAltarHUD;
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
        if( Minecraft.getInstance().world == null){
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
            return;
        }
        Vec3d vec3d = player.getLook(event.getPartialTicks());
        Vec3d vec3d1 = objectMouseOver.getHitVec();
        float d0 = 1.0f;
        Vec3d vec3d2 = vec3d.add(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
        BlockPos newpos = new BlockPos(vec3d2);
        block = player.world.getBlockState(newpos).getBlock();
        if(block instanceof BlockElementCoreAltar){
            TileEntityElementCoreAltar tileEntityElementCoreAltar  = (TileEntityElementCoreAltar)player.world.getTileEntity(newpos);
            ElementCoreAltarHUD elementCoreAltarHUD = new ElementCoreAltarHUD(tileEntityElementCoreAltar);
            elementCoreAltarHUD.render();
        }
    }
}
