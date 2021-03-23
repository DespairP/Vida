package teamHTBP.vida.modelRender.armormodel;

import net.minecraft.client.renderer.model.ModelRenderer;

public class ArmorModelFireChestplates extends ArmorModelElementChestplates{
    private final ModelRenderer bone6;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer bone12;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer bone3;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer bone4;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer bone5;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13;
    private final ModelRenderer cube_r14;

    public ArmorModelFireChestplates(){
        super(128,128);
        textureWidth = 128;
        textureHeight = 128;

        field_178724_i = new ModelRenderer(this);
        field_178724_i.setRotationPoint(-4.0F, 2.0F, 0.0F);
        field_178724_i.setTextureOffset(0, 28).addBox(-0.5F, -2.0F, -2.0F, 5.0F, 4.0F, 4.0F, 0.2F, true);
        field_178724_i.setTextureOffset(0, 36).addBox(-1.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, true);

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_178724_i.addChild(bone6);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(13.0F, 1.0F, -1.5F);
        bone6.addChild(cube_r1);
        setRotateAngle(cube_r1, -0.0436F, 0.1309F, -0.2182F);
        cube_r1.setTextureOffset(18, 30).addBox(-8.0F, -5.5F, -1.5F, 1.0F, 5.0F, 2.0F, 0.0F, true);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(13.25F, 0.0F, 0.0F);
        bone6.addChild(cube_r2);
        setRotateAngle(cube_r2, 0.0F, 0.0F, -0.2618F);
        cube_r2.setTextureOffset(18, 30).addBox(-8.0F, -6.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, true);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(13.0F, 1.0F, 1.5F);
        bone6.addChild(cube_r3);
        setRotateAngle(cube_r3, 0.0436F, -0.1309F, -0.2182F);
        cube_r3.setTextureOffset(18, 30).addBox(-8.0F, -5.5F, -0.5F, 1.0F, 5.0F, 2.0F, 0.0F, true);

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_178724_i.addChild(bone12);
        bone12.setTextureOffset(16, 37).addBox(2.25F, 8.0F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, true);
        bone12.setTextureOffset(0, 36).addBox(2.5F, 5.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(12.0F, 8.5F, -1.75F);
        bone12.addChild(cube_r4);
        setRotateAngle(cube_r4, 0.1745F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(0, 36).addBox(-9.5F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(12.0F, 8.5F, 1.75F);
        bone12.addChild(cube_r5);
        setRotateAngle(cube_r5, -0.1745F, 0.0F, 0.0F);
        cube_r5.setTextureOffset(0, 36).addBox(-9.5F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        field_178723_h = new ModelRenderer(this);
        field_178723_h.setRotationPoint(-4.0F, 2.0F, 0.0F);
        field_178723_h.setTextureOffset(0, 28).addBox(-4.5F, -2.0F, -2.0F, 5.0F, 4.0F, 4.0F, 0.2F, false);
        field_178723_h.setTextureOffset(0, 36).addBox(-3.0F, 5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.2F, false);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_178723_h.addChild(bone3);


        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-5.0F, 1.0F, -1.5F);
        bone3.addChild(cube_r6);
        setRotateAngle(cube_r6, -0.0436F, -0.1309F, 0.2182F);
        cube_r6.setTextureOffset(18, 30).addBox(-0.5F, -4.5F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(-5.25F, 0.0F, 0.0F);
        bone3.addChild(cube_r7);
        setRotateAngle(cube_r7, 0.0F, 0.0F, 0.2618F);
        cube_r7.setTextureOffset(18, 30).addBox(-0.5F, -4.5F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(-5.0F, 1.0F, 1.5F);
        bone3.addChild(cube_r8);
        setRotateAngle(cube_r8, 0.0436F, 0.1309F, 0.2182F);
        cube_r8.setTextureOffset(18, 30).addBox(-0.5F, -4.5F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_178723_h.addChild(bone4);
        bone4.setTextureOffset(16, 37).addBox(-3.25F, 8.0F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        bone4.setTextureOffset(0, 36).addBox(-3.5F, 5.25F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(-4.0F, 8.5F, -1.75F);
        bone4.addChild(cube_r9);
        setRotateAngle(cube_r9, 0.1745F, 0.0F, 0.0F);
        cube_r9.setTextureOffset(0, 36).addBox(0.5F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(-4.0F, 8.5F, 1.75F);
        bone4.addChild(cube_r10);
        setRotateAngle(cube_r10, -0.1745F, 0.0F, 0.0F);
        cube_r10.setTextureOffset(0, 36).addBox(0.5F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        field_78115_e = new ModelRenderer(this);
        field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_78115_e.setTextureOffset(0, 16).addBox(-4.0F, 0.25F, -2.0F, 8.0F, 7.0F, 4.0F, 0.2F, false);
        field_78115_e.setTextureOffset(30, 19).addBox(-1.0F, 1.2F, -2.65F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        field_78115_e.setTextureOffset(36, 16).addBox(3.0F, -0.5F, -2.5F, 2.0F, 6.0F, 5.0F, 0.0F, false);
        field_78115_e.setTextureOffset(36, 16).addBox(-5.0F, -0.5F, -2.5F, 2.0F, 6.0F, 5.0F, 0.0F, true);

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
        field_78115_e.addChild(bone5);


        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(-5.5F, -0.9F, -2.6F);
        bone5.addChild(cube_r11);
        setRotateAngle(cube_r11, -1.309F, 0.0F, -0.4363F);
        cube_r11.setTextureOffset(24, 19).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(-4.5F, -0.5F, -3.0F);
        bone5.addChild(cube_r12);
        setRotateAngle(cube_r12, -1.309F, 0.0F, -0.4363F);
        cube_r12.setTextureOffset(24, 19).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(5.5F, -0.9F, -2.6F);
        bone5.addChild(cube_r13);
        setRotateAngle(cube_r13, -1.309F, 0.0F, 0.4363F);
        cube_r13.setTextureOffset(24, 19).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(4.5F, -0.5F, -3.0F);
        bone5.addChild(cube_r14);
        setRotateAngle(cube_r14, -1.309F, 0.0F, 0.4363F);
        cube_r14.setTextureOffset(24, 19).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);
    }
}
