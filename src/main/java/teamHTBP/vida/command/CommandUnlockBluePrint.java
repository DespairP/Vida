package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;
import teamHTBP.vida.capability.VidaCapabilities;
import teamHTBP.vida.capability.blueprintCapability.Blueprint;
import teamHTBP.vida.capability.blueprintCapability.IBlueprintCapability;
import teamHTBP.vida.helper.blueprintHelper.BlueprintHelper;
import teamHTBP.vida.network.PacketChannel;
import teamHTBP.vida.network.PacketMessage;

public class CommandUnlockBluePrint implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        String argument = commandContext.getArgument("name", String.class);
        Blueprint blueprint = BlueprintHelper.getBluePrint(argument).orElse(null);
        if (blueprint == null) {
            commandContext.getSource().sendErrorMessage(new StringTextComponent("blueprint : " + argument + " is not exists"));
            return 0;
        }
        IBlueprintCapability capability = commandContext.getSource().asPlayer().getCapability(VidaCapabilities.blueprint_Capability).orElse(null);
        if (!capability.unlockBlueprint(blueprint)) {
            commandContext.getSource().sendErrorMessage(new StringTextComponent("blueprint : " + argument + " is already unlocked"));
            return 0;
        }
        PacketChannel.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            try {
                return commandContext.getSource().asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }), new PacketMessage(blueprint.getId() + " is unlocked!"));
        return 0;
    }
}
