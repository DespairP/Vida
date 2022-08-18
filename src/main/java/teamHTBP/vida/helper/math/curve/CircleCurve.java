package teamHTBP.vida.helper.math.curve;

import lombok.AllArgsConstructor;
import teamHTBP.vida.helper.math.Vector3;
import teamHTBP.vida.helper.math.VidaMath;

/**
 * @author DustW
 */
@AllArgsConstructor
public class CircleCurve extends Curve {
    public final double r;
    public final Vector3 axis;
    public final Vector3 center;

    @Override
    public Vector3 getPoint(float t) {
        float v = t * 360 * VidaMath.DEGREES_TO_RADIANS;
        return new Vector3(r, 0, 0).rotate(v, axis).add(center);
    }
}
