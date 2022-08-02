package teamHTBP.vida.helper.math.angle;

import com.mojang.math.Vector3f;
import net.minecraft.core.Direction;

/**
 * @author DustW
 */
public interface AngleHolder {
    Impl ZERO = new Impl(0, 0, 0);

    float x();
    float y();
    float z();

    record Impl(float x, float y, float z) implements AngleHolder { }

    record DirectionAngle(Direction side) implements AngleHolder {
        @Override
        public float x() {
            return AngleHelper.verticalAngle(side);
        }

        @Override
        public float y() {
            return AngleHelper.horizontalAngle(side);
        }

        @Override
        public float z() {
            return 0;
        }
    }

    record Vec3fAngle(Vector3f vec3) implements AngleHolder {
        @Override
        public float x() {
            return vec3.x();
        }

        @Override
        public float y() {
            return vec3.y();
        }

        @Override
        public float z() {
            return vec3.z();
        }
    }
}
