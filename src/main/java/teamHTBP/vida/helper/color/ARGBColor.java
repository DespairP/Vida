package teamHTBP.vida.helper.color;

/**
 * @author DustW
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * float: 0~1, int: 0~255
 *
 * @author DustW
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class ARGBColor extends VidaColor {
    public static final ARGBColor BLACK       = ARGBColor.of(255, 0, 0, 0);
    public static final ARGBColor DARK_BLUE   = ARGBColor.of(35, 202, 249);
    public static final ARGBColor DARK_RED    = ARGBColor.of(255, 71, 71);
    public static final ARGBColor DARK_BROWN  = ARGBColor.of(249, 212, 0);
    public static final ARGBColor CYAN_GREEN  = ARGBColor.of(35, 202, 249);
    public static final ARGBColor PURPLE      = ARGBColor.of(247, 81, 236);
    public static final ARGBColor LIGHT_BROWN = ARGBColor.of(255, 200, 145);

    /** 0~255 */
    private final int a;
    private final int r;
    private final int g;
    private final int b;

    public static ARGBColor of(float a, float r, float g, float b) {
        return new ARGBColor((int) a * 255, (int) r * 255, (int) g * 255, (int) b * 255);
    }

    public static ARGBColor of(int r, int g, int b) {
        return new ARGBColor(255, r, g, b);
    }

    public static ARGBColor of(float r, float g, float b) {
        return of(1F, r, g, b);
    }

    public static ARGBColor argb(int argb) {
        return new ARGBColor(argb >>> 24, argb >> 16 & 255, argb >> 8 & 255, argb & 255);
    }

    public static ARGBColor rgba(int rgba) {
        return new ARGBColor(rgba & 255, rgba >>> 24, rgba >> 16 & 255, rgba >> 8 & 255);
    }

    public static int argb(int a, int r, int g, int b) {
        return a << 24 | r << 16 | g << 8 | b;
    }

    @Override
    public int argb() {
        return a << 24 | r << 16 | g << 8 | b;
    }

    @Override
    public ARGBColor toARGB() {
        return this;
    }

    public int rgba() {
        return r << 24 | g << 16 | b << 8 | a;
    }

    @Override
    public HSVColor toHSV() {
        float r = this.r / 255F;
        float g = this.g / 255F;
        float b = this.b / 255F;

        float max = Math.max(Math.max(r, g), b);
        float min = Math.min(Math.min(r, g), b);

        float delta = max - min;

        float h;

        if (max == r) {
            h = (g - b) / delta * 60;
        }
        else if (max == g) {
            h = ((b - r) / delta + 2) * 60;
        }
        else {
            h = ((r - g) / delta + 4) * 60;
        }

        float s = max != 0 ? delta / max : 0;
        return new HSVColor(h, s, max);
    }
}
