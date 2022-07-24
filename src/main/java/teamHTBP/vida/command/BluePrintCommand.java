package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import teamHTBP.vida.capability.VidaCapabilityRegistry;

public class BluePrintCommand implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        commandContext.getSource().getPlayerOrException().getCapability(VidaCapabilityRegistry.BLUEPRINT).ifPresent(
                (cap) -> {
                    cap.getAllBlueprints().forEach((s, blueprint) -> commandContext.getSource().sendSuccess(new TextComponent(s), true));
                }
        );
        return 0;
    }
}
