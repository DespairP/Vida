package teamHTBP.vida.modelRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.TileEntity.TileEntityGemShower;

public class TileEntityRenderGemShower extends TileEntityRenderer<TileEntityGemShower> {
    float r=1,g=1,b=1,a=1;
    double height = 0.0f;

    public TileEntityRenderGemShower(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityGemShower tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
          if(!tileEntityIn.gemItem.isEmpty()){
              TextureAtlasSprite textureAtlasSprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(this.getGemResource(tileEntityIn.gemItem));
              double floatHeight = Math.sin(height) * 0.1;
              this.height += 0.01;
              if(height >= Math.PI * 2) this.height = 0.0f;

              float uMin = textureAtlasSprite.getMinU();
              float uMax = textureAtlasSprite.getMaxU();
              float vMin = textureAtlasSprite.getMinV();
              float vMax = textureAtlasSprite.getMaxV();

              IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getCutout());

              matrixStackIn.push();
              matrixStackIn.translate(.5F, 1.06F, .5F);
              matrixStackIn.scale(0.1f, 0.1f, 0.1f);
              matrixStackIn.rotate(this.renderDispatcher.renderInfo.getRotation());
              matrixStackIn.translate(-.5F, -0.9F, -.5F);
              Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
              //float f7 = uMin,f8 = uMax,f5 = vMin,f6 = vMax;

              int light = this.getBrightnessForRender(tileEntityIn, partialTicks);
              buffer.pos(matrix4f, 0, 1,   1).color(r, g, b, a).tex(uMin, vMin).lightmap(120,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(120,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 1).color(r, g, b, a).tex(uMax, vMax).lightmap(120,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 1, 0).color(r, g, b, a).tex(uMax, vMin).lightmap(120,240).normal(0, 1, 0).endVertex();

              buffer.pos(matrix4f, 0, 0, 0).color(r, g, b, a).tex(uMin, vMin).lightmap(120,240).normal(1, 1, 1).endVertex();
              buffer.pos(matrix4f, 1, 0, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(120,240).normal(1, 1, 1).endVertex();
              buffer.pos(matrix4f, 1, 0, 1).color(r, g, b, a).tex(uMax, vMax).lightmap(120,240).normal(1, 1, 1).endVertex();
              buffer.pos(matrix4f, 0, 0, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(120,240).normal(1, 1, 1).endVertex();

              buffer.pos(matrix4f, 0, 1, 0).color(r, g, b, a).tex(uMin, vMin).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 0, 0).color(r, g, b, a).tex(uMax, vMax).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 0, 0).color(r, g, b, a).tex(uMax, vMin).lightmap(120,240).normal(1, 1, 0).endVertex();

              buffer.pos(matrix4f, 0, 1, 1).color(r, g, b, a).tex(uMin, vMin).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 1, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 0, 0).color(r, g, b, a).tex(uMax, vMax).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 0, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(120,240).normal(1, 1, 0).endVertex();

              buffer.pos(matrix4f, 0, 1, 1).color(r, g, b, a).tex(uMin, vMin).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 1).color(r, g, b, a).tex(uMin, vMax).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 0, 1).color(r, g, b, a).tex(uMax, vMax).lightmap(120,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 0, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(120,240).normal(1, 1, 0).endVertex();


              buffer.pos(matrix4f, 1, 1, 1).color(r, g, b, a).tex(uMin, vMin).lightmap(120,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(120,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 0, 0).color(r, g, b, a).tex(uMax, vMax).lightmap(120,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 0, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(120,240).normal(0, 1, 0).endVertex();


              matrixStackIn.pop();


              textureAtlasSprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(this.getlogoResource(tileEntityIn.gemItem));
              uMin = textureAtlasSprite.getMinU();
              uMax = textureAtlasSprite.getMaxU();
              vMin = textureAtlasSprite.getMinV();
              vMax = textureAtlasSprite.getMaxV();
              //draw logo
              matrixStackIn.push();
              IVertexBuilder bufferLogo = bufferIn.getBuffer(RenderType.getCutout());
              matrixStackIn.translate(0.5f,2.2F + floatHeight,0.5f);
              matrixStackIn.rotate(this.renderDispatcher.renderInfo.getRotation());
              matrixStackIn.scale(0.5f, 0.5f, 0.5f);
              matrixStackIn.translate(-0.5f,-0.5,-0.5f);

              Matrix4f matrix4flogo = matrixStackIn.getLast().getMatrix();
              bufferLogo.pos(matrix4flogo, 0, 1, 0.5F).color(r, g, b, a).tex(uMin, vMin).lightmap(120,240).normal(0, 1, 0).endVertex();
              bufferLogo.pos(matrix4flogo, 1, 1, 0.5F).color(r, g, b, a).tex(uMin, vMax).lightmap(120,240).normal(0, 1, 0).endVertex();
              bufferLogo.pos(matrix4flogo, 1, 0, 0.5F).color(r, g, b, a).tex(uMax, vMax).lightmap(120,240).normal(0, 1, 0).endVertex();
              bufferLogo.pos(matrix4flogo, 0, 0, 0.5F).color(r, g, b, a).tex(uMax, vMin).lightmap(120,240).normal(0, 1, 0).endVertex();

              matrixStackIn.pop();

          }
    }

    public ResourceLocation getGemResource(ItemStack itemStack){
        Item putGemItem = itemStack.getItem();
        if(putGemItem == ItemLoader.ELEMENTGEM_FIRE.get()){
            return RenderLoader.firegemLocation;
        }else if(putGemItem == ItemLoader.ELEMENTGEM_GOLD.get()){
            return RenderLoader.goldgemLocation;
        }else if( putGemItem == ItemLoader.ELEMENTGEM_WOOD.get()){
            return RenderLoader.woodgemLocation;
        }else if(putGemItem == ItemLoader.ELEMENTGEM_AQUA.get()){
            return RenderLoader.aquagemLocation;
        }else{
            return RenderLoader.earthgemLocation;
        }
    }

    public ResourceLocation getlogoResource(ItemStack itemStack){
        Item putGemItem = itemStack.getItem();
        if(putGemItem == ItemLoader.ELEMENTGEM_FIRE.get()){
            return RenderLoader.firelogoLocation;
        }else if(putGemItem == ItemLoader.ELEMENTGEM_GOLD.get()){
            return RenderLoader.goldlogoLocation;
        }else if( putGemItem == ItemLoader.ELEMENTGEM_WOOD.get()){
            return RenderLoader.woodlogoLocation;
        }else if(putGemItem == ItemLoader.ELEMENTGEM_AQUA.get()){
            return RenderLoader.aqualogoLocation;
        }else{
            return RenderLoader.earthlogoLocation;
        }
    }

    protected int getBrightnessForRender(TileEntityGemShower tileEntityGemShower,float partialTick) {
        BlockPos blockpos = new BlockPos(tileEntityGemShower.getPos().getX(), tileEntityGemShower.getPos().getY(), tileEntityGemShower.getPos().getZ());
        return tileEntityGemShower.getWorld().isBlockLoaded(blockpos) ? WorldRenderer.getCombinedLight(tileEntityGemShower.getWorld(), blockpos) : 0;
    }
}
