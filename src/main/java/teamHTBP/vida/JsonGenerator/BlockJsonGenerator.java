package teamHTBP.vida.JsonGenerator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.util.JSONUtils;
import teamHTBP.vida.JsonGenerator.Helper.BlockModelWriterHelper;
import teamHTBP.vida.JsonGenerator.Helper.BlockStateWriterHelper;
import teamHTBP.vida.JsonGenerator.Properties.BlockModelJson;
import teamHTBP.vida.JsonGenerator.Properties.BlockModelProperties;
import teamHTBP.vida.JsonGenerator.impl.BlockModelImpl.FullBlockModel;
import teamHTBP.vida.JsonGenerator.impl.BlockModelImpl.PillarBlockModel;
import teamHTBP.vida.JsonGenerator.impl.BlockStateImpl.BasicStateWriterGenerator;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
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
                new BlockModelProperties("lushplank", BasicStateWriterGenerator.generateNoneAxis(), new FullBlockModel())
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
