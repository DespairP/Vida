package teamHTBP.vida.client.animation;

import net.minecraft.util.Mth;
import teamHTBP.vida.utils.math.VidaMath;

/**
 * @author DustW
 */
@FunctionalInterface
public interface TimeInterpolator {

    /** 线性插值 */
    TimeInterpolator LINEAR = in -> in;
    /** 加速 */
    TimeInterpolator ACCELERATE = in -> in * in;
    /** 减速 */
    TimeInterpolator DECELERATE = in -> 1.0f - (1.0f - in) * (1.0f - in);
    /** 减速立方 */
    TimeInterpolator DECELERATE_CUBIC = in -> 1.0f - (1.0f - in) * (1.0f - in) * (1.0f - in);
    /** 开始和结尾时减速，中间加速 */
    TimeInterpolator ACCELERATE_DECELERATE = in -> Mth.cos((in + 1.0f) * VidaMath.PI) * 0.5f + 0.5f;
    /** sin */
    TimeInterpolator SINE = in -> Mth.sin(VidaMath.PI_O_2 * in);

    TimeInterpolator ANTICIPATE = in -> in * in * (3.0f * in - 2.0f);

    TimeInterpolator OVERSHOOT = in -> (in - 1.0f) * (in - 1.0f) * (3.0f * (in - 1.0f) + 2.0f) + 1.0f;

    /**
     * 获取插值结果
     * @param value 待插值的值
     * @return      插值的结果
     */
    float interpolation(float value);
}
