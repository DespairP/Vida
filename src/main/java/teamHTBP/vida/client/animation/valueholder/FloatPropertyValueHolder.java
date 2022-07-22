package teamHTBP.vida.client.animation.valueholder;

import teamHTBP.vida.client.animation.TimeInterpolator;
import teamHTBP.vida.utils.property.Property;

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

        float interpolation = Math.min(maxTick, interpolator.interpolation(actualTick / maxTick));
        float value = interpolation * (k2 - k1) + k1;

        property.set(target, value);
    }
}
