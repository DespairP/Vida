package teamHTBP.vida.helper.math.curve;

import teamHTBP.vida.helper.math.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public abstract class Curve {
    public abstract Vector3 getPoint(float t);

    public List<Vector3> getPoints(int size) {
        List<Vector3> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            points.add(getPoint((float) i / size));
        }
        return points;
    }
}
