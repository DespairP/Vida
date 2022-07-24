package teamHTBP.vida.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandRegistry {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("vida")
                .then(Commands.literal("sendmessage")
                        .requires(commandSource -> commandSource.hasPermission(3))
                        .then(Commands.argument("message", StringArgumentType.greedyString()).executes(new TextTestCommand())) // vida send message <message>
                )
                .then(Commands.literal("blueprintRefresh").executes(new RefreshBluePrintCommand())) // vida blueprintrefresh
                .then(Commands.literal("showAllBluePrints").executes(new BluePrintCommand())) // vida showallblueprints
                .then(Commands.literal("unlockBluePrints")
                        .requires(commandSource -> commandSource.hasPermission(3))
                        .then(Commands.argument("name", StringArgumentType.greedyString()).executes(new UnlockBluePrintCommand())))
                .then(Commands.literal("tool")
                        .then(Commands.literal("setExp")
                                .requires((commandSource) -> commandSource.hasPermission(3))
                                .then(Commands.argument("exp", IntegerArgumentType.integer()).executes(new ToolLevelUpCommand())) // vida tool setExp <Exp>
                        )
                )
        );
    }
}
