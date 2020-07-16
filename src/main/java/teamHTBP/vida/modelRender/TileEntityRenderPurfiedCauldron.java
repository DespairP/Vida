package teamHTBP.vida.modelRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
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
    private float floating = 0;

    private  float rPlus = 0;
    private  float gPlus = 0;
    private  float bPlus = 0;



    public TileEntityRenderPurfiedCauldron(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityPurfiedCauldron tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if(tileEntityIn.isWater) {
            matrixStackIn.push();
            float level = tileEntityIn.container/85714.2f;
            Fluid fluid = Fluids.WATER.getStillFluid();
            BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
            ResourceLocation resourceLocation;
            int color;
            if (fluid.getAttributes().getStillTexture() != null) {
                resourceLocation = fluid.getAttributes().getStillTexture();
                color = getFluidColor(tileEntityIn.getWorld(), tileEntityIn.getPos(), fluid);
            } else if (fluid.getAttributes().getFlowingTexture() != null) {  // In case that Still Texture don't exist
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


            float a = 0.95F;
            float r = (color >> 16 & 0xFF) / 255.0F;
            float g = (color >> 8 & 0xFF) / 255.0F;
            float b = (color & 0xFF) / 255.0F;


            if(changeColor(tileEntityIn, r*255, g*255, b*255)) {
            r=rPlus/ 255.0F;
            g=gPlus/ 255.0f;
            b=bPlus/255.0f;
            }else{
                rPlus = 63;
                gPlus = 118;
                bPlus = 228;
            }




            Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();

            float y = MIN_HEIGHT + (2) * (MAX_HEIGHT - MIN_HEIGHT);

            matrixStackIn.translate(0, 0.5F + level, 0);

            IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getText(textureAtlasSprite.getAtlasTexture().getTextureLocation()));

            buffer.pos(matrix4f, 0, 0, 0).color(r, g, b, a).tex(uMin, vMin).normal(0, 1, 0).lightmap(light).endVertex();
            buffer.pos(matrix4f, 0, 0, 1).color(r, g, b, a).tex(uMin, vMax).normal(0, 1, 0).lightmap(light).endVertex();
            buffer.pos(matrix4f, 1, 0, 1).color(r, g, b, a).tex(uMax, vMax).normal(0, 1, 0).lightmap(light).endVertex();
            buffer.pos(matrix4f, 1, 0, 0).color(r, g, b, a).tex(uMax, vMin).normal(0, 1, 0).lightmap(light).endVertex();

            Minecraft.getInstance().getProfiler().endSection();
            matrixStackIn.pop();


        }

        if(!tileEntityIn.meltItem.isEmpty()) {
            matrixStackIn.push();
            double floatingLevel = 0.1 * Math.sin(floating);
            matrixStackIn.translate(0.5f, 1.3f + floatingLevel, 0.55f);
            matrixStackIn.scale(0.6f, 0.6f, 0.6f);

            matrixStackIn.rotate(new Quaternion(32 , 0, 0, true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(tileEntityIn.meltItem, tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(tileEntityIn.meltItem, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);

            if(floating >= 2*Math.PI){
                floating = 0;
            }else {
                floating += 0.01;
            }
            matrixStackIn.pop();
        }

        if(tileEntityIn.element == 0){
            rPlus = 0;
            gPlus = 0;
            bPlus = 0;
        }
    }

    private static int getFluidColor(World world, BlockPos pos, Fluid fluid) {
        if(fluid.isEquivalentTo(Fluids.WATER)) {
            return BiomeColors.getWaterColor(world, pos);
        }

        return fluid.getAttributes().getColor();
    }

    private boolean changeColor(TileEntityPurfiedCauldron tileEntityIn,float r,float g,float b){
        int element = tileEntityIn.element;
        int container = tileEntityIn.container < 5000?tileEntityIn.container:5000;
        float level = container / 5000.0f;
        switch (element){
            case 1:
                    rPlus = r + (255-r) * level;
                    gPlus = g + (243-g) * level;
                    bPlus = b + (109-b) * level;
                return true;
            case 2:
                   rPlus = r + (0-r) * level;
                   gPlus = g + (214-g) * level;
                   bPlus = b + (24-b) * level;
                 return true;
            case 3:
                rPlus = r + (0-r) * level;
                gPlus = g + (237-g) * level;
                bPlus = b + (255-b) * level;
                return true;
            case 4:
                rPlus = r + (255-r) * level;
                gPlus = g + (0-g) * level;
                bPlus = b + (8-b) * level;
                return true;
            case 5:
                rPlus = r + (153-r) * level;
                gPlus = g + (143-g) * level;
                bPlus = b + (79 -b) * level;
                return  true;
            default:
                return false;
        }
    }

}
