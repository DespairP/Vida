package teamHTBP.vida.JsonGenerator.Helper;

public enum EnumDirections {
    East("east",90),
    West("west",270),
    North("north",180),
    South("south",0);


    public String direction;
    public int axisRotate;
    EnumDirections(String direction,int rotation){
        this.direction = direction;
        this.axisRotate = rotation;
    }
}
