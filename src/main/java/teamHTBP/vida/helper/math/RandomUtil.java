package teamHTBP.vida.helper.math;

import java.util.Random;

/**
 * @author DustW
 */
public class RandomUtil {
    private static final Random RANDOM = new Random();

    public static int getRandomBounded(int startIndex, int endIndex) {
        return RANDOM.nextInt(endIndex - startIndex) + startIndex;
    }
}
