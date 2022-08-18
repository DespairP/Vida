package teamHTBP.vida.api.common.particle;

import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.helper.animation.property.Property;

/**
 * @author DustW
 */
public interface SizeParticle {
    float getSize();
    void setSize(float size);

    Property<SizeParticle, Float> SIZE = new Property<>() {

        @Override
        public void set(@NotNull SizeParticle object, Float value) {
            object.setSize(value);
        }

        @Override
        public Float get(@NotNull SizeParticle object) {
            return object.getSize();
        }
    };
}
