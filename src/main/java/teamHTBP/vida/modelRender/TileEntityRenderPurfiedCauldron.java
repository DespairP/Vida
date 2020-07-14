package teamHTBP.vida.modelRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.client.model.data.EmptyModelData;
import org.lwjgl.opengl.GL11;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;

public class TileEntityRenderPurfiedCauldron extends TileEntityRenderer<TileEntityPurfiedCauldron> {

    private static final float MIN_HEIGHT = 0.5625f;
    private static final float MAX_HEIGHT = 0.78125f;


    public TileEntityRenderPurfiedCauldron(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityPurfiedCauldron tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0, 0.1f, 0);
        Fluid fluid = Fluids.WATER.getStillFluid();
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        ResourceLocation resourceLocation;
        int color;
        if(fluid.getAttributes().getStillTexture() != null) {
            resourceLocation = fluid.getAttributes().getStillTexture();
            color = getFluidColor(tileEntityIn.getWorld(), tileEntityIn.getPos(), fluid);
        } else if(fluid.getAttributes().getFlowingTexture() != null) {  // In case that Still Texture don't exist
            resourceLocation = fluid.getAttributes().getFlowingTexture();
            color = getFluidColor(tileEntityIn.getWorld(), tileEntityIn.getPos(), fluid);
        } else { // In case that no texture exist
            resourceLocation = Fluids.WATER.getAttributes().getStillTexture();
            color = getFluidColor(tileEntityIn.getWorld(), tileEntityIn.getPos(), Fluids.WATER);
        }

        TextureAtlasSprite textureAtlasSprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(resourceLocation);

        float uMin = textureAtlasSprite.getMinU();
        float uMax = textureAtlasSprite.getMaxU();
        float vMin = textureAtlasSprite.getMinV();
        float vMax = textureAtlasSprite.getMaxV();

        int light = 15728880;

        float a = 1.0F;
        float r = (color >> 16 & 0xFF) / 255.0F;
        float g = (color >> 8 & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;

        //红：13、254、202
        //蓝：173、214、12
        //绿：173、24、142
        //赫：173、214、142
        //黄金：22、65、142

        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();

        float y = MIN_HEIGHT + (2) * (MAX_HEIGHT - MIN_HEIGHT);

        matrixStackIn.translate(0, 0.5F, 0);

        IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getText(textureAtlasSprite.getAtlasTexture().getTextureLocation()));

        buffer.pos(matrix4f, 0, 0, 0).color(r, g, b, a).tex(uMin, vMin).normal(0, 1, 0).lightmap(light).endVertex();
        buffer.pos(matrix4f, 0, 0, 1).color(r, g, b, a).tex(uMin, vMax).normal(0, 1, 0).lightmap(light).endVertex();
        buffer.pos(matrix4f, 1, 0, 1).color(r, g, b, a).tex(uMax, vMax).normal(0, 1, 0).lightmap(light).endVertex();
        buffer.pos(matrix4f, 1, 0, 0).color(r, g, b, a).tex(uMax, vMin).normal(0, 1, 0).lightmap(light).endVertex();

        Minecraft.getInstance().getProfiler().endSection();
        matrixStackIn.pop();
    }

    private static int getFluidColor(World world, BlockPos pos, Fluid fluid) {
        if(fluid.isEquivalentTo(Fluids.WATER)) {
            return BiomeColors.getWaterColor(world, pos);
        }

        return fluid.getAttributes().getColor();
    }

}
