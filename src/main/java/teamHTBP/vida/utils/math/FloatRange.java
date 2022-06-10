package teamHTBP.vida.utils.math;

public class FloatRange {
    private float currentValue;
    private final float maxValue;
    private final float minValue;

    public FloatRange(float initialValue,float maxValue,float minValue){
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.currentValue = set(initialValue);
    }

    public float get(){
        return currentValue;
    }

    public float set(float value){
        if(value <= maxValue && value >= minValue) currentValue = value;
        if(value < minValue) currentValue = minValue;
        if(value > maxValue) currentValue = maxValue;
        return currentValue;
    }

    public float increase(float step){
        return set(currentValue + step);
    }

    public float decrease(float step){
        return set(currentValue - step);
    }
}
