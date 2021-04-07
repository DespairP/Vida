package teamHTBP.vida.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
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
                .then(Commands.literal("sendmessage")
                        .requires(commandSource -> commandSource.hasPermissionLevel(3))
                        .then(Commands.argument("message", StringArgumentType.greedyString()).executes(new CommandTextTest()))
                )
                .then(Commands.literal("blueprintRefresh").executes(new CommandRefreshBluePrint()))
                .then(Commands.literal("showAllBluePrints").executes(new CommandBluePrint()))
                .then(Commands.literal("unlockBluePrints")
                        .requires(commandSource -> commandSource.hasPermissionLevel(3))
                        .then(Commands.argument("name", StringArgumentType.greedyString()).executes(new CommandUnlockBluePrint())))
                .then(Commands.literal("tool")
                        .then(Commands.literal("setExp")
                                .requires((commandSource)->commandSource.hasPermissionLevel(3))
                                .then(Commands.argument("exp", IntegerArgumentType.integer()).executes(new CommandToolLevelUp()))
                                )
                )
        );
    }

}
