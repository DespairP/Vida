package teamHTBP.vida.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import teamHTBP.vida.common.capability.VidaCapabilityLoader;

public class BluePrintCommand implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        commandContext.getSource().getPlayerOrException().getCapability(VidaCapabilityLoader.BLUEPRINT).ifPresent(
                (cap) -> {
                    cap.getAllBlueprints().forEach((s, blueprint) -> commandContext.getSource().sendSuccess(new TextComponent(s), true));
                }
        );
        return 0;
    }
}
