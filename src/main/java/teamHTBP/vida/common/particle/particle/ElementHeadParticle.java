package teamHTBP.vida.common.particle.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import teamHTBP.vida.Vida;
import teamHTBP.vida.api.common.particle.AlphaParticle;
import teamHTBP.vida.api.common.particle.SizeParticle;

import java.util.function.Function;

/**
 * @author DustW
 */
public class ElementHeadParticle extends TextureParticle implements AlphaParticle, SizeParticle {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Vida.MOD_ID, "textures/special/circle.png");

    private boolean glint;
    private Function<ElementHeadParticle, Boolean> alive;

    public ElementHeadParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
        setTexture(TEXTURE);
        setMoveless(true);
        setLight(0xf000f0);
    }

    public void setAliveCondition(Function<ElementHeadParticle, Boolean> alive) {
        this.alive = alive;
    }

    public void setGlint(boolean glint) {
        this.glint = glint;
    }

    @Override
    protected void update() {
        if (alive != null && !alive.apply(this)) {
            remove();
            return;
        }

        if (glint) {
            if (this.age % 3 == 0) {
                if (quadSize == 0.12f) {
                    quadSize = 0.09f;
                } else {
                    quadSize = 0.12f;
                }
            }
        } else {
            if (this.age % 3 == 0) {
                if (alpha == 1f) {
                    setAlpha(0.9f);
                } else {
                    setAlpha(1f);
                }
            }
        }
    }

    @Override
    public float getAlpha() {
        return alpha;
    }

    @Override
    public float getSize() {
        return quadSize;
    }

    @Override
    public void setSize(float size) {
        quadSize = size;
    }
}
