package teamHTBP.vida.client.event.listener.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import teamHTBP.vida.client.hud.BottleHUD;
import teamHTBP.vida.client.hud.ElementSwordHUD;
import teamHTBP.vida.input.KeyBoardBottle;
import teamHTBP.vida.item.armor.ItemArmorElementLegginsWithBottles;
import teamHTBP.vida.item.staff.ItemElementSword;
import teamHTBP.vida.utils.math.IntRange;

/**
 * 当玩家使用
 *
 * */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class HUDItemEventHandler extends HudHandler {
    /**瓶子的透明度*/
    public static IntRange alpha = new IntRange(0,100,0);
    /**剑元素HUD的透明度*/
    public static IntRange swordAlpha = new IntRange(0,100,0);
    /**瓶子渲染的透明度界限,如果低于界限就不渲染*/
    public final static int BOTTLE_ALPHA_MIN = 4;
    public static ItemStack itemStack = ItemStack.EMPTY;
    public static ItemStack itemStack1 = ItemStack.EMPTY;

    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        // 防止默认HUD覆盖
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null || Minecraft.getInstance().level == null) {
            return;
        }

        PoseStack matrixStack = event.getMatrixStack();
        Player player = Minecraft.getInstance().player;

        assert player != null;
        onElementBottleRender(matrixStack, player);
        onElementSwordRender(matrixStack,player);
    }


    /**渲染元素瓶HUD*/
    public static void onElementBottleRender(PoseStack matrixStack, Player player){
        // 获取装备栏
        ItemStack stack = player.getInventory().armor.get(1);

        // 当装备栏穿着元素瓶并按着Alt键时
        if (KeyBoardBottle.MESSAGE_KEY.isDown()
                && stack.getItem() instanceof ItemArmorElementLegginsWithBottles) {
            //增加alpha
            alpha.increase(3);
        } else {
            //减少alpha
            alpha.decrease(2);
        }

        // alpha没有到界限就不渲染
        if (alpha.get() < BOTTLE_ALPHA_MIN) return;

        BottleHUD hud = new BottleHUD(stack, alpha.get());
        setupShader();
        hud.render(matrixStack);

    }

    /**渲染元素剑HUD*/
    public static void onElementSwordRender(PoseStack matrixStack, Player player){
        if (player.getInventory().getSelected().getItem() instanceof ItemElementSword) {
            swordAlpha.increase(2);
            itemStack1 = player.getInventory().getSelected();
        } else {
            swordAlpha.decrease(2);
        }

        if (swordAlpha.get() > 0 && itemStack1 != ItemStack.EMPTY && !itemStack1.isEmpty()) {
            ElementSwordHUD hud = new ElementSwordHUD(itemStack1, swordAlpha.get());
            setupShader();
            hud.render(matrixStack);
        }
    }
}
