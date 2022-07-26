package teamHTBP.vida.utils.math;

import com.mojang.math.Vector3f;

/**
 * @author DustW
 */
public class VidaMath {
    public static float PI = (float) Math.PI;
    public static float PI_O_2 = PI / 2;

    /**
     * 进行贝塞尔曲线插值
     * @param pos0 控制点
     * @param pos1 控制点
     * @param pos2 控制点
     * @param pos3 控制点
     * @param t    0~1 时间
     * @return     插值结果
     */
    Vector3f bezier3(Vector3f pos0, Vector3f pos1, Vector3f pos2, Vector3f pos3, double t) {
        Vector3f pos0f = pos0.copy();
        pos0f.mul((float) Math.pow(1 - t, 3));

        Vector3f pos1f = pos1.copy();
        pos1f.mul((float) (3 * t * Math.pow(1 - t, 2)));

        Vector3f pos2f = pos2.copy();
        pos2f.mul((float) (3 * t * t * (1 - t)));

        Vector3f pos3f = pos3.copy();
        pos3f.mul((float) (t * t * t));

        pos0f.add(pos1f);
        pos0f.add(pos2f);
        pos0f.add(pos3f);

        return pos0f;
    }
}
