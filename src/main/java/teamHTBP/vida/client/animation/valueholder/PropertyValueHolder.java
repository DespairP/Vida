package teamHTBP.vida.client.animation.valueholder;

import teamHTBP.vida.client.animation.TimeInterpolator;
import teamHTBP.vida.utils.property.Property;

/**
 * @author DustW
 */
public abstract class PropertyValueHolder<O, V> {
    protected Property<O, V> property;
    protected V[] keyFrames;

    public PropertyValueHolder(Property<O, V> property, V... keyFrames) {
        this.property = property;
        this.keyFrames = keyFrames;
    }

    public abstract void setValue(O target, float actualTick, int maxTick, TimeInterpolator interpolator);
}
