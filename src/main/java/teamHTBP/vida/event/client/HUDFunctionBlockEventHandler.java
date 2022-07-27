package teamHTBP.vida.event.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.common.TileEntity.TileEntityCollector;
import teamHTBP.vida.common.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.common.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.common.block.function.BlockCollecter;
import teamHTBP.vida.common.block.function.BlockElementCoreAltar;
import teamHTBP.vida.common.block.function.BlockPurfiedCauldron;
import teamHTBP.vida.client.gui.HUD.CollectorHUD;
import teamHTBP.vida.client.gui.HUD.ElementCoreAltarHUD;
import teamHTBP.vida.client.gui.HUD.PurfiedCauldronHUD;


/**
 * 当玩家鼠标悬浮到某个Vida特定的功能方块上
 * 显示HUD
 * 
 * 
 * */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class HUDFunctionBlockEventHandler {

    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null || Minecraft.getInstance().world == null) {
            return;
        }

        //获取玩家对准的方块
        MatrixStack matrixStack = event.getMatrixStack();
        PlayerEntity player = Minecraft.getInstance().player;
        /*
            RayTraceResult objectMouseOver = Minecraft.getInstance().objectMouseOver;
            BlockPos pos = new BlockPos(objectMouseOver.getHitVec());
         */
        Block block = getBlockPlayerLookAt(player);
        BlockPos lookingPos = getLookingPos(player);

        //渲染纯净坩埚HUD
        if (block instanceof BlockPurfiedCauldron) {
            TileEntityPurfiedCauldron tileEntityPurfiedCauldron = (TileEntityPurfiedCauldron) player.world.getTileEntity(lookingPos);
            if (tileEntityPurfiedCauldron != null) {
                PurfiedCauldronHUD purfiedCauldronHUD = new PurfiedCauldronHUD(tileEntityPurfiedCauldron);
                purfiedCauldronHUD.render(event.getMatrixStack());
            }
            return;
        }

        //渲染元素祭坛HUD
        if (block instanceof BlockElementCoreAltar) {
            TileEntityElementCoreAltar tileEntityElementCoreAltar = (TileEntityElementCoreAltar) player.world.getTileEntity(lookingPos);
            ElementCoreAltarHUD elementCoreAltarHUD = new ElementCoreAltarHUD(tileEntityElementCoreAltar);
            elementCoreAltarHUD.render(matrixStack);
            return;
        }

        //渲染收集器HUD
        if (block instanceof BlockCollecter) {
            TileEntityCollector tileEntityCollector = (TileEntityCollector) player.world.getTileEntity(lookingPos);
            CollectorHUD collectorHUD = new CollectorHUD(tileEntityCollector);
            collectorHUD.render(matrixStack);
            return;
        }
    }


    @OnlyIn(Dist.CLIENT)
    public static Block getBlockPlayerLookAt(PlayerEntity player){
        RayTraceResult block =  player.pick(20.0D, 0.0F, false);
        if(block.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockRayTraceResult)block).getPos();
            BlockState blockstate = player.world.getBlockState(blockpos);
            return blockstate.getBlock();
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public static BlockPos getLookingPos(PlayerEntity player){
        RayTraceResult block =  player.pick(20.0D, 0.0F, false);
        if(block.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockRayTraceResult) block).getPos();
            return blockpos;
        }
        return new BlockPos(block.getHitVec());
    }
}
