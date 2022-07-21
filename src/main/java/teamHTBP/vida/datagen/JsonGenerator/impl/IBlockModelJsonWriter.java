package teamHTBP.vida.datagen.JsonGenerator.impl;

import java.util.Map;

public interface IBlockModelJsonWriter {

    String getParent();

    Map<String, String> getTextures(String name);
}
