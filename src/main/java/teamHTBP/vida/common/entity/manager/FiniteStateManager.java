package teamHTBP.vida.common.entity.manager;

import lombok.Getter;
import net.minecraft.world.entity.PathfinderMob;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author DustW
 */
public class FiniteStateManager<T extends PathfinderMob> {
    private final Map<Predicate<T>, String> states = new HashMap<>();

    @Getter
    String activeState;
    T mob;

    public FiniteStateManager(T mob) {
        this.mob = mob;
    }

    public void addState(Predicate<T> predicate, String state) {
        states.put(predicate, state);
    }

    public boolean isActive(@NotNull String state) {
        return state.equals(activeState);
    }

    public void refresh() {
        for (var entry : states.entrySet()) {
            if (entry.getKey().test(mob)) {
                activeState = entry.getValue();
                return;
            }
        }
    }
}
