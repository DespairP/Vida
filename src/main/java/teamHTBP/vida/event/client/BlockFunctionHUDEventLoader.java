package teamHTBP.vida.event.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.TileEntity.TileEntityCollector;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.block.function.BlockCollecter;
import teamHTBP.vida.block.function.BlockElementCoreAltar;
import teamHTBP.vida.block.function.BlockPurfiedCauldron;
import teamHTBP.vida.gui.HUD.*;
import teamHTBP.vida.input.KeyBoardBottle;
import teamHTBP.vida.item.armor.ItemArmorElementLegginsWithBottles;
import teamHTBP.vida.item.staff.ItemElementPickaxe;
import teamHTBP.vida.item.staff.ItemElementSword;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class BlockFunctionHUDEventLoader {
    public static int alpha = 0;
    public static int sword_alpha = 0;
    public static ItemStack itemStack = ItemStack.EMPTY;
    public static ItemStack itemStack1 = ItemStack.EMPTY;

    //锅的HUD
    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null || Minecraft.getInstance().world == null) {
            return;
        }

        MatrixStack matrixStack = event.getMatrixStack();
        PlayerEntity player = Minecraft.getInstance().player;

        /*
            RayTraceResult objectMouseOver = Minecraft.getInstance().objectMouseOver;
            BlockPos pos = new BlockPos(objectMouseOver.getHitVec());
         */

        Block block = getBlockPlayerLookAt(player);
        BlockPos lookingPos = getLookingPos(player);

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

        //others
        //渲染瓶子HUD
        if (KeyBoardBottle.MESSAGE_KEY.isKeyDown()) {
            if (KeyBoardBottle.alpha < 100) KeyBoardBottle.alpha += 3;
        } else {
            if (KeyBoardBottle.alpha >= 0) {
                KeyBoardBottle.alpha -= 2;
            }
        }
        if (KeyBoardBottle.alpha >= 4) {
            ItemStack stack = player.inventory.armorInventory.get(1);
            if (stack.getItem() instanceof ItemArmorElementLegginsWithBottles) {
                BottleHUD hud = new BottleHUD(stack, KeyBoardBottle.alpha);
                hud.render(matrixStack);
            }
        }

        //渲染元素剑HUD
        if (player.inventory.getCurrentItem().getItem() instanceof ItemElementSword) {
            if (sword_alpha < 100) sword_alpha += 2;
            itemStack1 = player.inventory.getCurrentItem();
        } else {
            if (sword_alpha > 0) sword_alpha -= 2;
        }

        if (sword_alpha > 0 && itemStack1 != ItemStack.EMPTY && !itemStack1.isEmpty()) {
            //ItemStack stack = player.inventory.getCurrentItem();
            ElementSwordHUD hud = new ElementSwordHUD(itemStack1, sword_alpha);
            hud.render(matrixStack);
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
