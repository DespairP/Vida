package teamHTBP.vida.client.event.listener.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import teamHTBP.vida.client.hud.BottleHud;
import teamHTBP.vida.input.BottleKeys;
import teamHTBP.vida.item.armor.ItemArmorElementLegginsWithBottles;
import teamHTBP.vida.utils.math.IntRange;

/**
 * 当玩家使用
 *
 * */
@OnlyIn(Dist.CLIENT)
//@Mod.EventBusSubscriber(Dist.CLIENT)
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
    }


    /**渲染元素瓶HUD*/
    public static void onElementBottleRender(PoseStack matrixStack, Player player){
        // 获取装备栏
        ItemStack stack = player.getInventory().armor.get(1);

        // 当装备栏穿着元素瓶并按着Alt键时
        if (BottleKeys.MESSAGE_KEY.isDown()
                && stack.getItem() instanceof ItemArmorElementLegginsWithBottles) {
            //增加alpha
            alpha.increase(3);
        } else {
            //减少alpha
            alpha.decrease(2);
        }

        // alpha没有到界限就不渲染
        if (alpha.get() < BOTTLE_ALPHA_MIN) return;

        BottleHud hud = new BottleHud(stack, alpha.get());
        setupShader();
        hud.render(matrixStack);

    }
}
