package teamHTBP.vida.helper.mathHelper;

import net.minecraft.util.math.vector.Vector3d;

public class Vector3dHelper {

    public static Vector3d copy(Vector3d toCopy){
        return new Vector3d((double)toCopy.getX(), (double)toCopy.getY(), (double)toCopy.getZ());
    }
}
