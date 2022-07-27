package teamHTBP.vida.helper.guidebook.components;

import com.google.gson.annotations.Expose;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ForgeHooksClient;
import teamHTBP.vida.helper.client.render.TextureSection;
import teamHTBP.vida.helper.color.ARGBColor;
import teamHTBP.vida.helper.math.FloatRange;

import static net.minecraft.client.gui.GuiComponent.blit;
import static teamHTBP.vida.helper.client.render.RenderHelper.renderTextWithTranslationKeyCenter;

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
    private final BlockModelShaper blockmodelshapes = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();
    /**材质管理*/
    private final TextureManager textureManager = Minecraft.getInstance().textureManager;
    private final static ResourceLocation componentLocation = new ResourceLocation("vida","textures/gui/book.png");
    private final static TextureSection componentSection = new TextureSection(80, 336, 128, 64);
    /**是否被悬浮*/
    private boolean isHovered = false;
    /**是否能被旋转*/
    private boolean isFocused = false;
    /**字体渲染*/
    private final Font fontRenderer = Minecraft.getInstance().font;

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
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
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
    public void renderBackground(PoseStack matrixStack){
        matrixStack.pushPose();
        RenderSystem.setShaderTexture(0, componentLocation);
        blit(matrixStack, x, y, 0, componentSection.mu(), componentSection.mv(), componentSection.w(), componentSection.h(), 512, 512);
        matrixStack.popPose();
    }

    /**渲染物品*/
    public void renderBlockInGui(PoseStack matrixStack, float partialTicks){
        matrixStack.pushPose();
        matrixStack.pushPose();
        //绑定方块模型,用于模型材质绑定
        ResourceLocation atlas = InventoryMenu.BLOCK_ATLAS;
        RenderSystem.setShaderTexture(0, atlas);
        textureManager.getTexture(atlas).setFilter(false, false);
        //GL11方法, @see:ItemRenderer#renderItemModelIntoGUI
        //RenderSystem.enableRescaleNormal();
        //RenderSystem.enableAlphaTest();
        //RenderSystem.defaultAlphaFunc();
        RenderSystem.enableBlend();
        //RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        //渲染值全颜色
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
        //设置渲染位移
        //设置渲染的位置
        matrixStack.translate((float)x + componentSection.cw() - 20.0F, (float)y + componentSection.ch() - 32.0F, 100.0F);
        matrixStack.translate(32.0F, 32.0F, 0.0F);
        //设置渲染大小
        matrixStack.scale(1.0F, -1.0F, 1.0F);
        matrixStack.scale(32.0F, 32.0F, 32.0F);
        //获取要渲染的模型
        Minecraft mc = Minecraft.getInstance();
        BakedModel model = itemRenderer.getModel(renderStack, mc.level, mc.player, 0);
        //获取渲染的方式
        RenderType rendertype = ItemBlockRenderTypes.getRenderType(renderStack, true);
        //将模型调整成适合GUI渲染的scale模型
        model = ForgeHooksClient.handleCameraTransforms(matrixStack, model, ItemTransforms.TransformType.GUI, false);
        //模型旋转
        matrixStack.translate(0.5,0.5,0.5); //将模型调整到正中央
        matrixStack.mulPose(new Quaternion(new Vector3f(0f,1f,0f) , Mth.clamp(partialTicks,prevRotateX, rotateX), false));
        matrixStack.mulPose(new Quaternion(new Vector3f(1f,0.0f,0f) , Mth.clamp(partialTicks,prevRotateY, rotateY), false));
        matrixStack.translate(-0.5,-0.5,-0.5f);// 将模型调整到旋转后的正中央
        // //再次将模型比例缩小50%
        // matrixStack.last().pose().multiply(0.5F);
        //渲染设定是否发光
        boolean flag = !model.usesBlockLight();
        if (flag) {
            Lighting.setupForFlatItems();
        }
        //获取渲染buffer
        MultiBufferSource.BufferSource source = mc.renderBuffers().bufferSource();
        //真正绑定模型
        VertexConsumer buffer = ItemRenderer.getFoilBuffer(source, rendertype, true, renderStack.hasFoil());
        //开始渲染
        itemRenderer.renderModelLists(model, renderStack, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
        //结束渲染
        source.endBatch();
        //恢复渲染初始值
        if (flag) {
            Lighting.setupFor3DItems();
        }
        // RenderSystem.disableAlphaTest();
        // RenderSystem.disableRescaleNormal();
        matrixStack.popPose();
        matrixStack.popPose();
    }


    /**渲染名字*/
    public void renderName(PoseStack matrixStack, float partialTicks){
        String key = I18n.get(renderStack.getDescriptionId());
        int bottomY = (y + componentSection.h()) - 10;
        //渲染文字
        renderTextWithTranslationKeyCenter(matrixStack, key, getWidth(), x, bottomY,
                ARGBColor.argb(0, 0, 0, (int) (alpha.get() * 255)));
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
            return Blocks.AIR.defaultBlockState();
        }
        return ((BlockItem)item).getBlock().defaultBlockState();
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

    @Override
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
