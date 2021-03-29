package teamHTBP.vida.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber
public class CommandLoader {

    @SubscribeEvent
    public static void onServerStaring(FMLServerStartingEvent event) {
        event.getCommandDispatcher().register(Commands.literal("vida")
                .then(Commands.literal("tool")
                        .then(Commands.literal("setExp")
                                .requires((commandSource)->commandSource.hasPermissionLevel(3))
                                .then(Commands.argument("exp", IntegerArgumentType.integer()).executes(new CommandToolLevelUp()))
                                )
                )
        );
    }

}
