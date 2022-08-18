package teamHTBP.vida.client.animation.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import teamHTBP.vida.api.common.particle.AlphaParticle;
import teamHTBP.vida.api.common.particle.SizeParticle;
import teamHTBP.vida.common.particle.particle.ElementHeadParticle;
import teamHTBP.vida.helper.animation.TimeInterpolator;
import teamHTBP.vida.helper.animation.animator.Animator;
import teamHTBP.vida.helper.animation.property.Property;
import teamHTBP.vida.helper.animation.valueholder.FloatPropertyValueHolder;
import teamHTBP.vida.helper.color.ARGBColor;
import teamHTBP.vida.helper.math.Vector3;
import teamHTBP.vida.helper.math.curve.CircleCurve;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 */
public class CircleParticleAnimation {
    final int enterTick;
    final int shootTick;
    final int finalTick;
    final int particleAmount = 40;
    final int enterAnimTime = 10;
    final float speed = 0.5F;

    static final Property<CircleParticleAnimation, Float> PROGRESS = new Property<>() {
        @Override
        public void set(@NotNull CircleParticleAnimation object, Float value) {
            object.progress = value;
        }

        @Override
        public Float get(@NotNull CircleParticleAnimation object) {
            return object.progress;
        }
    };

    public CircleParticleAnimation(int enterTick, int shootTick, int finalTick) {
        this.enterTick = enterTick;
        this.shootTick = shootTick;
        this.finalTick = finalTick;

        progressAnimator = Animator.of(this, new FloatPropertyValueHolder<>(PROGRESS, 0F, 1F))
                .interpolator(TimeInterpolator.DECELERATE_CUBIC).durationTick(finalTick - shootTick);
    }

    List<ElementHeadParticle> particles = new ArrayList<>();
    List<Animator> inAnimators = new ArrayList<>();

    int tick;
    float partialTick;

    float progress;
    Animator progressAnimator;

    int addedParticleAmount;

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START)
            partialTick = event.renderTickTime;

        float percent = (Math.min(tick, tick + partialTick)) / (enterTick - enterAnimTime);

        if (percent <= 1 && percent > (float) addedParticleAmount / particleAmount) {
            int beAdded = (int) (percent * particleAmount);

            for (int i = addedParticleAmount; i < beAdded; i++) {
                var particle = particles.get(i);
                particle.addParticle();

                Animator inA = Animator.of(particle, new FloatPropertyValueHolder<>(AlphaParticle.ALPHA, 0F, 1F))
                        .interpolator(TimeInterpolator.ACCELERATE).durationTick(enterAnimTime);
                inAnimators.add(inA);
                inA.start();

                Animator inS = Animator.of(particle, new FloatPropertyValueHolder<>(SizeParticle.SIZE, 1.2F, 1F))
                        .interpolator(TimeInterpolator.ACCELERATE).durationTick(enterAnimTime);
                inAnimators.add(inS);
                inS.start();
            }

            addedParticleAmount = beAdded;
        }

        for (int i = inAnimators.size() - 1; i >= 0; i--) {
            Animator anim = inAnimators.get(i);

            if (anim.fullTick()) {
                anim.end();
                inAnimators.remove(i);
            }
        }

        if (tick >= shootTick) {
            for (int i = 0; i < particles.size(); i++) {
                ElementHeadParticle particle = particles.get(i);
                Vector3 startPos = points.get(i);

                Vector3 pos = startPos.copy().subtract(center).normalize().multiply(progress * (finalTick - shootTick) * speed);
                pos = startPos.copy().add(pos);
                particle.setPos(pos.x, pos.y, pos.z);
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START)
            tick++;

        if (tick >= finalTick) {
            MinecraftForge.EVENT_BUS.unregister(this);
            progressAnimator.end();
        }
        else if (tick == shootTick) {
            progressAnimator.start();

            for (ElementHeadParticle particle : particles) {
                particle.setColor(ARGBColor.DARK_RED.argb());
            }
        }
    }

    Vector3 center;
    List<Vector3> points;

    public void start(Entity entity) {
        MinecraftForge.EVENT_BUS.register(this);

        center = new Vector3(entity.position());
        CircleCurve curve = new CircleCurve(10, new Vector3(0, 1, 0), center);
        points = curve.getPoints(particleAmount);

        for (Vector3 point : points) {
            ElementHeadParticle particle = new ElementHeadParticle((ClientLevel) entity.level, point.x, point.y, point.z);
            particle.setLifetime(finalTick + 1);

            particles.add(particle);
        }
    }
}
