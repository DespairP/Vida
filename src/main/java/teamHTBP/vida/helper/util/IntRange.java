package teamHTBP.vida.helper.util;

public class IntRange {
    private int currentValue;
    private final int maxValue;
    private final int minValue;

    public IntRange(int initialValue,int maxValue,int minValue){
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.currentValue = set(initialValue);
    }

    public int get(){
        return currentValue;
    }

    public int set(int value){
        if(value <= maxValue && value >= minValue) currentValue = value;
        if(value < minValue) currentValue = minValue;
        if(value > maxValue) currentValue = maxValue;
        return currentValue;
    }

    public int increase(int step){
        return set(currentValue + step);
    }

    public int decrease(int step){
        return set(currentValue - step);
    }
}
