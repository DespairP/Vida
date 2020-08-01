package teamHTBP.vida.modelRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import teamHTBP.vida.TileEntity.TileEntityElementCoreAltar;

import java.util.Random;

public class TileEntityRenderElementCoreAltar extends TileEntityRenderer<TileEntityElementCoreAltar> {
     int[] randomRotationY = {0,0,0,0};
     int[] randomRotationZ = {0,0,0,0};
     Random rand = new Random();

    public TileEntityRenderElementCoreAltar(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);


    }

    @Override
    public void render(TileEntityElementCoreAltar tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if(tileEntityIn.coreItem != ItemStack.EMPTY || !tileEntityIn.coreItem.isEmpty()){
            matrixStackIn.push();
            matrixStackIn.translate(0.5f, 0.7f, 0.5f);
            matrixStackIn.scale(0.5f, 0.5f, 0.5f);
            matrixStackIn.rotate(new Quaternion(90,0,0,true));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(tileEntityIn.coreItem, tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(tileEntityIn.coreItem, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
            matrixStackIn.pop();
        }
        for(int i =0;i < 4;i++){
            if(tileEntityIn.altarItem[i] != ItemStack.EMPTY || !tileEntityIn.altarItem[i].isEmpty()){
                if(randomRotationY[i] == 0) this.randomRotationY[i] = rand.nextInt();
                if(randomRotationZ[i] == 0) this.randomRotationZ[i] = rand.nextInt();
                matrixStackIn.push();
                switch (i){
                    case 0: matrixStackIn.translate(0.2,0.7 , 0.5); break;
                    case 1: matrixStackIn.translate(0.5, 0.7, 0.2); break;
                    case 2: matrixStackIn.translate(0.5, 0.7, 0.8); break;
                    case 3: matrixStackIn.translate(0.8, 0.7, 0.5); break;
                }
                matrixStackIn.rotate(new Quaternion(90, this.randomRotationY[i],0,true));
                matrixStackIn.scale(0.3f, 0.3f, 0.3f);
                ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
                IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(tileEntityIn.altarItem[i], tileEntityIn.getWorld(), null);
                itemRenderer.renderItem(tileEntityIn.altarItem[i], ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
                matrixStackIn.pop();
            }
        }

    }
}
