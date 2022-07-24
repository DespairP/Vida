package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.network.PacketDistributor;
import teamHTBP.vida.capability.VidaCapabilityRegistry;
import teamHTBP.vida.capability.blueprint.Blueprint;
import teamHTBP.vida.capability.blueprint.IBlueprintCapability;
import teamHTBP.vida.helper.blueprint.BlueprintHelper;
import teamHTBP.vida.network.PacketManager;
import teamHTBP.vida.network.MessagePacket;

public class UnlockBluePrintCommand implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        String argument = commandContext.getArgument("name", String.class);
        Blueprint blueprint = BlueprintHelper.getBluePrint(argument).orElse(null);
        if (blueprint == null) {
            commandContext.getSource().sendFailure(new TextComponent("blueprint : " + argument + " is not exists"));
            return 0;
        }
        IBlueprintCapability capability = commandContext.getSource().getPlayerOrException().getCapability(VidaCapabilityRegistry.BLUEPRINT).orElse(null);
        if (!capability.unlockBlueprint(blueprint)) {
            commandContext.getSource().sendFailure(new TextComponent("blueprint : " + argument + " is already unlocked"));
            return 0;
        }
        PacketManager.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            try {
                return commandContext.getSource().getPlayerOrException();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }), new MessagePacket(blueprint.getId() + " is unlocked!"));
        return 0;
    }
}
