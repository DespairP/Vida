package teamHTBP.vida.common.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;
import teamHTBP.vida.Vida;
import teamHTBP.vida.helper.mathHelper.Vector3;

import javax.xml.soap.Text;
import java.util.LinkedList;

import static teamHTBP.vida.helper.mathHelper.Vector3dHelper.copy;

/**轨道线粒子*/
public class TrailParticle extends AbstractTailParticle {

    public TrailParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public void update() {
        if (this.maxAge - this.age < 30) {
            tails.remove(0);
        }
    }
}
