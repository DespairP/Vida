package teamHTBP.vida.animation.valueholder;

import teamHTBP.vida.animation.TimeInterpolator;
import teamHTBP.vida.animation.property.Property;

/**
 * @author DustW
 */
public class FloatPropertyValueHolder<O> extends PropertyValueHolder<O, Float> {
    public FloatPropertyValueHolder(Property<O, Float> property, Float... keyFrames) {
        super(property, keyFrames);
    }

    @Override
    public void setValue(O target, float actualTick, int maxTick, TimeInterpolator interpolator) {
        float k1 = keyFrames[0];
        float k2 = keyFrames[1];

        float interpolation = interpolator.interpolation(actualTick / maxTick);
        float value = interpolation * (k2 - k1) + k1;

        property.set(target, value);
    }
}
