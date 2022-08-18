package teamHTBP.vida.api.common.particle;

import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.helper.animation.property.Property;

/**
 * @author DustW
 */
public interface AlphaParticle {
    float getAlpha();
    void setAlpha(float alpha);

    Property<AlphaParticle, Float> ALPHA = new Property<>() {
        @Override
        public void set(@NotNull AlphaParticle object, Float value) {
            object.setAlpha(value);
        }

        @Override
        public Float get(@NotNull AlphaParticle object) {
            return object.getAlpha();
        }
    };
}
