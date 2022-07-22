package teamHTBP.vida.client.animation.animator;

import teamHTBP.vida.client.animation.TimeInterpolator;
import teamHTBP.vida.client.animation.valueholder.PropertyValueHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public class Animator {
    public static final List<Animator> ANIMATORS = new ArrayList<>();

    Object target;
    PropertyValueHolder[] values;
    TimeInterpolator interpolator;
    int durationTick = 6;

    public Animator(Object target, PropertyValueHolder... values) {
        this.target = target;
        this.values = values;

        ANIMATORS.add(this);
    }

    int existingTick;

    public void addExistingTick() {
        if (started) {
            existingTick = Math.min(durationTick, existingTick + 1);
        }
        else {
            existingTick = Math.max(0, existingTick - 1);
        }
    }

    private boolean started;

    public void cancel() {
        started = false;
    }

    public void start() {
        started = true;
    }

    public void setTick(int existingTick) {
        this.existingTick = existingTick;
    }

    public void setTickPercent(float percent) {
        this.existingTick = (int) (durationTick * percent);
    }

    public void run(float partialTick) {
        if (started) {
            for (var value : values) {
                value.setValue(target, Math.min(durationTick, existingTick + partialTick), durationTick, interpolator);
            }
        }
    }

    public static Animator of(Object target, PropertyValueHolder... holders) {
        return new Animator(target, holders);
    }

    public Animator interpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public Animator durationTick(int durationTick) {
        this.durationTick = durationTick;
        return this;
    }
}
