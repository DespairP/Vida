package teamHTBP.vida.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;

public class ElementFireParticle extends SpriteTexturedParticle {

    private final double coordX;
    private final double coordY;
    private final double coordZ;

    public ElementFireParticle(ClientLevel worldIn, double posXIn, double posYIn, double posZIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, posXIn, posYIn, posZIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.coordX = posXIn;
        this.coordY = posYIn;
        this.coordZ = posZIn;
        this.rCol = 1;
        this.gCol = 1;
        this.bCol = 1;
        this.xo = posXIn + xSpeedIn;
        this.yo = posYIn + ySpeedIn;
        this.zo = posZIn + zSpeedIn;
        this.x = this.xo;
        this.y = this.yo;
        this.z = this.zo;
        this.hasPhysics = false;
        this.gravity = 0.3f;
        this.lifetime = (int) (Math.random() * 10.0D) + 30;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.yd += 0.004D;
            this.move(this.xd, this.yd, this.zd);
            if (this.y == this.yo) {
                this.xd *= 0.1D;
                this.zd *= 0.1D;
            }

            this.xd *= 0.36F;
            this.yd *= 0.36F;
            this.zd *= 0.36F;
            if (this.onGround) {
                this.xd *= 0.17F;
                this.zd *= 0.17F;
            }

        }
        if (this.alpha > 0.0)
            this.alpha -= 0.05f;
        //super.tick();
    }
}
