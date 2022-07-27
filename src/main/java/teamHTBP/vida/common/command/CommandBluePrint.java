package teamHTBP.vida.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.common.capability.VidaCapabilities;

public class CommandBluePrint implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        commandContext.getSource().asPlayer().getCapability(VidaCapabilities.blueprint_Capability).ifPresent(
                (cap) -> {
                    cap.getAllBlueprints().forEach((s, blueprint) -> commandContext.getSource().sendFeedback(new StringTextComponent(s), true));
                }
        );
        return 0;
    }
}
