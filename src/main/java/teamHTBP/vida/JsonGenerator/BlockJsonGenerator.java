package teamHTBP.vida.JsonGenerator;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import teamHTBP.vida.JsonGenerator.Properties.BlockModelProperties;
import teamHTBP.vida.JsonGenerator.impl.BlockModelImpl.FullBlockModel;
import teamHTBP.vida.JsonGenerator.impl.BlockModelImpl.PillarBlockModel;
import teamHTBP.vida.JsonGenerator.impl.BlockStateImpl.BasicStateWriterGenerator;

import java.util.List;

public class BlockJsonGenerator {
    private List<BlockModelProperties> blocks;

    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public static void main(String[] args) throws Exception {
        BlockJsonGenerator generator = new BlockJsonGenerator();
        generator.initialize();
        generator.write();
    }

    public void initialize(){
        blocks = ImmutableList.of(
                new BlockModelProperties("pillarlush3", BasicStateWriterGenerator.generateRotate(), new PillarBlockModel("pillarlush1", "pillarsidelush3")),
                new BlockModelProperties("pillarlush2", BasicStateWriterGenerator.generateRotate(), new PillarBlockModel("pillarlush1", "pillarsidelush2")),
                new BlockModelProperties("pillarlush1", BasicStateWriterGenerator.generateRotate(), new PillarBlockModel("pillarlush1", "pillarsidelush1")),
                new BlockModelProperties("pillarlush0", BasicStateWriterGenerator.generateRotate(), new PillarBlockModel("pillarlush0", "pillarsidelush0")),
                new BlockModelProperties("pillarlush4", BasicStateWriterGenerator.generateRotate(), new PillarBlockModel("pillarlush0", "pillarsidelush4")),
                new BlockModelProperties("planklushdeco0", BasicStateWriterGenerator.generateNoneAxis(),new PillarBlockModel())
              );
    }

    public void write(){
        blocks.forEach(blockModelProperties -> {
            try {
                blockModelProperties.writeBlockState(gson);
                blockModelProperties.writeBlockBasicModel(gson);
                blockModelProperties.writeItem(gson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
