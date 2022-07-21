package teamHTBP.vida.client.event.listener.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.block.function.BlockCollecter;
import teamHTBP.vida.block.function.BlockElementCoreAltar;
import teamHTBP.vida.block.function.BlockPurfiedCauldron;
import teamHTBP.vida.blockentity.TileEntityCollector;
import teamHTBP.vida.blockentity.TileEntityElementCoreAltar;
import teamHTBP.vida.blockentity.TileEntityPurfiedCauldron;
import teamHTBP.vida.client.hud.CollectorHUD;
import teamHTBP.vida.client.hud.ElementCoreAltarHUD;
import teamHTBP.vida.client.hud.PurfiedCauldronHUD;


/**
 * 当玩家鼠标悬浮到某个Vida特定的功能方块上
 * 显示HUD
 * 
 * 
 * */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class HUDFunctionBlockEventHandler extends HudHandler {

    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null || Minecraft.getInstance().level == null) {
            return;
        }

        //获取玩家对准的方块
        PoseStack matrixStack = event.getMatrixStack();
        Player player = Minecraft.getInstance().player;
        /*
            RayTraceResult objectMouseOver = Minecraft.getInstance().objectMouseOver;
            BlockPos pos = new BlockPos(objectMouseOver.getHitVec());
         */
        Block block = getBlockPlayerLookAt(player);
        BlockPos lookingPos = getLookingPos(player);

        //渲染纯净坩埚HUD
        if (block instanceof BlockPurfiedCauldron) {
            TileEntityPurfiedCauldron tileEntityPurfiedCauldron = (TileEntityPurfiedCauldron) player.level.getBlockEntity(lookingPos);
            if (tileEntityPurfiedCauldron != null) {
                PurfiedCauldronHUD purfiedCauldronHUD = new PurfiedCauldronHUD(tileEntityPurfiedCauldron);
                setupShader();
                purfiedCauldronHUD.render(event.getMatrixStack());
            }
            return;
        }

        //渲染元素祭坛HUD
        if (block instanceof BlockElementCoreAltar) {
            TileEntityElementCoreAltar tileEntityElementCoreAltar = (TileEntityElementCoreAltar) player.level.getBlockEntity(lookingPos);
            ElementCoreAltarHUD elementCoreAltarHUD = new ElementCoreAltarHUD(tileEntityElementCoreAltar);
            setupShader();
            elementCoreAltarHUD.render(matrixStack);
            return;
        }

        //渲染收集器HUD
        if (block instanceof BlockCollecter) {
            TileEntityCollector tileEntityCollector = (TileEntityCollector) player.level.getBlockEntity(lookingPos);
            CollectorHUD collectorHUD = new CollectorHUD(tileEntityCollector);
            setupShader();
            collectorHUD.render(matrixStack);
            return;
        }
    }


    @OnlyIn(Dist.CLIENT)
    public static Block getBlockPlayerLookAt(Player player){
        HitResult block =  player.pick(20.0D, 0.0F, false);
        if(block.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)block).getBlockPos();
            BlockState blockstate = player.level.getBlockState(blockpos);
            return blockstate.getBlock();
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public static BlockPos getLookingPos(Player player){
        HitResult block =  player.pick(20.0D, 0.0F, false);
        if(block.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult) block).getBlockPos();
            return blockpos;
        }
        return new BlockPos(block.getLocation());
    }
}
