package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelWoodHelmet extends  ArmorModelElementHelmet {
    public ModelRenderer part21;
    private final ModelRenderer bone;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer bone4;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer bone2;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer bone5;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;


    public ArmorModelWoodHelmet(){
        super(1.0f,0,128,128);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.2F, false);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(3.75F, -1.5F, -4.0F);
        head.addChild(bone);
        setRotationAngle(bone, -0.1745F, 0.0F, 0.0F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(1.75F, 0.5F, -0.25F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, -0.1745F, 0.0F);
        cube_r1.setTextureOffset(64, 6).addBox(-3.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.8F, 0.0F, 2.75F);
        bone.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.4363F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(80, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.75F, 0.5F, -0.25F);
        bone.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1745F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(70, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(-3.75F, -1.5F, -4.0F);
        head.addChild(bone4);
        setRotationAngle(bone4, -0.1745F, 0.0F, 0.0F);


        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-1.75F, 0.5F, -0.25F);
        bone4.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.1745F, 0.0F);
        cube_r4.setTextureOffset(64, 6).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-0.8F, 0.0F, 2.75F);
        bone4.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.4363F, 0.0F, 0.0F);
        cube_r5.setTextureOffset(80, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-0.75F, 0.5F, -0.25F);
        bone4.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.1745F, 0.0F, 0.0F);
        cube_r6.setTextureOffset(70, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(3.75F, -7.0F, -4.0F);
        head.addChild(bone2);
        setRotationAngle(bone2, 0.1309F, 0.1745F, 0.0F);


        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(1.75F, 0.5F, -0.25F);
        bone2.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, -0.1745F, 0.0F);
        cube_r7.setTextureOffset(64, 6).addBox(-3.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(0.8F, 0.0F, 2.75F);
        bone2.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0873F, -0.4363F, 0.0F);
        cube_r8.setTextureOffset(80, 3).addBox(-0.8086F, -0.8413F, 0.7076F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(0.75F, 0.5F, -0.25F);
        bone2.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.1745F, 0.0F, 0.0F);
        cube_r9.setTextureOffset(70, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(-3.75F, -7.0F, -4.0F);
        head.addChild(bone5);
        setRotationAngle(bone5, 0.1309F, -0.1745F, 0.0F);


        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(-1.75F, 0.5F, -0.25F);
        bone5.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.1745F, 0.0F);
        cube_r10.setTextureOffset(64, 6).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(-0.8F, 0.0F, 2.75F);
        bone5.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0873F, 0.4363F, 0.0F);
        cube_r11.setTextureOffset(80, 3).addBox(-0.1914F, -0.8413F, 0.7076F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(-0.75F, 0.5F, -0.25F);
        bone5.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.1745F, 0.0F, 0.0F);
        cube_r12.setTextureOffset(70, 3).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
