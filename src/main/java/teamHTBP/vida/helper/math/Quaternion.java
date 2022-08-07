package teamHTBP.vida.helper.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author DustW
 */
public class Quaternion {
    public double x;
    public double y;
    public double z;
    public double s;

    public Quaternion() {
        s = 1;
        x = 0;
        y = 0;
        z = 0;
    }

    public Quaternion(Quaternion quaternion) {
        x = quaternion.x;
        y = quaternion.y;
        z = quaternion.z;
        s = quaternion.s;
    }

    public Quaternion(double d, double d1, double d2, double d3) {
        x = d1;
        y = d2;
        z = d3;
        s = d;
    }

    public Quaternion set(Quaternion quaternion) {
        x = quaternion.x;
        y = quaternion.y;
        z = quaternion.z;
        s = quaternion.s;

        return this;
    }

    public Quaternion set(double d, double d1, double d2, double d3) {
        x = d1;
        y = d2;
        z = d3;
        s = d;

        return this;
    }

    public static Quaternion aroundAxis(double ax, double ay, double az, double angle) {
        return new Quaternion().setAroundAxis(ax, ay, az, angle);
    }

    public static Quaternion aroundAxis(Vector3 axis, double angle) {
        return aroundAxis(axis.x, axis.y, axis.z, angle);
    }

    public Quaternion setAroundAxis(double ax, double ay, double az, double angle) {
        angle *= 0.5;
        double d4 = Math.sin(angle);
        return set(Math.cos(angle), ax * d4, ay * d4, az * d4);
    }

    public Quaternion setAroundAxis(Vector3 axis, double angle) {
        return setAroundAxis(axis.x, axis.y, axis.z, angle);
    }

    public Quaternion multiply(Quaternion quaternion) {
        double d = s * quaternion.s - x * quaternion.x - y * quaternion.y - z * quaternion.z;
        double d1 = s * quaternion.x + x * quaternion.s - y * quaternion.z + z * quaternion.y;
        double d2 = s * quaternion.y + x * quaternion.z + y * quaternion.s - z * quaternion.x;
        double d3 = s * quaternion.z - x * quaternion.y + y * quaternion.x + z * quaternion.s;
        s = d;
        x = d1;
        y = d2;
        z = d3;

        return this;
    }

    public Quaternion rightMultiply(Quaternion quaternion) {
        double d = s * quaternion.s - x * quaternion.x - y * quaternion.y - z * quaternion.z;
        double d1 = s * quaternion.x + x * quaternion.s + y * quaternion.z - z * quaternion.y;
        double d2 = s * quaternion.y - x * quaternion.z + y * quaternion.s + z * quaternion.x;
        double d3 = s * quaternion.z + x * quaternion.y - y * quaternion.x + z * quaternion.s;
        s = d;
        x = d1;
        y = d2;
        z = d3;

        return this;
    }

    public double mag() {
        return Math.sqrt(x * x + y * y + z * z + s * s);
    }

    public Quaternion normalize() {
        double d = mag();
        if (d != 0) {
            d = 1 / d;
            x *= d;
            y *= d;
            z *= d;
            s *= d;
        }

        return this;
    }

    public Quaternion copy() {
        return new Quaternion(this);
    }

    public void rotate(Vector3 vec) {
        double d = -x * vec.x - y * vec.y - z * vec.z;
        double d1 = s * vec.x + y * vec.z - z * vec.y;
        double d2 = s * vec.y - x * vec.z + z * vec.x;
        double d3 = s * vec.z + x * vec.y - y * vec.x;
        vec.x = d1 * s - d * x - d2 * z + d3 * y;
        vec.y = d2 * s - d * y + d1 * z - d3 * x;
        vec.z = d3 * s - d * z - d1 * y + d2 * x;
    }

    public String toString() {
        MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
        return "Quaternion(" + new BigDecimal(s, cont) + ", " + new BigDecimal(x, cont) + ", " + new BigDecimal(y, cont) + ", " + new BigDecimal(z, cont) + ")";
    }
}
