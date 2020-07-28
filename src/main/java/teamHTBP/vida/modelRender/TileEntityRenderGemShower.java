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
import teamHTBP.vida.Item.ItemLoader;
import teamHTBP.vida.TileEntity.TileEntityGemShower;
import teamHTBP.vida.TileEntity.TileEntityPurfiedCauldron;
import teamHTBP.vida.Vida;

public class TileEntityRenderGemShower extends TileEntityRenderer<TileEntityGemShower> {
    float r=1,g=1,b=1,a=1;
    float rotation = 0.0f;

    public TileEntityRenderGemShower(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityGemShower tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
          if(!tileEntityIn.gemItem.isEmpty()){
              Minecraft.getInstance().getTextureManager().bindTexture(getGemResource(tileEntityIn.gemItem));
              TextureAtlasSprite textureAtlasSprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(RenderLoader.gemLocation);
              rotation += 1;
              if(rotation >= 360.0f) this.rotation = 0.0f;

              float uMin = textureAtlasSprite.getMinU();
              float uMax = textureAtlasSprite.getMaxU();
              float vMin = textureAtlasSprite.getMinV();
              float vMax = textureAtlasSprite.getMaxV();

              Vector3f[] avector3f = new Vector3f[]{
                      new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F),
                      new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 2.0F), new Vector3f(-1.0F, -1.0F, 2.0F),
                      new Vector3f(-1.0F, -1.0F, 2.0F), new Vector3f(-1.0F, 1.0F, 2.0F),  new Vector3f(1.0F,   1.0F, 2.0F), new Vector3f( 1.0F,-1.0F, 2.0F),
                      new Vector3f( 1.0F, -1.0F, 2.0F), new Vector3f( 1.0F, 1.0F, 2.0F),  new Vector3f(1.0F,   1.0F, 0.0F), new Vector3f( 1.0F,-1.0F, 0.0F),
                      new Vector3f( -1.0F, 1.0F, 2.0F), new Vector3f( -1.0F, 1.0F, 0.0F),  new Vector3f(1.0F,   1.0F, 0.0F), new Vector3f( 1.0F,1.0F, 2.0F),
                      new Vector3f( -1.0F, -1.0F, 2.0F), new Vector3f( -1.0F, -1.0F, 0.0F),  new Vector3f(1.0F,   -1.0F, 0.0F), new Vector3f( 1.0F,-1.0F, 2.0F)

              };
              IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getCutout());


              matrixStackIn.push();
              matrixStackIn.translate(.5F, 0.9F, .5F);
              matrixStackIn.scale(0.1f, 0.1f, 0.1f);
              matrixStackIn.rotate(this.renderDispatcher.renderInfo.getRotation());
              matrixStackIn.translate(-.5F, -0.9F, -.5F);
              Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
              float f7 = uMin,f8 = uMax,f5 = vMin,f6 = vMax;

              int light = this.getBrightnessForRender(tileEntityIn, partialTicks);
              buffer.pos(matrix4f, 0, 1,   1).color(r, g, b, a).tex(uMin, vMin).lightmap(0,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(0,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 1).color(r, g, b, a).tex(uMax, vMax).lightmap(0,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 1, 0).color(r, g, b, a).tex(uMax, vMin).lightmap(0,240).normal(0, 1, 0).endVertex();

              buffer.pos(matrix4f, 0, 0, 0).color(r, g, b, a).tex(uMin, vMin).lightmap(0,240).normal(1, 1, 1).endVertex();
              buffer.pos(matrix4f, 1, 0, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(0,240).normal(1, 1, 1).endVertex();
              buffer.pos(matrix4f, 1, 0, 1).color(r, g, b, a).tex(uMax, vMax).lightmap(0,240).normal(1, 1, 1).endVertex();
              buffer.pos(matrix4f, 0, 0, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(0,240).normal(1, 1, 1).endVertex();

              buffer.pos(matrix4f, 0, 1, 0).color(r, g, b, a).tex(uMin, vMin).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 0, 0).color(r, g, b, a).tex(uMax, vMax).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 0, 0).color(r, g, b, a).tex(uMax, vMin).lightmap(0,240).normal(1, 1, 0).endVertex();

              buffer.pos(matrix4f, 0, 1, 1).color(r, g, b, a).tex(uMin, vMin).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 1, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 0, 0).color(r, g, b, a).tex(uMax, vMax).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 0, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(0,240).normal(1, 1, 0).endVertex();

              buffer.pos(matrix4f, 0, 1, 1).color(r, g, b, a).tex(uMin, vMin).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 1).color(r, g, b, a).tex(uMin, vMax).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 0, 1).color(r, g, b, a).tex(uMax, vMax).lightmap(0,240).normal(1, 1, 0).endVertex();
              buffer.pos(matrix4f, 0, 0, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(0,240).normal(1, 1, 0).endVertex();


              buffer.pos(matrix4f, 1, 1, 1).color(r, g, b, a).tex(uMin, vMin).lightmap(0,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 1, 0).color(r, g, b, a).tex(uMin, vMax).lightmap(0,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 0, 0).color(r, g, b, a).tex(uMax, vMax).lightmap(0,240).normal(0, 1, 0).endVertex();
              buffer.pos(matrix4f, 1, 0, 1).color(r, g, b, a).tex(uMax, vMin).lightmap(0,240).normal(0, 1, 0).endVertex();




              matrixStackIn.pop();

          }
    }

    public ResourceLocation getGemResource(ItemStack itemStack){
        Item putGemItem = itemStack.getItem();
        if(putGemItem == ItemLoader.fireElementGem.get() || putGemItem == ItemLoader.goldElementGem.get() ||
                putGemItem == ItemLoader.woodElementGem.get() || putGemItem == ItemLoader.aquaElementGem.get()||
                putGemItem == ItemLoader.earthElementGem.get()){
            return new ResourceLocation(Vida.modId, "textures/model/goldgem");

        }

        return null;
    }

    protected int getBrightnessForRender(TileEntityGemShower tileEntityGemShower,float partialTick) {
        BlockPos blockpos = new BlockPos(tileEntityGemShower.getPos().getX(), tileEntityGemShower.getPos().getY(), tileEntityGemShower.getPos().getZ());
        return tileEntityGemShower.getWorld().isBlockLoaded(blockpos) ? WorldRenderer.getCombinedLight(tileEntityGemShower.getWorld(), blockpos) : 0;
    }
}
