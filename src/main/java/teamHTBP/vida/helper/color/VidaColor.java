package teamHTBP.vida.helper.color;

/**
 * @author DustW
 */
public abstract class VidaColor {
    public abstract int argb();

    public abstract ARGBColor toARGB();

    public abstract HSVColor toHSV();

    @Override
    public int hashCode() {
        return Integer.hashCode(argb());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VidaColor color && color.argb() == argb();
    }
}
