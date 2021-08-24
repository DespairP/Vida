package teamHTBP.vida.JsonGenerator.Helper;

public enum EnumDirections {
    East("east", 0),
    West("west", 180),
    North("north", 270),
    South("south", 90);


    public String direction;
    public int axisRotate;

    EnumDirections(String direction, int rotation) {
        this.direction = direction;
        this.axisRotate = rotation;
    }
}
