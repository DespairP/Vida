package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import teamHTBP.vida.capability.VidaCapabilities;

public class CommandBluePrint implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        commandContext.getSource().getPlayerOrException().getCapability(VidaCapabilities.BLUEPRINT).ifPresent(
                (cap) -> {
                    cap.getAllBlueprints().forEach((s, blueprint) -> commandContext.getSource().sendSuccess(new TextComponent(s), true));
                }
        );
        return 0;
    }
}
