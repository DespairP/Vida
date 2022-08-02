package teamHTBP.vida.helper.box;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import teamHTBP.vida.helper.math.RayCastHelper;

/**
 * @author DustW
 */
@Data
@AllArgsConstructor
public class InteractBox {

    private Direction side;
    private BlockPos pos;
    private BoxTransform transform;

    public boolean testHit(Player player, boolean isClient) {
        if (isClient) {
            return Client.testHit(this);
        }
        else {
            return Server.testHit(this, player);
        }
    }

    public boolean testHit(Vec3 hit) {
        Vec3 localHit = hit.subtract(pos.getX(), pos.getY() + 1, pos.getZ());
        return transform.testHit(localHit);
    }

    private static class Client {
        static boolean testHit(InteractBox behaviour) {
            HitResult hitResult = Minecraft.getInstance().hitResult;
            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK)
                return behaviour.testHit(hitResult.getLocation());
            return false;
        }
    }

    private static class Server {
        static boolean testHit(InteractBox behaviour, Player player) {
            BlockHitResult hitResult = RayCastHelper.rayTraceRange(player.level, player, 10);
            if (hitResult.getType() == HitResult.Type.BLOCK)
                return behaviour.testHit(hitResult.getLocation());
            return false;
        }
    }
}
