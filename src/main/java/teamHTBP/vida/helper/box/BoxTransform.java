package teamHTBP.vida.helper.box;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minecraft.world.phys.Vec3;
import teamHTBP.vida.helper.math.angle.AngleHolder;

/**
 * @author DustW
 */
@Data
@AllArgsConstructor
public class BoxTransform {

    private float scale;
    private Vec3 subPos;
    private AngleHolder angle;

    public boolean testHit(Vec3 localHit) {
        Vec3 offset = getLocalOffset();
        if (offset == null)
            return false;
        return localHit.distanceTo(offset) < scale;
    }

    public Vec3 getLocalOffset() {
        return subPos;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Client {
        public static void transform(BoxTransform transform, PoseStack ps) {
            Vec3 position = transform.getLocalOffset();
            if (position == null)
                return;

            ps.translate(0, 1, 0);
            var sub = transform.subPos;
            ps.translate(sub.x, sub.y, sub.z);
            rotate(transform.angle, ps);
            ps.translate(-sub.x, -sub.y, -sub.z);
            float scale = transform.getScale();
            ps.scale(scale, scale, scale);
            ps.translate(0, -1, 0);

            ps.translate(position.x / scale, position.y / scale, position.z / scale);
        }

        public static void rotate(AngleHolder angle, PoseStack ps) {
            ps.mulPose(Vector3f.YP.rotationDegrees(angle.y()));
            ps.mulPose(Vector3f.XP.rotationDegrees(angle.x()));
        }
    }
}
