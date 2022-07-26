package teamHTBP.vida.helper.guidebookHelper.components;

import com.google.gson.annotations.Expose;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import teamHTBP.vida.helper.renderHelper.TextureSection;
import teamHTBP.vida.utils.color.RGBAColor;
import teamHTBP.vida.utils.math.FloatRange;

import static net.minecraft.client.gui.AbstractGui.blit;
import static net.minecraft.client.renderer.ItemRenderer.getBuffer;
import static teamHTBP.vida.helper.renderHelper.RenderHelper.renderTextWithTranslationKeyCenter;

/**模型组件*/
public class ModelGuidebookComponent implements IGuidebookComponent {
    /**渲染id,minecraft中命名id*/
    @Expose
    public String id;
    @Expose
    public final String type = "model";
    /**渲染物品*/
    @Expose
    private ItemStack renderStack = ItemStack.EMPTY;
    /**渲染的x位置*/
    @Expose
    public int x;
    /**渲染的y位置->Yaw*/
    @Expose
    public int y;
    /*-----以下变量都为component内部变量-----*/
    /**绕y轴旋转多少度->Pitch*/
    private float rotateX = 0F;
    private float prevRotateX = 0F;
    /**绕x轴旋转多少度*/
    private float rotateY = 0F;
    private float prevRotateY = 0F;
    /**透明度*/
    private FloatRange alpha = new FloatRange(0,1f,0.2f);
    private float preAlpha = 0F;
    /**物品渲染器*/
    private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
    /**模型渲染器*/
    private final BlockModelShapes blockmodelshapes = Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes();
    /**材质管理*/
    private final TextureManager textureManager = Minecraft.getInstance().textureManager;
    private final static ResourceLocation componentLocation = new ResourceLocation("vida","textures/gui/book.png");
    private final static TextureSection componentSection = new TextureSection(80, 336, 128, 64);
    /**是否被悬浮*/
    private boolean isHovered = false;
    /**是否能被旋转*/
    private boolean isFocused = false;
    /**字体渲染*/
    private final FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        //是不是悬浮在组件上
        mouseMoved(mouseX,mouseY);
        //渲染背景
        renderBackground(matrixStack);
        //渲染模型
        renderBlockInGui(matrixStack, partialTicks);
        //
        renderName(matrixStack, partialTicks);
    }

    /**渲染背景*/
    public void renderBackground(MatrixStack matrixStack){
        RenderSystem.pushMatrix();
        textureManager.bindTexture(componentLocation);
        blit(matrixStack, x, y, 0, componentSection.mu(), componentSection.mv(), componentSection.w(), componentSection.h(), 512, 512);
        RenderSystem.popMatrix();
    }

    /**渲染物品*/
    public void renderBlockInGui(MatrixStack matrixStack,float partialTicks){
        RenderSystem.pushMatrix();
        matrixStack.push();
        //绑定方块模型,用于模型材质绑定
        textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        textureManager.getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).setBlurMipmapDirect(false, false);
        //GL11方法, @see:ItemRenderer#renderItemModelIntoGUI
        RenderSystem.enableRescaleNormal();
        RenderSystem.enableAlphaTest();
        RenderSystem.defaultAlphaFunc();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        //渲染值全颜色
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0f);
        //设置渲染位移
        //设置渲染的位置
        RenderSystem.translatef((float)x + componentSection.cw() - 20.0F, (float)y + componentSection.ch() - 32.0F, 100.0F);
        RenderSystem.translatef(32.0F, 32.0F, 0.0F);
        //设置渲染大小
        RenderSystem.scalef(1.0F, -1.0F, 1.0F);
        RenderSystem.scalef(64.0F, 64.0F, 64.0F);
        //获取要渲染的模型
        BlockState state = getBlockStateFromItemStack();
        IBakedModel model = itemRenderer.getItemModelWithOverrides(renderStack,(World)null, (LivingEntity)null);
        //获取渲染的方式
        RenderType rendertype = RenderTypeLookup.func_239219_a_(renderStack, true);
        //将模型调整成适合GUI渲染的scale模型
        model = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(matrixStack, model, ItemCameraTransforms.TransformType.GUI, false);
        //模型旋转
        matrixStack.translate(0.5,0.5,0.5); //将模型调整到正中央
        matrixStack.rotate(new Quaternion(new Vector3f(0f,1f,0f) , MathHelper.clamp(partialTicks,prevRotateX, rotateX), false));
        matrixStack.rotate(new Quaternion(new Vector3f(1f,0.0f,0f) , MathHelper.clamp(partialTicks,prevRotateY, rotateY), false));
        matrixStack.translate(-0.5,-0.5,-0.5f);// 将模型调整到旋转后的正中央
        //再次将模型比例缩小50%
        matrixStack.getLast().getMatrix().mul(0.5F);
        //渲染设定是否发光
        boolean flag = !model.isSideLit();
        if (flag) {
            RenderHelper.setupGuiFlatDiffuseLighting();
        }
        //获取渲染buffer
        IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        //真正绑定模型
        IVertexBuilder ivertexbuilder = getBuffer(irendertypebuffer$impl, rendertype, true, renderStack.hasEffect());
        //开始渲染
        itemRenderer.renderModel(model, renderStack, 15728880, OverlayTexture.NO_OVERLAY, matrixStack, ivertexbuilder);
        //结束渲染
        irendertypebuffer$impl.finish();
        //恢复渲染初始值
        RenderSystem.enableDepthTest();
        if (flag) {
            RenderHelper.setupGui3DDiffuseLighting();
        }
        RenderSystem.disableAlphaTest();
        RenderSystem.disableRescaleNormal();
        matrixStack.pop();
        RenderSystem.popMatrix();
    }


    /**渲染名字*/
    public void renderName(MatrixStack matrixStack,float partialTicks){
        String key = I18n.format(renderStack.getTranslationKey());
        int bottomY = (y + componentSection.h()) - 10;
        RGBAColor color = new RGBAColor(0,0,0,(int)(alpha.get() * 256));
        //渲染文字
        renderTextWithTranslationKeyCenter(matrixStack, key, getWidth(), x, bottomY, color.toHex());
    }

    /**创建组件*/
    public static ModelGuidebookComponent create(int x,int y,ItemStack itemStack){
        ModelGuidebookComponent component = new ModelGuidebookComponent();
        component.x = x;
        component.y = y;
        component.renderStack = itemStack;
        return component;
    }


    /**获取blockstate*/
    public BlockState getBlockStateFromItemStack(){
        Item item = renderStack.getItem();
        if(!(item instanceof BlockItem)){
            return Blocks.AIR.getDefaultState();
        }
        return ((BlockItem)item).getBlock().getDefaultState();
    }

    /*------- 组件监听 ----------*/

    /**当鼠标拖拽过程中*/
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if(!isFocused) {
            return false;
        }
        //保存上一次的值,用于线性插值
        prevRotateX = rotateX;
        prevRotateY = rotateY;
        //针对拖动进行旋转
        rotateX += dragX / 100;
        rotateY += dragY / 100;
        rotateX = rotateX % 361;
        rotateY = rotateY % 361;
        return true;
    }

    /**鼠标移动时监听是否是悬浮或者激活状态,如果是激活状态,增加alpha值*/
    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.getWidth() && mouseY < this.y + this.getHeight();
        preAlpha = alpha.get();
        if(!isActive()){
            alpha.decrease(0.05f );
            return;
        }
        alpha.increase(0.05f);
    }

    /**如果点击组件,标记组件能被旋转*/
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(isActive()) {
            this.isFocused = true;
        }
        return false;
    }

    /**放开组件,标记组件不能被旋转*/
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if(isFocused){
            this.isFocused = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.getWidth() && mouseY < this.y + this.getHeight();
    }

    public boolean isActive(){
        return isHovered || isFocused;
    }


    /**获取组件高度*/
    @Override
    public int getHeight(){
        return componentSection.h();
    }

    /**获取组件宽度*/
    @Override
    public int getWidth(){
        return componentSection.w();
    }

}
