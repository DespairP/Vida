package teamHTBP.vida.helper.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author DustW
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class HSVColor extends VidaColor {
    /** H: 0~360(degrees) s: 0%~100%(0~1) v: 0%~100%(0~1) */
    private final float h, s, v;

    @Override
    public int argb() {
        return toARGB().argb();
    }

    @Override
    public ARGBColor toARGB() {
        float c = v * s;
        float x = c * (1 - Math.abs(h / 60) % 2 - 1);
        float m = v - c;

        float r = 0;
        float g = 0;
        float b = 0;

        if (0 <= h && h < 60) {
            r = c;
            g = x;
        }
        else if (60 <= h && h < 120) {
            r = x;
            g = c;
        }
        else if (120 <= h && h < 180) {
            g = c;
            b = x;
        }
        else if (180 <= h && h < 240) {
            g = x;
            b = c;
        }
        else if (240 <= h && h < 300) {
            r = x;
            b = c;
        }
        else if (300 <= h && h < 360) {
            r = c;
            b = x;
        }

        return ARGBColor.of(r + m, g + m, b + m);
    }

    @Override
    public HSVColor toHSV() {
        return this;
    }
}
