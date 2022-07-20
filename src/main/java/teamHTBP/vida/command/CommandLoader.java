package teamHTBP.vida.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandLoader {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("vida")
                .then(Commands.literal("sendmessage")
                        .requires(commandSource -> commandSource.hasPermission(3))
                        .then(Commands.argument("message", StringArgumentType.greedyString()).executes(new CommandTextTest())) // vida send message <message>
                )
                .then(Commands.literal("blueprintRefresh").executes(new CommandRefreshBluePrint())) // vida blueprintrefresh
                .then(Commands.literal("showAllBluePrints").executes(new CommandBluePrint())) // vida showallblueprints
                .then(Commands.literal("unlockBluePrints")
                        .requires(commandSource -> commandSource.hasPermission(3))
                        .then(Commands.argument("name", StringArgumentType.greedyString()).executes(new CommandUnlockBluePrint())))
                .then(Commands.literal("tool")
                        .then(Commands.literal("setExp")
                                .requires((commandSource) -> commandSource.hasPermission(3))
                                .then(Commands.argument("exp", IntegerArgumentType.integer()).executes(new CommandToolLevelUp())) // vida tool setExp <Exp>
                        )
                )
        );
    }

}
