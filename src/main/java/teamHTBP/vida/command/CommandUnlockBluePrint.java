package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.network.PacketDistributor;
import teamHTBP.vida.capability.VidaCapabilities;
import teamHTBP.vida.capability.blueprintCapability.Blueprint;
import teamHTBP.vida.capability.blueprintCapability.IBlueprintCapability;
import teamHTBP.vida.helper.blueprintHelper.BlueprintHelper;
import teamHTBP.vida.network.PacketChannel;
import teamHTBP.vida.network.PacketMessage;

public class CommandUnlockBluePrint implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        String argument = commandContext.getArgument("name", String.class);
        Blueprint blueprint = BlueprintHelper.getBluePrint(argument).orElse(null);
        if (blueprint == null) {
            commandContext.getSource().sendFailure(new TextComponent("blueprint : " + argument + " is not exists"));
            return 0;
        }
        IBlueprintCapability capability = commandContext.getSource().getPlayerOrException().getCapability(VidaCapabilities.BLUEPRINT).orElse(null);
        if (!capability.unlockBlueprint(blueprint)) {
            commandContext.getSource().sendFailure(new TextComponent("blueprint : " + argument + " is already unlocked"));
            return 0;
        }
        PacketChannel.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            try {
                return commandContext.getSource().getPlayerOrException();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }), new PacketMessage(blueprint.getId() + " is unlocked!"));
        return 0;
    }
}
