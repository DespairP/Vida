package teamHTBP.vida.helper.math.angle;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;

public class AngleHelper {

	private AngleHelper() {}

	public static float horizontalAngle(Direction facing) {
		if (facing.getAxis().isVertical())
			return 0;
		float angle = facing.toYRot();
		if (facing.getAxis() == Axis.X)
			angle = -angle;
		return angle;
	}

	public static float verticalAngle(Direction facing) {
		return switch (facing) {
			case UP -> 270;
			case DOWN -> 90;
			default -> 0;
		};
	}
}
