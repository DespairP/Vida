package teamHTBP.vida.helper.box;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraftforge.client.model.ItemMultiLayerBakedModel;
import net.minecraftforge.common.util.Lazy;
import teamHTBP.vida.client.VidaRenderManager;
import teamHTBP.vida.client.VidaRenderTypes;
import teamHTBP.vida.client.model.LayerRegistryHandler;
import teamHTBP.vida.client.model.OutlineModel;
import teamHTBP.vida.helper.color.ARGBColor;

/**
 * @author DustW
 */
@NoArgsConstructor
public class BoxRender {
    private static final Lazy<OutlineModel> OUTLINE = Lazy.of(() -> LayerRegistryHandler.create(OutlineModel.class));

    public static void render(InteractBox behaviour, PoseStack ps) {
        ps.pushPose();
        RenderSystem.disableDepthTest();

        BoxTransform.Client.transform(behaviour.getTransform(), ps);

        var source = VidaRenderManager.source();
        var buffer = source.getBuffer(VidaRenderTypes.getOutlineSolid());

        ARGBColor color;

        if (behaviour.testHit(Minecraft.getInstance().player, true)) {
            color = ARGBColor.DARK_RED;
        }
        else {
            color = ARGBColor.LIGHT_BROWN;
        }

        OUTLINE.get().renderToBuffer(ps, buffer, LightTexture.FULL_BRIGHT,
                OverlayTexture.NO_OVERLAY, color.getR(), color.getG(), color.getB(), 1);

        RenderSystem.enableDepthTest();
        ps.popPose();
    }

    public static void renderWithItem(InteractBox behaviour, PoseStack ps, ItemStack stack) {
        render(behaviour, ps);

        if (stack.isEmpty()) {
            return;
        }

        ps.pushPose();

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        BakedModel modelWithOverrides = itemRenderer.getModel(stack, null, null, 0);

        boolean blockItem = modelWithOverrides.isGui3d() && !(modelWithOverrides instanceof ItemMultiLayerBakedModel);
        float scale = ((!blockItem ? .5f : 1f) - 1 / 64f) * 1.7F;
        float zOffset = (!blockItem ? .225F : -.4F) + customZOffset(stack.getItem());

        BoxTransform.Client.transform(behaviour.getTransform(), ps);
        ps.scale(scale, scale, scale);
        ps.translate(0, zOffset + 1, 0);

        itemRenderer.renderStatic(stack, ItemTransforms.TransformType.FIXED, LightTexture.FULL_BRIGHT,
                OverlayTexture.NO_OVERLAY, ps, VidaRenderManager.source(), 0);

        ps.popPose();
    }

    private static float customZOffset(Item item) {
        float nudge = -.1f;

        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();

            if (block instanceof FenceBlock)
                return nudge;
            if (block.builtInRegistryHolder().is(BlockTags.BUTTONS))
                return nudge;
            if (block == Blocks.END_ROD)
                return nudge;
        }
        return 0;
    }
}
