package teamHTBP.vida.JsonGenerator.impl;

public enum EnumBlockModelBasic {
    Cross("block/cross"),Pillar("cube_column"),Full("block/cube_all"),None(null);

    public String parent;
    EnumBlockModelBasic(String parent){
        this.parent = parent;
    }
}
