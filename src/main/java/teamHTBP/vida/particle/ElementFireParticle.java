package teamHTBP.vida.particle;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.world.World;

public class ElementFireParticle extends SpriteTexturedParticle {

    private final double coordX;
    private final double coordY;
    private final double coordZ;

    protected ElementFireParticle(World worldIn, double posXIn, double posYIn, double posZIn,double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, posXIn, posYIn, posZIn, xSpeedIn,  ySpeedIn,  zSpeedIn);

        this.coordX = posXIn;
        this.coordY = posYIn;
        this.coordZ = posZIn;
        this.particleRed = 1;
        this.particleGreen = 1;
        this.particleBlue = 1;
        this.canCollide = false;
        this.prevPosX = posXIn + xSpeedIn;
        this.prevPosY = posYIn + ySpeedIn;
        this.prevPosZ = posZIn + zSpeedIn;
        this.posX = this.prevPosX;
        this.posY = this.prevPosY;
        this.posZ = this.prevPosZ;
        this.canCollide = false;
        this.particleGravity = 0.3f;
        this.maxAge = (int)(Math.random() * 10.0D) + 30;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.motionY += 0.004D;
            this.move(this.motionX, this.motionY, this.motionZ);
            if (this.posY == this.prevPosY) {
                this.motionX *= 0.1D;
                this.motionZ *= 0.1D;
            }

            this.motionX *= (double)0.36F;
            this.motionY *= (double)0.36F;
            this.motionZ *= (double)0.36F;
            if (this.onGround) {
                this.motionX *= (double)0.17F;
                this.motionZ *= (double)0.17F;
            }

        }
        if(this.particleAlpha > 0.0)
            this.particleAlpha -= 0.05f;
        //super.tick();
    }
}
