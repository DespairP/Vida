package teamHTBP.vida.helper.animation.valueholder;

import teamHTBP.vida.helper.animation.TimeInterpolator;
import teamHTBP.vida.helper.animation.property.Property;

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

    /**
     * 使用 time interpolator 插值并将结果设置到目标对象上
     * @param target        目标对象
     * @param actualTick    实际的 tick
     * @param maxTick       最大的 tick
     * @param interpolator  插值器
     */
    public abstract void setValue(O target, float actualTick, int maxTick, TimeInterpolator interpolator);
}
