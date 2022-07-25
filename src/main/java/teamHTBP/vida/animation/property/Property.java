package teamHTBP.vida.animation.property;

import javax.annotation.Nonnull;

/**
 * @author DustW
 */
public interface Property<T, V> {

    void set(@Nonnull T object, V value);

    V get(@Nonnull T object);
}
