package teamHTBP.vida.Block;

import net.minecraft.block.Block;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import teamHTBP.vida.Block.function.BlockCollecter;
import teamHTBP.vida.Block.function.BlockElementCoreAltar;
import teamHTBP.vida.Block.function.BlockPurfiedCauldron;
import teamHTBP.vida.Input.KeyBoardBottle;
import teamHTBP.vida.Input.KeyBoardLoader;
import teamHTBP.vida.Item.armor.ItemArmorElementLegginsWithBottles;
import teamHTBP.vida.Item.staff.ItemElementPickaxe;
import teamHTBP.vida.Item.staff.ItemElementSword;
import teamHTBP.vida.TileEntity.TileEntityCollector;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.gui.HUD.*;


@Mod.EventBusSubscriber(Dist.CLIENT)
public class BlockEventLoader {
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
        Vec3d vec3d2 = vec3d.add(vec3d1.x * d0 , vec3d1.y * d0, vec3d1.z * d0 );
        BlockPos newpos = new BlockPos(vec3d2);
        block = player.world.getBlockState(newpos).getBlock();
        if(block instanceof BlockElementCoreAltar){
            TileEntityElementCoreAltar tileEntityElementCoreAltar  = (TileEntityElementCoreAltar)player.world.getTileEntity(newpos);
            ElementCoreAltarHUD elementCoreAltarHUD = new ElementCoreAltarHUD(tileEntityElementCoreAltar);
            elementCoreAltarHUD.render();
        }
        if(block instanceof BlockCollecter){
            TileEntityCollector tileEntityCollector = (TileEntityCollector)player.world.getTileEntity(newpos);
            CollectorHUD collectorHUD = new CollectorHUD(tileEntityCollector);
            collectorHUD.render();
        }

        //others
        if(KeyBoardBottle.MESSAGE_KEY.isKeyDown()){
            if(KeyBoardBottle.alpha < 100)  KeyBoardBottle.alpha += 3;
        }else{
           if(KeyBoardBottle.alpha >= 0){ KeyBoardBottle.alpha -= 2;}
        }
        if(KeyBoardBottle.alpha >= 4){
            ItemStack stack = player.inventory.armorInventory.get(1);
            if(stack.getItem() instanceof ItemArmorElementLegginsWithBottles){
            BottleHUD hud = new BottleHUD(stack, KeyBoardBottle.alpha);
            hud.render();
        }
        }
        if(player.inventory.getCurrentItem().getItem() instanceof ItemElementPickaxe){
            if(alpha < 100)alpha += 2;
            itemStack = player.inventory.getCurrentItem();
        }
        else{
            if(alpha > 0)alpha-=2;
        }

        if(alpha > 0 && itemStack != ItemStack.EMPTY && !itemStack.isEmpty()){
            //ItemStack stack = player.inventory.getCurrentItem();
            ElementPickaxeHUD hud = new ElementPickaxeHUD(itemStack, alpha);
            hud.render();
        }

        if(alpha == 0){
            itemStack = ItemStack.EMPTY;
        }

        if(player.inventory.getCurrentItem().getItem() instanceof ItemElementSword){
            if(sword_alpha < 100)sword_alpha += 2;
            itemStack1 = player.inventory.getCurrentItem();
        }
        else{
            if(sword_alpha > 0)sword_alpha-=2;
        }

        if(sword_alpha > 0 && itemStack1 != ItemStack.EMPTY && !itemStack1.isEmpty()){
            //ItemStack stack = player.inventory.getCurrentItem();
            ElementSwordHUD hud = new ElementSwordHUD(itemStack1, sword_alpha);
            hud.render();
        }


    }



}
