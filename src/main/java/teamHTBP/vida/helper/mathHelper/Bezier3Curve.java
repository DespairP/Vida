package teamHTBP.vida.helper.mathHelper;

import lombok.AllArgsConstructor;

/**
 * @author dustW
 * */
@AllArgsConstructor
public class Bezier3Curve extends Curve{
    public final Vector3 pos0;
    public final Vector3 pos1;
    public final Vector3 pos2;
    public final Vector3 pos3;

    @Override
    public Vector3 getPoint(float t) {
        return bezier3(pos0, pos1, pos2, pos3, t);
    }

    /**
     * 进行贝塞尔曲线插值
     * @param pos0 控制点
     * @param pos1 控制点
     * @param pos2 控制点
     * @param pos3 控制点
     * @param t    0~1 时间
     * @return     插值结果
     */
    public static Vector3 bezier3(Vector3 pos0, Vector3 pos1, Vector3 pos2, Vector3 pos3, double t) {
        double k = 1 - t;

        Vector3 pos0f = pos0.copy().multiply(k * k * k);
        Vector3 pos1f = pos1.copy().multiply(3 * t * k * k);
        Vector3 pos2f = pos2.copy().multiply(3 * t * t * k);
        Vector3 pos3f = pos3.copy().multiply(t * t * t);

        return pos0f.add(pos1f).add(pos2f).add(pos3f);
    }
}
